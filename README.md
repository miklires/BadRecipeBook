# Bad Recipe Book

Unlocks every recipe available in the current singleplayer or LAN world, so the vanilla recipe book is complete from the moment you join.

## Features

- unlocks recipes from Minecraft and installed datapacks
- updates the vanilla recipe book without replacing its interface
- runs only for the integrated singleplayer server
- creates a small, readable config on first launch
- includes English and Russian messages; Minecraft selects the language

## Multiplayer limitation

A remote server sends only the recipes it chooses to reveal. A client-only mod cannot safely reconstruct hidden server or datapack recipes. Bad Recipe Book therefore leaves remote multiplayer servers untouched. Install an equivalent server-side recipe-unlock feature when full multiplayer recipe access is required.

## Configuration

The file `config/bad-recipe-book.json` is generated with safe defaults:

```json
{
  "unlockAllRecipes": true,
  "showUnlockMessage": true
}
```

An invalid file is preserved as `bad-recipe-book.json.invalid`, then replaced with a valid default file.

## Requirements

- Minecraft Java Edition 26.2
- Fabric Loader 0.19.3 or newer
- Fabric API
- Java 25

## Build

```text
./gradlew clean build
```

The remapped mod JAR is written to `build/libs/`.

## License

MIT
