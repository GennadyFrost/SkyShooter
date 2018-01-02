package com.frost.game.Core.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frost.game.Core.GameScreen;

public class Menu {

    private MenuRenderer renderer;
    private float speedBackground = 0;
    public static boolean butttonOFF;

    public Menu(SpriteBatch batch, OrthographicCamera camera){
        this.renderer = new MenuRenderer(batch, camera);
    }

    public void render(float delta){
        if (GameScreen.getMenu()){
            renderer.render();
            if (!GameScreen.getPlay())
                update(delta);
        }else{
            speedBackground = 0;
            dispose();
        }
    }
    private void update(float delta){
        butttonOFF = (Gdx.input.getX() >= 0 && Gdx.input.getX() <= Gdx.graphics.getWidth() &&
                Gdx.input.getY() >= (Gdx.graphics.getHeight() * 8 / 10) &&
                Gdx.input.getY() <= (Gdx.graphics.getHeight() * 9 / 10));
        if (GameScreen.input.startGame) {
            speedBackground = -1000F * delta;
        }
        renderer.getPositionBackground().add(speedBackground, 0);
        if (renderer.getPositionBackground().x <= -renderer.getCamera().viewportWidth){
            GameScreen.setPlay(true);
            GameScreen.setMenu(false);
            butttonOFF = false;
        }
    }

    public void dispose(){
        renderer.getPositionBackground().set(0,0);
        butttonOFF = false;
    }

}
