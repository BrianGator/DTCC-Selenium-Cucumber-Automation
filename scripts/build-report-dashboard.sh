#!/usr/bin/env bash
set -euo pipefail

DASHBOARD_DIR="${1:-target/report-dashboard}"
ALLURE_DIR="${2:-target/site/allure-report}"
REPOSITORY="${GITHUB_REPOSITORY:-BrianGator/DTCC-Selenium-Cucumber-Automation}"
SERVER_URL="${GITHUB_SERVER_URL:-https://github.com}"
RUN_ID="${GITHUB_RUN_ID:-}"

mkdir -p "$DASHBOARD_DIR"

if [ -d "$ALLURE_DIR" ]; then
  rm -rf "$DASHBOARD_DIR/allure-report"
  mkdir -p "$DASHBOARD_DIR/allure-report"
  cp -R "$ALLURE_DIR"/. "$DASHBOARD_DIR/allure-report/"
fi

python - "$DASHBOARD_DIR" "$REPOSITORY" "$SERVER_URL" "$RUN_ID" <<'PY'
from pathlib import Path
import sys
import xml.etree.ElementTree as ET

dashboard_dir = Path(sys.argv[1])
repository = sys.argv[2]
server_url = sys.argv[3]
run_id = sys.argv[4]

total = failures = errors = skipped = 0
suite_rows = []
for report in Path("target/surefire-reports").glob("TEST-*.xml"):
    try:
        root = ET.parse(report).getroot()
    except ET.ParseError:
        continue
    tests = int(float(root.attrib.get("tests", 0)))
    failed = int(float(root.attrib.get("failures", 0)))
    errs = int(float(root.attrib.get("errors", 0)))
    skips = int(float(root.attrib.get("skipped", 0)))
    total += tests
    failures += failed
    errors += errs
    skipped += skips
    suite_rows.append((root.attrib.get("name", report.stem), tests, failed, errs, skips))

passed = max(total - failures - errors - skipped, 0)
run_url = f"{server_url}/{repository}/actions/runs/{run_id}" if run_id else f"{server_url}/{repository}/actions"
dispatch_url = f"{server_url}/{repository}/actions/workflows/selenium.yml"

rows = "\n".join(
    f"<tr><td>{name}</td><td>{tests}</td><td>{tests - failed - errs - skips}</td><td>{failed}</td><td>{errs}</td><td>{skips}</td></tr>"
    for name, tests, failed, errs, skips in suite_rows
) or "<tr><td colspan='6'>No Surefire XML files found.</td></tr>"

html = f"""<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>DTCC Automation Report Dashboard</title>
  <style>
    body {{ margin: 0; font-family: Arial, sans-serif; color: #172033; background: #f5f7fb; }}
    header {{ background: #14213d; color: #fff; padding: 28px 36px; }}
    main {{ padding: 28px 36px; max-width: 1180px; margin: 0 auto; }}
    .actions, .cards {{ display: grid; grid-template-columns: repeat(auto-fit, minmax(180px, 1fr)); gap: 14px; }}
    .card, .panel {{ background: #fff; border: 1px solid #d9e1ee; border-radius: 8px; padding: 18px; }}
    .metric {{ font-size: 34px; font-weight: 700; margin-top: 8px; }}
    .pass {{ color: #087f5b; }} .fail {{ color: #c92a2a; }} .skip {{ color: #a16207; }}
    a.button {{ display: block; text-decoration: none; color: #fff; background: #1d4ed8; border-radius: 6px; padding: 12px 14px; text-align: center; font-weight: 700; }}
    table {{ width: 100%; border-collapse: collapse; margin-top: 16px; background: #fff; }}
    th, td {{ padding: 11px 12px; border-bottom: 1px solid #e5e9f2; text-align: left; }}
    th {{ background: #eaf0f8; }}
  </style>
</head>
<body>
  <header>
    <h1>DTCC Automation Report Dashboard</h1>
    <p>Stable public-safe Selenium, TestNG, API, SQL, Selenide, Cucumber, and Allure reporting hub.</p>
  </header>
  <main>
    <section class="actions">
      <a class="button" href="allure-report/index.html">Open Allure Report</a>
      <a class="button" href="{run_url}">Open Workflow Run</a>
      <a class="button" href="{dispatch_url}">Run Tests in GitHub Actions</a>
    </section>
    <section class="cards" style="margin-top:18px">
      <div class="card"><div>Total Tests</div><div class="metric">{total}</div></div>
      <div class="card"><div>Passed</div><div class="metric pass">{passed}</div></div>
      <div class="card"><div>Failures</div><div class="metric fail">{failures}</div></div>
      <div class="card"><div>Errors</div><div class="metric fail">{errors}</div></div>
      <div class="card"><div>Skipped</div><div class="metric skip">{skipped}</div></div>
    </section>
    <section class="panel" style="margin-top:18px">
      <h2>Surefire/TestNG Summary</h2>
      <table>
        <thead><tr><th>Suite</th><th>Total</th><th>Passed</th><th>Failures</th><th>Errors</th><th>Skipped</th></tr></thead>
        <tbody>{rows}</tbody>
      </table>
    </section>
  </main>
</body>
</html>
"""
dashboard_dir.joinpath("index.html").write_text(html, encoding="utf-8")
PY

test -f "$DASHBOARD_DIR/index.html"
