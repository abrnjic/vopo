/* eslint-disable @typescript-eslint/no-unused-vars */
import test from 'node:test';
import assert from 'node:assert';
import { NextRequest } from 'next/server';

process.env.MOCK_FIREBASE = 'true';

import { mockState } from '../src/lib/mockFirebaseAdmin';
import { POST as rawConnectRoute } from '../src/app/api/connect/route';
import { POST as authUsersRoute } from '../src/app/api/admin/users/route';
import { POST as rawResellerActivateRoute } from '../src/app/api/reseller/activate/route';
import { POST as resellerBulkExtendRoute } from '../src/app/api/reseller/bulk-extend/route';
import { POST as resellerBulkDeleteRoute } from '../src/app/api/reseller/bulk-delete/route';
import { POST as logRoute } from '../src/app/api/log/route';
import { POST as adminCreditsRoute } from '../src/app/api/admin/credits/route';
import { POST as deviceRegisterRoute } from '../src/app/api/device/register/route';
import { GET as deviceLicenseRoute } from '../src/app/api/device/license/route';
import { POST as deviceDiagnosticsRoute } from '../src/app/api/device/diagnostics/route';
import { GET as portalDiagnosticsRoute } from '../src/app/api/diagnostics/route';
import { GET as listSubsellersRoute, POST as createSubsellerRoute, PATCH as updateSubsellerRoute } from '../src/app/api/reseller/subsellers/route';
import { POST as transferLicenseRoute } from '../src/app/api/licenses/transfer/route';
import { PATCH as licenseSecurityRoute } from '../src/app/api/licenses/security/route';
import { GET as securityLogsRoute } from '../src/app/api/security/logs/route';

import { checkRateLimit, resetFallbackCache } from '../src/lib/rateLimit';
import { hashDeviceToken } from '../src/lib/deviceLicense';
import { linePinMatches } from '../src/lib/lineSecurity';

const createMockReq = (body: any, token?: string, ip?: string) => {
  return {
    headers: {
      get: (key: string) => {
        if (key.toLowerCase() === 'authorization') return token ? `Bearer ${token}` : null;
        if (key.toLowerCase() === 'x-forwarded-for') return ip || '127.0.0.1';
        if (key.toLowerCase() === 'user-agent') return 'Vopo/1.0.0 (Android; Media3; OkHttp)';
        return null;
      }
    },
    json: async () => body
  } as any;
};

const withRequiredLinePin = (req: any) => ({
  ...req,
  json: async () => ({ ...(await req.json()), linePin: (await req.json()).linePin || '926483' })
});
const connectRoute = (req: any) => rawConnectRoute(withRequiredLinePin(req));
const resellerActivateRoute = (req: any) => rawResellerActivateRoute(withRequiredLinePin(req));

const DEVICE_TOKEN = 'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA';
const OTHER_DEVICE_TOKEN = 'BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB';
const createDeviceGet = (deviceId: string, token = DEVICE_TOKEN) => new NextRequest(
  `http://localhost/api/device/license?deviceId=${encodeURIComponent(deviceId)}`,
  { headers: { Authorization: `Device ${token}`, 'User-Agent': 'Vopo/1.0.0 (Android; Media3; OkHttp)' } }
) as any;
const createDevicePost = (body: any, token = DEVICE_TOKEN, ip = '203.0.113.42') => ({
  headers: {
    get: (key: string) => {
      if (key.toLowerCase() === 'authorization') return `Device ${token}`;
      if (key.toLowerCase() === 'x-real-ip') return ip;
      if (key.toLowerCase() === 'user-agent') return 'Vopo/1.0.0 (Android; Media3; OkHttp)';
      return null;
    }
  },
  json: async () => body
}) as any;

