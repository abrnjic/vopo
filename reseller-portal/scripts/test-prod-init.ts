/* eslint-disable @typescript-eslint/no-require-imports */
import assert from 'node:assert';

process.env.NODE_ENV = 'production';
process.env.MOCK_FIREBASE = 'true';

// Clear out credentials
delete process.env.FIREBASE_PROJECT_ID;
delete process.env.NEXT_PUBLIC_FIREBASE_PROJECT_ID;
delete process.env.FIREBASE_CLIENT_EMAIL;
delete process.env.FIREBASE_PRIVATE_KEY;

let threwError = false;

try {
  // Access a property on adminDb to trigger lazy initialization
  const { adminDb } = require('../src/lib/firebaseAdmin');
  adminDb.collection('system');
} catch (err: any) {
  threwError = true;
  assert.match(
    err.message,
    /Production Firebase initialization failed/i,
    'Expected error message to contain "Production Firebase initialization failed"'
  );
}

assert.ok(threwError, 'Expected importing firebaseAdmin in production without credentials to throw an error.');
console.log('✔ Production initialization correctly throws without credentials');
