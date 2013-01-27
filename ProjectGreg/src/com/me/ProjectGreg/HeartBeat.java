package com.me.ProjectGreg;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.files.FileHandle;

public class HeartBeat 
{
	private static HeartBeat INSTANCE = null;
	private static Sound heartBeat;


	public static synchronized HeartBeat INSTANCE()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new HeartBeat();
		}
		return INSTANCE;
	}
	private HeartBeat()
	{
		heartBeat = Gdx.audio.newSound(Gdx.files.internal("sound/1heartBeat.wav"));
		System.out.println("Sound " + heartBeat + " loaded");
		timer.schedule(playBeat, 3000);
	}

	private float rate = 0;
	public void calmDown()
	{
		if(rate > 0)
		{
			rate -= rate*.1;
			if(rate > 1)
			{
				rate = 1;
			}
			if(rate < 0)
			{
				rate = 0;
			}
			System.out.println(rate);
		}
	}
	public class PlayBeat extends TimerTask 
	{
		@Override
		public void run()
		{
			System.out.println("Playing sound");
			heartBeat.play(1f);
			secondBeat = new SecondBeat();
			timer.schedule(secondBeat, (long) (Math.max(200*(1 - rate), 50)));
			playBeat = new PlayBeat();
			timer.schedule(playBeat, (long) (2000*(1 - rate)));
		}
	}
	public class SecondBeat extends TimerTask
	{
		@Override
		public void run()
		{
			heartBeat.play(.7f);
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
}
