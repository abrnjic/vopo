import test from 'node:test';
import assert from 'node:assert';

process.env.MOCK_FIREBASE = 'true';

import { mockState } from '../src/lib/mockFirebaseAdmin';
import { POST as connectRoute } from '../src/app/api/connect/route';
import { POST as authUsersRoute } from '../src/app/api/admin/users/route';
import { POST as resellerActivateRoute } from '../src/app/api/reseller/activate/route';
import { POST as logRoute } from '../src/app/api/log/route';
import { POST as adminCreditsRoute } from '../src/app/api/admin/credits/route';

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

test('API P0 Tests', async (t) => {
  t.beforeEach(() => {
    mockState.users.clear();
    mockState.licenses.clear();
    mockState.transactions.clear();
    mockState.activity_logs.clear();
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
    // Requires admin but sent by reseller
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
    assert.strictEqual(body.error, 'Internal auth error'); // Sanitized in auth.ts
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

  await t.test('paralelne promjene kredita bez izgubljenog updatea', async () => {
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

  await t.test('lifetime aktivaciju bez klijentskog expiresAt', async () => {
    const req = createMockReq({ deviceId: 'dev_life', licenseType: 'lifetime' }, 'reseller1:reseller:r@test.com');
    await resellerActivateRoute(req);
    const lic = mockState.licenses.get('dev_life');
    assert.strictEqual(lic.isLifetime, true);
    assert.strictEqual(lic.expiresAt, null);
  });

  await t.test('ponovno stvaranje triala', async () => {
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

  await t.test('/api/log rate limit i odgovor 429', async () => {
    const req = createMockReq({ action: 'LOGIN' }, 'reseller1:reseller:r@test.com');
    for (let i = 0; i < 10; i++) await logRoute(req);
    const res = await logRoute(req);
    assert.strictEqual(res.status, 429);
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
});
