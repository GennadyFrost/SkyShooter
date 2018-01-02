package com.frost.game.Core.Object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.frost.game.Core.Handle.Animation;

public class Fire {

    public Vector2 position;
    private Animation animation;
    private float width;
    private float height;

    public Fire(String path, int num){

        Texture texture = new Texture(Gdx.files.internal(path));
        int x = texture.getWidth() / num;
        TextureRegion[] textureRegion = new TextureRegion[num];
        for (int i = 0; i < num; i++){
            textureRegion[i] = new TextureRegion(texture, x * i, 0, x, texture.getHeight());
        }
        this.animation = new Animation(textureRegion, 1/20F);
        this.width = x;
        this.height = textureRegion[0].getRegionHeight();
    }
    public void update(float delta){
        animation.update(delta);
    }
    public Animation getAnimation(){ return animation;}
    public float getWidth(){ return width;}
    public float getHeight(){ return height;}


}
