package com.Akoot.kragenui.util;

import java.io.File;

import com.Akoot.kragenui.Refrence;

public class Configs
{
	public static final File dir = new File(Refrence.MOD_NAME);
	public static final File configDir = new File(dir, "configuration");
	
	public static void load()
	{
		System.out.println("Loaded configuration files");
		if(!dir.exists()) dir.mkdir();
		if(!configDir.exists()) configDir.mkdir();
		if(!getConfig().exists())
		{
			CthFile cfg = getConfig();
			cfg.create();
			cfg.addComment("Basic Configuration");
			cfg.set("primary-color", "#FFFFFF");
			cfg.set("secondary-color", "#000000");
			cfg.addComment("The background of the main menu");
			cfg.addComment("Set to \"default\"  for vanilla background");
			cfg.set("background", "textures/gui/bg.png");
			cfg.addLine();
			cfg.addComment("X Y Z position colors");
			cfg.set("position.x.color", "#FFFFFF");
			cfg.set("position.y.color", "#FFFFFF");
			cfg.set("position.z.color", "#FFFFFF");
			cfg.addComment("X Y Z position position (on the screen)");
			cfg.set("position.x.position", "(0,0)");
			cfg.set("position.y.position", "(0,20)");
			cfg.set("position.z.position", "(0,30)");
			cfg.addComment("Direction colors");
			cfg.set("direction.north.color", "#FFFFFF");
			cfg.set("direction.south.color", "#FFFFFF");
			cfg.set("direction.east.color", "#FFFFFF");
			cfg.set("direction.west.color", "#FFFFFF");
			cfg.addComment("Direction position");
			cfg.set("direction.position", "(0,40)");
			cfg.addLine();
			cfg.addComment("Crosshair types: vanilla, dynamic, dynamic-color, dynamic-dynamic-color, normal, chevron, <any text>");
			cfg.set("crosshair.type", "vanilla");
			cfg.addComment("Custom crosshair size (non-vanilla)");
			cfg.set("crosshair.size", 8);
			cfg.addComment("Crosshair colors");
			cfg.set("crosshair.neutral.color", "#FFFFFF");
			cfg.addComment("These colors are only for dynamic-color crosshairs");
			cfg.set("crosshair.bad.color", "#FF0000");
			cfg.set("crosshair.normal.color", "#FFFF00");
			cfg.set("crosshair.good.color", "#00FF00");
		}
		if(!getModules().exists())
		{
			CthFile mod = getModules();
			mod.create();
			mod.addComment("Display X Y Z position");
			mod.set("xyzposition", true);
			mod.addComment("Enable elytra flight");
			mod.set("elytra", true);
			mod.addComment("Enable custom main menu");
			mod.set("mainmenu", true);
		}
	}
	
	public static CthFile getModules()
	{
		return new CthFile(dir, "modules.kr");
	}
	
	public static CthFile getConfig()
	{
		return new CthFile(configDir, "config.kr");
	}
	
	public static int getColor(String key)
	{
		return(getColor(getConfig(), key));
	}
	
	public static int getColor(CthFile config, String key)
	{
		String k = key + ".color";
		if(config.has(k))
		{
			return Colors.getColor(getConfig().getString(k));
		}
		return -1;
	}
}
