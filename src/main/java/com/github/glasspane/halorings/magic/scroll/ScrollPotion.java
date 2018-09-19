package com.github.glasspane.halorings.magic.scroll;

import com.github.glasspane.halorings.api.magic.scroll.ScrollEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ScrollPotion extends ScrollEffect {

    private final Potion potion;
    private final int duration;
    private final int amplifier;

    public ScrollPotion(Potion potion, int duration, int amplifier) {
        this.potion = potion;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public void onActivated(EntityPlayer player, World world, ItemStack stack, EnumHand hand) {
        player.addPotionEffect(new PotionEffect(this.potion, this.duration, this.amplifier));
    }
}
