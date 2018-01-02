package com.frost.game.Core.Handle;

import com.badlogic.gdx.math.Rectangle;
import com.frost.game.Core.Object.Enemy.Blades;
import com.frost.game.Core.Object.Entity;
import com.frost.game.Core.Object.Player;

public class Event {

    public static boolean overlapsBlades(Player player ,Blades blades){
        return (player.getBody().overlaps(blades.getBody()));
    }
    public static boolean overlapsRocket(Rectangle rockets, Entity entity) {
        return (rockets.overlaps(entity.getBody()));
    }
}
