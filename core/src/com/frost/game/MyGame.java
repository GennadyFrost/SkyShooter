package com.frost.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frost.game.Core.GameScreen;

public class MyGame extends Game {

    public static final float HEIGHT = 2048;
    public static final float WIDTH = 512;
    public static final float PPI = 100;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        this.setScreen(new GameScreen(batch, camera));
    }
}
