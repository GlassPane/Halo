package com.github.glasspane.halorings.magic;

import com.github.glasspane.halorings.api.capability.IMagicCaster;
import com.github.glasspane.halorings.config.HaloRingsConfig;
import com.google.common.collect.ImmutableSet;
import net.minecraft.util.ResourceLocation;

import java.util.*;

public class MagicCaster implements IMagicCaster {

    private int casterLevel;
    private int experience;
    private int mana;
    private int maxMana;
    private final Set<ResourceLocation> spells = new HashSet<>();

    @Override
    public int getCasterLevel() {
        return casterLevel;
    }

    @Override
    public void setCasterLevel(int level) {
        casterLevel = Math.max(0, level);
    }

    @Override
    public int addCasterLevel() {
        if(++casterLevel < 0) casterLevel = 0;
        return casterLevel;
    }

    @Override
    public int getXP() {
        return experience;
    }

    @Override
    public void setXP(int xp) {
        experience = Math.max(0, xp);
        clampXP();
    }

    private void clampXP() {
        while(experience > HaloRingsConfig.magic.xpPerLevel) {
            this.addCasterLevel();
            experience -= HaloRingsConfig.magic.xpPerLevel;
        }
    }

    @Override
    public void addXP(int xp) {
        xp = Math.max(0, xp);
        experience += xp;
        if(experience < 0) experience = Integer.MAX_VALUE; //overflow
        clampXP();
    }

    @Override
    public int getCurrentMana() {
        return mana;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public void setCurrentMana(int amount) {
        mana = Math.max(0, amount);
    }

    @Override
    public void setMaxMana(int max) {
        maxMana = Math.max(0, max);
    }

    @Override
    public boolean hasLearnedSpell(ResourceLocation spell) {
        return spells.contains(spell);
    }

    @Override
    public void unlockSpell(ResourceLocation spell) {
        spells.add(spell);
    }

    @Override
    public Collection<ResourceLocation> getLearnedSpells() {
        return ImmutableSet.copyOf(spells);
    }

    @Override
    public void setUnlockedSpells(Collection<ResourceLocation> spells) {
        this.spells.clear();
        this.spells.addAll(spells);
    }
}
