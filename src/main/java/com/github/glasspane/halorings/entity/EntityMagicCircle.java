package com.github.glasspane.halorings.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityMagicCircle extends Entity {

    private static final DataParameter<Integer> COLOR = EntityDataManager.createKey(EntityMagicCircle.class, DataSerializers.VARINT);

    public EntityMagicCircle(World worldIn, int color) {
        this(worldIn);
        this.setColor(color);
    }

    public EntityMagicCircle(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        this.dataManager.register(COLOR, 0xFFFFFFFF);
    }

    public void setColor(int color) {
        this.dataManager.set(COLOR, color);
    }

    public int getColor() {
        return this.dataManager.get(COLOR);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        this.setColor(compound.getInteger("Color"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("Color", this.getColor());
    }
}
