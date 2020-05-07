package com.stickstudios.segundo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Corona extends InputAdapter {

    Texture img;
    private Rectangle rectangle;
    Vector2 posision,velocity;
    ShapeRenderer renderer;
    SpriteBatch batch;
    Viewport viewport;
    Sound hit;

    public Corona(Viewport viewport, Vector2 position) {
        this.viewport = viewport;
        img = new Texture("coronanuevo.png");
        posision = position;
        hit = Gdx.audio.newSound(Gdx.files.internal("hit.wav"));
        setRectangle(new Rectangle(posision.x, posision.y, img.getWidth()-20, img.getHeight()-20));
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        velocity = new Vector2();
    }

    public void render(float delta,float velocidad) {
        //handleInput(delta);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);
        renderer.rect(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
        renderer.end();
        batch.begin();
        batch.draw(img,posision.x-10,posision.y-10);
        batch.end();
        velocity.mulAdd(new Vector2(0,-velocidad), delta);
        posision.mulAdd(velocity, delta);
        getRectangle().setPosition(posision);
    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            posision.x += delta * 180;
            getRectangle().x += delta * 180;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            posision.x -= delta * 180;
            getRectangle().x -= delta * 180;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            posision.y += delta * 180;
            getRectangle().y += delta * 180;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            posision.y -= delta * 180;
            getRectangle().y -= delta * 180;
        }
    }

    public void playSound(){
        hit.play(0.4f);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        if(getRectangle().contains(worldClick.x,worldClick.y)){
            System.out.println("Click en corona xd");
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    public void dispose(){
        img.dispose();
        batch.dispose();
        renderer.dispose();
        hit.dispose();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
