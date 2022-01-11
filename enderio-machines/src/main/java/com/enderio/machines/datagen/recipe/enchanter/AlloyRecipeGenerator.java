package com.enderio.machines.datagen.recipe.enchanter;

import com.enderio.base.common.init.EIOBlocks;
import com.enderio.base.common.init.EIOItems;
import com.enderio.base.common.recipe.EIOIngredient;
import com.enderio.base.common.recipe.EnderRecipeResult;
import com.enderio.base.common.tag.EIOTags;
import com.enderio.machines.EIOMachines;
import com.enderio.machines.common.recipe.AlloySmeltingRecipe;
import com.enderio.machines.datagen.recipe.RecipeResult;
import net.minecraft.core.NonNullList;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;
import java.util.function.Consumer;

public class AlloyRecipeGenerator extends RecipeProvider {

    public AlloyRecipeGenerator(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        // TODO: Review all recipes and alloy compositions
        // TODO: Experience values need set properly, i just used a filler value off the top of my head

        // region Metal Alloys

        build(new ItemStack(EIOItems.COPPER_ALLOY_INGOT.get()), List.of(EIOIngredient.of(Tags.Items.INGOTS_COPPER), EIOIngredient.of(EIOTags.Items.SILICON)), 10000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.ENERGETIC_ALLOY_INGOT.get()), List.of(EIOIngredient.of(Tags.Items.DUSTS_REDSTONE), EIOIngredient.of(Tags.Items.INGOTS_GOLD), EIOIngredient.of(Tags.Items.DUSTS_GLOWSTONE)), 10000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.VIBRANT_ALLOY_INGOT.get()), List.of(EIOIngredient.of(EIOItems.ENERGETIC_ALLOY_INGOT.get()), EIOIngredient.of(Tags.Items.ENDER_PEARLS)), 10000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.REDSTONE_ALLOY_INGOT.get()), List.of(EIOIngredient.of(Tags.Items.DUSTS_REDSTONE), EIOIngredient.of(EIOTags.Items.SILICON)), 10000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.CONDUCTIVE_IRON_INGOT.get()), List.of(EIOIngredient.of(Tags.Items.DUSTS_REDSTONE), EIOIngredient.of(Tags.Items.INGOTS_IRON)), 10000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.PULSATING_IRON_INGOT.get()), List.of(EIOIngredient.of(Tags.Items.INGOTS_IRON), EIOIngredient.of(Tags.Items.ENDER_PEARLS)), 10000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.DARK_STEEL_INGOT.get()), List.of(EIOIngredient.of(Tags.Items.INGOTS_IRON), EIOIngredient.of(EIOTags.Items.DUSTS_COAL), EIOIngredient.of(Tags.Items.OBSIDIAN)), 20000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.SOULARIUM_INGOT.get()), List.of(EIOIngredient.of(Items.SOUL_SAND, Items.SOUL_SOIL), EIOIngredient.of(Tags.Items.INGOTS_GOLD)), 10000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.END_STEEL_INGOT.get()), List.of(EIOIngredient.of(Tags.Items.END_STONES), EIOIngredient.of(EIOItems.DARK_STEEL_INGOT.get()), EIOIngredient.of(Tags.Items.OBSIDIAN)), 20000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.NETHERCOTTA.get()), List.of(EIOIngredient.of(Tags.Items.INGOTS_NETHER_BRICK), EIOIngredient.of(4, Items.NETHER_WART), EIOIngredient.of(6, Items.CLAY_BALL)), 20000, 0.3f, pFinishedRecipeConsumer);

        // endregion

        // region Dusts

        // TODO: These are just smelting recipes, prolly reading the old JEI isnt a good idea
