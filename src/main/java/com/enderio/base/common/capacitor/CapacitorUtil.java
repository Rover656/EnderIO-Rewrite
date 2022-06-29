package com.enderio.base.common.capacitor;

import com.enderio.api.capability.ICapacitorData;
import com.enderio.api.capacitor.CapacitorKey;
import com.enderio.EnderIO;
import com.enderio.base.common.init.EIOCapabilities;
import com.enderio.base.common.init.EIORegistries;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Helper class for Capacitors
 */
@Mod.EventBusSubscriber(modid = EnderIO.MODID)
public class CapacitorUtil {
    /**
     * Adds a tooltip for loot capacitors based on it's stats.
     *
     * @param stack
     * @param tooltipComponents
     */
    public static void getTooltip(ItemStack stack, List<Component> tooltipComponents) {
        // TODO: Revisit in future
//        stack.getCapability(EIOCapabilities.CAPACITOR).ifPresent(cap -> {
//            if (cap.getSpecializations().size() > 0) {
//                TranslatableComponent t = new TranslatableComponent(getFlavor(cap.getFlavor()),
//                    getGradeText(cap.getSpecializations().values().iterator().next()),
//                    getTypeText(cap.getSpecializations().keySet().iterator().next()),
//                    getBaseText(cap.getBase()));
//                tooltipComponents.add(t);
//            }
//        });
    }

    //TODO depending on direction
    private static String getFlavor(int flavor) {
        return "description.enderio.capacitor.flavor." + flavor;
    }

    //TODO depending on direction
    private static MutableComponent getBaseText(float base) {
        MutableComponent t = Component.translatable("description.enderio.capacitor.base." + (int) Math.ceil(base));
        t.withStyle(ChatFormatting.ITALIC);
        return t;
    }

    //TODO depending on direction
    private static MutableComponent getTypeText(String type) {
        MutableComponent t = Component.translatable("description.enderio.capacitor.type." + type);
        t.withStyle(ChatFormatting.ITALIC);
        return t;
    }

    //TODO depending on direction
    private static MutableComponent getGradeText(float grade) {
        MutableComponent t = Component.translatable("description.enderio.capacitor.grade." + (int) Math.ceil(grade));
        t.withStyle(ChatFormatting.ITALIC);
        return t;
    }

    public static Optional<ICapacitorData> getCapacitorData(ItemStack itemStack) {
        // Search for an ICapacitorData capability
        LazyOptional<ICapacitorData> capacitorDataCap = itemStack.getCapability(EIOCapabilities.CAPACITOR);
        if (capacitorDataCap.isPresent())
            return Optional.of(capacitorDataCap.orElseThrow(NullPointerException::new));
        return Optional.empty();
    }

    public static boolean isCapacitor(ItemStack itemStack) {
        LazyOptional<ICapacitorData> capacitorDataCap = itemStack.getCapability(EIOCapabilities.CAPACITOR);
        return capacitorDataCap.isPresent();
    }

    public static CapacitorKey getRandomKey() {
        CapacitorKey[] keys = EIORegistries.CAPACITOR_KEYS_REGISTRY.get().getValues().toArray(new CapacitorKey[0]);
        return keys[new Random().nextInt(keys.length)];
    }
}
