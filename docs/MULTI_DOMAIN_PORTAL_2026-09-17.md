# Više domena po reselleru

Administrator dobiva zasebnu karticu Domene. Bez reseller računa može održavati zajednički katalog HTTP/HTTPS adresa. U istoj kartici bira resellera i dodaje, uređuje ili uklanja njegove domene. Pri kreiranju resellera može odabrati više domena iz kataloga ili unijeti dodatne adrese, jednu po retku.

Reseller upravlja svojim dodijeljenim i vlastitim domenama u Postavkama te odabire jednu pri aktivaciji uređaja. Spremanje se izvršava preko autentificiranog serverskog API-ja s transakcijom i audit zapisom. Tuđi profili i administratorski katalog nisu dostupni reselleru. Kreiranje Auth računa i profila premješteno je na server; neuspješno spremanje profila pokušava ukloniti novokreirani Auth račun.

Zadržan je postojeći model users/{uid}.assignedDomains i customDomains. Katalog je settings/domainCatalog, dostupan samo kroz administratorski API. Adrese se normaliziraju, duplikati se odbijaju, podržani su HTTP, HTTPS, port i osnovna putanja. Adresa bez protokola dobiva HTTPS. Limit je 50 domena po korisniku. Aktivacija provjerava da odabrana domena pripada reselleru i sprema je u selectedDomain i xtreamConfig.url.

Uređivanje/uklanjanje popisa primjenjuje se na buduće aktivacije. Postojeće licence zadržavaju svoj URL; nema automatske migracije korisničkih licenci pri promjeni kataloga ili reseller popisa.

Provjere: npm ci i produkcijski Next.js build prolaze u /private/tmp/vopo-domains-validation. Postojeći API, APK i production-init testovi prolaze. Novi test-domains.ts ima 12 slučajeva (13 uključujući roditeljski test), svi prolaze. Android XtreamStreamUrlResolverTest: 22 prolazi, 0 grešaka; uključena provjera različitih domena s HTTP/HTTPS, portom i putanjom. git diff --check prolazi.

Ograničenje: nije potvrđeno stvarno emitiranje s dvije produkcijske IPTV domene. Android uređajska autentikacija/licenca (B01 iz PROJECT_READINESS_2026-09-17.md) ostaje zaseban blocker i ova izmjena ga ne rješava. graphify update . nije dostupan, pa graf nije ažuriran.

Dokazi: /private/tmp/vopo-domains-build.log i /private/tmp/vopo-domains-android-tests.log.

Objava: commit 34b3ba2f06ea53530ad0e12279f2886c158a1ed5 poslan je na main. Vercel deployment 69oWJ6p6SrKpLJWTuL79U53mzxWi završio je kao Ready i dodijeljen je www.vopoapp.com. U javnom administratorskom panelu potvrđena je kartica Domene i obrazac Dodaj. Runtime provjera otkrila je ERR_REQUIRE_ESM pri učitavanju jose kroz jwks-rsa/Firebase Admin, prije ulaska u API handler.

Popravak runtimea: package.json i lockfile određuju Node.js 24.x; CI koristi Node.js 24. Lokalno na v24.14.1 potvrđeno je učitavanje firebase-admin/auth i produkcijski build. Službena Vercel dokumentacija podržava Node.js 24 i engines odabir: https://vercel.com/docs/functions/runtimes/node-js/node-js-versions. Objavu popravka i autentificirani dohvat treba potvrditi u produkciji.
