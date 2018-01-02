package com.frost.game.Core.Object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    protected Vector2 position;
    protected Vector2 velocity;

    protected float width;
    protected float height;
    protected Rectangle body;

    public Entity() {

    }

    abstract public void render(SpriteBatch batch);

    abstract public void update(float delta);

    abstract protected void loadSprite();

    public float getWidth(){ return width;}
    public float getHeight(){ return height;}
    public Rectangle getBody(){ return body;}
    public Vector2 getPosition(){ return position;}
    public Vector2 getVelocity(){ return velocity;}

}