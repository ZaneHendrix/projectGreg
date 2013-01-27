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
	
	Game game;
	
	public Platform(Game g)
	{
		texture = new Texture(Gdx.files.internal("data/platform.png"));
		sprite = new Sprite(new TextureRegion(texture, 0,0,64,64));
		sprite.setPosition(10f,100f);
		//sprite.setSize(1f,1f);
		game=g;		
	}
	public Platform(int x, int y)
	{
		texture = new Texture(Gdx.files.internal("data/platform.png"));
		sprite = new Sprite(new TextureRegion(texture, 0,0,64,64));
		xPos = x;
		yPos = y;
		sprite.setPosition(xPos,yPos);
	}
	
	public void setPosition(int x, int y)
	{
		xPos = x;
		yPos = y;
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
	public Sprite sprite()
	{
		return sprite;
	}
	public void draw()
	{
		sprite.draw(game.batch);
	}

}
