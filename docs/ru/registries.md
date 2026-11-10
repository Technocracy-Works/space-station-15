# Регистры

## Главный поток регистрации

Большинство регистрации начинается в `SpaceStation.onInitialize()`:

- `ModComponents.register()`
- `ModSounds.register()`
- `ModItems.register()`
- `ModBlocks.register()`
- `ModBlockEntities.register()`
- `ModScreenHandlers.register()`
- `ChemRegistry.register()`
- `ModItemGroups.register()`
- `ModWorldGeneration.init()`
- `ModLootModifiers.register()`
- `ModPackets.register()`

## Предметы

`ModItems` является общей точкой входа для регистрации предметов. Сами поля разделены по категориям в `registry.items`:

- `ArmorItems`
- `ChemItems`
- `DrinkItems`
- `FoodItems`
- `MiscItems`
- `PlantItems`
- `PlushieItems`
- `ToolItems`

`ItemRegistry` содержит хелпер классы для простых предметов, еды, семян/ростков, плюшек и химических контейнеров.

## Блоки

`ModBlocks` регистрирует блоки. `PlantBlocks` отдельно регистрирует crop-блоки.

`ModBlocks.register()` также регистрирует рецепты сборки через `AssemblyBlock.registerUpgrade(...)`.

## Components

`ModComponents.CHEM_DATA` - кастомный `item data component` для хранения `ChemData`. У него есть `Codec` и сетевой `PacketCodec`.

## Block Entities и Screens

`ModBlockEntities.CHEM_MASTER` связывает `ChemMasterBlockEntity` с `ModBlocks.CHEM_MASTER`.

`ModScreenHandlers.CHEM_MASTER` - это `ExtendedScreenHandlerType` с `BlockPos.PACKET_CODEC`, чтобы клиент открывал экран для правильного энтити.

## Звуки

`ModSounds` регистрирует звуки для плюшек. Файлы звуков и `sounds.json` лежат в `src/main/resources/assets/spacestation`.

## Эффекты

`ModEffects` регистрирует эффекты. Для использования - метод состоящий из названия и `new КЛАСС_ЭФФЕКТА()`

## Типы урона

`ModDamageTypes` регистрирует ДамагСурсы. Для использования - `метод register(название_типа_урона)`