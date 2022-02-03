package com.enderio.base.common.capability.capacitor;

import com.enderio.base.common.capability.INamedNBTSerializable;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

/**
 * interface for storing capacitor data
 */
public interface ICapacitorData extends INamedNBTSerializable<Tag> {
    @Override
    default String getSerializedName() {
        return "CapacitorData";
    }

    /**
     * Sets Base value used for non specialization.
     */
    float getBase();

    void setBase(float value);

    default int getScaled(ResourceLocation specialization, int value) {
        return (int) getScaledFloat(specialization, value);
    }

    float getScaledFloat(ResourceLocation specialization, float value);

    /**
     * Add a specialization.
     */
    void addSpecialization(ResourceLocation type, float modifier);

    void addNewSpecialization(ResourceLocation type, float modifier);

    Map<ResourceLocation, Float> getSpecializations();

    /**
     * Flavor text used by loot capacitor.
     */
    int getFlavor();

}
