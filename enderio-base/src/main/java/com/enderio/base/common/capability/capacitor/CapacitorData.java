package com.enderio.base.common.capability.capacitor;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CapacitorData implements ICapacitorData {
    private float base;
    private final Map<ResourceLocation, Float> specializations;
    private int flavor = -1;

    public CapacitorData() {
        this(1.0f);
    }

    public CapacitorData(float base) {
        this.base = base;
        this.specializations = new HashMap<>();
    }

    public CapacitorData(float base, Map<ResourceLocation, Float> specializations) {
        this.base = base;
        this.specializations = specializations;
    }

    @Override
    public float getBase() {
        return this.base;
    }

    @Override
    public void setBase(float value) {
        base = value;
    }

    @Override
    public float getScaledFloat(ResourceLocation specialization, float value) {
        return value * specializations.getOrDefault(specialization, base);
    }

    @Override
    public void addSpecialization(ResourceLocation type, float value) {
        this.specializations.put(type, value);
    }

    @Override
    public void addNewSpecialization(ResourceLocation type, float modifier) {
        specializations.clear();
        addSpecialization(type, modifier);
    }

    @Override
    public Map<ResourceLocation, Float> getSpecializations() {
        return specializations;
    }

    // TODO more flavor text
    @Override
    public int getFlavor() {
        if (this.flavor == -1) {
            this.flavor = new Random().nextInt(2);
        }
        return this.flavor;
    }

    // region Serialization

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putFloat("base", this.base);
        ListTag list = new ListTag();
        this.specializations.forEach((s, f) -> {
            CompoundTag entry = new CompoundTag();
            entry.putString("key", s.toString());
            entry.putFloat("value", f);
            list.add(entry);
        });
        nbt.put("specializations", list);
        return nbt;
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        if (nbt instanceof CompoundTag tag) {
            this.specializations.clear();
            this.base = tag.getFloat("base");
            ListTag list = tag.getList("specializations", Tag.TAG_COMPOUND);
            for (int i = 0; i < list.size(); i++) {
                CompoundTag listElement = list.getCompound(i);
                addSpecialization(new ResourceLocation(listElement.getString("key")), listElement.getFloat("value"));
            }
        }
    }

    // endregion
}
