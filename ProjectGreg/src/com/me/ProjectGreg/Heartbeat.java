package com.me.ProjectGreg;


public class Heartbeat
{
	private static Heartbeat INSTANCE = null;
	

	public static synchronized Heartbeat INSTANCE()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new Heartbeat();
		}
		return INSTANCE;
	}
	private int heartrate = 1;
	public void calmDown()
	{
		if(heartrate > 1)
		{
			heartrate -= heartrate * .1;
			System.out.println(heartrate);
		}
	}
	public void speedUP(int x)
	{
		heartrate += x;
	}
	public int getHeartrate()
	{
		return heartrate;
	}
}
