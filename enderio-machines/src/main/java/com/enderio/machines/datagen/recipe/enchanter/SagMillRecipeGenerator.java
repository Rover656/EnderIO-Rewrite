package com.enderio.machines.datagen.recipe.enchanter;

import com.enderio.base.common.recipe.EnderRecipeResult;
import com.enderio.machines.EIOMachines;
import com.enderio.machines.common.recipe.RecipeBonusType;
import com.enderio.machines.common.recipe.SagMillRecipe;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.function.Consumer;

public class SagMillRecipeGenerator extends RecipeProvider {
    public SagMillRecipeGenerator(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer) {
        build("cobblestone", Ingredient.of(Blocks.STONE), List.of(SagMillRecipe.Output.of(Blocks.COBBLESTONE)), 2400, RecipeBonusType.NONE, finishedRecipeConsumer);
    }

    protected void build(String name, Ingredient input, List<SagMillRecipe.Output> outputs, int energy, RecipeBonusType bonus, Consumer<FinishedRecipe> recipeConsumer) {
        build(new SagMillRecipe(null, input, outputs, energy, bonus), name, recipeConsumer);
    }

    protected void build(SagMillRecipe recipe, String name, Consumer<FinishedRecipe> recipeConsumer) {
        recipeConsumer.accept(new EnderRecipeResult<>(recipe, EIOMachines.MODID, name));
    }
}