//        build(new ItemStack(Items.IRON_INGOT), List.of(EIOIngredient.of(EIOTags.Items.DUSTS_IRON)), 2000, 0.3f, pFinishedRecipeConsumer);
//        build(new ItemStack(Items.GOLD_INGOT), List.of(EIOIngredient.of(EIOTags.Items.DUSTS_GOLD)), 2000, 0.3f, pFinishedRecipeConsumer);

        // endregion

        // region Dyes

        build(new ItemStack(EIOItems.DYE_GREEN.get()), List.of(EIOIngredient.of(Items.GREEN_DYE), EIOIngredient.of(Items.EGG), EIOIngredient.of(EIOTags.Items.DUSTS_COAL)), 1500, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.DYE_GREEN.get(), 2), "double", List.of(EIOIngredient.of(2, Items.GREEN_DYE), EIOIngredient.of(Items.SLIME_BALL), EIOIngredient.of(2, EIOTags.Items.DUSTS_COAL)), 2000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.DYE_GREEN.get()), "clippings", List.of(EIOIngredient.of(6, EIOItems.PLANT_MATTER_GREEN.get()), EIOIngredient.of(Items.EGG)), 1500, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.DYE_GREEN.get(), 2), "double_clippings", List.of(EIOIngredient.of(12, EIOItems.PLANT_MATTER_GREEN.get()), EIOIngredient.of(Items.SLIME_BALL)), 2000, 0.3f, pFinishedRecipeConsumer);

        build(new ItemStack(EIOItems.DYE_BROWN.get()), List.of(EIOIngredient.of(Items.BROWN_DYE), EIOIngredient.of(Items.EGG), EIOIngredient.of(EIOTags.Items.DUSTS_COAL)), 1500, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.DYE_BROWN.get(), 2), "double", List.of(EIOIngredient.of(2, Items.BROWN_DYE), EIOIngredient.of(Items.SLIME_BALL), EIOIngredient.of(2, EIOTags.Items.DUSTS_COAL)), 2000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.DYE_BROWN.get()), "twigs", List.of(EIOIngredient.of(6, EIOItems.PLANT_MATTER_BROWN.get()), EIOIngredient.of(Items.EGG)), 1500, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.DYE_BROWN.get(), 2), "twigs_double", List.of(EIOIngredient.of(12, EIOItems.PLANT_MATTER_BROWN.get()), EIOIngredient.of(Items.SLIME_BALL)), 2000, 0.3f, pFinishedRecipeConsumer);

        build(new ItemStack(EIOItems.DYE_BLACK.get()), List.of(EIOIngredient.of(3, EIOTags.Items.DUSTS_COAL), EIOIngredient.of(Items.EGG)), 1500, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOItems.DYE_BLACK.get(), 2), "double", List.of(EIOIngredient.of(6, EIOTags.Items.DUSTS_COAL), EIOIngredient.of(Items.SLIME_BALL)), 2000, 0.3f, pFinishedRecipeConsumer);

        build(new ItemStack(Items.RED_DYE, 12), List.of(EIOIngredient.of(Items.BEETROOT), EIOIngredient.of(3, Items.CLAY_BALL), EIOIngredient.of(6, Items.EGG)), 15000, 0.3f, pFinishedRecipeConsumer);

        // endregion

        // region Chassis

        build(new ItemStack(EIOBlocks.INDUSTRIAL_MACHINE_CHASSIS.get()), List.of(EIOIngredient.of(EIOBlocks.SIMPLE_MACHINE_CHASSIS.get()), EIOIngredient.of(EIOItems.DYE_INDUSTRIAL_BLEND.get())), 3600, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOBlocks.ENHANCED_MACHINE_CHASSIS.get()), List.of(EIOIngredient.of(EIOBlocks.END_STEEL_MACHINE_CHASSIS.get()), EIOIngredient.of(EIOItems.DYE_ENHANCED_BLEND.get())), 3600, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOBlocks.SOUL_MACHINE_CHASSIS.get()), List.of(EIOIngredient.of(EIOBlocks.SIMPLE_MACHINE_CHASSIS.get()), EIOIngredient.of(EIOItems.DYE_SOUL_ATTUNED_BLEND.get())), 3600, 0.3f, pFinishedRecipeConsumer);

        // endregion

        // region Misc

        build(new ItemStack(EIOItems.CAKE_BASE.get(), 2), List.of(EIOIngredient.of(3, EIOItems.FLOUR.get()), EIOIngredient.of(Items.EGG)), 2000, 0.3f, pFinishedRecipeConsumer);

        build(new ItemStack(Items.COOKIE, 8), List.of(EIOIngredient.of(EIOItems.FLOUR.get()), EIOIngredient.of(Items.COCOA_BEANS)), 2000, 0.3f, pFinishedRecipeConsumer);

        build(new ItemStack(EIOBlocks.QUITE_CLEAR_GLASS.CLEAR.get()), List.of(EIOIngredient.of(Tags.Items.GLASS_COLORLESS)), 2500, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOBlocks.FUSED_QUARTZ.CLEAR.get()), List.of(EIOIngredient.of(4, Tags.Items.GEMS_QUARTZ)), 5000, 0.3f, pFinishedRecipeConsumer);
        build(new ItemStack(EIOBlocks.FUSED_QUARTZ.CLEAR.get()), "block", List.of(EIOIngredient.of(Tags.Items.STORAGE_BLOCKS_QUARTZ)), 5000, 0.3f, pFinishedRecipeConsumer);

        build(new ItemStack(EIOItems.PHOTOVOLTAIC_PLATE.get()), List.of(EIOIngredient.of(3, EIOItems.PHOTOVOLTAIC_COMPOSITE.get())), 15000, 0.3f, pFinishedRecipeConsumer);

        build(new ItemStack(Items.ENDER_PEARL), List.of(EIOIngredient.of(9, EIOItems.ENDER_PEARL_POWDER.get())), 2000, 0.3f, pFinishedRecipeConsumer);

        // TODO: Infinity reagent
