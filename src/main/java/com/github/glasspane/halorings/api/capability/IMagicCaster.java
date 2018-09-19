package com.github.glasspane.halorings.api.capability;

import net.minecraft.util.ResourceLocation;

import java.util.Collection;

public interface IMagicCaster {

    /**
     * @return the level of the magic caster
     * 20,000 XP = 1 level
     */
    int getCasterLevel();

    /**
     * set the caster level
     */
    void setCasterLevel(int level);

    /**
     * increase the caster's level by one.
     *
     * @return the new level
     */
    int addCasterLevel();

    /**
     * @return the current caster XP
     */
    int getXP();

    /**
     * set the current caster XP
     * @param xp the new XP amount
     */
    void setXP(int xp);

    /**
     * add XP to the current caster, leveling up if exceeding 20,000 points
     * @param xp how many XP to add
     */
    void addXP(int xp);

    int getCurrentMana();

    int getMaxMana();

    void setCurrentMana(int mana);

    void setMaxMana(int max);

    boolean hasLearnedSpell(ResourceLocation spell);

    void unlockSpell(ResourceLocation spell);

    Collection<ResourceLocation> getLearnedSpells();

    void setUnlockedSpells(Collection<ResourceLocation> spells);
}
