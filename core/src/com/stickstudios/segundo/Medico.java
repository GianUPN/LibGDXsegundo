package com.stickstudios.segundo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Medico {

    Texture img;
    private Rectangle rectangle;
    Vector2 posision;
    ShapeRenderer renderer;
    SpriteBatch batch;
    Viewport viewport;

    public Medico(Viewport viewport){
        this.viewport = viewport;
        batch = new SpriteBatch();
        img = new Texture("doctor.png");

        setRectangle(new Rectangle(250,50,img.getWidth(),img.getHeight()));
        posision = new Vector2(250,50);
        renderer = new ShapeRenderer();
    }

    public void render(float delta){
        batch.setProjectionMatrix(viewport.getCamera().combined);
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.VIOLET);
        renderer.rect(getRectangle().x, getRectangle().y, getRectangle().width, getRectangle().height);
        renderer.end();
        batch.begin();
        batch.draw(img,posision.x,posision.y);
        batch.end();
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

    public void collision(Rectangle rectangle) {
        if(this.rectangle.overlaps(rectangle)){
            System.out.println("perdi");
        }
    }
}
