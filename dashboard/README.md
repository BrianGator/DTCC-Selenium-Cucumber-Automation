# Web UI Report Dashboard

This is a lightweight static HTML dashboard for reviewing sample test results in a browser.

## Open Directly

Double-click `index.html`.

## Open with a Local Web Server

```bash
cd 13_Reports/web-ui
python -m http.server 8080
```

Then open:

```text
http://localhost:8080
```

## What Teams Typically Use

In real SDET teams, reports are usually reviewed through:

- Jenkins / CloudBees build pages
- Surefire or JUnit XML trend reports
- Cucumber HTML reports
- Allure Reports
- Extent Reports
- GitHub Actions artifacts
- Published HTML report pages from CI

This project includes static sample reports and runtime instructions for generating live Maven reports under `target/`.
