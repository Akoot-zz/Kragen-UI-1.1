package com.Akoot.kragenui.modules;

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
		drawRect(x, y, x + width, y + height, Colors.getColor(0.5, 0x000000));
		fr.drawStringWithShadow("§a" + mc.getSession().getUsername(), x + height + 2, y + 2, 0xffffffff);
		fr.drawStringWithShadow(Refrence.MOD_FULL_NAME, x + height + 2, y + 12, 0xffffffff);
		fixColor();
		drawPlayerHead(x + 2, y + 2, height - 4, getUsername());
	}

	public void drawBackground()
	{
		double ratio = 1.8;
		int width = (int) (getHeight() * ratio);
		int height = getHeight();

		if(getWidth() > width)
		{
			width = getWidth();
			height = (int) (getWidth() / ratio);
		}
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Refrence.MOD_ID, Configs.getConfig().getString("background")));
		this.drawModalRectWithCustomSizedTexture(0, 0, width, height, width, height, width, height);
	}

	public void drawButtons()
	{
	}
}
