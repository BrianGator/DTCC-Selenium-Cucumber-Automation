#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/../13_Reports/web-ui"
echo "Starting report UI at http://localhost:8080"
python -m http.server 8080
