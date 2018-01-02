package com.frost.game.Core.Handle;

public class Timer {

    private float start;
    private float delay;

    public Timer(float start){
        this.start = start;
    }

    public boolean update(float delta){
        delay += delta;
        if (delay > start){
            delay = 0;
            return true;
        }
        else return false;
    }
}
