# SpaceStation 15 Developer Documentation

This documentation explains how the current codebase is organized and how to extend it safely. The project is built using: Minecraft 1.21.1, Java 21, Fabric API, Yarn mappings.

## Start Here

- [Setup](setup.md): local environment, Gradle tasks, and project requirements.
- [Architecture](architecture.md): entrypoints, source sets, initialization order, and module map.
- [Registries](registries.md): how items, blocks, sounds, screens, components, and block entities are registered.
- [Items](items.md): item categories, helper classes, custom item behavior, and adding new items.
- [Blocks](blocks.md): blocks, crops, assembly blocks, block entities, models, loot, and adding new blocks.
- [Chemistry](chemistry.md): Chem Master, chemical containers, reactions, grinding recipes, and data formats.
- [Networking](networking.md): custom payloads, server/client packet flow, and where networking is used.
- [Saving](saving.md): item data components, block entity NBT, and resource-driven data.
- [Radiation](radiation.md): player radiation storage, sources, modifiers, and configuration.
- [Events And Timers](events.md): Fabric events, action timers, HUD progress, and loot table modification.
- [Resources](resources.md): assets, data packs, lang files, recipes, worldgen JSON, and naming conventions.
- [World Generation](worldgen.md): ore feature registration and JSON structure.
- [Coding Style](coding_style.md): code style and contribution expectations.
