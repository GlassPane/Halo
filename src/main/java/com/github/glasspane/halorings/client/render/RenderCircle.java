package com.github.glasspane.halorings.client.render;

import com.github.glasspane.halorings.HaloRings;
import com.github.glasspane.halorings.client.util.ShapeRenderer;
import com.github.glasspane.halorings.entity.EntityMagicCircle;
import com.github.upcraftlp.glasspane.api.util.MathUtils;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import javax.vecmath.Vector3f;
import java.awt.*;

public class RenderCircle extends Render<EntityMagicCircle> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(HaloRings.MODID, "textures/circles/circle_test.png");
    private static final int SPAWN_ANIMATION_TIME = 20;
    private static final float ROTATION_SPEED = 2.0F;

    public RenderCircle(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityMagicCircle entity, double x, double y, double z, float entityYaw, float partialTicks) {
        float[] colors = new Color(entity.getColor(), true).getComponents(new float[4]);
        float rotation = ((ROTATION_SPEED * (partialTicks + entity.ticksExisted)) % 360.0F) / 360.0F;
        float scale = entity.ticksExisted <= SPAWN_ANIMATION_TIME ? (entity.ticksExisted + partialTicks) / SPAWN_ANIMATION_TIME : 1.0F;

        Vector3f vec = new Vector3f(2F, 2F, 2F);
        vec.normalize();
        ShapeRenderer sr = new ShapeRenderer().color(colors).texture(getEntityTexture(entity)).pos(x, y, z);

        for(int i = 0; i < 3; i++) {
            sr.circle(0.0D, i * 0.9D, 0.0D, new Vector3f((float) (MathUtils.TAU * 0.3F * i), 1, i), i % 2 == 0 ? rotation : 360.0F - rotation, scale * (i + 1) * 1.1F);
        }
        sr.draw();
                //.circle(0.0D, 0.0D, 0.0D, new Vector3f(0, 1, 0), rotation, scale)
                //.circle(0.0D, 0.7D, 0.0D, new Vector3f(0, 1, 0), 360.F - rotation, scale * 1.5F)
                //.circle(0.0D, 1.1D, 0.0D, vec, rotation / 0.5F, scale * 2.2F)
                //.draw();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMagicCircle entity) {
        return TEXTURE;
    }
}
