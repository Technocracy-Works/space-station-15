# Radiation

`RadiationManager` stores an entity's radiation as a numeric value separate from Minecraft status effects. The value is saved in living-entity NBT; a player's value is copied on respawn. Change it through `addRadiation`, `removeRadiation`, and `clearRadiation`; query it with `getRadiation`.

## Sources and modifiers

An item can have a numeric dose in its `ModComponents.RADIOACTIVITY` component. The doses on radioactive items in either hand are added at the configured interval. Add the component directly to any item's or `BlockItem`'s settings:

```java
new Item(new Item.Settings()
        .maxCount(16)
        .component(ModComponents.RADIOACTIVITY, 20.0))
```

For a source other than a held item, use `RadiationManager.registerExposureSource(...)`. `RadiationModifier` lets protective equipment change exposure and temporary effects add radiation decay. Medicine and other treatment items are not implemented yet.

## Configuration

The first server launch creates `config/spacestation-radiation.properties` in the server configuration directory. Restart to apply changes.

- `itemExposureIntervalSeconds`: interval in seconds between doses from radioactive held items. The component value determines the dose; the legacy `uraniumIntervalSeconds` key is supported as a fallback.
- `naturalDecayPerSecond`: natural radiation decay per second.
- `effectRefreshIntervalSeconds`: symptom refresh interval.
- `level1`–`level5`: threshold, nausea/weakness/slowness flags, amplifiers, and periodic damage with an interval in seconds.

Thresholds must increase strictly. The symptom level is recalculated when radiation changes level; active effects are refreshed periodically, not every tick.

## Extending the system

Register another source during mod initialization with `registerExposureSource`, providing a player predicate, dose, and interval in ticks. Register a `RadiationModifier` implementation for protective equipment or temporary decay boosts. Do not edit the radiation NBT directly; doing so bypasses the common manager.
