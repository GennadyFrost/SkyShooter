package com.frost.game.Core.Play.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Button {

protected Vector2 position;
protected Texture[] buttons;
protected byte enumButton;

    public Button(String pathON, String pathOFF, float x, float y){
        this.buttons = new Texture[2];
        buttons[1] = new Texture(Gdx.files.internal(pathON));
        buttons[0] = new Texture(Gdx.files.internal(pathOFF));
        this.position = new Vector2(x, y);
        enumButton = 0;
    }
    public abstract void render(SpriteBatch batch);
    public abstract void isActive();
    public Texture getButton(){ return buttons[enumButton];}
    public boolean getValue(){ return (enumButton == 1);}
}
