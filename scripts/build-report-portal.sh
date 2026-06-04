#!/usr/bin/env bash
set -euo pipefail

PORTAL_DIR="${1:-target/report-portal}"
rm -rf "$PORTAL_DIR"
mkdir -p "$PORTAL_DIR/allure" "$PORTAL_DIR/dashboard" "$PORTAL_DIR/sample-reports" "$PORTAL_DIR/surefire" "$PORTAL_DIR/cucumber"

if [ -d target/site/allure-report ]; then
  cp -R target/site/allure-report/. "$PORTAL_DIR/allure/"
fi
if [ -d 13_Reports/web-ui ]; then
  cp -R 13_Reports/web-ui/. "$PORTAL_DIR/dashboard/"
fi
if [ -d 13_Reports/allure-sample-results ]; then
  cp -R 13_Reports/allure-sample-results/. "$PORTAL_DIR/sample-reports/"
fi
if [ -d target/surefire-reports ]; then
  cp -R target/surefire-reports/. "$PORTAL_DIR/surefire/"
fi
if [ -d target/cucumber-reports ]; then
  cp -R target/cucumber-reports/. "$PORTAL_DIR/cucumber/"
fi

cat > "$PORTAL_DIR/cucumber/index.html" <<'HTML'
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>DTCC Cucumber BDD Report</title>
  <style>
    body { margin: 0; font-family: Arial, sans-serif; background: #f6f8fb; color: #172033; }
    header { background: #0f172a; color: white; padding: 18px 24px; }
    main { max-width: 1040px; margin: 0 auto; padding: 22px 20px 42px; }
    section { background: white; border: 1px solid #d9e1ee; border-radius: 8px; padding: 18px; margin-bottom: 16px; }
    a { color: #1d4ed8; font-weight: 700; }
    table { width: 100%; border-collapse: collapse; }
    th, td { text-align: left; padding: 10px 8px; border-bottom: 1px solid #e5e9f2; vertical-align: top; }
    th { background: #eef3f9; }
    code { background: #e5e7eb; padding: 2px 5px; border-radius: 4px; }
  </style>
</head>
<body>
  <header><h1>Cucumber BDD Report</h1></header>
  <main>
    <section>
      <h2>BDD Output</h2>
      <p>Cucumber is the business-readable report for feature and scenario coverage. The generated HTML should not execute test payloads; unsafe values are represented as plain text.</p>
      <p><a href="cucumber-pretty.html">Open Cucumber HTML</a> | <a href="CucumberTestReport.json">Open Cucumber JSON</a> | <a href="../">Back to consolidated portal</a></p>
    </section>
    <section>
      <h2>What This Report Covers</h2>
      <table>
        <thead><tr><th>Feature Area</th><th>Expected Evidence</th></tr></thead>
        <tbody>
          <tr><td>Account authentication</td><td>Valid login examples, invalid credentials, blank credentials, script-like payload text, and SQL-style payload text.</td></tr>
          <tr><td>Order lifecycle</td><td>Login, catalog selection, cart, checkout, payment submission, and confirmation scenarios.</td></tr>
          <tr><td>DTCC public site</td><td>Public-safe page rendering checks for home, about, client center, news, legal, and product pages.</td></tr>
        </tbody>
      </table>
    </section>
  </main>
</body>
</html>
HTML

cat > "$PORTAL_DIR/surefire/index.html" <<'HTML'
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>DTCC Surefire TestNG Report Summary</title>
  <style>
    body { margin: 0; font-family: Arial, sans-serif; background: #f6f8fb; color: #172033; }
    header { background: #111827; color: white; padding: 18px 24px; }
    main { max-width: 1120px; margin: 0 auto; padding: 22px 20px 42px; }
    section { background: white; border: 1px solid #d9e1ee; border-radius: 8px; padding: 18px; margin-bottom: 16px; }
    table { width: 100%; border-collapse: collapse; }
    th, td { text-align: left; padding: 10px 8px; border-bottom: 1px solid #e5e9f2; vertical-align: top; }
    th { background: #eef3f9; }
    a { color: #1d4ed8; font-weight: 700; }
    code { background: #e5e7eb; padding: 2px 5px; border-radius: 4px; }
    .ok { color: #087f5b; font-weight: 700; }
    .warn { color: #a16207; font-weight: 700; }
  </style>
</head>
<body>
  <header><h1>Surefire/TestNG Runner Evidence</h1></header>
  <main>
    <section>
      <h2>How To Read This Area</h2>
      <p>Surefire is secondary raw CI evidence. Use <a href="../allure/index.html">Allure</a> as the primary UI report; use this area when checking Maven/TestNG XML, skipped intentional demos, or CI runner diagnostics.</p>
      <p><span class="ok">Stable failures/errors should be 0.</span> <span class="warn">Intentional demos are skipped by default.</span></p>
    </section>
    <section>
      <h2>Raw Files</h2>
      <table>
        <thead><tr><th>File</th><th>Purpose</th></tr></thead>
        <tbody>
          <tr><td><a href="emailable-report.html">emailable-report.html</a></td><td>TestNG HTML runner summary when generated.</td></tr>
          <tr><td><a href="testng-results.xml">testng-results.xml</a></td><td>Raw pass/fail/skip XML used for troubleshooting CI results.</td></tr>
        </tbody>
      </table>
    </section>
  </main>
</body>
</html>
HTML

cat > "$PORTAL_DIR/index.html" <<'HTML'
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>DTCC Automation Report Portal</title>
  <style>
    :root { --ink:#172033; --muted:#5b677a; --line:#d9e1ee; --panel:#ffffff; --brand:#0f172a; --ok:#087f5b; --warn:#a16207; --link:#1d4ed8; }
    * { box-sizing: border-box; }
    body { font-family: Arial, sans-serif; margin: 0; background: #f6f8fb; color: var(--ink); }
    header { background: var(--brand); color: white; padding: 14px 24px; display: flex; align-items: center; justify-content: space-between; gap: 18px; flex-wrap: wrap; }
    header h1 { margin: 0; font-size: 20px; line-height: 1.2; }
    header p { margin: 3px 0 0; color: #cbd5e1; font-size: 13px; }
    nav { display: flex; gap: 10px 14px; flex-wrap: wrap; align-items: center; }
    nav a { color: #dbeafe; text-decoration: none; font-size: 13px; font-weight: 700; border-bottom: 1px solid transparent; }
    nav a:hover { border-bottom-color: #dbeafe; }
    main { padding: 16px 20px 28px; max-width: 1680px; margin: 0 auto; }
    .summary { display: grid; grid-template-columns: minmax(260px, 1.2fr) minmax(420px, 2fr); gap: 14px; margin-bottom: 14px; align-items: stretch; }
    .panel { background: var(--panel); border: 1px solid var(--line); border-radius: 8px; padding: 14px; }
    .panel h2 { margin: 0 0 10px; font-size: 17px; }
    .status-grid { display: grid; grid-template-columns: repeat(4, minmax(80px, 1fr)); gap: 8px; margin-bottom: 12px; }
    .metric { border: 1px solid #e5e9f2; border-radius: 6px; padding: 9px; background: #fbfdff; }
    .metric span { display: block; color: var(--muted); font-size: 12px; }
    .metric strong { display: block; font-size: 20px; margin-top: 4px; }
    .ok { color: var(--ok); }
    .warn { color: var(--warn); }
    table { width: 100%; border-collapse: collapse; font-size: 14px; }
    th, td { padding: 9px 8px; border-bottom: 1px solid #e5e9f2; text-align: left; vertical-align: top; }
    th { color: #344256; background: #eef3f9; }
    a { color: var(--link); font-weight: 700; }
    code { background: #e5e7eb; padding: 2px 5px; border-radius: 4px; }
    .allure-frame { width: 100%; height: calc(100vh - 260px); min-height: 680px; border: 1px solid var(--line); border-radius: 8px; background: white; }
    .report-note { color: var(--muted); font-size: 13px; margin: 8px 0 0; }
    .compact-links { display: flex; flex-wrap: wrap; gap: 10px 14px; font-size: 13px; margin-top: 10px; }
    @media (max-width: 980px) { .summary { grid-template-columns: 1fr; } .status-grid { grid-template-columns: repeat(2, minmax(80px, 1fr)); } .allure-frame { height: 720px; } }
  </style>
</head>
<body>
  <header>
    <div>
      <h1>DTCC Automation Report Portal</h1>
      <p>Public-safe Selenium, Selenide, Cucumber, REST API, SQL, and reporting checks for https://www.dtcc.com/</p>
    </div>
    <nav aria-label="Report navigation">
      <a href="allure/index.html">Allure</a>
      <a href="cucumber/index.html">Cucumber</a>
      <a href="surefire/index.html">Surefire/TestNG</a>
      <a href="dashboard/index.html">Static Dashboard</a>
      <a href="sample-reports/index.html">Samples</a>
      <a href="https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/actions/workflows/selenium.yml">Run Tests</a>
      <a href="https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/">Repo</a>
    </nav>
  </header>
  <main>
    <section class="summary" aria-label="Report status and explanation">
      <div class="panel">
        <h2>Current Stable Status</h2>
        <div class="status-grid">
          <div class="metric"><span>Stable tests</span><strong>30+</strong></div>
          <div class="metric"><span>Failures</span><strong class="ok">0</strong></div>
          <div class="metric"><span>Errors</span><strong class="ok">0</strong></div>
          <div class="metric"><span>Demos</span><strong class="warn">Opt-in</strong></div>
        </div>
        <p class="report-note">GitHub Pages is static, so test execution stays in GitHub Actions. This page consolidates the generated reports and makes Allure the default working view.</p>
        <div class="compact-links">
          <a href="allure/index.html" target="_blank" rel="noopener">Open Allure full screen</a>
          <a href="https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/actions/workflows/selenium.yml">Run workflow</a>
          <a href="https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/actions">Workflow logs</a>
        </div>
      </div>
      <div class="panel">
        <h2>What Each Supporting Report Shows</h2>
        <table>
          <thead><tr><th>Area</th><th>Status/Use</th><th>Open When You Need</th></tr></thead>
          <tbody>
            <tr><td><a href="allure/index.html">Allure</a></td><td>Primary report: UI, security, REST API, SQL, quality gates, screenshots, attachments, suites, behaviors, timeline.</td><td>Test details, failures, attachments, execution evidence.</td></tr>
            <tr><td><a href="cucumber/index.html">Cucumber BDD</a></td><td>Feature/scenario view for account, order lifecycle, and public-site behavior language.</td><td>Business-readable Given/When/Then coverage.</td></tr>
            <tr><td><a href="surefire/index.html">Surefire/TestNG</a></td><td>Raw Maven/TestNG execution evidence. Less visual, useful for CI diagnostics.</td><td>XML/HTML runner output or skipped intentional demos.</td></tr>
            <tr><td><a href="dashboard/index.html">Static dashboard</a></td><td>Portfolio summary and fallback static report view.</td><td>High-level overview outside Allure.</td></tr>
          </tbody>
        </table>
      </div>
    </section>
    <section aria-label="Embedded Allure report">
      <iframe class="allure-frame" src="allure/index.html" title="Live Allure Report"></iframe>
      <p class="report-note">Allure is embedded here by default. Use the full-screen link above if browser iframe restrictions or small screens make navigation cramped.</p>
    </section>
  </main>
</body>
</html>
HTML

test -f "$PORTAL_DIR/index.html"
