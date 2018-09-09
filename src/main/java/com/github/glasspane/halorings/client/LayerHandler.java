package com.github.glasspane.halorings.client;

import com.github.glasspane.halorings.HaloRings;
import com.github.glasspane.halorings.client.render.layer.LayerHalo;
import com.github.upcraftlp.glasspane.api.event.client.RegisterRenderLayerEvent;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = HaloRings.MODID, value = Side.CLIENT)
public class LayerHandler {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onRegisterLayers(RegisterRenderLayerEvent event) {
        RenderLivingBase render = event.getRender();
        event.addRenderLayer(new LayerHalo(render));
        MinecraftForge.EVENT_BUS.unregister(LayerHandler.class);
    }

}
