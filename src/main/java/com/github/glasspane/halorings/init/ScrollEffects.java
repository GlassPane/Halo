package com.github.glasspane.halorings.init;

import com.github.glasspane.halorings.HaloRings;
import com.github.glasspane.halorings.api.magic.scroll.ScrollEffect;
import com.github.glasspane.halorings.magic.scroll.ScrollCleansing;
import com.github.glasspane.halorings.magic.scroll.ScrollPotion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ScrollEffects {

    public static void init() {
        register(CLEANSING, "cleansing");

        //TODO manually create scrolls for only certain potions
        ForgeRegistries.POTIONS.getValuesCollection().stream().filter(potion -> !potion.isBadEffect()).forEach(potion -> register(new ScrollPotion(potion, 3600, 1), "potion_" + potion.getRegistryName().toString().replace(":", ".")));
    }

    private static void register(ScrollEffect effect, String name) {
        ScrollEffect.REGISTRY.put(new ResourceLocation(HaloRings.MODID, name), effect);
    }

    public static final ScrollEffect CLEANSING = new ScrollCleansing();
}
