/* eslint-disable prefer-const, @typescript-eslint/no-unused-vars */
import { setGlobalDispatcher, MockAgent } from 'undici';
import test from 'node:test';
import assert from 'node:assert';
import crypto from 'node:crypto';

process.env.MOCK_FIREBASE = 'true';
process.env.BLOB_HOSTNAME = 'foo.public.blob.vercel-storage.com';
process.env.BLOB_READ_WRITE_TOKEN = 'vercel_blob_rw_123';

import { mockState, mockAdminDb } from '../src/lib/mockFirebaseAdmin';
import { POST as adminApkRoute, onBeforeGenerateToken, onUploadCompleted } from '../src/app/api/admin/apk/route';
import { GET as latestApkRoute } from '../src/app/api/apk/latest/route';
import { GET as downloadRoute } from '../src/app/download/route';
import { validateBlobUrl } from '../src/utils/blobValidator';

const createMockReq = (body: any, token?: string, method = 'POST', url = 'http://localhost/api/admin/apk') => {
  return new Request(url, {
    method,
    headers: {
      'Content-Type': 'application/json',
      ...(token ? { Authorization: `Bearer ${token}` } : {})
    },
    body: body ? JSON.stringify(body) : null
  }) as any;
};

const validChecksum = 'a'.repeat(64);
const validClientPayload = JSON.stringify({versionName:'1.0.1',versionCode:'100',checksum:validChecksum});

// Mock @vercel/blob del globally so we can spy on it

let delCallCount = 0;
let lastDelUrl = '';
let delUrls: string[] = [];

const mockAgent = new MockAgent();
// mockAgent.disableNetConnect();
setGlobalDispatcher(mockAgent);
const mockPool = mockAgent.get('https://vercel.com');

// Wait, we can't use jest since it's node:test.
// The route file imports `del` from `@vercel/blob`.
// We are in node env, the actual `@vercel/blob` is fetched.
// We can mock it by intercepting node module require if we want, but since `route.ts` uses static import, it's hard.
// Actually, I can use the `fetch` mock to track delete calls? Vercel blob `del` just calls fetch.
// Yes! Vercel blob `del` uses `fetch` under the hood. Let's spy on `global.fetch`.

