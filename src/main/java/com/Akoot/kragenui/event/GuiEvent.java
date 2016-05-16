package com.Akoot.kragenui.event;

import com.Akoot.kragenui.modules.DrawCrosshair;
import com.Akoot.kragenui.modules.DrawMainMenu;
import com.Akoot.kragenui.modules.DrawXYZ;
import com.Akoot.kragenui.modules.Module;
import com.Akoot.kragenui.util.Configs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiEvent extends Gui
{
	public Minecraft mc;
	public FontRenderer fr;

	public int getWidth;
	public int getHeight;

	private DrawXYZ drawXYZ;
	private DrawCrosshair drawCrosshair;
	private DrawMainMenu drawMainMenu;

	public GuiEvent(Minecraft mc)
	{
		this.mc = mc;
		this.fr = mc.fontRendererObj;
		drawXYZ = new DrawXYZ(mc);
		drawCrosshair = new DrawCrosshair(mc);
		drawMainMenu = new DrawMainMenu(mc);
	}

	@SubscribeEvent
	public void onRenderGuiIngame(RenderGameOverlayEvent event)
	{
		if(event.getType() == ElementType.TEXT)
		{
			if(Module.isEnabled(Module.XYZPOSITION)) drawXYZ.render();
		}

		if(event.getType() == ElementType.CROSSHAIRS)
		{
			if(!Configs.getConfig().getString("crosshair.type").toLowerCase().matches("default|classic|vanilla"))
			{
				event.setCanceled(true);
				if(!Configs.getConfig().getString("crosshair.type").toLowerCase().matches("empty|none|null")) drawCrosshair.render();
			}
		}
	}

	@SubscribeEvent
	public void onRenderGuiScreen(GuiScreenEvent event)
	{
		if(Module.isEnabled(Module.MAINMENU))
		{
			if(event.getGui().getClass().getSimpleName().equals("GuiMainMenu"))//ghetto is shit
			{
				drawMainMenu.render();
			}
		}
	}
}
