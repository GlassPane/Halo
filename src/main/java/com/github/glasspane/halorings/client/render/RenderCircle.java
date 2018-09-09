package com.github.glasspane.halorings.client.render;

import com.github.glasspane.halorings.HaloRings;
import com.github.glasspane.halorings.client.util.ShapeRenderer;
import com.github.glasspane.halorings.entity.EntityMagicCircle;
import com.github.glasspane.halorings.entity.EntityMagicCircleAgeable;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;
import javax.vecmath.Vector3d;
import java.awt.*;

public class RenderCircle extends Render<EntityMagicCircle> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(HaloRings.MODID, "textures/circles/circle_test.png");
    private static final int SPAWN_ANIMATION_TIME = 20;
    private static final float ROTATION_SPEED = 0.5F;

    public RenderCircle(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityMagicCircle entity, double x, double y, double z, float entityYaw, float partialTicks) {
        float[] colors = new Color(entity.getColor()).getComponents(new float[4]);
        if(entity instanceof EntityMagicCircleAgeable) colors[3] = MathHelper.clamp(1.0F - ((float) entity.ticksExisted) / ((EntityMagicCircleAgeable) entity).getMaxAge(), 0.12F, 1.0F);
        float rotation = ROTATION_SPEED * (partialTicks + entity.ticksExisted);
        float scale = entity.ticksExisted <= SPAWN_ANIMATION_TIME ? (entity.ticksExisted + partialTicks) / SPAWN_ANIMATION_TIME : 1.0F;

        new ShapeRenderer()
                .color(colors)
                .texture(getEntityTexture(entity))
                .circle(x, y, z, new Vector3d(0, 1, 0), rotation, scale)
                .circle(x, y + 1, z, new Vector3d(0, 1, 0), 360.F - rotation, scale * 2.0F)
                .draw();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMagicCircle entity) {
        return TEXTURE;
    }
}
