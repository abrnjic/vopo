# Rješavanje nedostataka pred objavu

## Prvi paket — Android onboarding i player lint

Implementirano i provjereno:

- Nakon neuspješne početne sinkronizacije svaki provider mora imati potvrđeni spremljeni sadržaj da bi onboarding mogao završiti uspješno. M3U bez sadržaja ostaje neaktivan, sa spremljenim providerom i zakazanim ponovnim pokušajem. M3U s preuzetim kanalima ostaje uporabljiv uz PARTIAL status. Dodan regresijski test za djelomični uspjeh.
- Test SettingsDerivedStateObservers dobio je nedostajući mock prijevoda za nepoznati istek. Uzrok tog neuspjeha bio je testni fixture.
- Player modul deklarira ACCESS_NETWORK_STATE koji koristi; API 26 audio focus helper ima RequiresApi anotaciju; uporaba Media3 experimental API-ja eksplicitno je označena na relevantnim datotekama; VisibleForTesting označava getter.

Provjere:

- Player release lint: **0 grešaka, 5 upozorenja** (prethodno 50 grešaka).
- Data unit testovi: **548 prolazi**, bez neuspjeha.
- App unit testovi: **200 prolazi**, bez neuspjeha.
- Player unit testovi: **181 prolazi**, bez neuspjeha.
- Domain unit testovi: **110 prolazi**, bez neuspjeha.
- Ukupno: **1.039 unit testova**, bez neuspjeha.
- Završni Gradle poziv `:player:lintRelease :data:testDebugUnitTest :app:testDebugUnitTest`: BUILD SUCCESSFUL.
- `git diff --check`: prolazi.

Ovaj paket zatvara dva ranija neuspjeha unit testova i player dio B06. Ne zatvara lint ostalih modula, release provjeru na uređaju ni B01–B05/B07–B08 iz izvornog izvještaja.

Sljedeći prioriteti: build ovisnosti portala/backenda; serversko kreiranje resellera i usklađivanje status akcija; API za domene i bulk produženje; uređajski pristup licenci i dosljedan istek; distribucijski metadata i CI.

`graphify update .` pokušano je nakon izmjena, ali naredba nije dostupna. Nema ni graphify-out/GRAPH_REPORT.md. Graf nije ažuriran.

Privremeni logovi: `/private/tmp/vopo-fixes-validation.log` i `/private/tmp/vopo-fixes-validation2.log`.

## Drugi paket — uređajski API i dosljedan istek licence

Pripremljen je sigurni serverski ugovor za Android licence. Aplikacija koristi nasumični token po instalaciji, a Firestore pohranjuje samo njegov hash. Izravan Android read/write licenci uklonjen je. Server provodi trial, godišnji i lifetime istek, uklanja konfiguraciju iz isteklih odgovora te obnavlja godišnju licencu od postojećeg budućeg roka. Produkcijski rate limit više ne ovisi obvezno o nepostavljenom Upstash servisu, nego ima distribuirani Firestore fallback.

Detalji, provjere i preostali stvarni uređajski dokaz: `docs/DEVICE_LICENSE_SECURITY_2026-09-20.md`.

## Treći paket — jedinstveni Vercel kanal za nadogradnje

Android updater više ne ovisi o GitHub Releasesu. Stabilni build čita
`https://www.vopoapp.com/api/apk/latest` i preuzima preko stalne poveznice
`https://www.vopoapp.com/download`; beta build koristi odvojene `/api/apk/test`
i `/download/test` rute. Metadata se odbija ako nema valjan versionName,
pozitivan versionCode ili SHA-256 checksum.

Legacy `/api/version` više nema hardkodiranu verziju 1.0.5/code 105, nego čita
isti Firestore `system/apk_metadata` zapis kao službeni endpoint. README i
zadana portal download konfiguracija također vode na stalni Vercel kanal.

Provjere:

- Portal API testovi: **34 prolaze**.
- APK distribucijski testovi: **32 prolaze**.
- Domenski testovi: **13 prolaze**.
- Portal lint: **0 grešaka, 2 upozorenja**.
- Next.js produkcijski build i TypeScript: prolaze.
- Android app unit testovi prolaze, uključujući nove testove stabilnog, beta i
  nevaljanog checksum metadata odgovora.
- Player release lint, data testovi i app testovi: BUILD SUCCESSFUL u prethodnom
  zajedničkom pozivu.

Prvi službeni VOPO release ključ izrađen je 21. rujna 2026. jer prije toga nije
bio distribuiran stabilni APK. Ključ se čuva izvan repozitorija u privatnoj
lokalnoj mapi, a `.gitignore` štiti konfiguraciju i sve uobičajene Android
keystore formate od slučajnog commita. Certifikat ima SHA-256 otisak
`D8:7A:B8:A6:39:92:32:C1:B5:53:13:E1:A7:D9:5E:49:C2:32:0B:DC:C8:2F:09:44:26:B0:38:72:F0:F5:3E:DA`.

Potpisani stabilni APK `1.0.16`/code `17` objavljen je na
`https://www.vopoapp.com/download`. Javni `/api/apk/latest` i legacy
`/api/version` vraćaju isti versionCode, versionName, download URL i SHA-256.
Objavljeni APK ponovno je preuzet, potpis je provjeren `apksigner` alatom, a
veličina `16159486` i SHA-256
`99579749360ed146c881554eab86e637803036caccc294d9faff0c22c4b07b73`
podudaraju se s lokalnim release artefaktom i javnim metadata zapisom. B07 je
zatvoren za prvi stabilni release; provjera nadogradnje na stvarnom uređaju
ostaje potrebna kada bude dostupna prethodno instalirana službeno potpisana
verzija.

## Četvrti paket — admin statusi i serversko skupno produženje

Administracijsko sučelje sada koristi službeni `/api/admin/users` ugovor
`{ uid, status }`. Suspendiranje, ponovna aktivacija i deaktivacija zato više ne
šalju zastarjeli payload koji API nije mogao obraditi. Deaktivirani korisnik se
odjavljuje i nema pristup portalu, a sučelje jasno prikazuje deaktivaciju umjesto
netočnog trajnog brisanja.

Skupno produženje reseller licenci premješteno je iz izravnih klijentskih
Firestore upisa u `/api/reseller/bulk-extend`. Server u jednoj transakciji
provjerava vlasništvo, vrstu licence i kredite, produžuje rok, naplaćuje kredite
te zapisuje operaciju i audit događaj. UUID zahtjeva omogućuje sigurno
ponavljanje bez dvostruke naplate.

Provjere:

- Portal API testovi: **38 prolaze**.
- APK distribucijski testovi: **32 prolaze**.
- Domenski testovi: **13 prolaze**.
- Ukupno: **83 testa**, bez neuspjeha.
- Portal lint: **0 grešaka, 2 postojeća upozorenja**.
- Next.js produkcijski build i TypeScript: prolaze.
- `git diff --check`: prolazi.

Preostali dio B04 je skupno brisanje licenci koje još koristi izravni
klijentski Firestore batch i treba zaseban serverski endpoint prije konačnog
zatvaranja projekta.
