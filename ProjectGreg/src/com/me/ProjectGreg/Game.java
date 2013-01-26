package com.me.ProjectGreg;

//import com.me.projectgreg.*;
import java.util.TimerTask;
import java.util.Timer;

import com.me.ProjectGreg.Heartbeat;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Game implements ApplicationListener 
{
	public class Task extends TimerTask 
	{
		@Override
		public void run()
		{
			Heartbeat.INSTANCE().calmDown();
		}
	}
	private OrthographicCamera camera;
	SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private Platform []level;
	private Player player;
	@Override
	public void create() 
	{		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		player = new Player(200,200);
		level = new Platform[1];
		for(int i = 0; i<level.length; i++)
		{
			level[i] = new Platform(this);
		}
		
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/platform.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		
		Task heartRateTask = new Task();
		Timer heartTimer = new Timer();
		
		heartTimer.schedule(heartRateTask, 0, 500); // Update simulation about 1 times per second.
	}

	@Override
	public void dispose() 
	{
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() 
	{
		for(int i = 0; i < level.length; i++)
		{
			//If player isn't touching platform 
			/*if(!player.sprite.getBoundingRectangle().overlaps(level[i].sprite.getBoundingRectangle()))
			{
				//Player affected by Gravity
			}*/
		}
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//camera.translate(.01f,0f);
		batch.setProjectionMatrix(camera.combined);
		player.update();
		batch.begin();
		for(Platform plat : level)
	{
			plat.sprite().draw(batch);
	}
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
