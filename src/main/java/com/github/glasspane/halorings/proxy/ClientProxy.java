package com.github.glasspane.halorings.proxy;

import com.github.glasspane.halorings.client.render.RenderCircle;
import com.github.glasspane.halorings.entity.EntityMagicCircle;
import com.github.glasspane.halorings.entity.EntityMagicCircleAgeable;
import com.github.upcraftlp.glasspane.api.proxy.IProxy;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("unused")
@SideOnly(Side.CLIENT)
public class ClientProxy implements IProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityMagicCircle.class, RenderCircle::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMagicCircleAgeable.class, RenderCircle::new);
    }
}
