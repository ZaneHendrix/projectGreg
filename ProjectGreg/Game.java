package com.me.projectgreg;

//import com.me.projectgreg.*;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Game implements ApplicationListener {
	private OrthographicCamera camera;
	SpriteBatch batch;
	SpriteBatch playerBatch;
	private Texture texture;
	private Sprite sprite;
	private Sprite playerSprite;
	private Texture playerTexture;
	private Platform []level;
	private Player player;
	
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
		//TextureRegion region = new TextureRegion(texture, (int)player.getX(), (int)player.getY(), 64, 64);
		playerSprite = new Sprite(playerRegion);
		playerSprite.setSize(64,64);
		player.setSprite(playerSprite);
		/*sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);*/
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
		
		playerBatch.begin();
		//batch.begin();
		//System.out.println(level[0].sprite()!=null);
		player.sprite().draw(batch);
		player.update();
		player.sprite().setPosition((float)player.getX(), (float)player.getY());
		level[0].sprite().draw(batch);
		//sprite.draw(batch);
		playerBatch.end();
		//batch.end();
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
