package com.stickstudios.segundo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Rectangle rectangle;
	Rectangle rectangle2;
	ShapeRenderer renderer;
	Vector2 posision;
	BitmapFont font;
	String xd;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("corona.png");
		posision = new Vector2(0,0);
		rectangle = new Rectangle(posision.x,posision.y,img.getWidth(),img.getHeight());
		rectangle2 = new Rectangle(100,100,50,50);
		renderer = new ShapeRenderer();
		font = new BitmapFont();
		font.getData().setScale(3);
		xd= "nada";
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float delta = Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			posision.x += delta * 80;
			rectangle.x  += delta * 80;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			posision.x -= delta * 80;
			rectangle.x -= delta * 80;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			posision.y += delta * 80;
			rectangle.y += delta * 80;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			posision.y -=delta * 80;
			rectangle.y -= delta * 80;
		}

		if(rectangle.overlaps(rectangle2)){
			xd= "si";
		}else{
			xd = "nada";
		}


		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(Color.WHITE);
		renderer.rect(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
		renderer.end();
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(Color.RED);
		renderer.rect(rectangle2.x,rectangle2.y,rectangle.width,rectangle.height);
		renderer.end();
		batch.begin();
		batch.draw(img, posision.x, posision.y);
		font.draw(batch,xd,100,40);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
