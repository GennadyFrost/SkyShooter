package com.frost.game.Core.Play.GUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ButtonFire extends Button{

    public ButtonFire(String pathON, String pathOFF, float x, float y){
        super(pathON, pathOFF, x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(buttons[enumButton], position.x, position.y, 15, 15);
        isActive();
    }

    public void isActive() {
            enumButton = 1;
    }
}