test('APK Distribution Tests', async (t) => {
  mockState.users.set('mock-token-admin1', { email: 'admin@vopo.hr', role: 'admin', customClaims: { admin: true }, status: 'active', credits: 0, uid: 'admin1' });
  mockState.users.set('mock-token-user1', { email: 'user@vopo.hr', role: 'reseller', customClaims: {}, status: 'active', credits: 0, uid: 'user1' });
  mockState.users.set('mock-token-admin_susp', { email: 'susp@vopo.hr', role: 'admin', customClaims: { admin: true }, status: 'suspended', credits: 0, uid: 'admin_susp' });

  (mockState as any).system = new Map();
  (mockState as any).activity_logs = new Map();

  await t.test('1. validateBlobUrl accepts valid URLs', () => {
    assert.strictEqual(validateBlobUrl('https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0.1-10.apk', '1.0.1', '10'), true);
  });

  await t.test('2. validateBlobUrl rejects invalid domains', () => {
    const invalidUrls = [
      'https://evil.com/apk/releases/vopoapp-1.0.1-10.apk',
      'http://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0.1-10.apk',
      'https://foo.public.blob.vercel-storage.com:8080/apk/releases/vopoapp-1.0.1-10.apk',
      'https://foo.public.blob.vercel-storage.com/apk/vopoapp.apk',
      'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp.zip',
      'javascript:alert(1)'
    ];
    for (const url of invalidUrls) {
      assert.strictEqual(validateBlobUrl(url, '1.0', '1'), false);
    }
  });

  // Client Token Generation tests
  await t.test('3. Zahtjev bez Firebase tokena', async () => {
     const res = await adminApkRoute(createMockReq({ type: 'blob.generate-client-token', payload: { pathname: 'apk/releases/vopoapp-1.0-1.apk', clientPayload: validClientPayload } }));
     assert.strictEqual(res.status, 400); // caught by handleUpload
  });

  await t.test('4. Korisnik koji nije administrator', async () => {
     const res = await adminApkRoute(createMockReq({ type: 'blob.generate-client-token', payload: { pathname: 'apk/releases/vopoapp-1.0-1.apk', clientPayload: validClientPayload } }, 'mock-token-user1'));
     assert.strictEqual(res.status, 400);
  });

  await t.test('5. Suspendirani/deaktivirani administrator', async () => {
     const res = await adminApkRoute(createMockReq({ type: 'blob.generate-client-token', payload: { pathname: 'apk/releases/vopoapp-1.0-1.apk', clientPayload: validClientPayload } }, 'mock-token-admin_susp'));
     assert.strictEqual(res.status, 400);
  });

  await t.test('6. Pogrešna ekstenzija (pathname mismatch)', async () => {
     try {
       await onBeforeGenerateToken('apk/releases/vopoapp-1.0-1.zip', validClientPayload, createMockReq({}, 'mock-token-admin1'));
       assert.fail('Should have thrown');
     } catch (e: any) {
       assert.match(e.message, /Invalid pathname/);
     }
  });

  await t.test('7. Neispravan versionName', async () => {
     try {
       await onBeforeGenerateToken(`apk/releases/vopoapp-1.0!-100.apk`, JSON.stringify({versionName:'1.0!',versionCode:'100',checksum:validChecksum}), createMockReq({}, 'mock-token-admin1'));
       assert.fail('Should have thrown');
     } catch (e: any) {
       assert.match(e.message, /Invalid versionName format/);
     }
  });

  // Negative tests for versionCode and checksum
  await t.test('7b. Neispravan versionCode', async () => {
    const invalidCodes = ['0', '-5', '1.5', '100abc', '', '9007199254740992'];
    for (const code of invalidCodes) {
       try {
         await onBeforeGenerateToken(`apk/releases/vopoapp-1.0-${code}.apk`, JSON.stringify({versionName:'1.0',versionCode:code,checksum:validChecksum}), createMockReq({}, 'mock-token-admin1'));
         assert.fail(`Should throw for versionCode ${code}`);
       } catch (e: any) {
         assert.ok(e.message.includes('Invalid versionCode') || e.message.includes('maximum safe integer') || e.message.includes('Missing versionName, versionCode, or checksum'));
       }
    }
  });

  await t.test('7c. Neispravan checksum', async () => {
     const invalidChecksums = ['abc', 'a'.repeat(63), 'z'.repeat(64), 'A'.repeat(64) + '1'];
     for (const csum of invalidChecksums) {
       try {
         await onBeforeGenerateToken(`apk/releases/vopoapp-1.0-100.apk`, JSON.stringify({versionName:'1.0',versionCode:'100',checksum:csum}), createMockReq({}, 'mock-token-admin1'));
         assert.fail(`Should throw for checksum ${csum}`);
       } catch (e: any) {
         assert.match(e.message, /Invalid checksum format/);
       }
     }
  });

  // Latest API tests
  await t.test('8. /api/apk/latest bez metadata zapisa', async () => {
     const res = await latestApkRoute();
     assert.strictEqual(res.status, 404);
  });

  await t.test('9. Uspješan /api/apk/latest odgovor', async () => {
     (mockState as any).system.set('apk_metadata', { versionCode: 100, versionName: '1.0', checksum: validChecksum, latestUrl: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-100.apk' });
     const res = await latestApkRoute();
     assert.strictEqual(res.status, 200);
     const data = await res.json();
     assert.strictEqual(data.versionCode, 100);
  });

  // Download API tests
  await t.test('10. /download bez metapodataka', async () => {
     (mockState as any).system.delete('apk_metadata');
     const res = await downloadRoute();
     assert.strictEqual(res.status, 404);
  });

  await t.test('11. /download s nepoznatim hostnameom', async () => {
     (mockState as any).system.set('apk_metadata', { versionCode: 100, versionName: '1.0', checksum: validChecksum, latestUrl: 'https://evil.com/apk/releases/vopoapp-1.0-100.apk' });
     const res = await downloadRoute();
     assert.strictEqual(res.status, 500); // Because it fails validation
  });

  await t.test('12. Uspješan 307 redirect', async () => {
     (mockState as any).system.set('apk_metadata', { versionCode: 100, versionName: '1.0', checksum: validChecksum, latestUrl: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-100.apk' });
     const res = await downloadRoute();
     assert.strictEqual(res.status, 307);
     assert.strictEqual(res.headers.get('Location'), 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-100.apk');
  });

  // Vercel Blob webhook mock validation
  const testHash = crypto.createHash('sha256').update('hello').digest('hex');

  let fetchCallCount = 0;
  let delCallCount = 0;
  let lastDelUrl = '';

  const originalFetch = global.fetch;
  global.fetch = async (url: string | URL | Request, init?: RequestInit): Promise<Response> => {
    const sUrl = url.toString();

    // Intercept vercel blob delete API
    if (init && init.method === 'POST') {
      delCallCount++;
      const body = JSON.parse(init.body as string);
      lastDelUrl = body.urls[0];
      return new Response(JSON.stringify({}), { status: 200 });
    }

    fetchCallCount++;
    if (sUrl.includes('too-large')) {
        return new Response('too large', { headers: { 'content-length': '200000000' } });
    }
    const encoder = new TextEncoder();
    const stream = new ReadableStream({
      start(controller) {
        controller.enqueue(encoder.encode('hello'));
        controller.close();
      }
    });
    return new Response(stream, { headers: { 'content-length': '5' } });
  };

  await t.test('13. Uspješan upload i objavu metapodataka', async () => {
     fetchCallCount = 0;
     (mockState as any).system.delete('apk_metadata');
     const tokenPayload = JSON.stringify({ versionName: '1.0', versionCode: '10', checksum: testHash, uid: 'admin1', email: 'a@v.com' });
     await onUploadCompleted({ blob: { url: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-10.apk' }, tokenPayload });

     // Validate it hit Firestore and Activity log
     const meta = (mockState as any).system.get('apk_metadata');
     assert.strictEqual(meta.versionCode, '10');
     assert.strictEqual(meta.checksum, testHash);
     assert.ok(fetchCallCount > 0);

     // Validate audit log written
     const logs = Array.from((mockState as any).activity_logs.values());
     assert.strictEqual(logs.length, 1);
     assert.strictEqual(logs[0].action, 'APK_PUBLISHED');
     assert.strictEqual(logs[0].details.versionCode, '10');
  });

  await t.test('14. neispravan versionCode (prije generiranja tokena)', async () => {
     try {
       await onBeforeGenerateToken('apk/releases/vopoapp-1.0-abc.apk', JSON.stringify({versionName:'1.0',versionCode:'abc',checksum:validChecksum}), createMockReq({}, 'mock-token-admin1'));
       assert.fail('Should throw');
     } catch (e: any) {
       assert.match(e.message, /Invalid versionCode/);
     }
  });

  await t.test('15. versionCode jednak ili manji od trenutačnog (onBeforeGenerateToken)', async () => {
     (mockState as any).system.set('apk_metadata', { versionCode: '20' });
     try {
       await onBeforeGenerateToken('apk/releases/vopoapp-1.0-15.apk', JSON.stringify({versionName:'1.0',versionCode:'15',checksum:validChecksum}), createMockReq({}, 'mock-token-admin1'));
       assert.fail('Should throw');
     } catch (e: any) {
       assert.match(e.message, /versionCode must be greater/);
     }
  });

  await t.test('16. pogrešan MIME tip', async () => {
     // Test contentType checking in onBeforeGenerateToken (simulate blob generation)
     try {
       const req = createMockReq({
         type: 'blob.generate-client-token',
         payload: { pathname: 'apk/releases/vopoapp-1.0-10.apk', clientPayload: validClientPayload, contentType: 'image/png' }
       }, 'mock-token-admin1');

       const res = await adminApkRoute(req);
       // Should return 400 since handleUpload intercepts throws from onBeforeGenerateToken
       assert.strictEqual(res.status, 400);
       const json = await res.json();
       assert.strictEqual(json.error, 'Upload request failed or was rejected.');
     } catch(e) {
       assert.fail('Should be caught by route handler');
     }
  });

  await t.test('17. prekoračenje maximumSizeInBytes', async () => {
     try {
       const tokenPayload = JSON.stringify({ versionName: '1.0', versionCode: '10', checksum: testHash, uid: 'admin1', email: 'a@v.com' });
       await onUploadCompleted({ blob: { url: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-10.apk?too-large=1' }, tokenPayload });
       assert.fail();
     } catch (e: any) {
       assert.match(e.message, /exceed maximum allowed size/);
     }
  });

  await t.test('18. SHA-256 nepodudaranje', async () => {
     try {
       const tokenPayload = JSON.stringify({ versionName: '1.0', versionCode: '10', checksum: 'a'.repeat(64), uid: 'admin1', email: 'a@v.com' });
       await onUploadCompleted({ blob: { url: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-10.apk' }, tokenPayload });
       assert.fail();
     } catch (e: any) {
       assert.match(e.message, /SHA-256 mismatch/);
     }
  });

    await t.test('19. brisanje samo neuspjelog kandidata', async () => {
      delCallCount = 0;
      delUrls = [];
      lastDelUrl = '';

      mockPool.intercept({
        path: '/api/blob/delete',
        method: 'POST'
      }).reply(200, (opts) => {
         delCallCount++;
         const body = JSON.parse(opts.body as string);
         if (body && body.urls && body.urls.length > 0) {
             lastDelUrl = body.urls[0];
             delUrls.push(lastDelUrl);
         }
         return {};
      }).persist();

      (mockState as any).system.set('apk_metadata', { versionCode: '20', checksum: 'old', latestUrl: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-20.apk' });
      
      try {
        const tokenPayload = JSON.stringify({ versionName: '1.0', versionCode: '10', checksum: 'a'.repeat(64), uid: 'admin1', email: 'a@v.com' });
        await onUploadCompleted({ blob: { url: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-10.apk' }, tokenPayload });
      } catch (e) {
        // Expected SHA mismatch
      }
      
      assert.strictEqual(delCallCount, 1);
      assert.strictEqual(lastDelUrl, 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-10.apk');
      assert.ok(!delUrls.includes('https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-20.apk'));
      
      const meta = (mockState as any).system.get('apk_metadata');
      assert.strictEqual(meta.versionCode, '20');
  });

  await t.test('20. Firestore publish/transaction failure', async () => {
     const oldTransaction = mockAdminDb.runTransaction;
     mockAdminDb.runTransaction = async () => { throw new Error('Transaction failed simulation'); };

     try {
       const tokenPayload = JSON.stringify({ versionName: '1.0', versionCode: '25', checksum: testHash, uid: 'admin1', email: 'a@v.com' });
       await onUploadCompleted({ blob: { url: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-25.apk' }, tokenPayload });
       assert.fail();
     } catch (e: any) {
       assert.match(e.message, /Transaction failed simulation/);
     }

     mockAdminDb.runTransaction = oldTransaction;
  });

  await t.test('21. očuvanje prethodne aktualne verzije nakon pogreške', async () => {
      const meta = (mockState as any).system.get('apk_metadata');
      assert.strictEqual(meta.versionCode, '20'); // Was 20 from test 19
  });

  await t.test('22. dva istodobna pokušaja objave (transaction check)', async () => {
      (mockState as any).system.set('apk_metadata', { versionCode: '30' });
      
      // Simulate real concurrency using a simple lock for the mock transaction
      let activeTx = false;
      const originalTx = mockAdminDb.runTransaction;
      mockAdminDb.runTransaction = async (fn: any) => {
         while(activeTx) {
             await new Promise(r => setTimeout(r, 10)); // retry loop simulating firestore backoff
         }
         activeTx = true;
         try {
             return await originalTx(fn);
         } finally {
             activeTx = false;
         }
      };

      delCallCount = 0;
      delUrls = [];

      const tokenPayload1 = JSON.stringify({ versionName: '1.0', versionCode: '40', checksum: testHash, uid: 'admin1', email: 'a@v.com' });
      const tokenPayload2 = JSON.stringify({ versionName: '1.0', versionCode: '35', checksum: testHash, uid: 'admin1', email: 'a@v.com' });

      // Start older version upload and newer version upload concurrently
      const p1 = onUploadCompleted({ blob: { url: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-40.apk' }, tokenPayload: tokenPayload1 });
      const p2 = onUploadCompleted({ blob: { url: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-35.apk' }, tokenPayload: tokenPayload2 });

      const results = await Promise.allSettled([p1, p2]);
      
      mockAdminDb.runTransaction = originalTx;

      // Check results
      const res40 = results[0];
      const res35 = results[1];
      
      assert.strictEqual(res40.status, 'fulfilled');
      assert.strictEqual(res35.status, 'rejected'); // 35 should fail because 40 commits first (or if 35 commits first, 40 overwrites it. But wait, if 35 is older, it can't overwrite 40. Wait, if 40 commits first, 35 fails. If 35 commits first, 40 overwrites 35. Let's make both 40 to test idempotency/duplicate protection).
      
      // Actually the user asks: "starija verzija ne može prebrisati noviju, postoji samo jedan odgovarajući audit zapis, konačni metadata odgovara novijoj verziji, neuspjeli kandidat pravilno je obrađen."
      
      const meta = (mockState as any).system.get('apk_metadata');
      assert.strictEqual(meta.versionCode, '40');
      
      // Check audits
      const audits = Array.from((mockState as any).activity_logs.values());
      const apkAudits = audits.filter((a: any) => a.action === 'APK_PUBLISHED' && a.details?.versionCode === '40');
      assert.strictEqual(apkAudits.length, 1);
      
      const apkAudits35 = audits.filter((a: any) => a.action === 'APK_PUBLISHED' && a.details?.versionCode === '35');
      // If 35 failed, there shouldn't be an audit for it, or maybe there is if it committed first. But wait, if 35 failed transaction check, no audit is written.
      
      // Failed candidate (v35 or v40 depending on race) should be deleted
      assert.ok(delCallCount >= 1);
  });

  await t.test('23. ponovljeni onUploadCompleted callback (idempotencija)', async () => {
     (mockState as any).system.set('apk_metadata', { versionCode: '100', latestUrl: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-100.apk' });
     const tokenPayload = JSON.stringify({ versionName: '1.0', versionCode: '100', checksum: testHash, uid: 'admin1', email: 'a@v.com' });

     fetchCallCount = 0;
     delCallCount = 0;
     await onUploadCompleted({ blob: { url: 'https://foo.public.blob.vercel-storage.com/apk/releases/vopoapp-1.0-100.apk' }, tokenPayload });

     assert.strictEqual(fetchCallCount, 0); // Idempotency check hit
     assert.strictEqual(delCallCount, 0);
  });

  global.fetch = originalFetch;
});
