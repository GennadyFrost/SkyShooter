package com.frost.game.Core.Object.Enemy;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.frost.game.Core.Handle.Animation;
import com.frost.game.Core.Object.Entity;
import com.frost.game.Core.Object.Player;

public class Blades extends Entity {

    private Animation animation;

    public Blades(float x, float y){
        loadSprite();
        width = height = animation.getFrame().getRegionHeight();
        position = new Vector2(x, y);
        velocity = new Vector2();
        body = new Rectangle(x, y, width, height);
    }
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(animation.getFrame(), body.x, body.y);
    }

    @Override
    public void update(float delta) {
        velocity.set((Player.GET_X - position.x)/1.5F, -300);
        animation.update(delta);
        position.add(velocity.cpy().scl(delta));
        body.setPosition(position);
    }

    @Override
    protected void loadSprite() {
        TextureRegion[] frames = new TextureRegion[16];
        for (int i = 0; i < frames.length; i++){
            if (i >= 10)
                frames[i] = new TextureRegion(
                        new Texture(Gdx.files.internal("Blades/saucer_blades00" + i + ".png")));
            else
                frames[i] = new TextureRegion(
                        new Texture(Gdx.files.internal("Blades/saucer_blades000" + i + ".png")));
        }
        animation = new Animation(frames, 1/26F);
    }

    @Override
    public float getWidth() {
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }
}
