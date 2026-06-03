#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/../13_Reports/web-ui"

echo "Starting static report dashboard at http://localhost:8080"
echo "Press Ctrl+C to stop the server."
python3 -m http.server 8080
