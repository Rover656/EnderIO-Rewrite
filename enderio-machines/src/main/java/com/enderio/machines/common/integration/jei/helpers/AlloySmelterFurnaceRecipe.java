package com.enderio.machines.common.integration.jei.helpers;

import com.enderio.base.common.recipe.EIOIngredient;
import com.enderio.machines.common.blockentity.AlloySmelterBlockEntity;
import com.enderio.machines.common.recipe.AlloySmeltingRecipe;
import net.minecraft.world.item.crafting.SmeltingRecipe;

public class AlloySmelterFurnaceRecipe extends AlloySmeltingRecipe {
    public AlloySmelterFurnaceRecipe(SmeltingRecipe recipe) {
        super(recipe.getId(), recipe.getIngredients().stream().map(EIOIngredient::fromVanilla).toList(), recipe.getResultItem(), AlloySmelterBlockEntity.RF_PER_ITEM, recipe.getExperience());
    }
}
