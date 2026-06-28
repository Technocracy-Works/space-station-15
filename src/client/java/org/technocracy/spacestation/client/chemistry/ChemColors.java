package org.technocracy.spacestation.client.chemistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Indicator colors for chemicals in the Chem Master GUI (ARGB).
 */
public final class ChemColors {

    private static final Map<String, Integer> COLORS = new HashMap<>();

    static {
        // Elements & basics
        put("water", 0xFF3399FF);
        put("hydrogen", 0xFFE8F4FF);
        put("oxygen", 0xFF66CCFF);
        put("carbon", 0xFF333333);
        put("nitrogen", 0xFF4466AA);
        put("chlorine", 0xFF99EE55);
        put("fluorine", 0xFFAAFFCC);
        put("sodium", 0xFFFFEEAA);
        put("potassium", 0xFFBB88FF);
        put("calcium", 0xFFEEEECC);
        put("iron", 0xFFAA7755);
        put("copper", 0xFFB87333);
        put("silicon", 0xFF888899);
        put("aluminum", 0xFFCCCCDD);
        put("lithium", 0xFFDDDDFF);
        put("phosphorus", 0xFFFF8844);
        put("sulfur", 0xFFFFFF66);
        put("iodine", 0xFF6633AA);
        put("mercury", 0xFFAAAAAA);
        put("radium", 0xFF88FF88);
        put("uranium", 0xFF44FF44);
        put("plasma", 0xFFFF66FF);
        put("ash", 0xFF555555);
        put("charcoal", 0xFF222222);

        // Simple compounds
        put("ammonia", 0xFF88CCFF);
        put("hydroxide", 0xFF55BBEE);
        put("sodium_hydroxide", 0xFF66AADD);
        put("sodium_carbonate", 0xFFEEEEFF);
        put("sodium_polycarbonate", 0xFFDDEEFF);
        put("table_salt", 0xFFFFFFFF);
        put("sulfuric_acid", 0xFFFFFF00);
        put("fluorosulfuric_acid", 0xFFCCFF66);
        put("fluorosurfactant", 0xFFAAFFAA);
        put("potassium_iodide", 0xFF9966CC);
        put("iron_silicide", 0xFF998877);
        put("benzene", 0xFFCCCC99);
        put("phenol", 0xFFFFCCAA);
        put("acetone", 0xFF99CCFF);
        put("ethanol", 0xFFFFEECC);
        put("oil", 0xFFCCAA44);
        put("welding_fuel", 0xFFFF9933);
        put("sugar", 0xFFFFDDEE);
        put("coffee", 0xFF664422);
        put("blood", 0xFFCC2222);
        put("zombie_blood", 0xFF558833);
        put("saline", 0xFF88DDFF);
        put("holy_water", 0xFFFFFFCC);
        put("blessing", 0xFFFFFFAA);

        // Medical — healing / stabilizers
        put("bicaridine", 0xFF4488FF);
        put("kelotane", 0xFFFF8844);
        put("dermaline", 0xFFFFAA66);
        put("leporazine", 0xFF66FFAA);
        put("cryoxadone", 0xFF66FFFF);
        put("dexalin", 0xFF55DDFF);
        put("dexalin_plus", 0xFF44CCFF);
        put("inaprovaline", 0xFF88AAFF);
        put("dylovene", 0xFF77BBEE);
        put("tricordrazine", 0xFF66AADD);
        put("hyronalin", 0xFF99CCFF);
        put("arithrazine", 0xFFAAEEFF);
        put("aloxadone", 0xFF55FFCC);
        put("ambuzol", 0xFF88FF88);
        put("ambuzol_plus", 0xFF66EE66);
        put("insuzine", 0xFFAAFFCC);
        put("mannitol", 0xFFCCFFFF);
        put("oculine", 0xFF88FFEE);
        put("sigynate", 0xFFBBDDFF);
        put("tranexamic_acid", 0xFF99BBEE);
        put("puncturase", 0xFF7799DD);
        put("bruizine", 0xFFCC8866);
        put("britvium", 0xFFDD9977);
        put("lacerinol", 0xFFEEAA88);
        put("hemorrhaginol", 0xFFCC4444);
        put("warfarin", 0xFFAA3333);
        put("ultravasculine", 0xFF55AAFF);

        // Stimulants / metabolic
        put("ephedrine", 0xFFFFAA44);
        put("desoxyephedrine", 0xFFFF9944);
        put("ethyloxyephedrine", 0xFFFFBB55);
        put("ethylredoxrazine", 0xFFFFCC66);
        put("stimulants", 0xFFFF6622);
        put("hyperzine", 0xFFFF5500);
        put("cryptobiolin", 0xFFFF8833);
        put("epinephrine", 0xFFFF4444);
        put("norepinephrine_acid", 0xFFEE3333);
        put("synaptizine", 0xFFFF77AA);
        put("cognizine", 0xFFFF99CC);
        put("pyrazine", 0xFFEE88FF);

        // Sedatives / psychoactive
        put("haloperidol", 0xFF8866CC);
        put("chloral_hydrate", 0xFF99AACC);
        put("diphenhydramine", 0xFFBB99DD);
        put("diphenylmethylamine", 0xFFAA88CC);
        put("diethylamine", 0xFFCCAAFF);
        put("psicodine", 0xFFCC66FF);
        put("nocturine", 0xFF5533AA);
        put("paks", 0xFF9944CC);
        put("space_drugs", 0xFFFF00FF);
        put("happiness", 0xFFFFEE55);
        put("laughter", 0xFFFFFF88);

        // Toxins
        put("mindbreaker_toxin", 0xFFAA44FF);
        put("heartbreaker_toxin", 0xFFFF2266);
        put("mute_toxin", 0xFF666666);
        put("carpotoxin", 0xFF44AA44);
        put("lipolicide", 0xFF88AA22);
        put("lipozine", 0xFF99BB33);
        put("necrosol", 0xFF442244);
        put("unstable_mutagen", 0xFF88FF44);
        put("doxarubixadone", 0xFF66FF66);
        put("opporozidone", 0xFF55EE55);
        put("phalangimine", 0xFF77CC77);
        put("arkryox", 0xFF44DDAA);

        // Specialty chems
        put("space_cleaner", 0xFFAAFFFF);
        put("bleach", 0xFFEEFFFF);
        put("foaming_agent", 0xFFDDFFEE);
        put("thermite", 0xFFFF4400);
        put("polytrinic_acid", 0xFFFFCC00);
        put("space_glue", 0xFFCCCCFF);
        put("impedrezene", 0xFFBBCCDD);
        put("ipecac", 0xFF99CC66);
        put("histamine", 0xFFFF8888);
        put("vestine", 0xFF88DD88);
        put("tazinide", 0xFF66CC99);
        put("lycoxide", 0xFF55BB88);
        put("lexorin", 0xFF4488BB);
        put("siderlac", 0xFF88CC55);
        put("stellibinin", 0xFFCCFF88);
        put("aloe", 0xFF66DD66);
        put("omnizine", 0xFFAAFFAA);
    }

    private ChemColors() {}

    private static void put(String id, int color) {
        COLORS.put(id, color);
    }

    public static int get(String chemId) {
        Integer color = COLORS.get(chemId);
        if (color != null) {
            return color;
        }
        return hashColor(chemId);
    }

    /** Stable distinct color for unknown/new reagents. */
    private static int hashColor(String chemId) {
        int h = chemId.hashCode();
        int r = 96 + ((h) & 0x7F);
        int g = 96 + ((h >> 8) & 0x7F);
        int b = 96 + ((h >> 16) & 0x7F);
        return 0xFF000000 | (r << 16) | (g << 8) | b;
    }
}
