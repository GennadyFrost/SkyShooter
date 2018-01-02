package com.frost.game.Core.Play.GUI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frost.game.Core.GameScreen;

public class ButtonTurbo extends Button{


    public ButtonTurbo(String pathON, String pathOFF, float x, float y) {
        super(pathON, pathOFF, x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(buttons[enumButton], position.x, position.y);
        isActive();
    }

    public void isActive() {
        if (GameScreen.input.turbo)
            enumButton = 1;
        else enumButton = 0;

    }
}
