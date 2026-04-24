# PvP Tiers Mod

Rodo žaidėjų PvP tiersus virš galvos Minecraft žaidime.

## Tieriai (geriausias → blogiausias)

| Tier | Spalva     |
|------|-----------|
| HT1  | Raudona   |
| LT1  | Raudona   |
| HT2  | Oranžinė  |
| LT2  | Oranžinė  |
| HT3  | Geltona   |
| LT3  | Geltona   |
| HT4  | Žalia     |
| LT4  | Žalia     |
| HT5  | Žydra     |
| LT5  | Žydra     |
| HT6  | Pilka     |
| LT6  | Pilka     |
| O    | Oranžinė  |
| NE   | Pilka     |
| B    | Mėlyna    |
| A    | Šv. mėlyna|

## Reikalavimai

- Minecraft 1.21.5 – 1.21.1
- Fabric Loader 0.16.14+
- Fabric API
- Java 21

## Kompiliavimas

```bash
./gradlew build
```

JAR failas bus `/build/libs/pvptiers-mod-1.0.0.jar`

## API konfigūracija

Failas: `src/main/java/com/pvptiers/mod/data/TierCache.java`

Eilutė:
```java
private static final String API_URL = "https://api.pvptierslist.com/v1/player/%s";
```

### Galimi API:
- **pvptierslist.com**: `https://api.pvptierslist.com/v1/player/%s`
- **PvPTiersLol**: pakeisk URL į jų API endpoint
- **Savo serveris**: bet koks API grąžinantis `{ "tier": "HT2" }`

### Tikėtamas JSON atsakymas:
```json
{
  "tier": "HT2",
  "uuid": "550e8400-...",
  "username": "Notch"
}
```

## Projekto struktūra

```
src/main/java/com/pvptiers/mod/
├── PvPTiersMod.java                    – pagrindinis entrypoint
├── data/
│   ├── Tier.java                       – tier enum su spalvomis
│   └── TierCache.java                  – cache + async API fetch
└── mixin/
    └── PlayerEntityRendererMixin.java  – tier rodymas virš galvos
```

## Kaip veikia rendering

1. Kiekvieną frame'ą Minecraft iškviečia `renderLabelIfPresent()`
2. Mixin įsiterpia prieš originalų nametag
3. Tier tekstas piešiamas `0.3` bloko aukščiau nametag
4. Spalva automatiškai parinkta pagal tier
5. Cache galioja 5 minutes, po to atnaujinama iš API
