package com.frost.game.Core.Play;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.frost.game.Core.GameScreen;
import com.frost.game.Core.Handle.Event;
import com.frost.game.Core.Handle.Timer;
import com.frost.game.Core.Object.Enemy.Blades;
import com.frost.game.Core.Object.Entity;
import com.frost.game.Core.Object.Player;
import com.frost.game.Core.Object.Rockets;
import com.frost.game.MyGame;


public class GameRunning {

    private Vector2 velocityBackground;
    private GameRenderer renderer;
    private Array<Entity> gameObject;
    private Timer createBlades;

    public GameRunning(SpriteBatch batch, OrthographicCamera camera){
        velocityBackground = new Vector2();
        gameObject = new Array<Entity>();
        gameObject.add(new Player());
        renderer = new GameRenderer(batch, camera, gameObject);

        // create timers
        createBlades = new Timer(10);
    }
    public void render(float delta){
        if (GameScreen.getPlay()) {
            renderer.render(delta);
            update(delta);
        }else {

        }
    }
    private void update(float delta){

        velocityBackground.set(Player.accelerateX / 3F, Player.accelerateY);
        if (renderer.getCamera().position.y + renderer.getCamera().viewportHeight / 2 <= MyGame.HEIGHT)
                renderer.getCamera().translate(velocityBackground.x, velocityBackground.y);
            renderer.getCamera().update();

        if (renderer.getCamera().position.x + renderer.getCamera().viewportWidth / 2 >= 505)
            renderer.getCamera().position.x = 510 - renderer.getCamera().viewportWidth / 2;
        if (renderer.getCamera().position.x -renderer.getCamera().viewportWidth / 2 - 15 < 0)
            renderer.getCamera().position.x = renderer.getCamera().viewportWidth / 2 + 15;

        for (int i = 0; i < gameObject.size; i++){
            gameObject.get(i).update(delta);
            if (i != 0 && gameObject.get(i) instanceof Blades){
                Event.overlapsBlades(getPlayer(), (Blades)gameObject.get(i));

            }
            if (gameObject.get(i).getPosition().y < -renderer.getCamera().viewportHeight/2){
                gameObject.removeIndex(i);
            }
        }
        Array<Rockets> array = getPlayer().getRocketBody();
        try{
            if (gameObject.size > 1)
                for (int i = 0; i < array.size; i++){
                    for (int j = 1; j < gameObject.size; j++){
                        if (Event.overlapsRocket(array.get(i).getBody(), gameObject.get(j))){
                            gameObject.removeIndex(j);
                            array.removeIndex(i);
                        }
                    }
                }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        for (int i = 1; i < gameObject.size; i++)
            for (int j = i + 1; j < gameObject.size; j++)
            if (gameObject.get(i).getBody().overlaps(gameObject.get(j).getBody())){
                gameObject.get(i).getVelocity().x = -gameObject.get(i).getVelocity().x;
                gameObject.get(j).getVelocity().x = -gameObject.get(j).getVelocity().x;
            }

        createBlades(delta);

    }
    private void createBlades(float delta){
        if(createBlades.update(delta)){
            gameObject.add(new Blades(MathUtils.random(0, renderer.getCamera().viewportWidth),
                    renderer.getCamera().viewportHeight/2 + renderer.getCamera().position.y));
            gameObject.add(new Blades(MathUtils.random(0, renderer.getCamera().viewportWidth),
                    renderer.getCamera().viewportHeight/2 + renderer.getCamera().position.y));
            gameObject.add(new Blades(MathUtils.random(0, renderer.getCamera().viewportWidth),
                    renderer.getCamera().viewportHeight/2 + renderer.getCamera().position.y));
        }
    }
    public Player getPlayer(){ return (Player)gameObject.get(0);}

}
