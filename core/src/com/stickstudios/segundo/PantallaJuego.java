package com.stickstudios.segundo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PantallaJuego implements Screen {

    public static final float WIDTH = 480.0f;
    public static final float HEIGH = 360.0f;

    Game game;
    BitmapFont font;
    Texture fondo;
    Medico medico;
    CoronaList coronaList;
    OrthographicCamera camera;
    Viewport viewport;
    SpriteBatch batch;
    ShapeRenderer renderer;

    public PantallaJuego(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        //camera = new OrthographicCamera();
        viewport = new FitViewport(WIDTH,HEIGH);
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        font = new BitmapFont();
        font.getData().setScale(2);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        coronaList = new CoronaList(viewport);
        medico = new Medico(viewport);
        fondo = new Texture("fondo.PNG");
        Gdx.input.setInputProcessor(coronaList);
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

        batch.begin();
        batch.draw(fondo,0,0);
        font.draw(batch,"",100,40);
        batch.end();

        coronaList.update(delta);
        medico.render(delta);
        //medico.collision(corona.rectangle);
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

        medico.dispose();
    }
}
