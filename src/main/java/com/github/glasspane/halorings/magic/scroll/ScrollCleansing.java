package com.github.glasspane.halorings.magic.scroll;

import com.github.glasspane.halorings.api.magic.scroll.ScrollEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.stream.Collectors;

public class ScrollCleansing extends ScrollEffect {

    @Override
    public void onActivated(EntityPlayer player, World world, ItemStack stack, EnumHand hand) {
        player.sendStatusMessage(new TextComponentTranslation("spell.halo_rings.cleansing.desc").setStyle(new Style().setItalic(true).setColor(TextFormatting.GRAY)), true);
        List<Potion> badEffects = player.getActivePotionEffects().stream().filter(effect -> effect.getPotion().isBadEffect()).map(PotionEffect::getPotion).collect(Collectors.toList());
        badEffects.forEach(player::removePotionEffect);
    }
}
