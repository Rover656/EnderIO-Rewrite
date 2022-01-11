package com.enderio.base.common.recipe;

import com.enderio.base.EnderIO;
import net.minecraft.ResourceLocationException;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface IEnderRecipe<R extends IEnderRecipe<R, C>, C extends Container> extends Recipe<C> {
    @Override
    DataGenSerializer<R, C> getSerializer();

    default String getOwningMod() {
        return EnderIO.MODID;
    }

    List<EIOIngredient> getInputs();

    // TODO: Fluid ingredient support?

    /**
     * Return an empty list if the output is dynamic.
     * @see Recipe#getResultItem() Recipe#getResultItem() for more information.
     */
    List<ItemStack> getOutputs();

    default List<ResourceLocation> getOtherDependencies() {
        return List.of();
    }

    default List<String> getModDependencies() {
        Set<String> mods = new HashSet<>();
        // TODO!!!! borks
//        for (EIOIngredient ingredient : getInputs()) {
//            for (ItemStack item : ingredient.getItems()) {
//                String mod = item.getItem().getRegistryName().getNamespace();
//                mods.add(mod);
//            }
//        }

        getOutputs().forEach(dep -> mods.add(dep.getItem().getRegistryName().getNamespace()));
        getOtherDependencies().forEach(dep -> mods.add(dep.getNamespace()));

        return mods.stream().filter(mod -> !StringUtils.equalsAny(mod, "minecraft", "forge", "enderio", getOwningMod())).toList();
    }
}
