package com.enderio.machines.common.menu;

import com.enderio.machines.common.MachineTier;
import com.enderio.machines.common.blockentity.AlloySmelterBlockEntity;
import com.enderio.machines.common.blockentity.SagMillBlockEntity;
import com.enderio.machines.common.init.MachineMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.Nullable;

public class SagMillMenu extends MachineMenu<SagMillBlockEntity> {
    public SagMillMenu(@Nullable SagMillBlockEntity blockEntity, Inventory inventory, int pContainerId) {
        super(blockEntity, inventory, MachineMenus.SAG_MILL.get(), pContainerId);
        if (blockEntity != null) {
            // Input
            addSlot(new MachineSlot(blockEntity.getItemHandler(), 0, 80, 12));

            // Outputs
            addSlot(new MachineSlot(blockEntity.getItemHandler(), 1, 49, 59));
            addSlot(new MachineSlot(blockEntity.getItemHandler(), 2, 70, 59));
            addSlot(new MachineSlot(blockEntity.getItemHandler(), 3, 91, 59));
            addSlot(new MachineSlot(blockEntity.getItemHandler(), 4, 112, 59));

            // Grinding ball
            addSlot(new MachineSlot(blockEntity.getItemHandler(), 5, 122, 23));

            // Capacitor slot
            if (blockEntity.getTier() != MachineTier.SIMPLE) {
                addSlot(new MachineSlot(blockEntity.getItemHandler(), 6, 12, 60));
            }
        }
        addInventorySlots(8,84);
    }

    public static SagMillMenu factory(@javax.annotation.Nullable MenuType<SagMillMenu> pMenuType, int pContainerId, Inventory inventory, FriendlyByteBuf buf) {
        BlockEntity entity = inventory.player.level.getBlockEntity(buf.readBlockPos());
        if (entity instanceof SagMillBlockEntity castBlockEntity)
            return new SagMillMenu(castBlockEntity, inventory, pContainerId);
        LogManager.getLogger().warn("couldn't find BlockEntity");
        return new SagMillMenu(null, inventory, pContainerId);
    }
}
