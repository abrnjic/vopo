import test from 'node:test';
import assert from 'node:assert/strict';
import { NextRequest } from 'next/server';
import { mockState } from '../src/lib/mockFirebaseAdmin';
import { createExpiryNotifications, migrateProserversDomainToHttp, migrateProserversUrl } from '../src/lib/operations';
import { GET as lines, PATCH as editLine } from '../src/app/api/lines/route';
import { hashLinePin } from '../src/lib/lineSecurity';
import { GET as reports } from '../src/app/api/reports/route';
import { POST as createTicket, PATCH as updateTicket } from '../src/app/api/support/tickets/route';

const request = (url: string, token: string, method = 'GET', body?: unknown) => new NextRequest(url, {
  method, headers: { Authorization: `Bearer ${token}`, ...(body ? { 'Content-Type': 'application/json' } : {}) },
  body: body ? JSON.stringify(body) : undefined
});

test('Operational portal features', async t => {
  t.beforeEach(() => {
    for (const map of [mockState.users, mockState.authUsers, mockState.licenses, mockState.transactions, mockState.activity_logs, mockState.notifications, mockState.support_tickets, mockState.backup_runs]) map.clear();
    mockState.users.set('admin1', { role: 'admin', status: 'active' });
    mockState.users.set('reseller1', { role: 'reseller', status: 'active' });
    mockState.users.set('reseller2', { role: 'reseller', status: 'active' });
  });

  await t.test('expiry notices are idempotent', async () => {
    const now = new Date('2026-09-22T10:00:00.000Z');
    mockState.licenses.set('device-1', { deviceId: 'device-1', resellerId: 'reseller1', status: 'Active', expiresAt: new Date('2026-09-29T10:00:00.000Z') });
    assert.deepEqual(await createExpiryNotifications(now), { scanned: 1, created: 1 });
    assert.deepEqual(await createExpiryNotifications(now), { scanned: 1, created: 0 });
    assert.equal(mockState.notifications.size, 1);
  });

  await t.test('proservers protocol migration repairs catalog, every account and existing lines', async () => {
    mockState.settings.set('domainCatalog', { assignedDomains: ['https://proservers.club', 'https://other.example'] });
    mockState.users.set('reseller1', { ...mockState.users.get('reseller1'), assignedDomains: ['https://proservers.club'], customDomains: [] });
    mockState.users.set('sub1', { role: 'subseller', parentResellerId: 'reseller1', assignedDomains: ['https://proservers.club/path'], customDomains: [] });
    mockState.licenses.set('device-http', {
      resellerId: 'reseller1', selectedDomain: 'https://proservers.club',
      xtreamConfig: { url: 'https://proservers.club', username: 'user', password: 'pass' }
    });

    assert.equal(migrateProserversUrl('https://other.example'), 'https://other.example');
    assert.deepEqual(await migrateProserversDomainToHttp(), { settings: 1, users: 2, licenses: 1 });
    assert.deepEqual(mockState.settings.get('domainCatalog').assignedDomains, ['http://proservers.club', 'https://other.example']);
    assert.deepEqual(mockState.users.get('reseller1').assignedDomains, ['http://proservers.club']);
    assert.deepEqual(mockState.users.get('sub1').assignedDomains, ['http://proservers.club/path']);
    assert.equal(mockState.licenses.get('device-http').selectedDomain, 'http://proservers.club');
    assert.equal(mockState.licenses.get('device-http').xtreamConfig.url, 'http://proservers.club');
    assert.deepEqual(await migrateProserversDomainToHttp(), { settings: 0, users: 0, licenses: 0 });
  });

  await t.test('line detail contains history and cannot expose another reseller line', async () => {
    mockState.licenses.set('device-1', { deviceId: 'device-1', resellerId: 'reseller1', status: 'Active' });
    mockState.licenses.set('private-2', { deviceId: 'private-2', resellerId: 'reseller2', status: 'Active' });
    mockState.activity_logs.set('log-1', { userId: 'reseller1', action: 'CREATE_LICENSE', details: 'Created device-1', timestamp: '2026-09-22T10:00:00Z' });
    const own = await lines(request('http://localhost/api/lines?id=device-1', 'reseller1:reseller'));
    assert.equal(own.status, 200); assert.equal((await own.json()).history.length, 1);
    assert.equal((await lines(request('http://localhost/api/lines?id=private-2', 'reseller1:reseller'))).status, 404);
  });

  await t.test('admin sees line owner and creator as accounts, including legacy audit fallback', async () => {
    mockState.users.set('reseller1', { role: 'reseller', email: 'prvi@example.com', status: 'active' });
    mockState.users.set('reseller2', { role: 'reseller', email: 'drugi@example.com', status: 'active' });
    mockState.licenses.set('new-line', { deviceId: 'new-line', resellerId: 'reseller2', createdByUid: 'reseller1', status: 'Active' });
    mockState.licenses.set('old-line', { deviceId: 'old-line', resellerId: 'reseller1', status: 'Trial' });
    mockState.licenses.set('unclaimed', { deviceId: 'unclaimed', resellerId: 'self_registered', status: 'Trial' });
    mockState.activity_logs.set('old-creator', { action: 'UPDATE_LINE', deviceId: 'old-line', userId: 'reseller1', timestamp: '2026-09-20T10:00:00Z' });
    mockState.activity_logs.set('old-editor', { action: 'EDIT_LINE', deviceId: 'old-line', userId: 'reseller2', timestamp: '2026-09-21T10:00:00Z' });
    const response = await lines(request('http://localhost/api/lines', 'admin1:admin'));
    assert.equal(response.status, 200);
    const { lines: listed } = await response.json();
    const newer = listed.find((line: any) => line.id === 'new-line');
    assert.equal(newer.ownerAccount.label, 'drugi@example.com');
    assert.equal(newer.creatorAccount.label, 'prvi@example.com');
    const older = listed.find((line: any) => line.id === 'old-line');
    assert.equal(older.ownerAccount.label, 'prvi@example.com');
    assert.equal(older.creatorAccount.label, 'prvi@example.com');
    assert.equal(listed.find((line: any) => line.id === 'unclaimed').ownerAccount, null);
    const detail = await (await lines(request('http://localhost/api/lines?id=new-line', 'admin1:admin'))).json();
    assert.equal(detail.line.ownerAccount.label, 'drugi@example.com');
    assert.equal(detail.line.creatorAccount.label, 'prvi@example.com');
    assert.equal((await lines(request('http://localhost/api/lines?id=new-line', 'reseller1:reseller'))).status, 404);
    const ownerView = await (await lines(request('http://localhost/api/lines?id=new-line', 'reseller2:reseller'))).json();
    assert.equal(ownerView.line.creatorAccount, undefined);
    assert.equal(ownerView.line.createdByUid, undefined);
  });

  await t.test('line editing preserves licence and credits, enforces PIN and owner domains, and hides secrets', async () => {
    mockState.users.set('reseller1', { ...mockState.users.get('reseller1'), credits: 10, assignedDomains: ['http://proservers.club'], customDomains: [] });
    const expiry = new Date('2027-05-01T10:00:00Z');
    mockState.licenses.set('device-edit', {
      deviceId: 'device-edit', resellerId: 'reseller1', status: 'Active', isLifetime: false,
      expiresAt: expiry, accessTokenHash: 'device-token-hash', linePinHash: hashLinePin('Pin12345'),
      selectedDomain: 'http://proservers.club', customerName: 'Prije',
      xtreamConfig: { url: 'http://proservers.club', username: 'olduser', password: 'old-secret' }
    });
    const payload = { id: 'device-edit', customerName: 'Poslije', customerContact: 'Kupac', selectedDomain: 'http://proservers.club', username: 'newuser', password: '', currentPin: 'Pin12345' };
    const wrongPin = await editLine(request('http://localhost/api/lines', 'reseller1:reseller', 'PATCH', { ...payload, currentPin: 'bad-pin' }));
    assert.equal(wrongPin.status, 403);
    assert.equal(mockState.licenses.get('device-edit').customerName, 'Prije');
    assert.equal((await editLine(request('http://localhost/api/lines', 'reseller2:reseller', 'PATCH', payload))).status, 403);
    assert.equal((await editLine(request('http://localhost/api/lines', 'reseller1:reseller', 'PATCH', { ...payload, selectedDomain: 'https://other.example' }))).status, 403);

    const saved = await editLine(request('http://localhost/api/lines', 'reseller1:reseller', 'PATCH', payload));
    assert.equal(saved.status, 200);
    const body = await saved.json();
    assert.equal(body.line.customerName, 'Poslije');
    assert.equal(body.line.xtreamConfig.password, undefined);
    const stored = mockState.licenses.get('device-edit');
    assert.equal(stored.xtreamConfig.username, 'newuser');
    assert.equal(stored.xtreamConfig.password, 'old-secret');
    assert.equal(stored.expiresAt, expiry);
    assert.equal(stored.status, 'Active');
    assert.equal(stored.accessTokenHash, 'device-token-hash');
    assert.equal(mockState.users.get('reseller1').credits, 10);
    const detail = await (await lines(request('http://localhost/api/lines?id=device-edit', 'reseller1:reseller'))).json();
    assert.equal(detail.line.xtreamConfig.password, undefined);
    assert.equal(detail.line.linePinHash, undefined);
    assert.equal(detail.history.some((entry: any) => entry.action === 'EDIT_LINE'), true);
    assert.equal(JSON.stringify(detail.history).includes('old-secret'), false);
  });

  await t.test('reports only aggregate owned activations', async () => {
    mockState.licenses.set('device-1', { resellerId: 'reseller1', status: 'Active', isLifetime: true });
    mockState.licenses.set('private-2', { resellerId: 'reseller2', status: 'Active', isLifetime: true });
    mockState.transactions.set('tx-1', { resellerId: 'reseller1', creditsDeducted: 2 });
    const response = await reports(request('http://localhost/api/reports', 'reseller1:reseller'));
    const data = await response.json(); assert.equal(data.summary.totalActivations, 1); assert.equal(data.summary.creditsUsed, 2);
  });

  await t.test('support ticket is linked to an owned Device ID and can be resolved', async () => {
    mockState.licenses.set('device-1', { deviceId: 'device-1', resellerId: 'reseller1', status: 'Active' });
    const created = await createTicket(request('http://localhost/api/support/tickets', 'reseller1:reseller:r@example.com', 'POST', { deviceId: 'device-1', subject: 'Nema slike', message: 'Molim provjeru uređaja.' }));
    assert.equal(created.status, 201); const { id } = await created.json(); assert.ok(id);
    const updated = await updateTicket(request('http://localhost/api/support/tickets', 'admin1:admin:a@example.com', 'PATCH', { id, status: 'resolved', message: 'Provjereno.' }));
    assert.equal(updated.status, 200); assert.equal(mockState.support_tickets.get(id).status, 'resolved');
    assert.equal((await createTicket(request('http://localhost/api/support/tickets', 'reseller2:reseller', 'POST', { deviceId: 'device-1', subject: 'Tuđi uređaj', message: 'Ne smije proći.' }))).status, 404);
  });
});
