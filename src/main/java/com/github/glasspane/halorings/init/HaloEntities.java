package com.github.glasspane.halorings.init;

import com.github.glasspane.halorings.HaloRings;
import com.github.glasspane.halorings.entity.EntityMagicCircle;
import com.github.glasspane.halorings.entity.EntityMagicCircleAgeable;
import com.github.upcraftlp.glasspane.api.registry.AutoRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@SuppressWarnings("unused")
@AutoRegistry(HaloRings.MODID)
public class HaloEntities {

    public static EntityEntry MAGIC_CIRCLE =
            EntityEntryBuilder.create()
                    .entity(EntityMagicCircle.class)
                    .id(new ResourceLocation(HaloRings.MODID, "magic_circle"), 0)
                    .name(HaloRings.MODID + ".magic_circle")
                    .factory(EntityMagicCircle::new)
                    .tracker(128, 10, false)
                    .build();

    public static EntityEntry MAGIC_CIRCLE_AGEABLE =
            EntityEntryBuilder.create()
                    .entity(EntityMagicCircleAgeable.class)
                    .id(new ResourceLocation(HaloRings.MODID, "magic_circle_ageable"), 1)
                    .name(HaloRings.MODID + ".magic_circle_ageable")
                    .factory(EntityMagicCircleAgeable::new)
                    .tracker(128, 10, false)
                    .build();
}
