## Прописывание эффектов
Каждый эффект имеет свой класс, они находятся в папке `effects`

## Регистрация эффекта
В `registry/ModEffects.java` пропишите:
`public static final StatusEffect НАЗВАНИЕ = register("название", new КЛАСС_ЭФФЕКТА());`