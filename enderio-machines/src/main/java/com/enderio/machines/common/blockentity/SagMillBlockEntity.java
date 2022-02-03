package com.enderio.machines.common.blockentity;

import com.enderio.base.common.util.CapacitorUtil;
import com.enderio.machines.common.MachineTier;
import com.enderio.machines.common.blockentity.base.PoweredCraftingMachineEntity;
import com.enderio.machines.common.blockentity.data.sidecontrol.item.ItemHandlerMaster;
import com.enderio.machines.common.blockentity.data.sidecontrol.item.ItemSlotLayout;
import com.enderio.machines.common.init.MachineRecipes;
import com.enderio.machines.common.menu.SagMillMenu;
import com.enderio.machines.common.recipe.SagMillRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class SagMillBlockEntity extends PoweredCraftingMachineEntity<SagMillRecipe> {
    public static class Simple extends SagMillBlockEntity {
        public Simple(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
            super(pType, pWorldPosition, pBlockState);
        }

        @Override
        public MachineTier getTier() {
            return MachineTier.SIMPLE;
        }

        @Override
        public int getEnergyCapacity() {
            return 2000;
        }
    }

    public SagMillBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    @Override
    protected RecipeType<?> getRecipeType() {
        return MachineRecipes.Types.SAGMILLING;
    }

    @Override
    protected void assembleRecipe(SagMillRecipe recipe) {

    }

    @Override
    protected boolean canTakeOutput(SagMillRecipe recipe) {
        return false;
    }

    @Override
    protected void consumeIngredients(SagMillRecipe recipe) {

    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new SagMillMenu(this, inventory, containerId);
    }

    @Override
    public Optional<ItemSlotLayout> getSlotLayout() {
        // TODO: Maybe not using "upgrades" for the extra input
        if (getTier() == MachineTier.SIMPLE)
            return Optional.of(ItemSlotLayout.basic(1, 4, 1));
        return Optional.of(ItemSlotLayout.withCapacitor(1, 4, 1));
    }

    @Override
    protected ItemHandlerMaster createItemHandler(ItemSlotLayout layout) {
        return new ItemHandlerMaster(getIoConfig(), layout) {
            @NotNull
            @Override
            public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                // If we already have a recipe, disallow use of an empty slot.
                // TODO: This doesn't work too great. Need a better solution.
                //            if (getOnlyInputs().contains(slot) && getCurrentRecipe() != null && getStackInSlot(slot).isEmpty())
                //                return stack;
                if (slot == 6 && !CapacitorUtil.isCapacitor(stack))
                    return stack;
                return super.insertItem(slot, stack, simulate);
            }

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }
}
