package com.Akoot.kragenui.gui;

import com.Akoot.kragenui.util.Delta;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GuiElement extends Gui
{
	public Minecraft mc;
	public FontRenderer fr;

	public GuiElement(Minecraft mc)
	{
		this.mc = mc;
		this.fr = mc.fontRendererObj;
		init();
	}
	
	public Delta getCenter()
	{
		return new Delta(getWidth() / 2, getHeight() / 2);
	}
	
	public int getWidth()
	{
		return new ScaledResolution(mc).getScaledWidth();
	}
	
	public int getHeight()
	{
		return new ScaledResolution(mc).getScaledHeight();
	}
	
	/** Why do I have to do this? */
	public void fixColor()
	{
		drawRect(0,0,0,0,0xffffffff);
	}
	
	public String getUsername()
	{
		return mc.getSession().getUsername();
	}
	
	public void drawPlayerHead(int x, int y, int size, String username)
	{
		//int tile = 8;
		ResourceLocation skin  = AbstractClientPlayer.getLocationSkin(username);
		AbstractClientPlayer.getDownloadImageSkin(skin, username);
		mc.getTextureManager().bindTexture(skin);
		this.drawModalRectWithCustomSizedTexture(x, y, size, size, size, size, size * 8, size * 8);
		this.drawModalRectWithCustomSizedTexture(x, y, size * 5, size, size, size, size * 8, size * 8);
	}
	
	public void init() {}
	
	public void render() {}
	
	public int animate(int current, int var)
	{
		if(var < current) var += 1;
		else if(var > current) var -= 1;
		else return current;
		return var;
	}
	
	public static double animateSmooth(double current, double var, double speed)
	{
		var = (double)Math.round(var * 100) / 100;
		double vel = 0.01;
		if(var < current) vel = speed - (speed * (var / current));
		else vel = -(speed - (speed * (current / var)));
		return var + vel;
	}
	
	public static double animateSmooth(double current, double var)
	{
		return animateSmooth(current, var, 1);
	}
	
	public static void drawRectSmooth(double left, double top, double right, double bottom, int color)
    {
        if (left < right)
        {
            double i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            double j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f, f1, f2, f3);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(left, bottom, 0.0D).endVertex();
        vertexbuffer.pos(right, bottom, 0.0D).endVertex();
        vertexbuffer.pos(right, top, 0.0D).endVertex();
        vertexbuffer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
