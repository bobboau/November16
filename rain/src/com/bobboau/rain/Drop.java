package com.bobboau.rain;

import android.graphics.Color;
import android.graphics.PointF;

public class Drop {
	private PointF position;
	private long birth_time = 0;
	private long max_life = 0;
	private float max_rad = 0;
	private float hue = 0;
	
	public Drop(PointF p, int life, float rad, float h)
	{
		position = p;
		birth_time = System.currentTimeMillis();
		
		max_life = life;
		max_rad = rad;
		hue = h*360;
	}
	
	/*
	 * how long has this drop been alive
	 */
	public long getAge()
	{
		return System.currentTimeMillis() - birth_time;
	}
	
	/*
	 * how long has this drop been alive in a range of 0.0f to 1.0f where 0 is new and 1 is dead
	 */
	public float getScalerAge()
	{
		return (float)getAge() / (float)max_life;
	}
	
	/*
	 * returns the position
	 */
	public PointF getPosition()
	{
		return position;
	}
	
	/*
	 * returns true if this drop has not yet expired
	 */
	public Boolean isAlive()
	{
		return max_life > getAge();
	}
	
	/*
	 * returns what the radius should be at this point in time
	 */
	public float getRadius()
	{
		return max_rad * getScalerAge();
	}
	
	public int getColor()
	{
		float hsv[] = new float[3];
		hsv[0] = hue;
		hsv[1] = getScalerAge();
		hsv[2] = 1.0f-getScalerAge();
		return Color.HSVToColor(hsv);
	}
}
