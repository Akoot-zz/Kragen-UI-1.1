package com.Akoot.kragenui;

import com.Akoot.kragenui.event.GuiEvent;
import com.Akoot.kragenui.proxy.CommonProxy;
import com.Akoot.kragenui.util.Configs;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Refrence.MOD_ID, name = Refrence.MOD_NAME, version = Refrence.VERSION)
public class KragenUI 
{
	@Instance(Refrence.MOD_ID)
	public static KragenUI instance;

	@SidedProxy(clientSide = "com.Akoot.kragenui.proxy.ClientProxy", serverSide = "com.Akoot.kragenui.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configs.load();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new GuiEvent(Minecraft.getMinecraft()));
	}
}
