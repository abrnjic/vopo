# Uređajska autentikacija i licence

Datum: 20.09.2026.

Android više ne čita niti stvara `licenses/{deviceId}` izravno kroz Firestore SDK. Nova instalacija generira 256-bitni nasumični uređajski token, sprema ga u privatnu pohranu aplikacije i registrira samo njegov SHA-256 hash preko `POST /api/device/register`. Status i Xtream konfiguracija dohvaćaju se preko `GET /api/device/license` uz `Authorization: Device <token>`. Token nije dio URL-a, Firestore dokumenta ni serverskog odgovora.

Serverski endpoint uspoređuje hash konstantnovremenskom usporedbom, vraća konfiguraciju samo nositelju odgovarajućeg tokena i postavlja `Cache-Control: no-store, private`. Drugi token ne može preuzeti već registrirani uređaj. Firestore pravila ostaju zatvorena za Android klijent; povjerljivi Xtream podaci prolaze samo kroz serverski API.

Istek se sada određuje na serveru. Trial dobiva `trialStartedAt` i `expiresAt` tri dana unaprijed. Godišnja licenca vrijedi samo dok je `expiresAt` u budućnosti; obnova produžuje postojeći budući rok za godinu. Lifetime vrijedi samo dok je status aktivan i `isLifetime=true`, pa opozvana lifetime licenca više nije aktivna. Istekli odgovori ne sadrže Xtream konfiguraciju.

Bez Upstash varijabli produkcija koristi distribuirani Firestore limiter u kolekciji `rate_limits`; klijentska pravila ne daju pristup toj kolekciji. Uređajski status nakon valjanog jakog tokena radi jedan Firestore read po provjeri i ne radi rate-limit write svakih pet sekundi.

Mrežna politika je fail-closed: bez dostupnog serverskog odgovora aplikacija ne otključava licencirani sadržaj. Offline grace period nije uveden. Postojeće instalacije bez tokena mogu jednokratno vezati token uz postojeći dokument koji još nema `accessTokenHash`; to je migracijski prozor i treba ga zatvoriti nakon nadogradnje aktivnih uređaja.

Provjere:

- API sigurnosni i poslovni testovi: 34/34 prolaze.
- Android ciljani `DeviceLicenseApiClientTest`: 3/3 prolaze.
- Android `:data:testDebugUnitTest :app:testDebugUnitTest`: BUILD SUCCESSFUL.
- Android `:data:compileDebugKotlin :app:compileDebugKotlin`: BUILD SUCCESSFUL.
- Next.js produkcijski build i TypeScript: prolaze; rute `/api/device/register` i `/api/device/license` uključene su u build.
- Lokalni produkcijski runtime smoke: šest API ruta vraća kontrolirane JSON odgovore i nema Firebase Admin module-load pada.

Za potpuno zatvaranje B01 još je potreban stvarni tok na instaliranom APK-u protiv objavljenog API-ja: nova instalacija, prikaz ID-a, connect/trial, reseller aktivacija i primitak konfiguracije. Taj dokaz ne treba zamijeniti mock testovima.
