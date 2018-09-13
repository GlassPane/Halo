package com.github.glasspane.halorings.client.util;

import com.github.upcraftlp.glasspane.api.util.ForgeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

@SideOnly(Side.CLIENT)
public final class ShapeRenderer {
    private Tessellator tessellator;
    private BufferBuilder vertexBuffer;
    private final float[] color;
    private ResourceLocation texture = ForgeUtils.MISSING;

    public ShapeRenderer() {
        this.color = new float[4];
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

    public ShapeRenderer circle(double x, double y, double z, Vector3f orientation, float rotation, float scale) {
        return circle(new Vector3f((float) x, (float) y, (float) z), orientation, rotation, scale);
    }

    public ShapeRenderer circle(Vector3f pos, Vector3f orientation, float rotation, float scale) {
        //TODO move
        this.setup();


        Matrix4f model = new Matrix4f();
        model.setIdentity();
        model.translate(pos);
        //model.rotate(1.0F, orientation);
        //model.rotate(90.0F / 360.0F, new Vector3f(0, 1, 0));
        model.rotate(rotation * 0.01F, new Vector3f(0.0F, 1.0F, 0.0F));
        model.scale(new Vector3f(scale, scale, scale));
        //model.translate(pos.negate(null));
        //model.invert();
        vertexBuffer.setTranslation(pos.x, pos.y, pos.z);

        Vector3f pos1 = multiply(model, new Vector4f(1.0F, 0.0005F, -1.0F, 1.0F));
        Vector3f pos2 = multiply(model, new Vector4f(-1.0F, 0.0005F, -1.0F, 1.0F));
        Vector3f pos3 = multiply(model, new Vector4f(-1.0F, 0.0005F, 1.0F, 1.0F));
        Vector3f pos4 = multiply(model, new Vector4f(1.0F, 0.0005F, 1.0F, 1.0F));

        //Vector3f pos1 = orientation.normalise(new Vector3f(1.0F, 0.0005F, -1.0F));
        //Vector3f pos2 = orientation.normalise(new Vector3f(-1.0F, 0.0005F, -1.0F));
        //Vector3f pos3 = orientation.normalise(new Vector3f(-1.0F, 0.0005F, 1.0F));
        //Vector3f pos4 = orientation.normalise(new Vector3f(1.0F, 0.0005F, 1.0F));

        this.vertexBuffer.pos(pos1.x, pos1.y, pos1.z).tex(1.0D, 0.0D).color(this.color[0], this.color[1], this.color[2], this.color[3]).endVertex();
        this.vertexBuffer.pos(pos2.x, pos2.y, pos2.z).tex(0.0D, 0.0D).color(this.color[0], this.color[1], this.color[2], this.color[3]).endVertex();
        this.vertexBuffer.pos(pos3.x, pos3.y, pos3.z).tex(0.0D, 1.0D).color(this.color[0], this.color[1], this.color[2], this.color[3]).endVertex();
        this.vertexBuffer.pos(pos4.x, pos4.y, pos4.z).tex(1.0D, 1.0D).color(this.color[0], this.color[1], this.color[2], this.color[3]).endVertex();

        this.vertexBuffer.setTranslation(0.0D, 0.D, 0.0D);

        //TODO move as well
        this.drawTesselator();
        this.cleanup();

        return this;
    }

    private static Vector3f multiply(Matrix4f model, Vector4f vector) {
        Vector3f vec = new Vector3f(
                vector.x * model.m00 + vector.y * model.m01 + vector.z * model.m02 + vector.w * model.m03,
                vector.x * model.m10 + vector.y * model.m11 + vector.z * model.m12 + vector.w * model.m13,
                vector.x * model.m20 + vector.y * model.m21 + vector.z * model.m21 + vector.w * model.m23
        );
        //vec.scale(vector.x * model.m30 + vector.y * model.m30 + vector.z * model.m30 + vector.w * model.m30);
        return vec;
    }

    public void draw() {
        //FIXME move setup and draw here!
    }

    private void setup() {
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();

        this.tessellator = Tessellator.getInstance();
        this.vertexBuffer = this.tessellator.getBuffer();
        this.vertexBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
    }

    private void drawTesselator() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.tessellator.draw();
    }

    private void cleanup() {
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }
}
