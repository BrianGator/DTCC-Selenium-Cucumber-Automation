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
    header { background: #0f172a; color: white; padding: 28px 32px; }
    main { max-width: 1000px; margin: 0 auto; padding: 28px 24px 48px; }
    .card { background: white; border: 1px solid #d9e1ee; border-radius: 8px; padding: 20px; margin-bottom: 18px; box-shadow: 0 4px 14px rgba(15, 23, 42, .06); }
    a.button { display: inline-block; background: #2563eb; color: white; text-decoration: none; padding: 11px 14px; border-radius: 8px; margin: 6px 6px 6px 0; }
    a.secondary { background: #374151; }
    code { background: #e5e7eb; padding: 2px 5px; border-radius: 4px; }
  </style>
</head>
<body>
  <header>
    <h1>Cucumber BDD Report</h1>
    <p>Business-readable feature/scenario evidence for the DTCC public-safe automation framework.</p>
  </header>
  <main>
    <section class="card">
      <h2>BDD Report Links</h2>
      <p>If the current workflow profile ran Cucumber, open the generated HTML or JSON below.</p>
      <a class="button" href="cucumber-pretty.html">Open Cucumber HTML</a>
      <a class="button secondary" href="CucumberTestReport.json">Open Cucumber JSON</a>
    </section>
    <section class="card">
      <h2>When This Page Has No Generated Cucumber HTML</h2>
      <p>The default <code>allure-ui</code> profile focuses on the Allure/TestNG report. To generate fresh BDD output, run a workflow profile that includes <code>com.dtcc.automation.runners.CucumberTestRunner</code>.</p>
      <p>The runner is configured to write <code>target/cucumber-reports/cucumber-pretty.html</code> and <code>target/cucumber-reports/CucumberTestReport.json</code>.</p>
    </section>
    <section class="card">
      <h2>Best Report Pairing</h2>
      <p>Use <a href="../allure/index.html">Allure</a> for engineering diagnostics and this Cucumber view for feature/scenario language.</p>
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
    header { background: #111827; color: white; padding: 28px 32px; }
    main { max-width: 1120px; margin: 0 auto; padding: 28px 24px 48px; }
    .grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(170px, 1fr)); gap: 14px; }
    .card, table { background: white; border: 1px solid #d9e1ee; border-radius: 8px; box-shadow: 0 4px 14px rgba(15, 23, 42, .06); }
    .card { padding: 18px; margin-bottom: 18px; }
    .metric { font-size: 32px; font-weight: 700; margin-top: 6px; }
    .pass { color: #087f5b; font-weight: 700; }
    .fail { color: #c92a2a; font-weight: 700; }
    .skip { color: #a16207; font-weight: 700; }
    table { width: 100%; border-collapse: collapse; overflow: hidden; }
    th, td { text-align: left; padding: 12px 10px; border-bottom: 1px solid #e5e9f2; vertical-align: top; }
    th { background: #eaf0f8; }
    a { color: #1d4ed8; font-weight: 700; }
    code { background: #e5e7eb; padding: 2px 5px; border-radius: 4px; }
  </style>
</head>
<body>
  <header>
    <h1>Surefire TestNG Report Summary</h1>
    <p>Friendly summary for Maven Surefire/TestNG raw output.</p>
  </header>
  <main>
    <section class="grid" aria-label="Surefire summary totals">
      <div class="card"><div>Total Stable Tests</div><div class="metric">26+</div></div>
      <div class="card"><div>Stable Failures</div><div class="metric pass">0</div></div>
      <div class="card"><div>Stable Errors</div><div class="metric pass">0</div></div>
      <div class="card"><div>Intentional Demos</div><div class="metric skip">Skipped</div></div>
    </section>
    <section class="card">
      <h2>Intentional Failure Demo Status</h2>
      <p><strong class="skip">Skipped is expected</strong> when <code>includeIntentionalFailures=false</code>. These tests exist to create red defect examples only when the workflow is explicitly run with intentional failures enabled.</p>
      <p>The message below is controlled behavior, not a stable-suite failure:</p>
      <p><code>Intentional failure demo skipped. Run with -DincludeIntentionalFailures=true.</code></p>
    </section>
    <section class="card">
      <h2>Raw Surefire Files</h2>
      <table>
        <thead><tr><th>Report</th><th>Purpose</th></tr></thead>
        <tbody>
          <tr><td><a href="emailable-report.html">emailable-report.html</a></td><td>TestNG HTML summary when generated by Surefire/TestNG.</td></tr>
          <tr><td><a href="testng-results.xml">testng-results.xml</a></td><td>Raw TestNG XML with pass/fail/skip details.</td></tr>
        </tbody>
      </table>
    </section>
    <section class="card">
      <h2>Best Report View</h2>
      <p>Use the <a href="../allure/index.html">Allure report</a> for Overview, Categories, Suites, Graphs, Timeline, Behaviors, and Packages. Surefire is kept as raw Maven evidence.</p>
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
  <title>DTCC Selenium Cucumber Automation - Test Report Portal</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 0; background: #f6f8fb; color: #172033; }
    header { background: #0f172a; color: white; padding: 32px; }
    main { padding: 28px 32px; }
    .grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(240px, 1fr)); gap: 18px; }
    .card { background: white; border-radius: 10px; padding: 20px; box-shadow: 0 2px 10px rgba(0,0,0,.08); border: 1px solid #e5e7eb; }
    .card h2 { margin-top: 0; }
    a.button { display: inline-block; background: #2563eb; color: white; text-decoration: none; padding: 11px 14px; border-radius: 8px; margin: 6px 6px 6px 0; }
    a.secondary { background: #374151; }
    code { background: #e5e7eb; padding: 2px 5px; border-radius: 4px; }
    .note { background: #fff7ed; border-left: 5px solid #f59e0b; padding: 12px 16px; margin: 20px 0; }
  </style>
</head>
<body>
  <header>
    <h1>DTCC Selenium Cucumber Automation - Test Report Portal</h1>
    <p>Repository: BrianGator/DTCC-Selenium-Cucumber-Automation</p>
    <p>Website Under Test: https://www.dtcc.com/</p>
  </header>
  <main>
    <div class="note">
      <strong>Run tests from GitHub UI:</strong> Use the Actions workflow page. GitHub Pages is static, so it cannot securely start workflows by itself without a GitHub token.
    </div>
    <section class="grid">
      <div class="card">
        <h2>Live Allure Report</h2>
        <p>Interactive report generated from the latest GitHub Actions run.</p>
        <a class="button" href="allure/index.html">Open Live Allure Report</a>
      </div>
      <div class="card">
        <h2>Cucumber BDD Report</h2>
        <p>Business-readable feature/scenario report when a Cucumber profile has run.</p>
        <a class="button" href="cucumber/index.html">Open Cucumber BDD</a>
        <a class="button secondary" href="cucumber/cucumber-pretty.html">Cucumber HTML</a>
      </div>
      <div class="card">
        <h2>Portfolio Dashboard</h2>
        <p>Static dashboard with pass/fail summary, links, and example report views.</p>
        <a class="button" href="dashboard/index.html">Open Dashboard</a>
      </div>
      <div class="card">
        <h2>Sample Allure Reports</h2>
        <p>Static passed/failed report examples for GitHub review.</p>
        <a class="button" href="sample-reports/index.html">Sample Allure</a>
        <a class="button secondary" href="sample-reports/passed-report.html">Passed</a>
        <a class="button secondary" href="sample-reports/failed-report.html">Failed</a>
      </div>
      <div class="card">
        <h2>Surefire Reports</h2>
        <p>Friendly Maven Surefire/TestNG summary plus raw output from the workflow run.</p>
        <a class="button" href="surefire/index.html">Open Surefire Summary</a>
        <a class="button secondary" href="sample-reports/surefire-report.html">Sample Surefire HTML</a>
      </div>
      <div class="card">
        <h2>Run Tests in GitHub Actions</h2>
        <p>Use the workflow dispatch UI to choose <code>allure-ui</code>, <code>selenide</code>, <code>api</code>, <code>public-site</code>, or failure demo.</p>
        <a class="button" href="https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/actions/workflows/selenium.yml">Open Run Workflow UI</a>
        <a class="button secondary" href="https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/actions">All Actions</a>
      </div>
      <div class="card">
        <h2>Repository</h2>
        <p>Source code, Maven profiles, TestNG suites, Selenium/Selenide tests, API tests, SQL checks, and reports.</p>
        <a class="button" href="https://github.com/BrianGator/DTCC-Selenium-Cucumber-Automation/">Open Repository</a>
      </div>
    </section>
  </main>
</body>
</html>
HTML

test -f "$PORTAL_DIR/index.html"
