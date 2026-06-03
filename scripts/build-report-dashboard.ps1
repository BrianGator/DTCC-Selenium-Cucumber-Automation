param(
    [string]$DashboardDir = "target/report-dashboard",
    [string]$AllureDir = "target/site/allure-report"
)

$repository = if ($env:GITHUB_REPOSITORY) { $env:GITHUB_REPOSITORY } else { "BrianGator/DTCC-Selenium-Cucumber-Automation" }
$serverUrl = if ($env:GITHUB_SERVER_URL) { $env:GITHUB_SERVER_URL } else { "https://github.com" }
$runUrl = if ($env:GITHUB_RUN_ID) { "$serverUrl/$repository/actions/runs/$env:GITHUB_RUN_ID" } else { "$serverUrl/$repository/actions" }
$dispatchUrl = "$serverUrl/$repository/actions/workflows/selenium.yml"

New-Item -ItemType Directory -Force -Path $DashboardDir | Out-Null

if (Test-Path $AllureDir) {
    $dashboardAllure = Join-Path $DashboardDir "allure-report"
    if (Test-Path $dashboardAllure) {
        Remove-Item -Recurse -Force $dashboardAllure
    }
    New-Item -ItemType Directory -Force -Path $dashboardAllure | Out-Null
    Copy-Item -Path (Join-Path $AllureDir "*") -Destination $dashboardAllure -Recurse -Force
}

$total = 0
$failures = 0
$errors = 0
$skipped = 0
$rows = New-Object System.Collections.Generic.List[string]

Get-ChildItem -Path "target/surefire-reports" -Filter "TEST-*.xml" -ErrorAction SilentlyContinue | ForEach-Object {
    [xml]$xml = Get-Content $_.FullName
    $suite = $xml.testsuite
    $tests = [int]$suite.tests
    $failed = [int]$suite.failures
    $errs = [int]$suite.errors
    $skips = [int]$suite.skipped
    $passed = [Math]::Max($tests - $failed - $errs - $skips, 0)
    $total += $tests
    $failures += $failed
    $errors += $errs
    $skipped += $skips
    $rows.Add("<tr><td>$($suite.name)</td><td>$tests</td><td>$passed</td><td>$failed</td><td>$errs</td><td>$skips</td></tr>")
}

if ($rows.Count -eq 0) {
    $rows.Add("<tr><td colspan='6'>No Surefire XML files found.</td></tr>")
}

$passedTotal = [Math]::Max($total - $failures - $errors - $skipped, 0)
$rowsHtml = $rows -join "`n"

$html = @"
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>DTCC Automation Report Dashboard</title>
  <style>
    body { margin: 0; font-family: Arial, sans-serif; color: #172033; background: #f5f7fb; }
    header { background: #14213d; color: #fff; padding: 28px 36px; }
    main { padding: 28px 36px; max-width: 1180px; margin: 0 auto; }
    .actions, .cards { display: grid; grid-template-columns: repeat(auto-fit, minmax(180px, 1fr)); gap: 14px; }
    .card, .panel { background: #fff; border: 1px solid #d9e1ee; border-radius: 8px; padding: 18px; }
    .metric { font-size: 34px; font-weight: 700; margin-top: 8px; }
    .pass { color: #087f5b; } .fail { color: #c92a2a; } .skip { color: #a16207; }
    a.button { display: block; text-decoration: none; color: #fff; background: #1d4ed8; border-radius: 6px; padding: 12px 14px; text-align: center; font-weight: 700; }
    table { width: 100%; border-collapse: collapse; margin-top: 16px; background: #fff; }
    th, td { padding: 11px 12px; border-bottom: 1px solid #e5e9f2; text-align: left; }
    th { background: #eaf0f8; }
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
      <a class="button" href="$runUrl">Open Workflow Run</a>
      <a class="button" href="$dispatchUrl">Run Tests in GitHub Actions</a>
    </section>
    <section class="cards" style="margin-top:18px">
      <div class="card"><div>Total Tests</div><div class="metric">$total</div></div>
      <div class="card"><div>Passed</div><div class="metric pass">$passedTotal</div></div>
      <div class="card"><div>Failures</div><div class="metric fail">$failures</div></div>
      <div class="card"><div>Errors</div><div class="metric fail">$errors</div></div>
      <div class="card"><div>Skipped</div><div class="metric skip">$skipped</div></div>
    </section>
    <section class="panel" style="margin-top:18px">
      <h2>Surefire/TestNG Summary</h2>
      <table>
        <thead><tr><th>Suite</th><th>Total</th><th>Passed</th><th>Failures</th><th>Errors</th><th>Skipped</th></tr></thead>
        <tbody>$rowsHtml</tbody>
      </table>
    </section>
  </main>
</body>
</html>
"@

Set-Content -Path (Join-Path $DashboardDir "index.html") -Value $html -Encoding UTF8
