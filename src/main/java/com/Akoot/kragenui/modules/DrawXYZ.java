package com.Akoot.kragenui.modules;

import com.Akoot.kragenui.gui.GuiElement;
import com.Akoot.kragenui.util.Colors;
import com.Akoot.kragenui.util.Configs;
import com.Akoot.kragenui.util.Delta;

import net.minecraft.client.Minecraft;

public class DrawXYZ extends GuiElement
{
	
	double lastX;
	double lastY;
	double lastZ;
	double lastDir;
	
	public DrawXYZ(Minecraft mc)
	{
		super(mc);
		lastX = 0;
		lastY = 0;
		lastZ = 0;
		lastDir = 0;
	}
	
	@Override
	public void render()
	{
		String x = "x: " + mc.thePlayer.getPosition().getX();
		String y = "y: " + mc.thePlayer.getPosition().getY();
		String z = "z: " + mc.thePlayer.getPosition().getZ();
		String dir = mc.thePlayer.getHorizontalFacing().getName();
		
		int dircol = 0xffffff;
		
		if(dir.toLowerCase().startsWith("n")) dircol = Colors.getColor(Configs.getConfig().getString("direction.north.color"));
		if(dir.toLowerCase().startsWith("s")) dircol = Colors.getColor(Configs.getConfig().getString("direction.south.color"));
		if(dir.toLowerCase().startsWith("e")) dircol = Colors.getColor(Configs.getConfig().getString("direction.east.color"));
		if(dir.toLowerCase().startsWith("w")) dircol = Colors.getColor(Configs.getConfig().getString("direction.west.color"));
		
		lastX = animateSmooth((double) (fr.getStringWidth(x)), lastX, 3);
		lastY = animateSmooth((double) (fr.getStringWidth(y)), lastY, 3);
		lastZ = animateSmooth((double) (fr.getStringWidth(z)), lastZ, 3);
		lastDir = animateSmooth((double) (fr.getStringWidth(dir)), lastDir, 4);

		int padding = 2;

		Delta xpos;
		Delta ypos;
		Delta zpos;
		Delta dirpos;

		String xp = Configs.getConfig().getString("position.x.position");
		String yp = Configs.getConfig().getString("position.y.position");
		String zp = Configs.getConfig().getString("position.z.position");
		String dp = Configs.getConfig().getString("direction.position");

		if(Delta.isInFormat(xp)) xpos = Delta.getDelta(xp);
		else xpos = new Delta(0,0 + padding);

		if(Delta.isInFormat(yp)) ypos = Delta.getDelta(yp);
		else if(yp.equalsIgnoreCase("x<")) ypos = new Delta((int) (xpos.x + lastX + (padding * 2) + padding), xpos.y);
		else if(yp.equalsIgnoreCase("x^")) ypos = new Delta(xpos.x, xpos.y + 7 + (padding * 2) + 1);
		else ypos = new Delta(0,10 + padding);

		if(Delta.isInFormat(zp)) zpos = Delta.getDelta(zp);
		else if(zp.equalsIgnoreCase("x<")) zpos = new Delta((int) (xpos.x + lastX + (padding * 2) + padding), xpos.y);
		else if(zp.equalsIgnoreCase("y<")) zpos = new Delta((int) (ypos.x + lastY + (padding * 2) + padding), ypos.y);
		else zpos = new Delta(0,22 + padding);
		
		if(Delta.isInFormat(dp)) dirpos = Delta.getDelta(dp);
		else if(dp.equalsIgnoreCase(">^")) dirpos = new Delta(getWidth() - fr.getStringWidth(dir) - (padding * 2),0);
		else dirpos = new Delta(0,34 + padding);

		this.drawRectSmooth(xpos.x - padding, xpos.y, (double) (xpos.x) + lastX + (padding * 2), (xpos.y + 8) + (padding * 2), Colors.getColor(0.4, 0x000000));
		this.drawRectSmooth(ypos.x - padding, ypos.y, (double) (ypos.x) + lastY + (padding * 2), (ypos.y + 8) + (padding * 2), Colors.getColor(0.35, 0x000000));
		this.drawRectSmooth(zpos.x - padding, zpos.y, (double) (zpos.x) + lastZ + (padding * 2), (zpos.y + 8) + (padding * 2), Colors.getColor(0.4, 0x000000));
		
		this.drawRectSmooth(dirpos.x - padding, dirpos.y, (double) (dirpos.x) + lastDir + (double) (padding * 2), (dirpos.y + 8) + (padding * 2), Colors.getColor(0.35, 0x000000));

		fr.drawString(x, xpos.x + padding, xpos.y + padding, Configs.getColor("position.x"));
		fr.drawString(y, ypos.x + padding, ypos.y + padding, Configs.getColor("position.y"));
		fr.drawString(z, zpos.x + padding, zpos.y + padding, Configs.getColor("position.z"));
		
		fr.drawString(dir, dirpos.x + padding, dirpos.y + padding, dircol);
	}
}
