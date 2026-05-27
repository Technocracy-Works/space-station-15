import json
from pathlib import Path


ID_MAP = {
    "aloksadon": "aloxadone",
    "ambuzol": "ambuzol",
    "ambuzol_plyus": "ambuzol_plus",
    "ammiak": "ammonia",
    "aritrazin": "arithrazine",
    "arkrioks": "arkryox",
    "atseton": "acetone",
    "azot": "nitrogen",
    "alyuminiy": "aluminum",
    "benzol": "benzene",
    "blagoslavenie": "blessing",
    "britvium": "britvium",
    "bruizin": "bruizine",
    "deksalin": "dexalin",
    "deksalin_plyus": "dexalin_plus",
    "dermalin": "dermaline",
    "dezoksiefedrin": "desoxyephedrine",
    "dietilamin": "diethylamine",
    "difengidramin": "diphenhydramine",
    "difenilmetilamin": "diphenylmethylamine",
    "diloven": "dylovene",
    "doksarubiksadon": "doxarubixadone",
    "efedrin": "ephedrine",
    "epinefrin": "epinephrine",
    "etanol": "ethanol",
    "etiloksiefedrin": "ethyloxyephedrine",
    "etilredoksrazin": "ethylredoxrazine",
    "falangimin": "phalangimine",
    "fenol": "phenol",
    "fizrastvor": "saline",
    "fosfor": "phosphorus",
    "ftor": "fluorine",
    "ftorosernaya_kislota": "fluorosulfuric_acid",
    "ftorsurfaktant": "fluorosurfactant",
    "galoperidol": "haloperidol",
    "gemorrginol": "hemorrhaginol",
    "gidroksid": "hydroxide",
    "gidroksid_natriya": "sodium_hydroxide",
    "giperzin": "hyperzine",
    "gistamin": "histamine",
    "hironalin": "hyronalin",
    "hloralgidrat": "chloral_hydrate",
    "impedrezen": "impedrezene",
    "inaprovalin": "inaprovaline",
    "insuzin": "insuzine",
    "ipekak": "ipecac",
    "karbonat_natriya": "sodium_carbonate",
    "karpotoksin": "carpotoxin",
    "kelotan": "kelotane",
    "kofe": "coffee",
    "kognizin": "cognizine",
    "kosmicheskie_narkotiki": "space_drugs",
    "kosmicheskiy_kley": "space_glue",
    "kosmicheskiy_ochistitel": "space_cleaner",
    "krioksadon": "cryoxadone",
    "kriptobiolin": "cryptobiolin",
    "krov": "blood",
    "krov_zombi": "zombie_blood",
    "latserinol": "lacerinol",
    "leksorin": "lexorin",
    "leporazin": "leporazine",
    "likoksid": "lycoxide",
    "lipolitsid": "lipolicide",
    "lipozin": "lipozine",
    "litiy": "lithium",
    "maslo": "oil",
    "nekrozol": "necrosol",
    "nestabilnyy_mutagen": "unstable_mutagen",
    "noktyurin": "nocturine",
    "norepinefrinovaya_kislota": "norepinephrine_acid",
    "okulin": "oculine",
    "omnizin": "omnizine",
    "opporozidon": "opporozidone",
    "otbelivatel": "bleach",
    "penoobrazovatel": "foaming_agent",
    "pirazin": "pyrazine",
    "plazma": "plasma",
    "polikarbonat_natriya": "sodium_polycarbonate",
    "politrinikovaya_kislota": "polytrinic_acid",
    "psikodin": "psicodine",
    "punkturaz": "puncturase",
    "radiy": "radium",
    "rtut": "mercury",
    "sahar": "sugar",
    "schaste": "happiness",
    "sera": "sulfur",
    "sernaya_kislota": "sulfuric_acid",
    "siderlak": "siderlac",
    "siginat": "sigynate",
    "silitsid_zheleza": "iron_silicide",
    "sinaptizin": "synaptizine",
    "smeh": "laughter",
    "stellibinin": "stellibinin",
    "stimulyatory": "stimulants",
    "stolovaya_sol": "table_salt",
    "svarochnoe_toplivo": "welding_fuel",
    "svyataya_voda": "holy_water",
    "tazinid": "tazinide",
    "termit": "thermite",
    "toksin_hartbreyker": "heartbreaker_toxin",
    "toksin_mayndbreyker": "mindbreaker_toxin",
    "toksin_nemoty": "mute_toxin",
    "traneksamovaya_kislota": "tranexamic_acid",
    "trikordrazin": "tricordrazine",
    "ugol": "charcoal",
    "ultravaskulin": "ultravasculine",
    "varfarin": "warfarin",
    "vestin": "vestine",
    "zola": "ash",
}


def canonical(name: str) -> str:
    return ID_MAP.get(name, name)


def remap_amounts(amounts: dict) -> dict:
    out = {}
    for key, value in amounts.items():
        ck = canonical(key)
        out[ck] = round(out.get(ck, 0.0) + float(value), 5)
    return out


def main() -> None:
    reactions_dir = Path("src/main/resources/data/spacestation/reactions")
    grinding_dir = Path("src/main/resources/data/spacestation/grinding")

    rewritten = []
    for path in sorted(reactions_dir.glob("*.json")):
        data = json.loads(path.read_text(encoding="utf-8"))
        reagents = remap_amounts(data.get("reagents", {}))
        results = remap_amounts(data.get("results", {}))
        min_volume = float(data.get("min_volume", 0.0))
        fixed = {
            "reagents": reagents,
            "results": results,
            "min_volume": min_volume,
        }
        path.write_text(json.dumps(fixed, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
        rewritten.append(path)

    # Rename each reaction file to match result ID.
    for path in rewritten:
        data = json.loads(path.read_text(encoding="utf-8"))
        result_keys = list(data["results"].keys())
        if not result_keys:
            continue
        target = reactions_dir / f"{result_keys[0]}.json"
        if target == path:
            continue
        if target.exists():
            # Keep existing target and remove duplicate file.
            path.unlink()
        else:
            path.rename(target)

    for path in sorted(grinding_dir.glob("*.json")):
        data = json.loads(path.read_text(encoding="utf-8"))
        fixed = {
            "ingredient": data.get("ingredient"),
            "results": remap_amounts(data.get("results", {})),
        }
        path.write_text(json.dumps(fixed, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

    print("Canonicalization complete")


if __name__ == "__main__":
    main()
