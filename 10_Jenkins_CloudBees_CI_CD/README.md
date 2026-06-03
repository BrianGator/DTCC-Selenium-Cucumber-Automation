# 10_Jenkins_CloudBees_CI_CD

Jenkinsfile is provided at the repository root. GitHub Actions workflow is in .github/workflows.


---

## Project File Structure Reference

See the root `README.md` for the full repository tree. The automation code lives mainly under:

```text
src/main/java/com/dtcc/automation/
src/test/java/com/dtcc/automation/
src/test/resources/features/
src/test/resources/testng-suites/
```

## Related DTCC.com Public Website Tests

Public website coverage is documented in:

```text
16_DTCC_Public_Website_Page_Coverage/
docs/test-strategy/DTCC-Public-Website-Automation-Strategy.md
docs/test-cases/DTCC-Public-Website-Test-Case-Matrix.md
```

## GitHub Actions as the Cloud Test UI

For GitHub publishing, `.github/workflows/selenium.yml` is the main cloud execution interface. It runs tests on Ubuntu with Java 17 and headless Chrome, archives Surefire/Allure artifacts, generates an Allure dashboard, and deploys the report to GitHub Pages.

Use GitHub Actions when the project is public or portfolio-focused. Use Jenkins/CloudBees when the project is running inside an enterprise network with internal credentials, private environments, or internal test data.
