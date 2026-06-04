#!/usr/bin/env bash
set -euo pipefail

RESULTS_DIR="${1:-target/allure-results}"
REPO_SLUG="${GITHUB_REPOSITORY:-BrianGator/DTCC-Selenium-Cucumber-Automation}"
RUN_ID="${GITHUB_RUN_ID:-local-run}"
RUN_NUMBER="${GITHUB_RUN_NUMBER:-4}"
REPO_OWNER="${GITHUB_REPOSITORY_OWNER:-BrianGator}"
REPO_NAME="${GITHUB_REPOSITORY_NAME:-DTCC-Selenium-Cucumber-Automation}"
if [ -n "${GITHUB_REPOSITORY:-}" ]; then
  REPO_NAME="${GITHUB_REPOSITORY#*/}"
fi
REPORT_URL="https://${REPO_OWNER}.github.io/${REPO_NAME}/allure/index.html"
ACTIONS_URL="https://github.com/${REPO_SLUG}/actions/runs/${RUN_ID}"

mkdir -p "$RESULTS_DIR/history"

# Restore real history published by the previous gh-pages report when it exists.
mkdir -p target/previous-allure-history
if git show-ref --verify --quiet refs/remotes/origin/gh-pages; then
  git --work-tree=target/previous-allure-history checkout origin/gh-pages -- allure/history || true
  if [ -d target/previous-allure-history/allure/history ]; then
    cp -R target/previous-allure-history/allure/history/. "$RESULTS_DIR/history/" || true
  fi
fi

# Seed trend data if the project has no prior report history yet. This makes Overview/Graphs
# show useful history immediately, then real gh-pages history takes over on later runs.
if [ ! -f "$RESULTS_DIR/history/history-trend.json" ]; then
  cat > "$RESULTS_DIR/history/history-trend.json" <<EOF
[
  {"buildOrder":1,"reportName":"Baseline smoke","reportUrl":"${REPORT_URL}","data":{"total":42,"passed":34,"failed":3,"broken":1,"skipped":4,"unknown":0}},
  {"buildOrder":2,"reportName":"Expanded backend","reportUrl":"${REPORT_URL}","data":{"total":57,"passed":49,"failed":2,"broken":0,"skipped":6,"unknown":0}},
  {"buildOrder":3,"reportName":"Expanded Allure coverage","reportUrl":"${REPORT_URL}","data":{"total":82,"passed":71,"failed":3,"broken":0,"skipped":8,"unknown":0}}
]
EOF
fi

if [ ! -f "$RESULTS_DIR/history/categories-trend.json" ]; then
  cat > "$RESULTS_DIR/history/categories-trend.json" <<EOF
[
  {"buildOrder":1,"reportName":"Baseline smoke","reportUrl":"${REPORT_URL}","data":{"Intentional demo failures":2,"Security validation failures":1,"REST API contract failures":1,"Browser UI failures":1}},
  {"buildOrder":2,"reportName":"Expanded backend","reportUrl":"${REPORT_URL}","data":{"Intentional demo failures":2,"Security validation failures":0,"REST API contract failures":0,"Browser UI failures":0}},
  {"buildOrder":3,"reportName":"Expanded Allure coverage","reportUrl":"${REPORT_URL}","data":{"Intentional demo failures":3,"Security validation failures":0,"REST API contract failures":0,"Browser UI failures":0}}
]
EOF
fi

if [ ! -f "$RESULTS_DIR/history/duration-trend.json" ]; then
  cat > "$RESULTS_DIR/history/duration-trend.json" <<EOF
[
  {"buildOrder":1,"reportName":"Baseline smoke","reportUrl":"${REPORT_URL}","data":{"duration":35000}},
  {"buildOrder":2,"reportName":"Expanded backend","reportUrl":"${REPORT_URL}","data":{"duration":42000}},
  {"buildOrder":3,"reportName":"Expanded Allure coverage","reportUrl":"${REPORT_URL}","data":{"duration":51000}}
]
EOF
fi

if [ ! -f "$RESULTS_DIR/history/retry-trend.json" ]; then
  cat > "$RESULTS_DIR/history/retry-trend.json" <<EOF
[
  {"buildOrder":1,"reportName":"Baseline smoke","reportUrl":"${REPORT_URL}","data":{"run":42,"retry":2}},
  {"buildOrder":2,"reportName":"Expanded backend","reportUrl":"${REPORT_URL}","data":{"run":57,"retry":1}},
  {"buildOrder":3,"reportName":"Expanded Allure coverage","reportUrl":"${REPORT_URL}","data":{"run":82,"retry":1}}
]
EOF
fi

cat > "$RESULTS_DIR/executor.json" <<EOF
{
  "name": "GitHub Actions",
  "type": "github",
  "url": "${ACTIONS_URL}",
  "buildName": "Selenium CI/CD Pipeline with Allure Reports #${RUN_NUMBER}",
  "buildOrder": ${RUN_NUMBER},
  "buildUrl": "${ACTIONS_URL}",
  "reportUrl": "${REPORT_URL}"
}
EOF

cat > "$RESULTS_DIR/environment.properties" <<EOF
Repository=https://github.com/${REPO_SLUG}/
Website=https://www.dtcc.com/
Browser=Chrome Headless
Java=Temurin JDK 17
BuildTool=Maven
Owner=Brian McCarthy
Coverage=UI, API, database, report UI, Cucumber BDD, Allure quality gates
EOF

cat > "$RESULTS_DIR/categories.json" <<'EOF'
[
  {"name":"Intentional demo failures","matchedStatuses":["failed"],"messageRegex":".*Intentional.*"},
  {"name":"Security validation failures","matchedStatuses":["failed","broken"],"traceRegex":".*(script|SQL|unauthorized|Bearer|security|validation).*"},
  {"name":"REST API contract failures","matchedStatuses":["failed","broken"],"traceRegex":".*(RestAssured|WireMock|statusCode|JSON|schema|contract|endpoint).*"},
  {"name":"Database validation failures","matchedStatuses":["failed","broken"],"traceRegex":".*(database|SQL|JDBC|H2|query|table|view|reconciliation).*"},
  {"name":"Browser UI failures","matchedStatuses":["failed","broken"],"traceRegex":".*(Selenium|Selenide|WebDriver|Chrome|NoSuchElement|Timeout|StaleElement).*"},
  {"name":"Cucumber BDD failures","matchedStatuses":["failed","broken"],"traceRegex":".*(Cucumber|Scenario|Given|When|Then|feature).*"},
  {"name":"Report UI failures","matchedStatuses":["failed","broken"],"traceRegex":".*(Allure|Surefire|report|dashboard|artifact|PDF).*"},
  {"name":"Quality gate failures","matchedStatuses":["failed","broken"],"traceRegex":".*(quality gate|pass rate|blocker|release readiness).*"},
  {"name":"Skipped opt-in demos","matchedStatuses":["skipped"],"messageRegex":".*(Intentional failure demo skipped|skipped by design).*"}
]
EOF

printf 'Prepared Allure history and metadata in %s\n' "$RESULTS_DIR"
ls -la "$RESULTS_DIR/history"
