package com.me.projectgreg;

//import com.me.projectgreg.*;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.me.projectgreg.Heartbeat;

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
	SpriteBatch playerBatch;
	private Texture texture;
	private Sprite sprite;
	private Sprite playerSprite;
	private Texture playerTexture;
	private Platform []level;
	private Player player;
	private Rectangle rect;

	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		level = new Platform[1];
		player = new Player(200, 100);
		for(int i = 0; i<level.length; i++)
		{
			level[i] = new Platform(this);
		}
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		playerBatch = new SpriteBatch();
		playerTexture = new Texture(Gdx.files.internal("data/Greg.png"));
		texture = new Texture(Gdx.files.internal("data/platform.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		TextureRegion playerRegion = new TextureRegion(playerTexture, 0, 0, 64, 64);
		TextureRegion region = new TextureRegion(texture, 100, 150, 512, 275);
		playerSprite = new Sprite(playerRegion);
		playerSprite.setSize(64,64);
		player.setSprite(playerSprite);
		//rect.set();
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);

		Task heartRateTask = new Task();
		Timer heartTimer = new Timer();

		heartTimer.schedule(heartRateTask, 0, 500); // Update simulation about 1 times per second.
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
		playerTexture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//camera.translate(.01f,0f);
		batch.setProjectionMatrix(camera.combined);

		//playerBatch.begin();
		batch.begin();
		//System.out.println(level[0].sprite()!=null);
		player.sprite().draw(batch);
		player.update();
		collision(player, level);
		player.sprite().setPosition((float)player.getX(), (float)player.getY());
		batch.end();
		batch.begin();
		level[0].sprite().draw(batch);
		//sprite.draw(batch);
		//playerBatch.end();
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
	
	
		static Rectangle xRect = new Rectangle();
		static Rectangle yRect = new Rectangle();
		public static void collision(Player p, Platform[] level){
	      float x, y, width, height, xSpeed, ySpeed;
	      x = (float)p.getX();
	      y = (float)p.getY();
	      width = (float)p.sprite.getWidth();
	      height = (float)p.sprite.getHeight();
	      xSpeed = (float)p.getCurrentXSpeed();
	      ySpeed = (float)p.getCurrentYSpeed();
	  
		  if(xSpeed>0)
	      {
	    	  xRect.set(x,y,xSpeed+width,height);  
	      }
	      else {
	    	  xRect.set(x+xSpeed,y,-xSpeed,height); 
	      }
	      if(ySpeed<0)
	      {
	    	  yRect.set(x,y+ySpeed,width,ySpeed); 
	      }
	      else {
	    	  yRect.set(x,y+height,width,-ySpeed); 
	      }
	      for(int i = 0; i < level.length;i++)
		  {
		    if(xRect.overlaps(level[i].sprite.getBoundingRectangle())) {
		    	p.setX(p.getX()-p.getCurrentXSpeed());
		    	p.setCurrentXSpeed(0);
		    }
		    if(yRect.overlaps(level[i].sprite.getBoundingRectangle())) {
		    	p.setY(p.getY()-p.getCurrentYSpeed());
		    	p.setCurrentYSpeed(0);
		    	p.setJump(true);
		    }
		  }
		}
	}

