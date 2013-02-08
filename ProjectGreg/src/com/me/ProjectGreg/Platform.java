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
import java.awt.*;


public class Platform {

	float xPos;
	float yPos;
	float width;
	float height;
	
	Texture texture;
	Sprite sprite;
	Rectangle rect;
	boolean island;
	//boolean dark=false;
	short darkness = 0;
	Texture text1;
	Texture text2;
	Texture text12; 
	Texture text22;
	Texture text13;
	Texture text23;
	
	Game game;
	
	public Platform(Game g)
	{
		texture = new Texture(Gdx.files.internal("data/platform.png"));
		sprite = new Sprite(new TextureRegion(texture, 0,0,64,64));
		sprite.setPosition(10f,100f);
		//sprite.setSize(1f,1f);
		game=g;		
	}
	public Platform(int x, int y, boolean isle)
	{
		text1  = new Texture(Gdx.files.internal("data/Tile1.png"));
		text2  = new Texture(Gdx.files.internal("data/Tile2.png"));
		text12 = new Texture(Gdx.files.internal("data/Tile12.png")); 
		text22 = new Texture(Gdx.files.internal("data/Tile22.png"));
		text13 = new Texture(Gdx.files.internal("data/Tile13.png")); 
		text23 = new Texture(Gdx.files.internal("data/Tile23.png"));	
		xPos = x;
		yPos = y;
		island = isle;
		if(!island)
		{
			texture = text1;
		}
		else{
			texture = text2;
		}
		sprite = new Sprite(new TextureRegion(texture, 0,0,64,64));
		sprite.setPosition(xPos,yPos);
	}
	
	public void setPosition(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	public void setDarkness(short level)
	{   
		if(level >= 0 && level <= 2)
		{
		darkness = level;
		}
		setSprite();
	}
	public void setSprite()
	{   
		switch(darkness)
		{
		case 0: if(island)
				{
					 sprite = new Sprite(new TextureRegion(text23,0,0,64,64));
				     sprite.setPosition(xPos,yPos);
				}
				 
				else
				{
					sprite = new Sprite(new TextureRegion(text13,0,0,64,64));
					sprite.setPosition(xPos,yPos);
				}break;
		case 1: if(island)
				{
					 sprite = new Sprite(new TextureRegion(text22,0,0,64,64));
					 sprite.setPosition(xPos,yPos);
				}
					 
				else
				{
					sprite = new Sprite(new TextureRegion(text12,0,0,64,64));
					sprite.setPosition(xPos,yPos);
				}break;
		case 2:if(island)
				{
					 sprite = new Sprite(new TextureRegion(text2,0,0,64,64));
					 sprite.setPosition(xPos,yPos);
				}
					 
				else
				{
					sprite = new Sprite(new TextureRegion(text1,0,0,64,64));
					sprite.setPosition(xPos,yPos);
				}break;
		}
	}
	public void Translate(int x, int y)
	{
		xPos+=x;
		yPos+=y;
	}
	
	public Rectangle getRect()
	{	
	return rect;
	}
	public boolean getIsland()
	{
		return island;
	}
	public Sprite sprite()
	{
		return sprite;
	}
	public void draw()
	{
		sprite.draw(game.batch);
	}

}
