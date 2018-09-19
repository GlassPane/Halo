package com.github.glasspane.halorings.api.magic.scroll;

import com.google.common.collect.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.*;

@SuppressWarnings("unused")
public abstract class ScrollEffect {

    public static final BiMap<ResourceLocation, ScrollEffect> REGISTRY = HashBiMap.create();

    public abstract void onActivated(EntityPlayer player, World world, ItemStack stack, EnumHand hand);

    public void playActivationSound(EntityPlayer player, World world, ItemStack stack, EnumHand hand) {
        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.UI_TOAST_IN, SoundCategory.PLAYERS, 2.4F, 0.4F);
    }

    public void spawnParticles(EntityPlayer player, World world, ItemStack stack, EnumHand hand) {
        //TODO adjust particle speed?
        //TODO blue flame particle
        //TODO offset particles to appear at the used hand instead of inside the player
        if(!world.isRemote) {
            ((WorldServer) world).spawnParticle(EnumParticleTypes.END_ROD, false, player.posX, player.posY + 1.2D, player.posZ, 30, 0.0D, 0.0D, 0.0D, 0.035D);
        }
    }
}
