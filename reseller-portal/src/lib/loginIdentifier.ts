const USERNAME_PATTERN = /^[a-z0-9][a-z0-9._-]{2,63}$/i;

export function normalizeLoginIdentifier(value: string): string {
  const normalized = value.trim().toLowerCase();
  if (normalized.includes('@')) return normalized;
  if (!USERNAME_PATTERN.test(normalized)) {
    throw new Error('Unesite ispravno korisničko ime ili email adresu.');
  }
  return `${normalized}@vopoapp.com`;
}

export function loginErrorMessage(error: unknown): string {
  const code = typeof error === 'object' && error !== null && 'code' in error
    ? String((error as { code?: unknown }).code || '')
    : '';
  if (code === 'auth/invalid-credential' || code === 'auth/user-not-found' || code === 'auth/wrong-password') {
    return 'Korisničko ime ili lozinka nisu ispravni.';
  }
  if (error instanceof Error && error.message.startsWith('Unesite ispravno')) return error.message;
  return 'Prijava trenutno nije uspjela. Pokušajte ponovno.';
}
