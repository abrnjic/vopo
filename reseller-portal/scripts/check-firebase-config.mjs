import nextEnv from '@next/env';

nextEnv.loadEnvConfig(process.cwd(), process.argv.includes('--development'));

const required = [
  'NEXT_PUBLIC_FIREBASE_API_KEY',
  'NEXT_PUBLIC_FIREBASE_AUTH_DOMAIN',
  'NEXT_PUBLIC_FIREBASE_PROJECT_ID',
  'NEXT_PUBLIC_FIREBASE_APP_ID',
];
const invalid = required.filter((name) => {
  const value = process.env[name]?.trim().replace(/^["']|["']$/g, '');
  return !value || /dummy|\.\.\.|your[_-]/i.test(value)
    || (name === 'NEXT_PUBLIC_FIREBASE_API_KEY' && !/^AIza[\w-]{35}$/.test(value));
});

if (invalid.length) {
  console.error(`Firebase konfiguracija portala nedostaje ili nije valjana: ${invalid.join(', ')}.`);
  console.error('Postavite varijable u build okruženju i ponovno izgradite portal. Vrijednosti ključeva nisu ispisane.');
  process.exitCode = 1;
} else {
  console.log('Firebase web konfiguracija je prisutna; build može nastaviti.');
}