test('API P0 Tests', async (t) => {
  t.beforeEach(() => {
    resetFallbackCache();
    mockState.users.clear();
    mockState.authUsers.clear();
    mockState.settings.clear();
    mockState.licenses.clear();
    mockState.transactions.clear();
    mockState.activity_logs.clear();
    mockState.security_logs.clear();
    mockState.device_diagnostics.clear();
    mockState.rate_limits.clear();
    mockState.throwAuthError = false;
    mockState.throwDbError = false;

    mockState.users.set('admin1', { role: 'admin', status: 'active', disabled: false });
    mockState.users.set('reseller1', { role: 'reseller', status: 'active', disabled: false, credits: 10 });
    mockState.users.set('reseller2', { role: 'reseller', status: 'active', disabled: false, credits: 0 });
  });

  await t.test('valjan prvi /api/connect', async () => {
    const req = createMockReq({ deviceId: 'dev1' }, 'reseller1:reseller:r@test.com');
    const res = await connectRoute(req);
    assert.strictEqual(res.status, 201);
    const lic = mockState.licenses.get('dev1');
    assert.strictEqual(lic.status, 'Trial');
  });

  await t.test('uređaj registrira tajni token prije aktivacije i jedini čita licencu', async () => {
    const registration = await deviceRegisterRoute(createMockReq({ deviceId: 'SEC-URE-001', deviceToken: DEVICE_TOKEN }) as any);
    assert.strictEqual(registration.status, 200);
    const registeredLicense = mockState.licenses.get('SEC-URE-001');
    assert.strictEqual(registeredLicense.status, 'Trial');
    assert.ok(registeredLicense.trialStartedAt);
    assert.ok(registeredLicense.expiresAt instanceof Date);
    const originalExpiry = registeredLicense.expiresAt.getTime();

    await connectRoute(createMockReq({ deviceId: 'SEC-URE-001', portalUrl: 'https://tv.example', username: 'u', password: 'p' }, 'admin1:admin:a@test.com'));
    assert.strictEqual(mockState.licenses.get('SEC-URE-001').expiresAt.getTime(), originalExpiry);
    const response = await deviceLicenseRoute(createDeviceGet('SEC-URE-001'));
    assert.strictEqual(response.status, 200);
    const body = await response.json();
    assert.strictEqual(body.status, 'trial');
    assert.deepStrictEqual(body.config, { url: 'https://tv.example', username: 'u', password: 'p' });

    const denied = await deviceLicenseRoute(createDeviceGet('SEC-URE-001', OTHER_DEVICE_TOKEN));
    assert.strictEqual(denied.status, 401);
  });

  await t.test('ponovna registracija iste instalacije ne obnavlja trial', async () => {
    const request = createMockReq({ deviceId: 'SEC-URE-RETRY', deviceToken: DEVICE_TOKEN }) as any;
    await deviceRegisterRoute(request);
    const firstExpiry = mockState.licenses.get('SEC-URE-RETRY').expiresAt.getTime();
    const firstStart = mockState.licenses.get('SEC-URE-RETRY').trialStartedAt;
    await deviceRegisterRoute(createMockReq({ deviceId: 'SEC-URE-RETRY', deviceToken: DEVICE_TOKEN }) as any);
    const license = mockState.licenses.get('SEC-URE-RETRY');
    assert.strictEqual(license.expiresAt.getTime(), firstExpiry);
    assert.strictEqual(license.trialStartedAt, firstStart);
  });

  await t.test('drugi token ne može preuzeti već registrirani uređaj', async () => {
    await deviceRegisterRoute(createMockReq({ deviceId: 'SEC-URE-002', deviceToken: DEVICE_TOKEN }) as any);
    const takeover = await deviceRegisterRoute(createMockReq({ deviceId: 'SEC-URE-002', deviceToken: OTHER_DEVICE_TOKEN }) as any);
    assert.strictEqual(takeover.status, 409);
    assert.ok([...mockState.security_logs.values()].some((log: any) => log.eventType === 'DEVICE_BINDING_MISMATCH'));
  });

  await t.test('neslužbeni klijent dobiva 403 i sigurnosni incident se zapisuje', async () => {
    const request = createMockReq({ deviceId: 'BAD-UA', deviceToken: DEVICE_TOKEN }) as any;
    const originalGet = request.headers.get;
    request.headers.get = (key: string) => key.toLowerCase() === 'user-agent' ? 'VLC/3.0' : originalGet(key);
    const response = await deviceRegisterRoute(request);
    assert.strictEqual(response.status, 403);
    assert.strictEqual(mockState.licenses.has('BAD-UA'), false);
    assert.ok([...mockState.security_logs.values()].some((log: any) => log.eventType === 'INVALID_USER_AGENT'));
  });

  await t.test('PIN linije je obvezan, hashiran i reseller ga mora potvrditi za promjenu', async () => {
    let response = await rawResellerActivateRoute(createMockReq({
      deviceId: 'PIN-001', licenseType: 'trial', selectedDomain: 'https://tv.example', username: 'u', password: 'p'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 400);

    mockState.users.set('reseller1', { ...mockState.users.get('reseller1'), assignedDomains: ['https://tv.example'] });
    response = await rawResellerActivateRoute(createMockReq({
      deviceId: 'PIN-001', licenseType: 'trial', selectedDomain: 'https://tv.example', username: 'u', password: 'p', linePin: '926483'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 200);
    assert.notStrictEqual(mockState.licenses.get('PIN-001').linePinHash, '926483');
    assert.ok(linePinMatches('926483', mockState.licenses.get('PIN-001').linePinHash));

    response = await licenseSecurityRoute(createMockReq({ deviceId: 'PIN-001', currentPin: 'wrong99', newPin: '135790' }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 403);
    response = await licenseSecurityRoute(createMockReq({ deviceId: 'PIN-001', currentPin: '926483', newPin: '135790' }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 200);
    assert.ok(linePinMatches('135790', mockState.licenses.get('PIN-001').linePinHash));

    const logsResponse = await securityLogsRoute(createMockReq({}, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(logsResponse.status, 200);
    const payload = await logsResponse.json();
    assert.ok(payload.logs.some((log: any) => log.eventType === 'INVALID_LINE_PIN'));
    assert.ok(payload.logs.some((log: any) => log.eventType === 'LINE_PIN_CHANGED'));
  });

  await t.test('uređaj sigurno šalje dijagnostiku, a IP određuje server', async () => {
    await deviceRegisterRoute(createMockReq({ deviceId: 'DIAG-001', deviceToken: DEVICE_TOKEN }) as any);
    mockState.licenses.set('DIAG-001', {
      ...mockState.licenses.get('DIAG-001'),
      resellerId: 'reseller1',
      customerName: 'Pretplatnik',
      status: 'Active',
      isLifetime: true,
    });
    const response = await deviceDiagnosticsRoute(createDevicePost({
      deviceId: 'DIAG-001',
      appVersion: '1.0.18',
      appVersionCode: 19,
      androidVersion: '14',
      deviceModel: 'Test TV',
      connectionType: 'ETHERNET',
      availableStorageBytes: 123456,
      availableMemoryBytes: 654321,
      licenseStatus: 'unknown',
      downloadMbps: 84.64,
      speedMeasuredAtMs: Date.now(),
    }, DEVICE_TOKEN, '2001:db8::42'));
    assert.strictEqual(response.status, 200);
    const saved = mockState.device_diagnostics.get('DIAG-001');
    assert.strictEqual(saved.publicIp, '2001:db8::42');
    assert.strictEqual(saved.resellerId, 'reseller1');
    assert.strictEqual(saved.downloadMbps, 84.6);
    assert.strictEqual(saved.licenseStatus, 'active');
  });

  await t.test('dijagnostika odbija pogrešan token i reseller vidi samo svoje uređaje', async () => {
    await deviceRegisterRoute(createMockReq({ deviceId: 'DIAG-002', deviceToken: DEVICE_TOKEN }) as any);
    const denied = await deviceDiagnosticsRoute(createDevicePost({
      deviceId: 'DIAG-002', appVersion: '1', appVersionCode: 1, androidVersion: '14',
      deviceModel: 'TV', connectionType: 'WIFI', licenseStatus: 'trial'
    }, OTHER_DEVICE_TOKEN));
    assert.strictEqual(denied.status, 401);

    mockState.device_diagnostics.set('own', { resellerId: 'reseller1', publicIp: '198.51.100.1', lastSeenAt: new Date() });
    mockState.device_diagnostics.set('foreign', { resellerId: 'reseller2', publicIp: '198.51.100.2', lastSeenAt: new Date() });
    const portalResponse = await portalDiagnosticsRoute(createMockReq({}, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(portalResponse.status, 200);
    const payload = await portalResponse.json();
    assert.deepStrictEqual(payload.diagnostics.map((item: any) => item.deviceId), ['own']);
    assert.strictEqual(payload.diagnostics[0].publicIp, '198.51.100.1');
  });

  await t.test('istekla godišnja i opozvana lifetime licenca nisu aktivne', async () => {
    await deviceRegisterRoute(createMockReq({ deviceId: 'SEC-URE-003', deviceToken: DEVICE_TOKEN }) as any);
    const tokenHash = mockState.licenses.get('SEC-URE-003').accessTokenHash;
    mockState.licenses.set('SEC-URE-003', { accessTokenHash: tokenHash, status: 'Active', isLifetime: false, expiresAt: new Date(Date.now() - 1000), xtreamConfig: { url: 'https://secret', username: 'u', password: 'p' } });
    let response = await deviceLicenseRoute(createDeviceGet('SEC-URE-003'));
    assert.strictEqual((await response.json()).status, 'expired');

    mockState.licenses.set('SEC-URE-003', { accessTokenHash: tokenHash, status: 'Expired', isLifetime: true, expiresAt: null, xtreamConfig: { url: 'https://secret', username: 'u', password: 'p' } });
    response = await deviceLicenseRoute(createDeviceGet('SEC-URE-003'));
    const revoked = await response.json();
    assert.strictEqual(revoked.status, 'expired');
    assert.strictEqual(revoked.config, null);
  });

  await t.test('nevaljan format aktivacijskog koda', async () => {
    const req = createMockReq({ deviceId: 'dev1', badField: true }, 'reseller1:reseller:r@test.com');
    const res = await connectRoute(req);
    assert.strictEqual(res.status, 400);
  });

  await t.test('javna aktivacija uređaja je zatvorena, a dopuštene su samo registrirane portal uloge', async () => {
    let response = await connectRoute(createMockReq({ deviceId: 'public-denied' }));
    assert.strictEqual(response.status, 401);
    assert.strictEqual(mockState.licenses.has('public-denied'), false);

    mockState.users.set('ordinary1', { role: 'user', status: 'active', disabled: false });
    response = await connectRoute(createMockReq({ deviceId: 'role-denied' }, 'ordinary1:user:u@test.com'));
    assert.strictEqual(response.status, 403);
    assert.strictEqual(mockState.licenses.has('role-denied'), false);

    mockState.users.set('sub-connect', { role: 'subseller', status: 'active', disabled: false, assignedDomains: [] });
    for (const [deviceId, token] of [
      ['admin-connect', 'admin1:admin:a@test.com'],
      ['reseller-connect', 'reseller1:reseller:r@test.com'],
      ['subseller-connect', 'sub-connect:subseller:s@test.com'],
    ]) {
      response = await connectRoute(createMockReq({ deviceId }, token));
      assert.strictEqual(response.status, 201);
      assert.strictEqual(mockState.licenses.get(deviceId).resellerId, token.split(':')[0]);
    }
  });

  await t.test('reseller i subseller kroz zaštićeno povezivanje koriste samo dodijeljene domene', async () => {
    mockState.users.set('reseller1', {
      ...mockState.users.get('reseller1'), assignedDomains: ['https://tv.example'], customDomains: []
    });
    let response = await connectRoute(createMockReq({
      deviceId: 'assigned-domain', portalUrl: 'https://tv.example', username: 'user', password: 'pass'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 201);
    assert.strictEqual(mockState.licenses.get('assigned-domain').selectedDomain, 'https://tv.example');

    response = await connectRoute(createMockReq({
      deviceId: 'foreign-domain', portalUrl: 'https://foreign.example', username: 'user', password: 'pass'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 403);
    assert.strictEqual(mockState.licenses.has('foreign-domain'), false);
  });

  await t.test('zaštićeno povezivanje ne može preuzeti tuđi trial niti ponovno koristiti preneseni uređaj', async () => {
    const future = new Date(Date.now() + 86_400_000);
    mockState.licenses.set('foreign-trial-connect', {
      resellerId: 'reseller2', status: 'Trial', isLifetime: false, expiresAt: future
    });
    let response = await connectRoute(createMockReq({ deviceId: 'foreign-trial-connect' }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 403);
    assert.strictEqual(mockState.licenses.get('foreign-trial-connect').resellerId, 'reseller2');

    mockState.licenses.set('transferred-connect', {
      resellerId: 'reseller1', status: 'Transferred', transferredTo: 'new-device'
    });
    response = await connectRoute(createMockReq({ deviceId: 'transferred-connect' }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 409);
    assert.strictEqual(mockState.licenses.get('transferred-connect').status, 'Transferred');
  });

  await t.test('admin endpoint bez tokena', async () => {
    const req = createMockReq({ uid: 'reseller1', status: 'active' });
    const res = await authUsersRoute(req);
    assert.strictEqual(res.status, 401);
  });

  await t.test('nema tokena -> 401', async () => {
    const req = createMockReq({ uid: 'r1', status: 'active' });
    const res = await authUsersRoute(req);
    assert.strictEqual(res.status, 401);
  });

  await t.test('nevaljan ili opozvan token -> 401', async () => {
    const req = createMockReq({ uid: 'r1', status: 'active' }, 'invalid_token');
    const res = await authUsersRoute(req);
    assert.strictEqual(res.status, 401);
  });

  await t.test('valjan token pogrešne uloge -> 403', async () => {
    const req = createMockReq({ targetUserId: 'reseller1', newCredits: 20 }, 'reseller1:reseller:r@test.com');
    const res = await adminCreditsRoute(req);
    assert.strictEqual(res.status, 403);
  });

  await t.test('valjan token suspendiranog/deaktiviranog korisnika -> 403', async () => {
    mockState.users.set('admin_susp', { role: 'admin', status: 'suspended', disabled: true });
    const req = createMockReq({ targetUserId: 'reseller1', newCredits: 20 }, 'admin_susp:admin:a@test.com');
    const res = await adminCreditsRoute(req);
    assert.strictEqual(res.status, 403); // Since the user is suspended
  });

  await t.test('interna Firebase pogreška -> kontrolirani odgovor', async () => {
    mockState.throwDbError = true; // DB failure
    const req = createMockReq({ targetUserId: 'reseller1', newCredits: 20 }, 'admin1:admin:a@test.com');
    const res = await adminCreditsRoute(req);
    assert.strictEqual(res.status, 500);
    const body = await res.json();
    assert.strictEqual(body.error, 'Internal auth error');
  });

  await t.test('admin dodaje kredite', async () => {
    const req = createMockReq({ targetUserId: 'reseller1', newCredits: 20 }, 'admin1:admin:a@test.com');
    const res = await adminCreditsRoute(req);
    assert.strictEqual(res.status, 200);
    assert.strictEqual(mockState.users.get('reseller1').credits, 20);
  });

  await t.test('admin oduzima kredite', async () => {
    const req = createMockReq({ targetUserId: 'reseller1', newCredits: 5 }, 'admin1:admin:a@test.com');
    const res = await adminCreditsRoute(req);
    assert.strictEqual(res.status, 200);
    assert.strictEqual(mockState.users.get('reseller1').credits, 5);
  });

  await t.test('paralelne promjene kredita bez izgubljenog updatea (concurrency)', async () => {
    const req1 = createMockReq({ targetUserId: 'reseller1', newCredits: 15 }, 'admin1:admin:a@test.com');
    const req2 = createMockReq({ targetUserId: 'reseller1', newCredits: 25 }, 'admin1:admin:a@test.com');
    await Promise.all([adminCreditsRoute(req1), adminCreditsRoute(req2)]);
    const c = mockState.users.get('reseller1').credits;
    assert.ok(c === 15 || c === 25);
  });

  await t.test('nedovoljan broj kredita pri aktivaciji', async () => {
    const req = createMockReq({ deviceId: 'dev1', licenseType: '1_year' }, 'reseller2:reseller:r@test.com');
    const res = await resellerActivateRoute(req);
    assert.strictEqual(res.status, 400);
  });

  await t.test('tuđa licenca (pokušaj produženja tuđe)', async () => {
    mockState.licenses.set('dev_tuda', { resellerId: 'other_reseller', status: 'Active', expiresAt: new Date() });
    const req = createMockReq({ deviceId: 'dev_tuda', licenseType: '1_year' }, 'reseller1:reseller:r@test.com');
    const res = await resellerActivateRoute(req);
    assert.strictEqual(res.status, 403);
  });

  await t.test('dvostruka aktivacija bez ponovne naplate', async () => {
    const req = createMockReq({ deviceId: 'dev1', licenseType: '1_year' }, 'reseller1:reseller:r@test.com');
    await resellerActivateRoute(req);
    assert.strictEqual(mockState.users.get('reseller1').credits, 9); // Charge 1
    const req2 = createMockReq({ deviceId: 'dev1', licenseType: '1_year' }, 'reseller1:reseller:r@test.com');
    await resellerActivateRoute(req2);
    assert.strictEqual(mockState.users.get('reseller1').credits, 9); // No extra charge
  });

  await t.test('izračun jednogodišnjeg isteka na serveru', async () => {
    const req = createMockReq({ deviceId: 'devx', licenseType: '1_year' }, 'reseller1:reseller:r@test.com');
    await resellerActivateRoute(req);
    const lic = mockState.licenses.get('devx');
    assert.strictEqual(mockState.users.get('reseller1').credits, 9);
    assert.ok(lic.expiresAt);
    const msInYear = 365 * 24 * 60 * 60 * 1000;
    const diff = lic.expiresAt.getTime() - Date.now();
    assert.ok(diff > msInYear - 10000 && diff <= msInYear + 10000); // 1 year diff
  });

  await t.test('obnova produžuje postojeći budući rok umjesto skraćivanja od danas', async () => {
    const previousExpiry = new Date();
    previousExpiry.setMonth(previousExpiry.getMonth() + 6);
    mockState.licenses.set('dev_renew', {
      resellerId: 'reseller1',
      status: 'Active',
      isLifetime: false,
      expiresAt: previousExpiry,
      updatedAt: { toMillis: () => Date.now() - 10 * 60 * 1000 },
    });
    const req = createMockReq({ deviceId: 'dev_renew', licenseType: '1_year' }, 'reseller1:reseller:r@test.com');
    const res = await resellerActivateRoute(req);
    assert.strictEqual(res.status, 200);
    const renewed = mockState.licenses.get('dev_renew').expiresAt;
    assert.ok(renewed.getTime() > previousExpiry.getTime() + 364 * 24 * 60 * 60 * 1000);
  });

  await t.test('lifetime aktivaciju bez klijentskog expiresAt', async () => {
    const req = createMockReq({ deviceId: 'dev_life', licenseType: 'lifetime' }, 'reseller1:reseller:r@test.com');
    await resellerActivateRoute(req);
    const lic = mockState.licenses.get('dev_life');
    assert.strictEqual(lic.isLifetime, true);
    assert.strictEqual(lic.expiresAt, null);
    assert.strictEqual(mockState.users.get('reseller1').credits, 8);
  });

  await t.test('aktivna trajna licenca ne može se skratiti na godinu dana', async () => {
    mockState.licenses.set('dev_lifetime_no_downgrade', {
      resellerId: 'reseller1', status: 'Active', isLifetime: true, expiresAt: null
    });
    const res = await resellerActivateRoute(createMockReq({
      deviceId: 'dev_lifetime_no_downgrade', licenseType: '1_year'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 409);
    assert.strictEqual(mockState.users.get('reseller1').credits, 10);
    assert.strictEqual(mockState.licenses.get('dev_lifetime_no_downgrade').isLifetime, true);
  });

  await t.test('reseller trial dobiva serverski početak i istek za tri dana', async () => {
    const req = createMockReq({ deviceId: 'dev_trial_expiry', licenseType: 'trial' }, 'reseller1:reseller:r@test.com');
    const res = await resellerActivateRoute(req);
    assert.strictEqual(res.status, 200);
    const lic = mockState.licenses.get('dev_trial_expiry');
    assert.ok(lic.trialStartedAt);
    assert.ok(lic.expiresAt instanceof Date);
    const threeDays = 3 * 24 * 60 * 60 * 1000;
    assert.ok(lic.expiresAt.getTime() - Date.now() > threeDays - 10_000);
  });

  await t.test('ponovno stvaranje triala (overwrite zaštita)', async () => {
    const req = createMockReq({ deviceId: 'dev_trial_rep' }, 'reseller1:reseller:r@test.com');
    await connectRoute(req);
    const res = await connectRoute(req);
    assert.strictEqual(res.status, 201);
    const body = await res.json();
    assert.strictEqual(body.message, 'Trial already exists');
  });

  await t.test('reseller ne može obnoviti postojeći ili istekli trial', async () => {
    const expiredAt = new Date(Date.now() - 1000);
    mockState.licenses.set('dev_trial_no_reset', {
      resellerId: 'self_registered', status: 'Trial', isLifetime: false,
      trialStartedAt: new Date(Date.now() - 4 * 24 * 60 * 60 * 1000), expiresAt: expiredAt
    });
    const res = await resellerActivateRoute(createMockReq({
      deviceId: 'dev_trial_no_reset', licenseType: 'trial'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 200);
    assert.strictEqual(mockState.licenses.get('dev_trial_no_reset').expiresAt, expiredAt);
    assert.strictEqual(mockState.users.get('reseller1').credits, 10);
  });

  await t.test('/api/connect odbija nepotpune pristupne podatke', async () => {
    const res = await connectRoute(createMockReq({ deviceId: 'partial-config', portalUrl: 'https://tv.example' }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 400);
    assert.strictEqual(mockState.licenses.has('partial-config'), false);
  });

  await t.test('/api/log nepoznata akciju', async () => {
    const req = createMockReq({ action: 'UNKNOWN' }, 'reseller1:reseller:r@test.com');
    const res = await logRoute(req);
    assert.strictEqual(res.status, 400);
  });

  await t.test('/api/log prevelike ili ugniježđene metapodatke', async () => {
    const req = createMockReq({ action: 'LOGIN', details: { test: 1 } }, 'reseller1:reseller:r@test.com');
    const res = await logRoute(req);
    assert.strictEqual(res.status, 400);
  });

  await t.test('/api/log zabrana spoofanja actorUid', async () => {
    // API naturally uses authContext.uid from token, not body payload, so we test if sending userId in body overrides it
    // Wait, the body schema doesn't even accept userId. So it will be a 400 invalid payload, or ignored.
    const req = createMockReq({ action: 'LOGIN', userId: 'admin1' }, 'reseller1:reseller:r@test.com');
    const res = await logRoute(req);
    assert.strictEqual(res.status, 400); // Strict schema prevents extra fields
  });

  await t.test('zaštitu posljednjeg aktivnog administratora', async () => {
    const req = createMockReq({ uid: 'admin1', status: 'suspended' }, 'admin1:admin:a@test.com');
    const res = await authUsersRoute(req);
    assert.strictEqual(res.status, 400);
  });

  await t.test('admin suspendira i deaktivira resellera kroz službeni payload', async () => {
    let res = await authUsersRoute(createMockReq({ uid: 'reseller1', status: 'suspended' }, 'admin1:admin:a@test.com'));
    assert.strictEqual(res.status, 200);
    assert.strictEqual(mockState.users.get('reseller1').status, 'suspended');
    assert.strictEqual(mockState.users.get('reseller1').disabled, true);

    res = await authUsersRoute(createMockReq({ uid: 'reseller1', status: 'deactivated' }, 'admin1:admin:a@test.com'));
    assert.strictEqual(res.status, 200);
    assert.strictEqual(mockState.users.get('reseller1').status, 'deactivated');
  });

  await t.test('bulk produženje je transakcijsko i produžuje budući rok', async () => {
    const future = new Date();
    future.setMonth(future.getMonth() + 4);
    mockState.licenses.set('bulk-1', { resellerId: 'reseller1', status: 'Active', isLifetime: false, expiresAt: future });
    mockState.licenses.set('bulk-2', { resellerId: 'reseller1', status: 'Expired', isLifetime: false, expiresAt: new Date(Date.now() - 1000) });
    const req = createMockReq({ requestId: '11111111-1111-4111-8111-111111111111', licenseIds: ['bulk-1', 'bulk-2'] }, 'reseller1:reseller:r@test.com');
    const res = await resellerBulkExtendRoute(req);
    assert.strictEqual(res.status, 200);
    assert.strictEqual(mockState.users.get('reseller1').credits, 8);
    assert.ok(mockState.licenses.get('bulk-1').expiresAt.getTime() > future.getTime() + 364 * 24 * 60 * 60 * 1000);
    assert.ok(mockState.licenses.get('bulk-2').expiresAt.getTime() > Date.now() + 364 * 24 * 60 * 60 * 1000);
    assert.strictEqual(mockState.activity_logs.size, 1);
  });

  await t.test('ponovljeni bulk zahtjev ne naplaćuje kredite dvaput', async () => {
    mockState.licenses.set('bulk-idempotent', { resellerId: 'reseller1', status: 'Active', isLifetime: false, expiresAt: new Date() });
    const body = { requestId: '22222222-2222-4222-8222-222222222222', licenseIds: ['bulk-idempotent'] };
    let res = await resellerBulkExtendRoute(createMockReq(body, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 200);
    res = await resellerBulkExtendRoute(createMockReq(body, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 200);
    assert.strictEqual(mockState.users.get('reseller1').credits, 9);
    assert.strictEqual((await res.json()).idempotent, true);

    mockState.licenses.set('bulk-other', { resellerId: 'reseller1', status: 'Active', isLifetime: false, expiresAt: new Date() });
    res = await resellerBulkExtendRoute(createMockReq({ ...body, licenseIds: ['bulk-other'] }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 409);
    assert.strictEqual(mockState.users.get('reseller1').credits, 9);
  });

  await t.test('bulk produženje odbija tuđu i lifetime licencu bez naplate', async () => {
    mockState.licenses.set('bulk-foreign', { resellerId: 'reseller2', status: 'Active', isLifetime: false });
    let res = await resellerBulkExtendRoute(createMockReq({ requestId: '33333333-3333-4333-8333-333333333333', licenseIds: ['bulk-foreign'] }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 403);
    assert.strictEqual(mockState.users.get('reseller1').credits, 10);

    mockState.licenses.set('bulk-lifetime', { resellerId: 'reseller1', status: 'Active', isLifetime: true });
    res = await resellerBulkExtendRoute(createMockReq({ requestId: '44444444-4444-4444-8444-444444444444', licenseIds: ['bulk-lifetime'] }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 400);
    assert.strictEqual(mockState.users.get('reseller1').credits, 10);
  });

  await t.test('bulk brisanje je atomsko i zapisuje audit', async () => {
    mockState.licenses.set('delete-1', { resellerId: 'reseller1', status: 'Active' });
    mockState.licenses.set('delete-2', { resellerId: 'reseller1', status: 'Expired' });
    const body = { requestId: '55555555-5555-4555-8555-555555555555', licenseIds: ['delete-2', 'delete-1'] };
    const res = await resellerBulkDeleteRoute(createMockReq(body, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 200);
    assert.strictEqual(mockState.licenses.has('delete-1'), false);
    assert.strictEqual(mockState.licenses.has('delete-2'), false);
    assert.strictEqual(mockState.activity_logs.size, 1);
    assert.ok(mockState.transactions.has('bulk-delete-reseller1-55555555-5555-4555-8555-555555555555'));
    assert.deepStrictEqual((await res.json()).deletedIds, ['delete-1', 'delete-2']);
  });

  await t.test('ponovljeni bulk delete zahtjev je idempotentan', async () => {
    mockState.licenses.set('delete-idempotent', { resellerId: 'reseller1', status: 'Active' });
    const body = { requestId: '66666666-6666-4666-8666-666666666666', licenseIds: ['delete-idempotent'] };
    let res = await resellerBulkDeleteRoute(createMockReq(body, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 200);
    res = await resellerBulkDeleteRoute(createMockReq(body, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 200);
    assert.strictEqual((await res.json()).idempotent, true);
    assert.strictEqual(mockState.activity_logs.size, 1);

    mockState.licenses.set('delete-other', { resellerId: 'reseller1', status: 'Active' });
    res = await resellerBulkDeleteRoute(createMockReq({ ...body, licenseIds: ['delete-other'] }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 409);
    assert.strictEqual(mockState.licenses.has('delete-other'), true);
  });

  await t.test('bulk brisanje odbija tuđe i nepostojeće licence bez djelomičnog brisanja', async () => {
    mockState.licenses.set('delete-owned', { resellerId: 'reseller1', status: 'Active' });
    mockState.licenses.set('delete-foreign', { resellerId: 'reseller2', status: 'Active' });
    let res = await resellerBulkDeleteRoute(createMockReq({
      requestId: '77777777-7777-4777-8777-777777777777',
      licenseIds: ['delete-owned', 'delete-foreign']
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 403);
    assert.strictEqual(mockState.licenses.has('delete-owned'), true);
    assert.strictEqual(mockState.licenses.has('delete-foreign'), true);

    res = await resellerBulkDeleteRoute(createMockReq({
      requestId: '88888888-8888-4888-8888-888888888888',
      licenseIds: ['delete-owned', 'delete-missing']
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 404);
    assert.strictEqual(mockState.licenses.has('delete-owned'), true);
  });

  await t.test('bulk brisanje zahtijeva aktivnog resellera', async () => {
    const body = { requestId: '99999999-9999-4999-8999-999999999999', licenseIds: ['delete-1'] };
    let res = await resellerBulkDeleteRoute(createMockReq(body));
    assert.strictEqual(res.status, 401);
    res = await resellerBulkDeleteRoute(createMockReq(body, 'admin1:admin:a@test.com'));
    assert.strictEqual(res.status, 403);
  });

  await t.test('bulk brisanje odbija duplikate i dodatna polja', async () => {
    let res = await resellerBulkDeleteRoute(createMockReq({
      requestId: 'aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa',
      licenseIds: ['duplicate', 'duplicate']
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 400);
    res = await resellerBulkDeleteRoute(createMockReq({
      requestId: 'bbbbbbbb-bbbb-4bbb-8bbb-bbbbbbbbbbbb',
      licenseIds: ['delete-1'],
      resellerId: 'reseller2'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(res.status, 400);
  });

  await t.test('reseller kreira subsellera i početni prijenos kredita je atomski evidentiran', async () => {
    mockState.users.set('reseller1', { ...mockState.users.get('reseller1'), assignedDomains: ['https://proservers.club', 'https://tvgpm.net'], customDomains: [] });
    const response = await createSubsellerRoute(createMockReq({
      email: 'subseller1@vopoapp.com', password: 'Sigurna#921', credits: 4,
      assignedDomains: ['https://proservers.club']
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 201);
    const payload = await response.json();
    const child = mockState.users.get(payload.uid);
    assert.strictEqual(payload.parentCredits, 6);
    assert.strictEqual(mockState.users.get('reseller1').credits, 6);
    assert.strictEqual(child.role, 'subseller');
    assert.strictEqual(child.parentResellerId, 'reseller1');
    assert.strictEqual(child.credits, 4);
    assert.deepStrictEqual(child.assignedDomains, ['https://proservers.club']);
    assert.strictEqual(mockState.transactions.size, 1);
    assert.strictEqual(mockState.activity_logs.size, 1);

    const list = await listSubsellersRoute(createMockReq({}, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(list.status, 200);
    const listed = await list.json();
    assert.strictEqual(listed.subsellers.length, 1);
    assert.strictEqual(listed.subsellers[0].email, 'subseller1@vopoapp.com');
  });

  await t.test('kreiranje subsellera odbija prekoračenje balansa i neodobrenu domenu bez ostataka', async () => {
    mockState.users.set('reseller1', { ...mockState.users.get('reseller1'), assignedDomains: ['https://proservers.club'], customDomains: [] });
    let response = await createSubsellerRoute(createMockReq({
      email: 'too-many@vopoapp.com', password: 'Sigurna#921', credits: 11, assignedDomains: []
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 409);
    assert.strictEqual(mockState.users.get('reseller1').credits, 10);
    assert.strictEqual(mockState.authUsers.size, 0);

    response = await createSubsellerRoute(createMockReq({
      email: 'foreign-domain@vopoapp.com', password: 'Sigurna#921', credits: 1,
      assignedDomains: ['https://nije-odobrena.example']
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 403);
    assert.strictEqual(mockState.users.get('reseller1').credits, 10);
    assert.strictEqual(mockState.authUsers.size, 0);
  });

  await t.test('korekcija subseller kredita prenosi i vraća točnu razliku uz logove', async () => {
    mockState.users.set('sub1', { email: 'sub1@vopoapp.com', role: 'subseller', parentResellerId: 'reseller1', status: 'active', credits: 4, assignedDomains: [] });
    let response = await updateSubsellerRoute(createMockReq({
      action: 'adjust_credits', targetUserId: 'sub1', newCredits: 7, reason: 'Dodatna narudžba'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 200);
    assert.strictEqual(mockState.users.get('reseller1').credits, 7);
    assert.strictEqual(mockState.users.get('sub1').credits, 7);

    response = await updateSubsellerRoute(createMockReq({
      action: 'adjust_credits', targetUserId: 'sub1', newCredits: 2, reason: 'Ispravak pogrešne dodjele'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 200);
    assert.strictEqual(mockState.users.get('reseller1').credits, 12);
    assert.strictEqual(mockState.users.get('sub1').credits, 2);
    assert.strictEqual(mockState.transactions.size, 2);
    assert.strictEqual(mockState.activity_logs.size, 2);
  });

  await t.test('reseller upravlja samo vlastitim subsellerom, odobrenim domenama i statusom', async () => {
    mockState.users.set('reseller1', { ...mockState.users.get('reseller1'), assignedDomains: ['https://proservers.club', 'https://tvgpm.net'], customDomains: [] });
    mockState.users.set('sub1', { email: 'sub1@vopoapp.com', role: 'subseller', parentResellerId: 'reseller1', status: 'active', credits: 1, assignedDomains: [] });
    mockState.users.set('foreign-sub', { role: 'subseller', parentResellerId: 'reseller2', status: 'active', credits: 1, assignedDomains: [] });
    let response = await updateSubsellerRoute(createMockReq({
      action: 'set_domains', targetUserId: 'sub1', assignedDomains: ['https://tvgpm.net']
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 200);
    assert.deepStrictEqual(mockState.users.get('sub1').assignedDomains, ['https://tvgpm.net']);

    response = await updateSubsellerRoute(createMockReq({
      action: 'set_domains', targetUserId: 'sub1', assignedDomains: ['https://foreign.example']
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 403);

    response = await updateSubsellerRoute(createMockReq({
      action: 'set_status', targetUserId: 'sub1', status: 'suspended'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 200);
    assert.strictEqual(mockState.users.get('sub1').status, 'suspended');
    assert.strictEqual(mockState.users.get('sub1').disabled, true);

    response = await updateSubsellerRoute(createMockReq({
      action: 'adjust_credits', targetUserId: 'foreign-sub', newCredits: 2, reason: 'Nedopušten pokušaj'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 404);
    assert.strictEqual(mockState.users.get('foreign-sub').credits, 1);
  });

  await t.test('subseller aktivira vlastitu liniju iz vlastitog balansa', async () => {
    mockState.users.set('sub1', {
      email: 'sub1@vopoapp.com', role: 'subseller', parentResellerId: 'reseller1',
      status: 'active', disabled: false, credits: 3, assignedDomains: ['https://proservers.club'], customDomains: []
    });
    const response = await resellerActivateRoute(createMockReq({
      deviceId: 'sub-device-1', licenseType: '1_year', selectedDomain: 'https://proservers.club',
      username: 'line-user', password: 'line-password'
    }, 'sub1:subseller:sub1@vopoapp.com'));
    assert.strictEqual(response.status, 200);
    assert.strictEqual(mockState.users.get('sub1').credits, 2);
    assert.strictEqual(mockState.users.get('reseller1').credits, 10);
    assert.strictEqual(mockState.licenses.get('sub-device-1').resellerId, 'sub1');
  });

  await t.test('prijenos lifetime licence i linije deaktivira stari uređaj i čuva token novog uređaja', async () => {
    mockState.licenses.set('old-lifetime', {
      deviceId: 'old-lifetime', resellerId: 'reseller1', status: 'Active', isLifetime: true,
      expiresAt: null, accessTokenHash: hashDeviceToken(DEVICE_TOKEN), selectedDomain: 'https://proservers.club',
      xtreamConfig: { url: 'https://proservers.club', username: 'line-user', password: 'line-pass' },
      customerName: 'Pretplatnik', customerContact: 'kontakt'
    });
    mockState.licenses.set('new-lifetime', {
      deviceId: 'new-lifetime', resellerId: 'self_registered', status: 'Trial', isLifetime: false,
      expiresAt: new Date(Date.now() + 60_000), accessTokenHash: hashDeviceToken(OTHER_DEVICE_TOKEN),
      selectedDomain: 'https://temporary.example'
    });

    const response = await transferLicenseRoute(createMockReq({
      requestId: '12121212-1212-4212-8212-121212121212', oldDeviceId: 'old-lifetime',
      newDeviceId: 'new-lifetime', mode: 'license_and_line'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 200);
    const migrated = mockState.licenses.get('new-lifetime');
    const source = mockState.licenses.get('old-lifetime');
    assert.strictEqual(migrated.isLifetime, true);
    assert.strictEqual(migrated.expiresAt, null);
    assert.strictEqual(migrated.accessTokenHash, hashDeviceToken(OTHER_DEVICE_TOKEN));
    assert.strictEqual(migrated.selectedDomain, 'https://proservers.club');
    assert.deepStrictEqual(migrated.xtreamConfig, { url: 'https://proservers.club', username: 'line-user', password: 'line-pass' });
    assert.strictEqual(source.status, 'Transferred');
    assert.strictEqual(source.transferredTo, 'new-lifetime');
    assert.strictEqual(mockState.transactions.size, 1);
    assert.strictEqual(mockState.activity_logs.size, 1);
    const oldDeviceResponse = await deviceLicenseRoute(createDeviceGet('old-lifetime', DEVICE_TOKEN));
    assert.strictEqual((await oldDeviceResponse.json()).status, 'unregistered');
    const newDeviceResponse = await deviceLicenseRoute(createDeviceGet('new-lifetime', OTHER_DEVICE_TOKEN));
    const newDeviceLicense = await newDeviceResponse.json();
    assert.strictEqual(newDeviceLicense.status, 'active');
    assert.strictEqual(newDeviceLicense.isLifetime, true);
    assert.deepStrictEqual(newDeviceLicense.config, { url: 'https://proservers.club', username: 'line-user', password: 'line-pass' });
  });

  await t.test('subseller prenosi samo vlastitu vremensku licencu uz isti datum isteka', async () => {
    mockState.users.set('sub1', {
      email: 'sub1@vopoapp.com', role: 'subseller', parentResellerId: 'reseller1',
      status: 'active', disabled: false, credits: 1
    });
    const exactExpiry = new Date(Date.now() + 120 * 24 * 60 * 60 * 1000 + 12_345);
    mockState.licenses.set('sub-old', {
      deviceId: 'sub-old', resellerId: 'sub1', status: 'Active', isLifetime: false,
      expiresAt: exactExpiry, selectedDomain: 'https://source.example',
      xtreamConfig: { url: 'https://source.example', username: 'source', password: 'source-pass' }
    });
    mockState.licenses.set('sub-new', {
      deviceId: 'sub-new', resellerId: 'sub1', status: 'Trial', isLifetime: false,
      expiresAt: new Date(Date.now() + 60_000), accessTokenHash: 'destination-secret',
      selectedDomain: 'https://destination.example', customerName: 'Novi uređaj',
      xtreamConfig: { url: 'https://destination.example', username: 'destination', password: 'destination-pass' }
    });

    const response = await transferLicenseRoute(createMockReq({
      requestId: '23232323-2323-4232-8232-232323232323', oldDeviceId: 'sub-old',
      newDeviceId: 'sub-new', mode: 'license_only'
    }, 'sub1:subseller:sub1@vopoapp.com'));
    assert.strictEqual(response.status, 200);
    const migrated = mockState.licenses.get('sub-new');
    assert.strictEqual(migrated.expiresAt.getTime(), exactExpiry.getTime());
    assert.strictEqual(migrated.selectedDomain, 'https://destination.example');
    assert.strictEqual(migrated.customerName, 'Novi uređaj');
    assert.strictEqual(migrated.xtreamConfig.username, 'destination');
    assert.strictEqual(migrated.accessTokenHash, 'destination-secret');
    assert.strictEqual(migrated.resellerId, 'sub1');
    assert.strictEqual(mockState.users.get('reseller1').credits, 10);
  });

  await t.test('reseller ne može prenijeti tuđu licencu ili prepisati tuđi probni uređaj, admin može prenijeti bilo koju', async () => {
    const expiry = new Date(Date.now() + 30 * 24 * 60 * 60 * 1000);
    mockState.licenses.set('foreign-old', { resellerId: 'reseller2', status: 'Active', isLifetime: false, expiresAt: expiry });
    let response = await transferLicenseRoute(createMockReq({
      requestId: '34343434-3434-4434-8434-343434343434', oldDeviceId: 'foreign-old',
      newDeviceId: 'foreign-new', mode: 'license_only'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 403);
    assert.strictEqual(mockState.licenses.has('foreign-new'), false);

    mockState.licenses.set('owned-old', { resellerId: 'reseller1', status: 'Active', isLifetime: false, expiresAt: expiry });
    mockState.licenses.set('foreign-trial', { resellerId: 'reseller2', status: 'Trial', isLifetime: false, expiresAt: expiry });
    response = await transferLicenseRoute(createMockReq({
      requestId: '45454545-4545-4454-8454-454545454545', oldDeviceId: 'owned-old',
      newDeviceId: 'foreign-trial', mode: 'license_only'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 403);
    assert.strictEqual(mockState.licenses.get('owned-old').status, 'Active');

    response = await transferLicenseRoute(createMockReq({
      requestId: '56565656-5656-4565-8565-565656565656', oldDeviceId: 'foreign-old',
      newDeviceId: 'admin-new', mode: 'license_only'
    }, 'admin1:admin:a@test.com'));
    assert.strictEqual(response.status, 200);
    assert.strictEqual(mockState.licenses.get('foreign-old').status, 'Transferred');
    assert.strictEqual(mockState.licenses.get('admin-new').resellerId, 'reseller2');
  });

  await t.test('prijenos je idempotentan te odbija istekle licence i zauzeti novi uređaj', async () => {
    const request = {
      requestId: '67676767-6767-4676-8676-676767676767', oldDeviceId: 'idempotent-old',
      newDeviceId: 'idempotent-new', mode: 'license_only'
    };
    mockState.licenses.set('idempotent-old', {
      resellerId: 'reseller1', status: 'Active', isLifetime: false,
      expiresAt: new Date(Date.now() + 86_400_000)
    });
    let response = await transferLicenseRoute(createMockReq(request, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 200);
    response = await transferLicenseRoute(createMockReq(request, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 200);
    assert.strictEqual((await response.json()).idempotent, true);
    assert.strictEqual(mockState.transactions.size, 1);
    assert.strictEqual(mockState.activity_logs.size, 1);

    mockState.licenses.set('expired-old', {
      resellerId: 'reseller1', status: 'Active', isLifetime: false,
      expiresAt: new Date(Date.now() - 1)
    });
    response = await transferLicenseRoute(createMockReq({
      requestId: '78787878-7878-4787-8787-787878787878', oldDeviceId: 'expired-old',
      newDeviceId: 'expired-new', mode: 'license_only'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 409);

    mockState.licenses.set('another-old', {
      resellerId: 'reseller1', status: 'Active', isLifetime: false,
      expiresAt: new Date(Date.now() + 86_400_000)
    });
    mockState.licenses.set('occupied-new', { resellerId: 'reseller1', status: 'Active', isLifetime: true });
    response = await transferLicenseRoute(createMockReq({
      requestId: '89898989-8989-4898-8898-898989898989', oldDeviceId: 'another-old',
      newDeviceId: 'occupied-new', mode: 'license_and_line'
    }, 'reseller1:reseller:r@test.com'));
    assert.strictEqual(response.status, 409);
    assert.strictEqual(mockState.licenses.get('another-old').status, 'Active');
  });

  // Rollback tests
  await t.test('Rollback: Firestore promjena uspije, Auth/custom-claims promjena ne uspije', async () => {
    mockState.users.set('admin2', { role: 'admin', status: 'active', disabled: false });
    mockState.users.set('targetU2', { role: 'user', status: 'active', disabled: false });
    mockState.throwAuthError = true;
    const req = createMockReq({ uid: 'targetU2', status: 'suspended' }, 'admin1:admin:a@test.com');
    const res = await authUsersRoute(req);
    assert.strictEqual(res.status, 500);
    // Db should be rolled back to active
    assert.strictEqual(mockState.users.get('targetU2').status, 'active');
  });

  await t.test('Atomski kredit-licenca-audit rollback: Auth error cancels everything', async () => {
    // We mock throwing an error during transaction/write in activate route
    mockState.throwDbError = true;
    const req = createMockReq({ deviceId: 'dev_atomic', licenseType: '1_year' }, 'reseller1:reseller:r@test.com');
    const res = await resellerActivateRoute(req);
    assert.strictEqual(res.status, 500);
    // User credits should not have decreased
    assert.strictEqual(mockState.users.get('reseller1').credits, 10);
    assert.strictEqual(mockState.licenses.has('dev_atomic'), false);
  });

  // Rate Limiting mock tests
  await t.test('Rate limit (fallback) 429 i ispravan Retry-After', async () => {
    const req = createMockReq({ action: 'LOGIN' }, 'reseller1:reseller:r@test.com');
    let lastRes;
    for (let i = 0; i < 11; i++) {
      lastRes = await logRoute(req);
    }
    assert.strictEqual(lastRes!.status, 429);
    assert.ok(Number(lastRes!.headers.get('Retry-After')) > 0);
  });

  await t.test('Rate limit za registriranu aktivaciju vraća 429 i ispravan Retry-After', async () => {
    const req = createMockReq({ deviceId: 'dev_ratelimit' }, 'reseller1:reseller:r@test.com', '10.0.0.1');
    let lastRes;
    for (let i = 0; i < 11; i++) {
      lastRes = await connectRoute(req);
    }
    assert.strictEqual(lastRes!.status, 429);
    assert.ok(Number(lastRes!.headers.get('Retry-After')) > 0);
  });

  await t.test('Rate limit spoofani proxy header ne omogućuje zaobilaženje limita (zato što koristimo x-real-ip)', async () => {
    // If x-real-ip is missing but x-forwarded-for is spoofed, Vercel overwrites x-forwarded-for.
    // We already use x-real-ip first. In this test environment, createMockReq returns the passed ip for x-forwarded-for and x-real-ip.
    const req1 = {
      headers: { get: (k: string) => k.toLowerCase() === 'authorization' ? 'Bearer reseller1:reseller:r@test.com' : (k === 'x-real-ip' ? '1.1.1.1' : (k === 'x-forwarded-for' ? 'spoof, 1.1.1.1' : null)) },
      json: async () => ({ deviceId: 'dev_sp1' })
    };
    for (let i = 0; i < 11; i++) await connectRoute(req1 as any);
    const res1 = await connectRoute(req1 as any);
    assert.strictEqual(res1.status, 429); // IP blocked
  });

  await t.test('Rate limit produkcija bez Redisa koristi distribuirani Firestore fallback', async () => {
    const originalEnv = process.env.NODE_ENV;
    process.env.NODE_ENV = 'production';
    const req = createMockReq({ deviceId: 'dev_prod_test' }, 'reseller1:reseller:r@test.com');
    const res = await connectRoute(req);
    assert.strictEqual(res.status, 201);
    assert.strictEqual(mockState.rate_limits.size, 2);
    process.env.NODE_ENV = originalEnv;
  });

  await t.test('Rate limit pogreška ne otkriva podatke infrastrukture', async () => {
    const originalEnv = process.env.NODE_ENV;
    process.env.NODE_ENV = 'production';
    mockState.throwDbError = true;
    const req = createMockReq({ deviceId: 'dev_prod_test' }, 'reseller1:reseller:r@test.com');
    const res = await connectRoute(req);
    const body = await res.json();
    assert.ok(!JSON.stringify(body).includes('redis'));
    assert.strictEqual(res.status, 500);
    mockState.throwDbError = false;
    process.env.NODE_ENV = originalEnv;
  });
});
