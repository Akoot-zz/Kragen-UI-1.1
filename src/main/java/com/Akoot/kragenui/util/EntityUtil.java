package com.Akoot.kragenui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayerMP;

public class EntityUtil
{	
	public static boolean isFriendlyPlayer(EntityPlayerMP player)
	{
		if(player.isOnSameTeam(Minecraft.getMinecraft().thePlayer)) return true;
		return false;
	}

	public static boolean isAgressiveNow(Entity entity)
	{
		if(canBeAgressive(entity))
		{
			if(entity.hitByEntity(Minecraft.getMinecraft().thePlayer)) return true;
		}
		else
		{
			if(entity instanceof EntityMob) return true;
		}
		return false;
	}
	
	public static boolean canBeAgressive(Entity entity)
	{
		if(entity instanceof EntityIronGolem || entity instanceof EntitySpider || entity instanceof EntityEnderman || entity instanceof EntityWolf) return true;
		return false;
	}
}
