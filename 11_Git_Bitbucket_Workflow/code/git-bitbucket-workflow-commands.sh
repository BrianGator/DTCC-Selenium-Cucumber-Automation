#!/usr/bin/env bash
set -euo pipefail

# Example enterprise Git/Bitbucket workflow for SDET automation changes.
# This works the same conceptually for GitHub branches and pull requests.

feature_branch="feature/add-dtcc-public-site-tests"

git checkout main
git pull --rebase origin main
git checkout -b "$feature_branch"

# Developer edits automation code, test data, and README files here.
git status
git add src/test/java src/test/resources 12_Test_Cases README.md
git commit -m "Add public site automation coverage and reports"

git push -u origin "$feature_branch"

cat <<'NEXT_STEPS'
Open a pull request with:
- Summary of test coverage added
- Maven command used
- Screenshot/report artifact links
- Risk notes for public-site tests
- Reviewer checklist
NEXT_STEPS
