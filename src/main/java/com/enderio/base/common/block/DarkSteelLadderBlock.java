package com.enderio.base.common.block;

import com.enderio.base.common.lang.EIOLang;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.LadderBlock;

import org.jetbrains.annotations.Nullable;
import java.util.List;

public class DarkSteelLadderBlock extends LadderBlock {

    public DarkSteelLadderBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(EIOLang.DARK_STEEL_LADDER_FASTER);
    }
}