//        build(new ItemStack(), List.of(EIOIngredient.of(EIOItems.GRAINS_OF_INFINITY.get()), EIOIngredient.of(EIOTags.Items.DUSTS_COAL)), 5000, 0.3f, pFinishedRecipeConsumer);

        build(new ItemStack(Items.DEAD_BUSH), List.of(EIOIngredient.of(ItemTags.SAPLINGS)), 2000, 0.3f, pFinishedRecipeConsumer);

        build(new ItemStack(EIOItems.DARK_STEEL_UPGRADE_BLANK.get()), List.of(EIOIngredient.of(EIOBlocks.DARK_STEEL_BARS.get()), EIOIngredient.of(Items.CLAY_BALL), EIOIngredient.of(4, Items.STRING)), 30000, 0.3f, pFinishedRecipeConsumer);

        build(new ItemStack(EIOItems.CLAYED_GLOWSTONE.get(), 2), List.of(EIOIngredient.of(Tags.Items.DUSTS_GLOWSTONE), EIOIngredient.of(Items.CLAY_BALL)), 5000, 0.3f, pFinishedRecipeConsumer);

        // endregion
    }

    protected void build(ItemStack result, List<EIOIngredient> ingredients, int energy, float experience, Consumer<FinishedRecipe> recipeConsumer) {
        build(new AlloySmeltingRecipe(null, ingredients, result, energy, experience), result.getItem().getRegistryName().getPath(), recipeConsumer);
    }

    protected void build(ItemStack result, String suffix, List<EIOIngredient> ingredients, int energy, float experience, Consumer<FinishedRecipe> recipeConsumer) {
        build(new AlloySmeltingRecipe(null, ingredients, result, energy, experience), result.getItem().getRegistryName().getPath() + "_" + suffix, recipeConsumer);
    }

    protected void build(AlloySmeltingRecipe recipe, String name, Consumer<FinishedRecipe> recipeConsumer) {
        recipeConsumer.accept(new EnderRecipeResult<>(recipe, EIOMachines.MODID, name));
    }
}
