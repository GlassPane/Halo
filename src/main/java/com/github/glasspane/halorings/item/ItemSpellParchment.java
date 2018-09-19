package com.github.glasspane.halorings.item;

import com.github.glasspane.halorings.HaloRings;
import com.github.glasspane.halorings.api.magic.scroll.ScrollEffect;
import com.github.glasspane.halorings.init.HaloItems;
import com.github.upcraftlp.glasspane.api.util.NBTUtil;
import com.github.upcraftlp.glasspane.item.ItemBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSpellParchment extends ItemBase {

    public static final String NBT_KEY_SPELL = HaloRings.MODID + "_spell";

    public ItemSpellParchment() {
        super("spell_parchment");
        this.setCreativeTab(HaloRings.CREATIVE_TAB);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return NBTUtil.getSubCompound(stack, NBT_KEY_SPELL, false).isEmpty() ? this.getItemStackLimit() : 1;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        ScrollEffect effect = getEffect(stack);
        if(effect != null) {
            effect.playActivationSound(playerIn, worldIn, stack, handIn);
            effect.spawnParticles(playerIn, worldIn, stack, handIn);
            effect.onActivated(playerIn, worldIn, stack, handIn);
            playerIn.getCooldownTracker().setCooldown(this, 40);
            stack.shrink(1);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        super.getSubItems(tab, items);
        if(this.isInCreativeTab(tab)) {
            ScrollEffect.REGISTRY.keySet().forEach(registryName -> items.add(createScroll(registryName)));
        }
    }

    @Nullable
    public static ScrollEffect getEffect(ItemStack stack) {
        String spellName = NBTUtil.getSubCompound(stack, NBT_KEY_SPELL, false).getString("id");
        if(!spellName.isEmpty()) {
            ResourceLocation loc = new ResourceLocation(spellName);
            return ScrollEffect.REGISTRY.get(loc);
        }
        return null;
    }

    public static ItemStack createScroll(ScrollEffect effect) {
        return createScroll(ScrollEffect.REGISTRY.inverse().get(effect));
    }

    public static ItemStack createScroll(ResourceLocation effect) {
        ItemStack stack = new ItemStack(HaloItems.SPELL_PARCHMENT);
        NBTUtil.getSubCompound(stack, NBT_KEY_SPELL).setString("id", effect.toString());
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String spellName = NBTUtil.getSubCompound(stack, NBT_KEY_SPELL, false).getString("id");
        if(!spellName.isEmpty()) {
            tooltip.add(TextFormatting.DARK_PURPLE.toString() + TextFormatting.ITALIC.toString() + I18n.format("spell." + spellName.replace(":", ".") + ".name"));
            tooltip.add(TextFormatting.GRAY.toString() + TextFormatting.ITALIC.toString() + I18n.format("spell." + spellName.replace(":", ".") + ".desc"));
        }
        else tooltip.add(TextFormatting.ITALIC.toString() + I18n.format("spell.none.name"));
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return super.hasEffect(stack) || NBTUtil.getSubCompound(stack, NBT_KEY_SPELL, false).hasKey("id", Constants.NBT.TAG_STRING);
    }
}
