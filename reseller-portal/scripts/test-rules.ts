/* eslint-disable prefer-const, @typescript-eslint/no-unused-vars */
import { assertFails, assertSucceeds, initializeTestEnvironment, RulesTestEnvironment } from '@firebase/rules-unit-testing';
import { readFileSync } from 'fs';
import { resolve } from 'path';
import test from 'node:test';
import assert from 'node:assert';
import { setDoc, getDoc, doc, updateDoc, deleteDoc } from 'firebase/firestore';

let testEnv: RulesTestEnvironment;

test('Firestore Security Rules', async (t) => {
  t.before(async () => {
    testEnv = await initializeTestEnvironment({
      projectId: 'vopo-reseller-test',
      firestore: {
        rules: readFileSync(resolve(__dirname, '../firebase/firestore.rules'), 'utf8'),
      },
    });
  });

  t.after(async () => {
    await testEnv.cleanup();
  });

  t.beforeEach(async () => {
    await testEnv.clearFirestore();
    // Setup some initial admin state if needed using testEnv.withSecurityRulesDisabled
    await testEnv.withSecurityRulesDisabled(async (context) => {
      const db = context.firestore();
      await setDoc(doc(db, 'users', 'admin1'), { role: 'admin', status: 'active' });
      await setDoc(doc(db, 'users', 'reseller1'), { role: 'reseller', status: 'active', credits: 10 });
      await setDoc(doc(db, 'licenses', 'lic1'), { resellerId: 'reseller1', status: 'Active' });
    });
  });

  await t.test('neprijavljeno čitanje (unauthenticated read) is denied', async () => {
    const unauthedDb = testEnv.unauthenticatedContext().firestore();
    await assertFails(getDoc(doc(unauthedDb, 'licenses', 'lic1')));
    await assertFails(getDoc(doc(unauthedDb, 'users', 'admin1')));
  });

  await t.test('reseller čita tuđi zapis (reseller reads other record) is denied', async () => {
    const resellerDb = testEnv.authenticatedContext('reseller2', { role: 'reseller' }).firestore();
    await assertFails(getDoc(doc(resellerDb, 'licenses', 'lic1')));
    await assertFails(getDoc(doc(resellerDb, 'transactions', 'newT')));
  });

  await t.test('reseller čita vlastiti zapis (reseller reads own record) is allowed', async () => {
    const resellerDb = testEnv.authenticatedContext('reseller1', { role: 'reseller' }).firestore();
    await assertSucceeds(getDoc(doc(resellerDb, 'users', 'reseller1')));
    await assertSucceeds(getDoc(doc(resellerDb, 'licenses', 'lic1')));
  });

  await t.test('klijentski create/update/delete u licenses i devices je zabranjen', async () => {
    const db = testEnv.authenticatedContext('reseller1', { role: 'reseller' }).firestore();
    await assertFails(setDoc(doc(db, 'licenses', 'newLic'), { test: 1 }));
    await assertFails(updateDoc(doc(db, 'licenses', 'lic1'), { status: 'Expired' }));
    await assertFails(deleteDoc(doc(db, 'licenses', 'lic1')));

    await assertFails(setDoc(doc(db, 'devices', 'dev1'), { test: 1 }));
    await assertFails(updateDoc(doc(db, 'devices', 'dev1'), { test: 2 }));
    await assertFails(deleteDoc(doc(db, 'devices', 'dev1')));
  });

  await t.test('klijentski write u transactions je zabranjen', async () => {
    const db = testEnv.authenticatedContext('reseller1', { role: 'reseller' }).firestore();
    await assertFails(setDoc(doc(db, 'transactions', 'newT'), { test: 1 }));
    await assertFails(updateDoc(doc(db, 'transactions', 'newT'), { test: 2 }));
    await assertFails(deleteDoc(doc(db, 'transactions', 'newT')));
  });

  await t.test('klijentski write u activity_logs je zabranjen', async () => {
    const db = testEnv.authenticatedContext('reseller1', { role: 'reseller' }).firestore();
    await assertFails(setDoc(doc(db, 'activity_logs', 'newLog'), { test: 1 }));
  });

  await t.test('klijent mijenja vlastiti role je zabranjeno', async () => {
    const db = testEnv.authenticatedContext('reseller1').firestore();
    await assertFails(updateDoc(doc(db, 'users', 'reseller1'), { role: 'admin' }));
  });

  await t.test('klijent mijenja vlastiti status je zabranjeno', async () => {
    const db = testEnv.authenticatedContext('reseller1').firestore();
    await assertFails(updateDoc(doc(db, 'users', 'reseller1'), { status: 'inactive' }));
  });

  await t.test('klijent mijenja vlastite credits je zabranjeno', async () => {
    const db = testEnv.authenticatedContext('reseller1').firestore();
    await assertFails(updateDoc(doc(db, 'users', 'reseller1'), { credits: 100 }));
  });

  await t.test('dodavanje novog nedopuštenog polja u users je zabranjeno', async () => {
    const db = testEnv.authenticatedContext('reseller1').firestore();
    await assertFails(updateDoc(doc(db, 'users', 'reseller1'), { newFieldHacked: true }));
  });

  await t.test('brisanje zaštićenog polja je zabranjeno', async () => {
    const db = testEnv.authenticatedContext('reseller1').firestore();
    await assertFails(updateDoc(doc(db, 'users', 'reseller1'), { role: null })); // effectively deleting or changing
  });
});
