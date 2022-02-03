package com.enderio.machines.common.energy;

import com.enderio.machines.common.MachineTier;
import com.enderio.machines.common.blockentity.base.PoweredMachineEntity;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.IEnergyStorage;

public class MachineEnergyStorage implements INBTSerializable<Tag>, IEnergyStorage {
    protected int storedEnergy;

    // TODO: Provide via constructor
    protected int baseMaxTransfer = 120;
    protected int baseMaxConsumption = 40;

    private final PoweredMachineEntity owner;

    public MachineEnergyStorage(PoweredMachineEntity machine) {
        this.owner = machine;
    }

    // region Getters

    @Override
    public int getEnergyStored() {
//        return storedEnergy;
        // TODO: For testing
        return getMaxEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        if (owner.getTier() == MachineTier.SIMPLE)
            return owner.getEnergyCapacity();
        return owner.getCapacitorData().map(data -> data.getScaled(owner.getEnergyBufferKey(), owner.getEnergyCapacity())).orElse(0);
    }

    public int getMaxEnergyConsumption() {
        return baseMaxConsumption;
    }

    public int getMaxEnergyTransfer() {
        return baseMaxTransfer;
    }

    // endregion

    // region Energy Manipulation

    public void setStoredEnergy(int energy) {
        this.storedEnergy = Math.min(energy, getMaxEnergyStored());
        onEnergyChanged();
    }

    public void addEnergy(int energy) {
        this.storedEnergy = Math.min(this.storedEnergy + energy, getMaxEnergyStored());
        onEnergyChanged();
    }

    public boolean hasEnergy(int energy) {
        return storedEnergy >= energy;
    }

    /**
     * Consume energy.
     * Returns the amount of energy consumed.
     * @param energy
     * @return
     */
    public int consumeEnergy(int energy) {
        int energyConsumed = Math.min(energy, Math.min(getMaxEnergyConsumption(), energy));
        this.storedEnergy -= energyConsumed;
        return energyConsumed;
    }

    // endregion

    // region Forge Energy API Receive and Extract

    // TODO

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public boolean canExtract() {
        return getMaxEnergyTransfer() > 0;
    }

    @Override
    public boolean canReceive() {
        return getMaxEnergyTransfer() > 0;
    }

    // endregion

    // Override in a BE to run setChanged
    protected void onEnergyChanged() { }

    // region Serialization

    @Override
    public Tag serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(Tag nbt) {

    }

    // endregion
}
