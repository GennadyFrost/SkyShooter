package com.frost.game.Core.Handle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class GameInput extends InputAdapter {

    public boolean startGame = false;
    public boolean turbo = false;
    public boolean createRockets = false;

    public boolean keyDown(int keycode) {

        return false;
    }


    public boolean keyUp(int keycode) {

        return super.keyUp(keycode);
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenX <= Gdx.graphics.getWidth() / 3 && screenY >= Gdx.graphics.getHeight() * 13/14){
            turbo = true;
        }
        if (screenX >= Gdx.graphics.getWidth() * 2 / 3 && screenY >= Gdx.graphics.getHeight() * 13/14){
            createRockets = true;
        }

        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (screenX <= Gdx.graphics.getWidth() &&
                screenY >= (Gdx.graphics.getHeight() * 8 / 10) &&
                screenY <= (Gdx.graphics.getHeight() * 9 / 10)){
            startGame = true;
            return true;
        }
        if (screenX >= Gdx.graphics.getWidth() * 2 / 3 && screenY >= Gdx.graphics.getHeight() * 13/14){
            turbo = false;
        }
        if (screenX <= Gdx.graphics.getWidth() / 3 && screenY >= Gdx.graphics.getHeight() * 13/14){
            turbo = false;
        }
        startGame = false;
        return false;
    }
}
