package com.github.glasspane.halorings.client.util;

import com.github.upcraftlp.glasspane.api.util.ForgeUtils;
import com.github.upcraftlp.glasspane.api.util.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

@SuppressWarnings("WeakerAccess")
@SideOnly(Side.CLIENT)
public final class ShapeRenderer {
    private Tessellator tessellator;
    private BufferBuilder vertexBuffer;
    private final float[] color;
    private ResourceLocation texture = ForgeUtils.MISSING;
    private Matrix4f model = new Matrix4f();
    private Vector3f pos = new Vector3f();

    public ShapeRenderer() {
        this.color = new float[4];
        //render setup
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();

        this.tessellator = Tessellator.getInstance();
        this.vertexBuffer = this.tessellator.getBuffer();
        this.vertexBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        this.model.setIdentity();
    }

    public ShapeRenderer texture(ResourceLocation texture) {
        this.texture = texture;
        return this;
    }

    public ShapeRenderer color(float[] color) {
        System.arraycopy(color, 0, this.color, 0, 4);
        return this;
    }

    public ShapeRenderer color(float red, float green, float blue) {
        return color(red, green, blue, 1.0F);
    }

    public ShapeRenderer color(float red, float green, float blue, float alpha) {
        this.color[0] = red;
        this.color[1] = green;
        this.color[2] = blue;
        this.color[3] = alpha;
        return this;
    }

    public ShapeRenderer pos(double x, double y, double z) {
        return pos(new Vector3f((float) x, (float) y, (float) z));
    }

    public ShapeRenderer pos(Vector3f position) {
        this.pos = position;
        model.setTranslation(this.pos);
        return this;
    }

    public ShapeRenderer circle(double x, double y, double z, Vector3f orientation, float rotation, float scale) {
        return circle(new Vector3f((float) x, (float) y, (float) z), orientation, rotation, scale);
    }

    public ShapeRenderer circle(Vector3f relativePos, Vector3f orientation, float rotation, float scale) {
        this.model.rotY((float) (rotation * MathUtils.TAU));
        relativePos.add(this.pos);
        this.vertexBuffer.setTranslation(relativePos.x, relativePos.y, relativePos.z);

        Vector3f pos1 = new Vector3f(1.0F, 0.0005F, -1.0F);
        Vector3f pos2 = new Vector3f(-1.0F, 0.0005F, -1.0F);
        Vector3f pos3 = new Vector3f(-1.0F, 0.0005F, 1.0F);
        Vector3f pos4 = new Vector3f(1.0F, 0.0005F, 1.0F);
        this.model.transform(pos1);
        this.model.transform(pos2);
        this.model.transform(pos3);
        this.model.transform(pos4);

        this.vertexBuffer.pos(pos1.x, pos1.y, pos1.z).tex(1.0D, 0.0D).color(this.color[0], this.color[1], this.color[2], this.color[3]).endVertex();
        this.vertexBuffer.pos(pos2.x, pos2.y, pos2.z).tex(0.0D, 0.0D).color(this.color[0], this.color[1], this.color[2], this.color[3]).endVertex();
        this.vertexBuffer.pos(pos3.x, pos3.y, pos3.z).tex(0.0D, 1.0D).color(this.color[0], this.color[1], this.color[2], this.color[3]).endVertex();
        this.vertexBuffer.pos(pos4.x, pos4.y, pos4.z).tex(1.0D, 1.0D).color(this.color[0], this.color[1], this.color[2], this.color[3]).endVertex();

        this.vertexBuffer.setTranslation(0.0D, 0.D, 0.0D);
        return this;
    }

    public void draw() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }
}
