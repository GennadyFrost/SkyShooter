package com.frost.game.Core.Object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.frost.game.Core.Handle.Animation;

public abstract class Rockets {

    protected Vector2 position;
    protected Vector2 velocity;
    private Animation animation;
    protected TextureRegion[] frames;
    protected float width;
    protected float height;
    protected Fire fire;

    public Rockets(float x, float y, String pathFolder, String nameFile, int col){
        loadAssets(pathFolder, nameFile, col);
        width = frames[0].getRegionWidth();
        height = frames[0].getRegionHeight();
        position = new Vector2(x, y);
        velocity = new Vector2();
    }
    private void loadAssets(String pathFolder, String nameFile, int col){
        TextureRegion[] frames = new TextureRegion[col];

        for (int i = 0; i < col; i++){
            if (i < 10)
                frames[i] = new TextureRegion(new Texture(Gdx.files.internal(pathFolder)+ "/" + nameFile +"0" + i + ".png"));
            else
                frames[i] = new TextureRegion(new Texture(Gdx.files.internal(pathFolder)+ "/" + nameFile + i + ".png"));
        }
        this.frames = frames;
    }
    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);

    public TextureRegion[] getFrames(){ return frames;}
    public Vector2 getPosition(){ return position;}
    public abstract Rectangle getBody();
}
