package com.stickstudios.segundo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Medico {

    Texture img;
    Texture img2;
    private boolean state;
    private Rectangle rectangle;
    Vector2 posision;
    ShapeRenderer renderer;
    SpriteBatch batch;
    Viewport viewport;
    private static final float ACCELEROMETER_SENSITIVITY = 0.5f;
    private static final float GRAVITATIONAL_ACCELERATION = 9.8f;

    public Medico(Viewport viewport){
        this.viewport = viewport;
        batch = new SpriteBatch();
        img = new Texture("doctor.png");
        setState(true);
        setRectangle(new Rectangle(250,50,img.getWidth(),img.getHeight()));
        posision = new Vector2(250,50);
        renderer = new ShapeRenderer();
    }

    public void render(float delta){
        batch.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setProjectionMatrix(viewport.getCamera().combined);

        float accelerometerInput = -Gdx.input.getAccelerometerY() / (GRAVITATIONAL_ACCELERATION * ACCELEROMETER_SENSITIVITY);
        posision.x += -delta * accelerometerInput * 100f;//VELOCIDAD 80f

        if(Gdx.input.isKeyPressed(Input.Keys.A)) posision.x -= delta *180f;
        if(Gdx.input.isKeyPressed(Input.Keys.D)) posision.x += delta *180f;

        ensureInBounds();
        rectangle.setPosition(posision);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.VIOLET);
        renderer.rect(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
        renderer.end();
        batch.begin();
        batch.draw(img,posision.x,posision.y);
        batch.end();
    }

    private void ensureInBounds() {
        if (posision.x < 0) {
            posision.x = 0;
        }
        if (posision.x + img.getWidth() > viewport.getWorldWidth()) {
            posision.x = viewport.getWorldWidth() - img.getWidth();
        }
    }

    public void dispose(){
        img.dispose();
        batch.dispose();
        renderer.dispose();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
