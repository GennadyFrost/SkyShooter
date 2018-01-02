package com.frost.game.Core.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.frost.game.Core.GameScreen;
import com.frost.game.MyGame;

public class MenuRenderer {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector2 positionBackground;

    MenuRenderer(SpriteBatch batch, OrthographicCamera camera){
        this.batch = batch;
        this.camera = camera;
        this.camera.setToOrtho(false, 480, 800);
        this.positionBackground = new Vector2(0, 0);

        loadAsset();
    }
    public void render(){
        Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
        Gdx.graphics.getGL20().glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        if (GameScreen.assetManager.update()) {
            // Background draw
            batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, 0.8F);
            batch.draw(GameScreen.assetManager.get("Background2.jpg", Texture.class),
                    positionBackground.x + camera.viewportWidth - 15, 0, MyGame.WIDTH, MyGame.HEIGHT);
            batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, 1F);
            batch.draw(GameScreen.assetManager.get("Menu.png", Texture.class),
                    positionBackground.x, positionBackground.y, camera.viewportWidth, camera.viewportHeight);
            // Button draw
             batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, 0.5F);
            if (Menu.butttonOFF)
                batch.draw(GameScreen.assetManager.get("ButtonMenu/buttonON.png", Texture.class),
                        positionBackground.x, camera.viewportHeight * 1 / 10,
                        camera.viewportWidth, camera.viewportHeight / 10);
            else
                batch.draw(GameScreen.assetManager.get("ButtonMenu/buttonOFF.png", Texture.class),
                        positionBackground.x, camera.viewportHeight * 1 / 10,
                        camera.viewportWidth, camera.viewportHeight / 10);
        }
        batch.end();
    }
    private void loadAsset(){
        GameScreen.assetManager.load("Menu.png", Texture.class);
        GameScreen.assetManager.load("ButtonMenu/buttonOFF.png", Texture.class);
        GameScreen.assetManager.load("ButtonMenu/buttonON.png", Texture.class);

    }
    public Vector2 getPositionBackground(){ return positionBackground;}
    public OrthographicCamera getCamera(){ return camera;}
}
