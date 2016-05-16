package com.Akoot.kragenui.util;

import java.util.Random;

public class Colors
{
	private static Random random = new Random();
	
	public static int getColor(double a, int r, int g, int b)
	{
		return (int) Long.parseLong(String.format("%02x%02x%02x%02x", (int) (a * 255), r, g, b), 16);
	}
	
	public static int getColor(int r, int g, int b)
	{
		return getColor(1, r, g ,b);
	}
	
	public static int getColor(double a, String hex)
	{
		hex = hex.replace("#", "");
		return (int) Long.parseLong((String.format("%02x", (int) (a * 255)) + hex), 16);
	}
	
	public static int getColor(double a, int hex)
	{
		return getColor(a, (hex & 0xFF0000) >> 16, (hex & 0xFF00) >> 8, (hex & 0xFF));
	}
	
	public static int getColor(int hex)
	{
		return getColor(1, hex);
	}
	
	public static int getColor(int a, String hex)
	{
		return getColor(a, Integer.parseInt(hex.replaceAll("#", ""), 16));
	}
	
	public static int getColor(String hex)
	{
		return getColor(1, Integer.parseInt(hex.replaceAll("#", ""), 16));
	}
	
	public static int randomColor(int min, int max)
	{
		return getColor((random.nextInt((max - min) + 1) + min), (random.nextInt((max - min) + 1) + min), (random.nextInt((max - min) + 1) + min));
	}
	
	public static int randomColor()
	{
		return randomColor(0, 255);
	}
}
