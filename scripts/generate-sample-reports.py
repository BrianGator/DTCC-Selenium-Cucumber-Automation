from pathlib import Path
import json

root = Path(__file__).resolve().parents[1]
summary = root / '13_Reports' / 'sample-execution-summary.json'
html_out = root / '13_Reports' / 'web-ui' / 'index.html'
print(f'Report summary available: {summary}')
print(f'Web UI dashboard available: {html_out}')
print('Open the dashboard directly or run: cd 13_Reports/web-ui && python -m http.server 8080')
