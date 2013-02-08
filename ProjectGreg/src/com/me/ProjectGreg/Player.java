package com.me.ProjectGreg;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.*;
import java.lang.Math;


public class Player {

	double xPos;
	double yPos;
	boolean up = false,left = false,down = false,right = false;
	int maxSpeed = 10;
	int minSpeed = -10;
	double currentXSpeed = 0;
	double currentYSpeed = 0;
	double friction = 1;
	double minimalFriction = .0001;
	double acceleration = 3.5;//2.7864;
	double jump = -17;
	double gravity = -1;
	boolean canJump = true;
    Texture idle;
    Texture falling;
    Texture skip1;
    Texture skip2;
    Texture skip3;
	Texture texture;
	Texture death1;
	Texture death2;
	Texture death3;
	Texture fallingCenter;
	Sprite sprite;
	Sprite idleSprite, fallingSprite, fallingSpriteLeft, fallingSpriteCenter, skip1Sprite, skip2Sprite, skip3Sprite, skip1SpriteLeft, skip2SpriteLeft, skip3SpriteLeft, deathSprite1, deathSprite2, deathSprite3;
	public Player(int x, int y)
	{
		idle = new Texture(Gdx.files.internal("data/GregIdle.png"));
		idleSprite = new Sprite(new TextureRegion(idle, 0,0,64,64));
		falling = new Texture(Gdx.files.internal("data/GregFall1.png"));
		fallingSprite = new Sprite(new TextureRegion(falling,0,0,64,64));
		fallingCenter = new Texture(Gdx.files.internal("data/GregFallCenter.png"));
		fallingSpriteCenter = new Sprite(new TextureRegion(fallingCenter,0,0,64,64));
		fallingSpriteLeft = new Sprite(new TextureRegion(falling,0,0,64,64));
		fallingSpriteLeft.flip(true, false);
		skip1 = new Texture(Gdx.files.internal("data/GregSkip1.png"));
		skip1Sprite = new Sprite(new TextureRegion(skip1, 0,0,64,64));
		skip2 = new Texture(Gdx.files.internal("data/GregSkip2.png"));
		skip2Sprite = new Sprite(new TextureRegion(skip2, 0,0,64,64));
		skip3 = new Texture(Gdx.files.internal("data/GregSkip3Temp.png"));
		skip3Sprite = new Sprite(new TextureRegion(skip3, 0,0,64,64));
		skip1SpriteLeft = new Sprite(new TextureRegion(skip1, 0,0,64,64));
		skip1SpriteLeft.flip(true, false);
		skip2SpriteLeft = new Sprite(new TextureRegion(skip2, 0,0,64,64));
		skip2SpriteLeft.flip(true, false);
		skip3SpriteLeft = new Sprite(new TextureRegion(skip3, 0,0,64,64));
		skip3SpriteLeft.flip(true, false);
		death1 = new Texture(Gdx.files.internal("data/GregDeath1.png"));
		deathSprite1 = new Sprite(new TextureRegion(death1,0,0,64,64));
		death2 = new Texture(Gdx.files.internal("data/GregDeath2.png"));
		deathSprite2 = new Sprite(new TextureRegion(death2,0,0,64,64));
		death3 = new Texture(Gdx.files.internal("data/GregDeath3.png"));
		deathSprite3 = new Sprite(new TextureRegion(death3,0,0,64,64));
		sprite = idleSprite;
	    sprite.setSize(64,64);
		xPos = x;
		yPos = y;
	}
	int animation = 0;
	public void update()
	{
		////System.out.println(animation);
		if(currentXSpeed==0 && canJump)
		  sprite = idleSprite;
		else if(currentXSpeed==0 && !canJump)
		{
			sprite = fallingSpriteCenter;

		}
		else if(currentYSpeed < 0 && !canJump)
		{
			if(currentXSpeed > 0)
			{
				sprite = fallingSprite;
			}
			else if(currentXSpeed < 0)
			{
				sprite = fallingSpriteLeft;
			}
		}
		else if (currentXSpeed>0){
			if(animation < 10)
				sprite = skip1Sprite;
			else if(animation < 20)
				sprite = skip2Sprite;
			else if( animation < 30)
				sprite = skip3Sprite;
			else if(animation < 40)
//				sprite=skip2Sprite;
//			else if(animation < 50)
			    animation = 0;
			animation++;
		}
		else {
			if(animation < 10)
				sprite = skip1SpriteLeft;
			else if(animation < 20)
				sprite = skip2SpriteLeft;
			else if( animation < 30)
				sprite = skip3SpriteLeft;
			else if(animation < 40)
//				sprite = skip2SpriteLeft;
//			else if(animation < 50)
			    animation = 0;
			animation++;
		}
		/*if(Heartbeat.INSTANCE().getRate()>.9f)
		{
			for(int i=1; i < 3; i++)
			{
				switch(i)
				{
				case 1:setSprite(deathSprite1);
					   break;
				case 2:setSprite(deathSprite2);
					   break;
				case 3:setSprite(deathSprite3);
					   break;
				}
			}
		}*/
		up = Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.SPACE);
		left = Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT);
		down = Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN);
		right = Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT);
		currentYSpeed += gravity;
		if(currentXSpeed != 0)
		{
			if(currentXSpeed < 0)
			{
				if(currentXSpeed < (-1 * friction))
					currentXSpeed += friction;
				else
					currentXSpeed = 0;
					//currentXSpeed += friction;

			}
			else
			{
				if(currentXSpeed < .5)
					currentXSpeed = 0;
					//currentXSpeed -= minimalFriction;
				else
					currentXSpeed -= friction;
			}
		}

		if(up == true && canJump)
		{
			Heartbeat.INSTANCE().speedUP(.03f);
			//System.out.println("TRUE");
			currentYSpeed -= jump;
			canJump = false;
		}
		if(left == true)
		{
			Heartbeat.INSTANCE().speedUP(.004f);
			////System.out.println("A");
			if((currentXSpeed - acceleration) < minSpeed)
				currentXSpeed = minSpeed;
			else
				currentXSpeed -= acceleration;
		}
		if(right == true)
		{
			Heartbeat.INSTANCE().speedUP(.004f);
			////System.out.println("D");
			if((currentXSpeed + acceleration) > maxSpeed)
				currentXSpeed = maxSpeed;
			else
				currentXSpeed += acceleration;
		}
		//For testing purposes, remove this code when finished
	/*	if(down == true)
		{
			if(Heartbeat.INSTANCE().getRate() > 0)
			{
			Heartbeat.INSTANCE().setRate(Heartbeat.INSTANCE().getRate() - .01f);
			}
			else
			{
				Heartbeat.INSTANCE().setRate(0);
			}
		}*/
		Heartbeat.INSTANCE().calmDown();
		currentXSpeed *= Math.log((double)Heartbeat.INSTANCE().getRate()+1.5);
		setX(xPos + currentXSpeed);
		setY(yPos + currentYSpeed);
		////System.out.println("xPos: " + xPos + "\tyPos: "+ yPos + "\tySpeed: " + currentYSpeed);


	}
	public Sprite getSprite(){
		return sprite;
	}
	public void setJump(boolean j)
	{
		canJump = j;
	}
	public void setX(double x)
	{
		xPos = x;
	}
	public void setY(double y)
	{
		yPos = y;
	}
	public double getX()
	{
		return xPos;
	}
	public double getY()
	{
		return yPos;
	}
	public double getCurrentYSpeed()
	{
		return currentYSpeed;
	}
	public void setCurrentYSpeed(double y)
	{
	  currentYSpeed = y ;
	} 
	public double getCurrentXSpeed()
	{
		return currentXSpeed;
	}
	public void setCurrentXSpeed(double x)
	{
		currentXSpeed = x;
	}
	public void setSprite(Sprite s)
	{
		sprite = s;
	}
	public Sprite sprite()
	{
		return sprite;
	}
	//public void draw()
}