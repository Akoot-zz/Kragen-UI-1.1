package com.Akoot.kragenui.modules;

import com.Akoot.kragenui.gui.GuiElement;
import com.Akoot.kragenui.util.Colors;
import com.Akoot.kragenui.util.Configs;
import com.Akoot.kragenui.util.EntityUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.RayTraceResult.Type;

public class DrawCrosshair extends GuiElement
{
	public DrawCrosshair(Minecraft mc)
	{
		super(mc);
	}
	
	int lastFocus;

	@Override
	public void render()
	{
		String type = Configs.getConfig().getString("crosshair.type").toLowerCase();
		int size = Configs.getConfig().getInt("crosshair.size");

		int color;
		
		int colorneutral = Configs.getColor("crosshair.neutral");
		int colorbad = Configs.getColor("crosshair.bad");
		int colornormal = Configs.getColor("crosshair.normal");
		int colorgood = Configs.getColor("crosshair.good");

		if(type.matches("(|.*-)dynamic-color"))
		{
			if(mc.objectMouseOver.typeOfHit == Type.ENTITY && mc.objectMouseOver.entityHit != null)
			{
				Entity entity = mc.objectMouseOver.entityHit;
				if(entity instanceof EntityPlayerMP)
				{
					if(EntityUtil.isFriendlyPlayer((EntityPlayerMP) entity)) color = colorgood;
					else color = colorbad;
				}
				else
				{
					if(EntityUtil.isAgressiveNow(entity)) color = colorbad;
					else if(EntityUtil.canBeAgressive(entity)) color =  colornormal;
					else color = colorneutral;
				}
			}
			else color = colorneutral;
		}
		else color = colorneutral;

		if(type.matches("(cross|\\+|normal)($|-.*)"))
		{
			this.drawRect(getCenter().x, getCenter().y - size / 2, getCenter().x + 1, getCenter().y + size / 2 + 1, color); //horizontal
			this.drawRect(getCenter().x - size / 2, getCenter().y, getCenter().x + size / 2 + 1, getCenter().y + 1, color); //vertical
		}
		else if(type.matches("(chevron|chev)($|-.*)"))
		{
			fr.drawString("^", getCenter().x - (fr.getStringWidth(type) / 2), getCenter().y - 3, color); //yeah i cheated
		}
		else if(type.matches("dynamic($|-.*)"))
		{
			int focus = 0;
			
			EntityPlayer player = mc.thePlayer;
			
			if(player.isSneaking() && focus >= 2) focus -= 2;
			
			if(!player.onGround) focus += 10;
			if(player.isSprinting()) focus += 8;
			if(player.isSwingInProgress) focus += 5;
			if(!player.isSneaking() && !player.isActiveItemStackBlocking()) focus += 2;
			if(player.isElytraFlying()) focus += 10;
			if(player.isBurning()) focus += 12;
			
			if(focus < 0) focus = 0;
			
			lastFocus = animate(focus, lastFocus);
			
			double percent = (double) (50 - lastFocus) / 50.0;
			
			if(percent < 0) percent = 0;
			
			color = Colors.getColor(percent, color);
			
			this.drawRect(getCenter().x - lastFocus - size / 2, getCenter().y, getCenter().x - lastFocus, getCenter().y + 1, color); //left
			this.drawRect(getCenter().x, getCenter().y - lastFocus - size / 2, getCenter().x + 1, getCenter().y - lastFocus, color); //top
			this.drawRect(getCenter().x + lastFocus + size / 2 + 1, getCenter().y, getCenter().x + lastFocus + 1, getCenter().y + 1, color); //right
			this.drawRect(getCenter().x, getCenter().y + lastFocus + size / 2 + 1, getCenter().x + 1, getCenter().y + lastFocus + 1, color); //bottom
			
			if(lastFocus == 0) this.drawRect(getCenter().x, getCenter().y, getCenter().x + 1, getCenter().y + 1, color);
		}
		else
		{
			fr.drawString(type, getCenter().x - (fr.getStringWidth(type) / 2), getCenter().y - 3, color);
		}
	}
}
