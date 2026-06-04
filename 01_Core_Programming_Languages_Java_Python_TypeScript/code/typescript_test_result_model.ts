export type TestLayer = 'ui' | 'api' | 'database' | 'reporting';

export interface TestResult {
  id: string;
  layer: TestLayer;
  name: string;
  status: 'passed' | 'failed' | 'skipped';
  durationMs: number;
}

export function summarize(results: TestResult[]) {
  const total = results.length;
  const passed = results.filter(r => r.status === 'passed').length;
  const failed = results.filter(r => r.status === 'failed').length;
  const skipped = results.filter(r => r.status === 'skipped').length;
  const passRate = total === 0 ? 0 : Math.round((passed / total) * 10000) / 100;
  return { total, passed, failed, skipped, passRate };
}

const sample: TestResult[] = [
  { id: 'UI-001', layer: 'ui', name: 'Client Center loads', status: 'passed', durationMs: 812 },
  { id: 'API-001', layer: 'api', name: 'Notice search contract', status: 'passed', durationMs: 105 }
];

console.log(summarize(sample));
