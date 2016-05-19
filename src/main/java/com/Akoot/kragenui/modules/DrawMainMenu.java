package com.Akoot.kragenui.modules;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Akoot.kragenui.Refrence;
import com.Akoot.kragenui.gui.GuiElement;
import com.Akoot.kragenui.util.Colors;
import com.Akoot.kragenui.util.Configs;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class DrawMainMenu extends GuiElement
{
	public DrawMainMenu(Minecraft mc)
	{
		super(mc);
	}

	@Override
	public void render()
	{
		//drawBackground();
		drawPlayerInfo(5, 5, fr.getStringWidth(Refrence.MOD_FULL_NAME) + 32, 24);
		drawButtons();
	}
	
	double lastInfo;

	public void drawPlayerInfo(int x, int y, int width, int height)
	{
		lastInfo = animateSmooth(width, lastInfo, 3);
		drawRectSmooth(x, y, x + lastInfo, y + height, Colors.getColor(0.3, 0x000000));
		fr.drawStringWithShadow(mc.getSession().getUsername(), x + height + 2, y + 2, 0xffe4e4e4);
		fr.drawStringWithShadow(Refrence.MOD_FULL_NAME, x + height + 2, y + 12, 0xff61e8d7);
		fixColor();
		drawPlayerHead(x + 2, y + 2, height - 4, getUsername());
	}

	public void drawBackground()
	{
		ResourceLocation img = new ResourceLocation(Refrence.MOD_ID, Configs.getConfig().getString("background"));
		int w = 1920;
		int h = 1080;		
		double ratio = w / h;
		int width = (int) (getHeight() * ratio);
		int height = getHeight();

		if(getWidth() > width)
		{
			width = getWidth();
			height = (int) (getWidth() / ratio);
		}
		
		mc.getTextureManager().bindTexture(img);
		this.drawModalRectWithCustomSizedTexture(0, 0, width, height, width, height, width, height);
	}

	public void drawButtons()
	{
	}
}
