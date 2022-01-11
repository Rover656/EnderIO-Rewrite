package com.enderio.base.common.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * Helpful wrapper around vanilla recipes for machines.
 */
public class WrappedVanillaRecipe<R extends Recipe<C>, C extends Container> implements IEnderRecipe<WrappedVanillaRecipe<R, C>, C> {
    private final R recipe;

    public WrappedVanillaRecipe(R recipe) {
        this.recipe = recipe;
    }

    @Override
    public String getOwningMod() {
        return "minecraft";
    }

    @Override
    public List<EIOIngredient> getInputs() {
        List<Ingredient> ingredients = this.recipe.getIngredients();
        return ingredients.stream().map(EIOIngredient::fromVanilla).toList();
    }

    @Override
    public List<ItemStack> getOutputs() {
        // This doesn't really matter too much.
        return List.of(this.recipe.getResultItem());
    }

    @Override
    public boolean matches(C container, Level level) {
        return this.recipe.matches(container, level);
    }

    @Override
    public ItemStack assemble(C container) {
        return this.recipe.assemble(container);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return this.recipe.canCraftInDimensions(width, height);
    }

    @Override
    public ItemStack getResultItem() {
        return this.recipe.getResultItem();
    }

    @Override
    public ResourceLocation getId() {
        return this.recipe.getId();
    }

    @Override
    public DataGenSerializer<WrappedVanillaRecipe<R, C>, C> getSerializer() {
        // No serialization from wrappers thanks!
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return this.recipe.getType();
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(C container) {
        return this.recipe.getRemainingItems(container);
    }

    @Override
    public boolean isSpecial() {
        return this.recipe.isSpecial();
    }

    @Override
    public String getGroup() {
        return this.recipe.getGroup();
    }

    @Override
    public ItemStack getToastSymbol() {
        return this.recipe.getToastSymbol();
    }

    @Override
    public boolean isIncomplete() {
        return this.recipe.isIncomplete();
    }
}
