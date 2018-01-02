package com.frost.game.Core.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.frost.game.Core.GameScreen;
import com.frost.game.Core.Object.Entity;
import com.frost.game.Core.Object.Player;
import com.frost.game.Core.Play.GUI.Button;
import com.frost.game.Core.Play.GUI.ButtonFire;
import com.frost.game.Core.Play.GUI.ButtonTurbo;
import com.frost.game.MyGame;



public class GameRenderer {

    public static OrthographicCamera camera;
    private OrthographicCamera guiCamera;
    private SpriteBatch batch;


    public static Array<Button> buttons;

    private Array<Entity> gameObject;

    public GameRenderer(SpriteBatch batch, OrthographicCamera Camera, Array<Entity> gameObject){
        this.gameObject = gameObject;
        camera = Camera;
        loadAsset();
        this.batch = batch;
        buttons = new Array<Button>();
        loadButton();

        guiCamera = new OrthographicCamera();
        guiCamera.setToOrtho(false, 480, 800);

        camera.setToOrtho(false, 480, 800);
        camera.translate(15, 0);
    }
    public void render(float delta) {

        Gdx.graphics.getGL20().glClearColor(0, 0, 0, 0);
        Gdx.graphics.getGL20().glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
        this.batch.setProjectionMatrix(camera.combined);
        batch.setColor(batch.getColor().r, batch.getColor().b, batch.getColor().b, 1F);
        batch.begin();
        if (GameScreen.assetManager.update()) {
            batch.draw(GameScreen.assetManager.get("Background2.jpg", Texture.class), 0, 0, MyGame.WIDTH, MyGame.HEIGHT);
        }
        for (Entity entity : gameObject){
            entity.render(batch);
        }

        batch.setProjectionMatrix(guiCamera.combined);
        for (Button button : buttons){
            if (button instanceof ButtonFire){
                batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, Player.block);
                button.render(batch);
            }else {
                batch.setColor(batch.getColor().r, batch.getColor().g, batch.getColor().b, 0.8F);
                button.render(batch);
            }

        }
        batch.end();

    }

    private void loadAsset(){
        GameScreen.assetManager.load("Background2.jpg", Texture.class);
    }
    private void loadButton(){
        buttons.add(new ButtonTurbo("ButtonGame/buttonON.png", "ButtonGame/buttonOFF.png", 0, 0));
        buttons.add(new ButtonFire("ButtonGame/buttonFIRE.png", "ButtonGame/buttonFIRE.png", camera.viewportWidth - 20, 0));
    }

    public OrthographicCamera getCamera(){ return camera;}
}
