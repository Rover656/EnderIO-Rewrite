package com.enderio.machines.common.menu;

import com.enderio.machines.common.io.item.MachineInventory;
import com.enderio.machines.common.io.item.NMachineInventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class MachineSlot extends SlotItemHandler {

    public MachineSlot(NMachineInventory itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public NMachineInventory getItemHandler() {
        return (NMachineInventory) super.getItemHandler();
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return getItemHandler().getLayout().guiCanInsert(this.getSlotIndex()) && super.mayPlace(stack);
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        return getItemHandler().getLayout().guiCanExtract(this.getSlotIndex()) && super.mayPickup(playerIn);
    }
}
