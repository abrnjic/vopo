import test from 'node:test';
import assert from 'node:assert/strict';
import { NextRequest } from 'next/server';
import { mockState } from '../src/lib/mockFirebaseAdmin';
import { createExpiryNotifications, migrateProserversDomainToHttp, migrateProserversUrl } from '../src/lib/operations';
import { GET as lines } from '../src/app/api/lines/route';
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
