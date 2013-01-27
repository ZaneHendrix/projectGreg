
package com.me.ProjectGreg;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.*;


public class Player {

	float xPos;
	float yPos;
	boolean up = false,left = false,down = false,right = false;
	int maxSpeed = 10;
	int minSpeed = -10;
	float currentXSpeed = 0;
	float currentYSpeed = 0;
	double friction = 1;
	double minimalFriction = .0001;
	double acceleration = 2.7864;
	double jump = -15;
	double gravity = -1;
	boolean canJump = true;

	Texture texture;
	Sprite sprite;
	public Player(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	public void update()
	{
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
			Heartbeat.INSTANCE().speedUP(5);
			System.out.println("TRUE");
			currentYSpeed -= jump;
			canJump = false;
		}
		if(left == true)
		{
			Heartbeat.INSTANCE().speedUP(1);
			//System.out.println("A");
			if((currentXSpeed - acceleration) < minSpeed)
				currentXSpeed = minSpeed;
			else
				currentXSpeed -= acceleration;
		}
		if(right == true)
		{
			Heartbeat.INSTANCE().speedUP(1);
			//System.out.println("D");
			if((currentXSpeed + acceleration) > maxSpeed)
				currentXSpeed = maxSpeed;
			else
				currentXSpeed += acceleration;
		}
		setX((float)(xPos + currentXSpeed));
		setY((float)(yPos + currentYSpeed));
		System.out.println("xPos: " + xPos + "\tyPos: "+ yPos + "\tySpeed: " + currentYSpeed);


	}
	public void setJump(boolean j)
	{
		canJump = j;
	}
	public void setX(float x)
	{
		xPos = x;
	}
	public void setY(float y)
	{
		yPos = y;
	}
	public float  getX()
	{
		return xPos;
	}
	public float getY()
	{
		return yPos;
	}
	public float getCurrentYSpeed()
	{
		return currentYSpeed;
	}
	public void setCurrentYSpeed(float y)
	{
	  currentYSpeed = y ;
	} 
	public float getCurrentXSpeed()
	{
		return currentXSpeed;
	}
	public void setCurrentXSpeed(float x)
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

