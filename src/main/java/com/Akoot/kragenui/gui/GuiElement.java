package com.Akoot.kragenui.gui;

import com.Akoot.kragenui.util.Delta;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
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
		return animate(current, var, 1);
	}
	
	public int animate(int current, int var, double speed)
	{
		if(var < current) var = (int) (Math.round(var + speed));
		else if(var > current) var = (int) (Math.round(var - speed));
		else return current;
		return var;
	}
}
