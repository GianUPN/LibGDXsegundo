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
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Vector;

public class PantallaJuego implements Screen {

    public static final float WIDTH = 480.0f;
    public static final float HEIGH = 360.0f;

    Game game;
    BitmapFont balas_text, tiempo_restante_text;
    Texture fondo;
    Medico medico;
    CoronaList coronaList;
    OrthographicCamera camera;
    Viewport viewport;
    SpriteBatch batch;
    ShapeRenderer renderer;
    Vector<Integer> cant_balas;
    long startTime = 0;
    int tiempo_restante;

    public PantallaJuego(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        //camera = new OrthographicCamera();
        viewport = new FitViewport(WIDTH,HEIGH);
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        balas_text = new BitmapFont();
        balas_text.getData().setScale(2);
        balas_text.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        tiempo_restante_text = new BitmapFont();
        tiempo_restante_text.getData().setScale(2);
        tiempo_restante_text.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        cant_balas = new Vector<>();
        cant_balas.add(30);
        tiempo_restante = 60;
        startTime = TimeUtils.millis();
        medico = new Medico(viewport);
        coronaList = new CoronaList(viewport,cant_balas,medico);
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

        /** Textos */
        batch.begin();
        batch.draw(fondo,0,0);
        balas_text.draw(batch,"vacunas: " + cant_balas.get(0),20,30);
        tiempo_restante_text.draw(batch,"Sobrevive: " + tiempo_restante,270,30);
        batch.end();
        /** Contador de segundos */
        if (TimeUtils.timeSinceMillis(startTime) > 1000) {
            startTime = TimeUtils.millis();
            tiempo_restante--;
        }

        coronaList.update(delta);
        medico.render(delta);

        if(!medico.isState()){ //REINICIAR
            game.setScreen(new PantallaJuego(game));
        }
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
        balas_text.dispose();
        renderer.dispose();
        medico.dispose();
    }
}
