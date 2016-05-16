package com.Akoot.kragenui.modules;

import com.Akoot.kragenui.util.Configs;

public enum Module
{
	XYZPOSITION,
	ELYTRA,
	MAINMENU
	;

	public static boolean isEnabled(Module mod)
	{
		if(Configs.getModules().getBoolean(mod.name().toLowerCase())) return true;
		return false;
	}
}
