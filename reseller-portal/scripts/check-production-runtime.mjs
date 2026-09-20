import { spawn } from 'node:child_process';
import assert from 'node:assert/strict';

// Exercise the built routes with native require(ESM) disabled, as in the failed deployment.
const port = 3106;
const server = spawn(process.execPath, ['--no-experimental-require-module', 'node_modules/next/dist/bin/next', 'start', '--hostname', '127.0.0.1', '--port', String(port)], { stdio: ['ignore', 'pipe', 'pipe'] });
let output = '';
server.stdout.on('data', chunk => { output += chunk.toString(); });
server.stderr.on('data', chunk => { output += chunk.toString(); });
const stopped = new Promise(resolve => server.once('exit', resolve));
try {
  await new Promise((resolve, reject) => {
    const timeout = setTimeout(() => { clearInterval(poll); reject(new Error(`Production server startup timed out. ${output}`)); }, 30000);
    const poll = setInterval(() => {
      if (output.includes('Ready in')) { clearInterval(poll); clearTimeout(timeout); resolve(); }
      else if (server.exitCode !== null) { clearInterval(poll); clearTimeout(timeout); reject(new Error(`Production server failed to start. ${output}`)); }
    }, 100);
    server.once('error', error => { clearInterval(poll); clearTimeout(timeout); reject(error); });
  });
  const checks = [
    { path: '/api/domains?catalog=1', method: 'GET', status: 401, error: 'No token provided' },
    { path: '/api/admin/users', method: 'POST', status: 401, error: 'No token provided' },
    { path: '/api/admin/resellers', method: 'POST', status: 401, error: 'No token provided' },
    { path: '/api/reseller/activate', method: 'POST', status: 401, error: 'No token provided' },
    { path: '/api/device/license?deviceId=runtime-check', method: 'GET', status: 401, error: 'Unauthorized' },
    { path: '/api/device/register', method: 'POST', status: 400, error: 'Invalid device registration.' },
  ];
  for (const check of checks) {
    const res = await fetch(`http://127.0.0.1:${port}${check.path}`, { method: check.method, headers: { 'Content-Type': 'application/json' }, body: check.method === 'GET' ? undefined : '{}', signal: AbortSignal.timeout(10000) });
    assert.equal(res.status, check.status, `${check.path} must reject an invalid request without crashing`);
    assert.match(res.headers.get('content-type') || '', /application\/json/);
    const body = await res.json();
    assert.equal(body.error, check.error);
    console.log(`Runtime check passed: ${check.path}`);
  }
} catch (error) {
  console.error(error.message);
  process.exitCode = 1;
} finally {
  server.kill('SIGTERM');
  const timeout = setTimeout(() => server.kill('SIGKILL'), 5000);
  await stopped;
  clearTimeout(timeout);
}
