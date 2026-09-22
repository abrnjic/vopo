import test from 'node:test';
import assert from 'node:assert/strict';
import { NextRequest } from 'next/server';
import { mockState } from '../src/lib/mockFirebaseAdmin';
import { POST as changeDomain, GET as getDomains } from '../src/app/api/domains/route';
import { POST as createReseller } from '../src/app/api/admin/resellers/route';
import { POST as activate } from '../src/app/api/reseller/activate/route';
import { normalizeDomain } from '../src/lib/domains';

const req = (body: unknown, token = 'reseller1:reseller', url = 'http://localhost/api/domains') => new NextRequest(url, { method: 'POST', headers: { Authorization: `Bearer ${token}`, 'Content-Type': 'application/json' }, body: JSON.stringify(body) });
test('Multiple reseller domains', async t => {
  t.beforeEach(() => {
    for (const map of [mockState.users, mockState.authUsers, mockState.settings, mockState.licenses, mockState.transactions, mockState.activity_logs]) map.clear();
    mockState.throwDbError = false; mockState.throwAuthError = false;
    mockState.users.set('admin1', { role: 'admin', status: 'active' });
    mockState.users.set('reseller1', { role: 'reseller', status: 'active', credits: 10, assignedDomains: ['http://one.example:8080'], customDomains: ['https://two.example/base'] });
    mockState.users.set('reseller2', { role: 'reseller', status: 'active', assignedDomains: [] });
  });
  await t.test('normalizes addresses while retaining protocol, port and base path', () => {
    assert.equal(normalizeDomain('HTTP://ONE.example:8080/'), 'http://one.example:8080');
    assert.equal(normalizeDomain('two.example/base/'), 'https://two.example/base');
    for (const value of ['ftp://one.example', 'https://u:p@one.example', 'https://one.example?key=x', 'https://one.example#frag']) assert.throws(() => normalizeDomain(value));
  });
  await t.test('admin creates complete reseller profile with multiple domains', async () => {
    const res = await createReseller(req({ email: 'new@example.com', password: 'test-password', credits: 3, assignedDomains: ['http://ONE.example:8080/', 'https://two.example/base', 'http://one.example:8080'] }, 'admin1:admin'));
    assert.equal(res.status, 201);
    const { uid } = await res.json();
    assert.equal(mockState.authUsers.get(uid).email, 'new@example.com');
    assert.deepEqual(mockState.users.get(uid).assignedDomains, ['http://one.example:8080', 'https://two.example/base']);
    assert.equal(mockState.users.get(uid).role, 'reseller');
    assert.equal(mockState.users.get(uid).credits, 3);
  });
  await t.test('reseller cannot create other accounts', async () => {
    assert.equal((await createReseller(req({ email: 'new@example.com', password: 'test-password', credits: 3, assignedDomains: [] }))).status, 403);
    assert.equal(mockState.authUsers.size, 0);
  });
  await t.test('failed profile persistence compensates Auth creation', async () => {
    // Auth is verified before the DB failure is injected by createUser.
    const { mockAdminAuth } = await import('../src/lib/mockFirebaseAdmin');
    const original = mockAdminAuth.createUser;
    mockAdminAuth.createUser = async props => { const user = await original(props); mockState.throwDbError = true; return user; };
    try {
      assert.equal((await createReseller(req({ email: 'new@example.com', password: 'test-password', credits: 0, assignedDomains: [] }, 'admin1:admin'))).status, 500);
      assert.equal(mockState.authUsers.size, 0);
    } finally { mockAdminAuth.createUser = original; }
  });
  await t.test('own domains can be added, edited and removed including assigned domains', async () => {
    assert.equal((await changeDomain(req({ action: 'add', source: 'customDomains', domain: 'http://three.example:9000' }))).status, 200);
    assert.equal((await changeDomain(req({ action: 'edit', source: 'assignedDomains', domain: 'http://one.example:8080', replacement: 'https://new.example' }))).status, 200);
    assert.equal((await changeDomain(req({ action: 'delete', source: 'customDomains', domain: 'https://two.example/base' }))).status, 200);
    assert.deepEqual(mockState.users.get('reseller1').assignedDomains, ['https://new.example']);
    assert.deepEqual(mockState.users.get('reseller1').customDomains, ['http://three.example:9000']);
    assert.equal(mockState.activity_logs.size, 3);
  });
  await t.test('admin can manage another reseller domains', async () => {
    assert.equal((await changeDomain(req({ targetUserId: 'reseller2', action: 'add', source: 'assignedDomains', domain: 'https://new.example' }, 'admin1:admin'))).status, 200);
    assert.deepEqual(mockState.users.get('reseller2').assignedDomains, ['https://new.example']);
  });
  await t.test('reseller cannot read or change another reseller domains', async () => {
    assert.equal((await changeDomain(req({ targetUserId: 'reseller2', action: 'add', source: 'customDomains', domain: 'https://evil.example' }))).status, 403);
    const getReq = new NextRequest('http://localhost/api/domains?targetUserId=reseller2', { headers: { Authorization: 'Bearer reseller1:reseller' } });
    assert.equal((await getDomains(getReq)).status, 403);
    assert.deepEqual(mockState.users.get('reseller2').assignedDomains, []);
  });
  await t.test('duplicates across both lists and malformed domain are rejected', async () => {
    assert.equal((await changeDomain(req({ action: 'add', source: 'customDomains', domain: 'HTTP://ONE.example:8080/' }))).status, 409);
    assert.equal((await changeDomain(req({ action: 'add', source: 'customDomains', domain: 'ftp://one.example' }))).status, 400);
  });
  await t.test('activation delivers each selected domain to application config', async () => {
    for (const [index, domain] of ['http://one.example:8080', 'https://two.example/base'].entries()) {
      const res = await activate(req({ deviceId: `device${index}`, licenseType: '1_year', selectedDomain: domain, username: 'demo', password: 'test', linePin: '926483' }));
      assert.equal(res.status, 200);
      assert.equal(mockState.licenses.get(`device${index}`).xtreamConfig.url, domain);
      assert.equal(mockState.licenses.get(`device${index}`).selectedDomain, domain);
    }
    assert.equal(mockState.users.get('reseller1').credits, 8);
  });
  await t.test('activation cannot use an unassigned or omitted domain', async () => {
    assert.equal((await activate(req({ deviceId: 'device', licenseType: '1_year', selectedDomain: 'https://foreign.example', linePin: '926483' }))).status, 403);
    assert.equal((await activate(req({ deviceId: 'device', licenseType: '1_year', linePin: '926483' }))).status, 400);
    assert.equal(mockState.licenses.size, 0);
    assert.equal(mockState.users.get('reseller1').credits, 10);
  });
  await t.test('admin manages global catalog without a reseller, reseller has no access', async () => {
    assert.equal((await changeDomain(req({ catalog: true, action: 'add', source: 'assignedDomains', domain: 'https://catalog.example' }, 'admin1:admin'))).status, 200);
    assert.deepEqual(mockState.settings.get('domainCatalog').assignedDomains, ['https://catalog.example']);
    assert.equal((await changeDomain(req({ catalog: true, action: 'delete', source: 'assignedDomains', domain: 'https://catalog.example' }))).status, 403);
    const getReq = new NextRequest('http://localhost/api/domains?catalog=1', { headers: { Authorization: 'Bearer reseller1:reseller' } });
    assert.equal((await getDomains(getReq)).status, 403);
  });
  await t.test('suspended reseller cannot mutate domains', async () => {
    mockState.users.get('reseller1').status = 'suspended';
    assert.equal((await changeDomain(req({ action: 'add', source: 'customDomains', domain: 'https://new.example' }))).status, 403);
  });
});
