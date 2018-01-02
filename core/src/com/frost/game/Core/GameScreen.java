package com.frost.game.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frost.game.Core.Menu.Menu;
import com.frost.game.Core.Handle.GameInput;
import com.frost.game.Core.Play.GameRunning;

public class GameScreen implements Screen {

    private GameRunning gameRunning;
    private Menu menu;

    public static AssetManager assetManager;
    public static GameInput input;

    private static boolean[] states;
    static {
        input = new GameInput();
        states = new boolean[3];
        states[0] = false;
        states[1] = false;
        states[2] = false;
    }
    public GameScreen(SpriteBatch batch, OrthographicCamera camera){

        Gdx.input.setInputProcessor(input);
        assetManager = new AssetManager();
        this.menu = new Menu(batch, camera);
        this.gameRunning = new GameRunning(batch, camera);
        setMenu(true);
    }

    public void show() {

    }
    public void render(float delta) {
        menu.render(delta);
        gameRunning.render(delta);

    }
    public void resize(int width, int height) {

    }
    public void pause() {

    }
    public void resume() {

    }
    public void hide() {

    }
    public void dispose() {
        assetManager.dispose();
    }
    public static void setPause(boolean b){ states[2] = b;}
    public static void setPlay(boolean b){ states[1] = b;}
    public static void setMenu(boolean b){ states[0] = b;}
    public static boolean getPause(){ return states[2];}
    public static boolean getPlay(){ return states[1];}
    public static boolean getMenu(){ return states[0];}
}
