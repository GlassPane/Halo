package com.github.glasspane.halorings.client.render.layer;

import com.github.glasspane.halorings.HaloRings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public class LayerHalo implements LayerRenderer<EntityLivingBase> {

    private final RenderLivingBase render;

    public LayerHalo(RenderLivingBase render) {
        this.render = render;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if(Minecraft.getSystemTime() % 1000L == 0) HaloRings.getLogger().info("render ticked another second!"); //TODO halo rendering!
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
