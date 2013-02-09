package com.me.ProjectGreg;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;

public class Heartbeat 
{
	private static Heartbeat INSTANCE = null;
	private static Sound Heartbeat;


	public static synchronized Heartbeat INSTANCE()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new Heartbeat();
		}
		return INSTANCE;
	}
	private Heartbeat()
	{
		Heartbeat = Gdx.audio.newSound(Gdx.files.internal("sound/1heartbeat.wav"));
		System.out.println("Sound " + Heartbeat + " loaded");
		timer.schedule(playBeat, 3000);
	}

	private float rate = 0;
	public void calmDown()
	{
		if(rate > 0)
		{
			rate -= 0.0025; //rate*.005;
			if(rate > 1)
			{
				rate = 1;
			}
			if(rate < 0)
			{
				rate = 0;
			}
			//System.out.println(rate);
		}
	}
	public class PlayBeat extends TimerTask 
	{
		@Override
		public void run()
		{
			System.out.println("Playing sound");
			Heartbeat.play(/*(float) (rate + 10)*/);
			secondBeat = new SecondBeat();
			timer.schedule(secondBeat, (long) (Math.max(300*((1 - rate)/2), 100)));
			playBeat = new PlayBeat();
			timer.schedule(playBeat, (long) (Math.max(2000*(1 - rate), 150)));
		}
	}
	public class SecondBeat extends TimerTask
	{
		@Override
		public void run()
		{
			Heartbeat.play (/*(float) (0.7*(rate + 10))*/);
		}
	}

	PlayBeat playBeat = new PlayBeat();
	SecondBeat secondBeat = new SecondBeat();
	Timer timer = new Timer();

	public void speedUP(float x)
	{
		rate += x;
	}
	public float getRate()
	{
		return rate;
	}	
	public void setRate(float r)
	{
		rate = r;
	}
}