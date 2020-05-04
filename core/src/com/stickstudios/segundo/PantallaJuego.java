package com.stickstudios.segundo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PantallaJuego implements Screen {

    public static final float WIDTH = 480.0f;
    public static final float HEIGH = 360.0f;

    Game game;
    Rectangle rectangle2;
    ShapeRenderer renderer;
    SpriteBatch batch;
    BitmapFont font;
    Corona corona;
    String xd;
    OrthographicCamera camera;
    Viewport viewport;

    public PantallaJuego(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        //camera = new OrthographicCamera();
        viewport = new StretchViewport(WIDTH,HEIGH);
        rectangle2 = new Rectangle(100,100,50,50);
        renderer = new ShapeRenderer();
        font = new BitmapFont();
        font.getData().setScale(2);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        batch = new SpriteBatch();
        corona = new Corona(viewport);
        xd= "nada";
        Gdx.input.setInputProcessor(corona);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /**
         * No olvidar el projection matrix o de lo contrario
         *  las coordenadas no seran las correctas.
         */
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        batch.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.RED);
        renderer.rect(rectangle2.x,rectangle2.y,50,50);
        renderer.end();
        batch.begin();
        font.draw(batch,xd,100,40);
        batch.end();

        if(corona.rectangle.overlaps(rectangle2)){
            xd = "si";
        }else{
            xd = "nada";
        }
        corona.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        renderer.dispose();
        corona.dispose();
    }
}
