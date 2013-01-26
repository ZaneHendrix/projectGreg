package com.me.projectgreg;

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

	double xPos;
	double yPos;
	boolean SPACE = false,A = false,S = false,D = false;
	int maxSpeed = 10;
	int minSpeed = -10;
	double currentXSpeed = 0;
	double currentYSpeed = 0;
	double friction = 1;
	double minimalFriction = .0001;
	double acceleration = 2.7864;
	double jump = 4;
	double gravity = 2; 
	
	Texture texture;
	Sprite sprite;
	public Player(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	public void update()
	{
		SPACE = Gdx.input.isKeyPressed(Keys.SPACE);
		A = Gdx.input.isKeyPressed(Keys.A);
		S = Gdx.input.isKeyPressed(Keys.S);
		D = Gdx.input.isKeyPressed(Keys.D);
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
			
		
		if(SPACE == true)
		{
			//System.out.println("SPACE");
			currentYSpeed -= jump; 
		}
		if(A == true)
		{
			//System.out.println("A");
			if((currentXSpeed - acceleration) < minSpeed)
				currentXSpeed = minSpeed;
			else
				currentXSpeed -= acceleration;
		}
		if(D == true)
		{
			//System.out.println("D");
			if((currentXSpeed + acceleration) > maxSpeed)
				currentXSpeed = maxSpeed;
			else
				currentXSpeed += acceleration;			
		}
		setX(xPos + currentXSpeed);
		setY(yPos + currentYSpeed);
		System.out.println("xPos: " + xPos + "\tyPos: "+ yPos);
		
			
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
