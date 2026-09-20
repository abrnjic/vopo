/* eslint-disable @typescript-eslint/no-unused-vars */
import test from 'node:test';
import assert from 'node:assert';
import { NextRequest } from 'next/server';

process.env.MOCK_FIREBASE = 'true';

import { mockState } from '../src/lib/mockFirebaseAdmin';
import { POST as connectRoute } from '../src/app/api/connect/route';
import { POST as authUsersRoute } from '../src/app/api/admin/users/route';
import { POST as resellerActivateRoute } from '../src/app/api/reseller/activate/route';
import { POST as logRoute } from '../src/app/api/log/route';
import { POST as adminCreditsRoute } from '../src/app/api/admin/credits/route';
import { POST as deviceRegisterRoute } from '../src/app/api/device/register/route';
import { GET as deviceLicenseRoute } from '../src/app/api/device/license/route';

import { checkRateLimit, resetFallbackCache } from '../src/lib/rateLimit';

const createMockReq = (body: any, token?: string, ip?: string) => {
  return {
    headers: {
      get: (key: string) => {
        if (key.toLowerCase() === 'authorization') return token ? `Bearer ${token}` : null;
        if (key.toLowerCase() === 'x-forwarded-for') return ip || '127.0.0.1';
        return null;
      }
    },
    json: async () => body
  } as any;
};

const DEVICE_TOKEN = 'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA';
const OTHER_DEVICE_TOKEN = 'BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB';
const createDeviceGet = (deviceId: string, token = DEVICE_TOKEN) => new NextRequest(
  `http://localhost/api/device/license?deviceId=${encodeURIComponent(deviceId)}`,
  { headers: { Authorization: `Device ${token}` } }
) as any;

test('API P0 Tests', async (t) => {
  t.beforeEach(() => {
    resetFallbackCache();
    mockState.users.clear();
    mockState.licenses.clear();
    mockState.transactions.clear();
    mockState.activity_logs.clear();
    mockState.rate_limits.clear();
    mockState.throwAuthError = false;
    mockState.throwDbError = false;

    mockState.users.set('admin1', { role: 'admin', status: 'active', disabled: false });
    mockState.users.set('reseller1', { role: 'reseller', status: 'active', disabled: false, credits: 10 });
    mockState.users.set('reseller2', { role: 'reseller', status: 'active', disabled: false, credits: 0 });
  });

  await t.test('valjan prvi /api/connect', async () => {
    const req = createMockReq({ deviceId: 'dev1' });
    const res = await connectRoute(req);
    assert.strictEqual(res.status, 201);
    const lic = mockState.licenses.get('dev1');
    assert.strictEqual(lic.status, 'Trial');
  });

  await t.test('uređaj registrira tajni token prije aktivacije i jedini čita licencu', async () => {
    const registration = await deviceRegisterRoute(createMockReq({ deviceId: 'SEC-URE-001', deviceToken: DEVICE_TOKEN }) as any);
    assert.strictEqual(registration.status, 200);

    await connectRoute(createMockReq({ deviceId: 'SEC-URE-001', portalUrl: 'https://tv.example', username: 'u', password: 'p' }));
    const response = await deviceLicenseRoute(createDeviceGet('SEC-URE-001'));
    assert.strictEqual(response.status, 200);
    const body = await response.json();
    assert.strictEqual(body.status, 'trial');
    assert.deepStrictEqual(body.config, { url: 'https://tv.example', username: 'u', password: 'p' });

    const denied = await deviceLicenseRoute(createDeviceGet('SEC-URE-001', OTHER_DEVICE_TOKEN));
    assert.strictEqual(denied.status, 401);
  });

  await t.test('drugi token ne može preuzeti već registrirani uređaj', async () => {
    await deviceRegisterRoute(createMockReq({ deviceId: 'SEC-URE-002', deviceToken: DEVICE_TOKEN }) as any);
    const takeover = await deviceRegisterRoute(createMockReq({ deviceId: 'SEC-URE-002', deviceToken: OTHER_DEVICE_TOKEN }) as any);
    assert.strictEqual(takeover.status, 409);
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
    const req = createMockReq({ deviceId: 'dev1', badField: true });
    const res = await connectRoute(req);
    assert.strictEqual(res.status, 400);
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
    const req = createMockReq({ deviceId: 'dev_trial_rep' });
    await connectRoute(req);
    const res = await connectRoute(req);
    assert.strictEqual(res.status, 201);
    const body = await res.json();
    assert.strictEqual(body.message, 'Trial already exists'); // Success but no overwrite
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

  await t.test('Rate limit public 429 i ispravan Retry-After', async () => {
    const req = createMockReq({ deviceId: 'dev_ratelimit' }, undefined, '10.0.0.1');
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
      headers: { get: (k: string) => k === 'x-real-ip' ? '1.1.1.1' : (k === 'x-forwarded-for' ? 'spoof, 1.1.1.1' : null) },
      json: async () => ({ deviceId: 'dev_sp1' })
    };
    for (let i = 0; i < 11; i++) await connectRoute(req1 as any);
    const res1 = await connectRoute(req1 as any);
    assert.strictEqual(res1.status, 429); // IP blocked
  });

  await t.test('Rate limit produkcija bez Redisa koristi distribuirani Firestore fallback', async () => {
    const originalEnv = process.env.NODE_ENV;
    process.env.NODE_ENV = 'production';
    const req = createMockReq({ deviceId: 'dev_prod_test' });
    const res = await connectRoute(req);
    assert.strictEqual(res.status, 201);
    assert.strictEqual(mockState.rate_limits.size, 2);
    process.env.NODE_ENV = originalEnv;
  });

  await t.test('Rate limit pogreška ne otkriva podatke infrastrukture', async () => {
    const originalEnv = process.env.NODE_ENV;
    process.env.NODE_ENV = 'production';
    mockState.throwDbError = true;
    const req = createMockReq({ deviceId: 'dev_prod_test' });
    const res = await connectRoute(req);
    const body = await res.json();
    assert.ok(!JSON.stringify(body).includes('redis'));
    assert.strictEqual(res.status, 503);
    mockState.throwDbError = false;
    process.env.NODE_ENV = originalEnv;
  });
});
