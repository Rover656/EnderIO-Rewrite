package com.enderio.machines.common.lang;

import com.enderio.EnderIO;
import com.tterrag.registrate.Registrate;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class MachineLang {
    private static final Registrate REGISTRATE = EnderIO.registrate();

    public static final MutableComponent PROGRESS_TOOLTIP = REGISTRATE.addLang("gui", EnderIO.loc("progress"), "Progress %s%%");

    public static final Component ALLOY_SMELTER_MODE = REGISTRATE.addLang("gui", EnderIO.loc("alloy_smelter.mode"), "Smelting Mode");
    public static final Component ALLOY_SMELTER_MODE_ALL = REGISTRATE.addLang("gui", EnderIO.loc("alloy_smelter.mode_all"), "Alloying and Smelting");
    public static final Component ALLOY_SMELTER_MODE_ALLOY = REGISTRATE.addLang("gui", EnderIO.loc("alloy_smelter.mode_alloy"), "Alloys Only");
    public static final Component ALLOY_SMELTER_MODE_FURNACE = REGISTRATE.addLang("gui", EnderIO.loc("alloy_smelter.mode_furnace"), "Furnace Only");
    public static final MutableComponent SAG_MILL_GRINDINGBALL_REMAINING = REGISTRATE.addLang("tooltip", EnderIO.loc("grinding_ball_remaining"), "Remaining: %s%%");
    public static final MutableComponent SAG_MILL_GRINDINGBALL_TITLE = REGISTRATE.addLang("tooltip", EnderIO.loc("grinding_ball_title"), "SAG Mill Grinding Ball");

    public static final Component TOOLTIP_ENERGY_EQUIVALENCE = REGISTRATE.addLang("gui", EnderIO.loc("energy_equivalence"), "A unit of energy, equivalent to FE.");
    public static final MutableComponent TOOLTIP_SAG_MILL_CHANCE_BALL = REGISTRATE.addLang("gui", EnderIO.loc("sag_mill_chance_ball"), "Chance: %s%% (modified by grinding ball)");
    public static final MutableComponent TOOLTIP_SAG_MILL_CHANCE = REGISTRATE.addLang("gui", EnderIO.loc("sag_mill_chance"), "Chance: %s%%");

    // region JEI Categories

    public static final MutableComponent CATEGORY_ALLOY_SMELTING = REGISTRATE.addLang("gui", EnderIO.loc("category.alloy_smelting"), "Alloy Smelting");
    public static final MutableComponent CATEGORY_ENCHANTER = REGISTRATE.addLang("gui", EnderIO.loc("category.enchanter"), "Enchanting");
    public static final MutableComponent CATEGORY_PRIMITIVE_ALLOY_SMELTING = REGISTRATE.addLang("gui", EnderIO.loc("category.primitive_alloy_smelting"), "Primitive Alloy Smelting");
    public static final MutableComponent CATEGORY_SAG_MILL = REGISTRATE.addLang("gui", EnderIO.loc("category.sag_mill"), "SAG Mill");
    public static final MutableComponent CATEGORY_SLICING = REGISTRATE.addLang("gui", EnderIO.loc("category.slicing"), "Slicing");

    // endregion

    public static void register() {}
}
