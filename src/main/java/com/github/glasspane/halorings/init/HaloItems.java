package com.github.glasspane.halorings.init;

import com.github.glasspane.halorings.HaloRings;
import com.github.glasspane.halorings.item.ItemSpellParchment;
import com.github.upcraftlp.glasspane.api.registry.AutoRegistry;
import net.minecraft.item.Item;

@SuppressWarnings("unused")
@AutoRegistry(HaloRings.MODID)
public class HaloItems {

    public static final Item SPELL_PARCHMENT = new ItemSpellParchment();

}
