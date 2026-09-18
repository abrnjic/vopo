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
  for (const path of ['/api/domains?catalog=1', '/api/admin/users', '/api/admin/resellers', '/api/reseller/activate']) {
    const isGet = path.startsWith('/api/domains');
    const res = await fetch(`http://127.0.0.1:${port}${path}`, { method: isGet ? 'GET' : 'POST', headers: { 'Content-Type': 'application/json' }, body: isGet ? undefined : '{}', signal: AbortSignal.timeout(10000) });
    assert.equal(res.status, 401, `${path} must reject unauthenticated access without crashing`);
    assert.match(res.headers.get('content-type') || '', /application\/json/);
    const body = await res.json();
    assert.equal(body.error, 'No token provided');
    console.log(`Runtime check passed: ${path}`);
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
