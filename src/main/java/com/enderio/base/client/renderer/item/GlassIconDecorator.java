package com.enderio.base.client.renderer.item;

import com.enderio.base.common.block.glass.FusedQuartzBlock;
import com.enderio.core.client.gui.screen.IEnderScreen;
import com.enderio.core.common.util.Vector2i;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemDecorator;

public class GlassIconDecorator implements IItemDecorator {
    public static final GlassIconDecorator INSTANCE = new GlassIconDecorator();

    private static final float COUNT_BLIT_HEIGHT = 200;

    @Override
    public boolean render(Font font, ItemStack stack, int xOffset, int yOffset, float blitOffset) {
        PoseStack poseStack = new PoseStack();
        poseStack.translate(xOffset, yOffset, blitOffset + COUNT_BLIT_HEIGHT - 1);
        if (stack.getItem() instanceof BlockItem blockItem) {
            if (blockItem.getBlock() instanceof FusedQuartzBlock block) {
                IEnderScreen.renderIcon(poseStack, new Vector2i(0,0), block.getCollisionPredicate());
                IEnderScreen.renderIcon(poseStack, new Vector2i(0,0), block.getGlassLighting());
                return true;
            }
        }
        return false;
    }
}
