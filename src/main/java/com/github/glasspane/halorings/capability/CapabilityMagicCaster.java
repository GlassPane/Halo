package com.github.glasspane.halorings.capability;

import com.github.glasspane.halorings.api.capability.IMagicCaster;
import com.github.glasspane.halorings.magic.MagicCaster;
import net.minecraft.nbt.*;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.Constants;

import java.util.stream.*;

public class CapabilityMagicCaster {

    @CapabilityInject(IMagicCaster.class)
    public static final Capability<IMagicCaster> MAGIC_CASTER_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IMagicCaster.class, new Capability.IStorage<IMagicCaster>() {
                    @Override
                    public NBTBase writeNBT(Capability<IMagicCaster> capability, IMagicCaster instance, EnumFacing side) {
                        NBTTagCompound nbt = new NBTTagCompound();
                        nbt.setInteger("Level", instance.getCasterLevel());
                        nbt.setInteger("Xp", instance.getXP());
                        nbt.setInteger("Mana", instance.getCurrentMana());
                        nbt.setInteger("MaxMana", instance.getMaxMana());
                        NBTTagList list = new NBTTagList();
                        instance.getLearnedSpells().forEach(spell -> list.appendTag(new NBTTagString(spell.toString())));
                        nbt.setTag("Spells", list);
                        return nbt;
                    }

                    @Override
                    public void readNBT(Capability<IMagicCaster> capability, IMagicCaster instance, EnumFacing side, NBTBase nbt) {
                        if(instance instanceof MagicCaster) {
                            MagicCaster caster = (MagicCaster) instance;
                            NBTTagCompound data = (NBTTagCompound) nbt;
                            caster.setCasterLevel(data.getInteger("Level"));
                            caster.setXP(data.getInteger("Xp"));
                            caster.setCurrentMana(data.getInteger("Mana"));
                            caster.setMaxMana(data.getInteger("MaxMana"));
                            caster.setUnlockedSpells(StreamSupport.stream(data.getTagList("Spells", Constants.NBT.TAG_STRING).spliterator(), false).map(tag -> new ResourceLocation(((NBTTagString) tag).getString())).collect(Collectors.toSet()));
                        }
                        else {
                            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                        }

                    }
                },
                MagicCaster::new);
    }
}
