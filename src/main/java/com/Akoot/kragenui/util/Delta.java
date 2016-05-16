package com.Akoot.kragenui.util;

public class Delta 
{
	public int x, y;
	
	public Delta(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @param point Must be in form (x,y)"
	 */
	public static int getX(String point)
	{
		point = point.replaceAll(" ", "");
		point = point.replaceAll("\\(|\\)", "");
		if(point.matches("\\d,\\d")) return 0;
		return Integer.parseInt(point.substring(0, point.indexOf(",")));
	}
	
	public static boolean isInFormat(String point)
	{
		if(point.matches("(|\\()\\d,(|\\s)\\d(|\\))"))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * @param point Must be in form "(x,y)"
	 */
	public static int getY(String point)
	{
		point = point.replaceAll(" ", "");
		point = point.replaceAll("\\(|\\)", "");
		if(point.matches("\\d,\\d")) return 0;
		return Integer.parseInt(point.substring(point.indexOf(",") + 1));
	}
	
	@Override
	public String toString()
	{
		return String.format("(%s,%s)", x, y);
	}
	
	/**
	 * @param point Must be in form "(x,y)"
	 */
	public static Delta getDelta(String point)
	{
		point = point.replaceAll(" ", "");
		return new Delta(getX(point), getY(point));
	}
}
