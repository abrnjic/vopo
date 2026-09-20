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
