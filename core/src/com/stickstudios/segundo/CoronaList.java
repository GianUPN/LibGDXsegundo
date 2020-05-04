package com.stickstudios.segundo;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CoronaList extends InputAdapter {

    DelayedRemovalArray<Corona> coronaList;
    Viewport viewport;

    public CoronaList(Viewport viewport) {
        this.viewport = viewport;
        init();
    }

    public void init() {
        coronaList = new DelayedRemovalArray<Corona>(false, 100);
    }

    public void update(float delta) {
        if (MathUtils.random() < delta * 2f) {
            Vector2 position = new Vector2(
                    MathUtils.random() * (viewport.getWorldWidth()-30),
                    viewport.getWorldHeight()
            );
            Corona newIcicle = new Corona(viewport, position);
            coronaList.add(newIcicle);
        }

        for (Corona corona : coronaList) {
            corona.render(delta);
        }

        coronaList.begin();
        for (int i = 0; i < coronaList.size; i++) {
            if (coronaList.get(i).posision.y < -1.0f) {
                coronaList.removeIndex(i);
            }
        }
        coronaList.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 worldClick = viewport.unproject(new Vector2(screenX, screenY));
        for (int i = 0; i < coronaList.size; i++) {
            if(coronaList.get(i).rectangle.contains(worldClick.x,worldClick.y)){
                coronaList.removeIndex(i);
                System.out.println("murioxd");
            }
        }

        return true;
    }
}
