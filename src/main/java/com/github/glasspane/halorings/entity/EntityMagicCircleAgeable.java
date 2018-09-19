package com.github.glasspane.halorings.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMagicCircleAgeable extends EntityMagicCircle {

    private static final DataParameter<Integer> MAX_AGE = EntityDataManager.createKey(EntityMagicCircleAgeable.class, DataSerializers.VARINT);

    public EntityMagicCircleAgeable(World worldIn, int color) {
        this(worldIn, color, 6000);
    }

    public EntityMagicCircleAgeable(World worldIn, int color, int maxAgeTicks) {
        super(worldIn, color);
        this.setMaxAge(maxAgeTicks);
    }

    public EntityMagicCircleAgeable(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(MAX_AGE, 20);
    }

    public void setMaxAge(int maxAge) {
        this.dataManager.set(MAX_AGE, maxAge);
    }

    public int getMaxAge() {
        return this.dataManager.get(MAX_AGE);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setMaxAge(compound.getInteger("MaxAge"));
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("MaxAge", this.getMaxAge());
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        if(this.ticksExisted > this.getMaxAge()) this.setDead();
    }

    @Override
    public int getColor() {
        //TODO move to "explosive mine" entity
        int alpha = (int) (MathHelper.clamp(1.0F - ((float) this.ticksExisted) / this.getMaxAge(), 0.12F, 1.0F) * 255.0F);
        return (super.getColor() & 0x00FFFFFF) | (alpha << 24);
    }
}
