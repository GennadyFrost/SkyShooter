package com.frost.game.Core.Object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.frost.game.Core.GameScreen;
import com.frost.game.Core.Handle.Animation;
import com.frost.game.Core.Play.GameRenderer;
import com.frost.game.MyGame;

public class Player extends Entity{

    public float width;
    public float height;
    public static Vector2 position;
    public static Vector2 velocity;
    public static float accelerateX = 0;
    public static float accelerateY = 0.1F;
    public static float GET_X;
    public static float GET_Y;
    public static float block = 5;

    private Animation[] animationPlayer;
    private int CENTER = 1;
    private int LEFT = 0;
    private int RIGHT = 2;

    private boolean isLeft;
    private boolean isRight;
    private boolean turbo;

    private Fire fire;
    private Array<Rockets> rockets;

    public Player() {
        loadSprite();
        fire = new Fire("Fire/firePlayer.png", 16);
        position = new Vector2(MyGame.WIDTH / 2 - width/2, 100);
        velocity = new Vector2();
        body = new Rectangle(position.x, position.y, width, height);
        rockets = new Array<Rockets>();
    }

    public void render(SpriteBatch batch) {

        if (rockets.size != 0)
            for (Rockets rocket : rockets)
                rocket.render(batch);


        if (isRight)
            batch.draw(animationPlayer[RIGHT].getFrame(), position.x, position.y);
        else if (isLeft)
            batch.draw(animationPlayer[LEFT].getFrame(), position.x, position.y);
        else
            batch.draw(animationPlayer[CENTER].getFrame(), position.x, position.y);

        // draw fire
        if (turbo)
            batch.draw(fire.getAnimation().getFrame(),
                    position.x - 2, position.y - fire.getHeight() + 20,
                    fire.getHeight() / 1.7F, fire.getHeight() / 1.F);
        else
            batch.draw(fire.getAnimation().getFrame(),
                    position.x + fire.getHeight() / 8 - 2, position.y - fire.getHeight() / 2 - 2,
                    fire.getHeight() / 3, fire.getHeight() / 1.5F);

    }

    public void update(float delta) {

        // tmp
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            position.add(10, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            position.add(-10, 0);
        // tmp

        for (int i = 0; i < animationPlayer.length; i++)
            animationPlayer[i].update(delta);

        velocity.set(-Gdx.input.getAccelerometerX() * 4, accelerateY);

        if (GameRenderer.buttons.get(0).getValue()){
            accelerateX = MathUtils.random(-2, 2);
            velocity.add(accelerateX, 0);
            turbo = true;
            fire.getAnimation().setDelay(1/50F);
            accelerateY = 0.5F;
        }else {
            accelerateX = 0;
            accelerateY = 0.1F;
            turbo = false;
            fire.getAnimation().setDelay(1/20F);
        }


        isLeft = (-Gdx.input.getAccelerometerX() * 4 < -2) || Gdx.input.isKeyPressed(Input.Keys.A);
        isRight = (-Gdx.input.getAccelerometerX() * 4 > 2) || Gdx.input.isKeyPressed(Input.Keys.D);
        position.add(velocity);
        fire.update(delta);

        if (position.x + width / 2 > MyGame.WIDTH){
            position.x = - width / 2;
        }
        if (position.x < -(width/2 + 20 )){
            position.x = MyGame.WIDTH - width / 2;
        }
        body.setPosition(position);
        blockShot(delta);
        if (blockShot(delta)){
            createRockets();
        }

        if (rockets.size != 0)
            for (Rockets rocket : rockets)
            rocket.update(delta);

        GET_X = position.x;
        GET_Y = position.y;
    }

    protected void loadSprite() {
        animationPlayer = new Animation[3];
        TextureRegion[] spritesCenter = new TextureRegion[16];
        for (int i = 0; i < spritesCenter.length; i++){
            if (i < 10)
                spritesCenter[i] = new TextureRegion(new Texture(Gdx.files.internal("PlayerShotCENTR/player000" + i + ".png")));
            else
                spritesCenter[i] = new TextureRegion(new Texture(Gdx.files.internal("PlayerShotCENTR/player00" + i + ".png")));
        }
        animationPlayer[CENTER] = new Animation(spritesCenter,1/16f);
        TextureRegion[] spritesRight = new TextureRegion[15];
        for (int i = 32, m = 0; i < 48; i++, m++){
            if (i == 45){
                m--;
                continue;
            }
            spritesRight[m] = new TextureRegion(new Texture(Gdx.files.internal("PlayerShotRIGHT/player00" + i + ".png")));
        }

        animationPlayer[RIGHT] = new Animation(spritesRight, 1/16F);
        TextureRegion[] spritesLeft = new TextureRegion[16];
        for (int i = 16, m = 0; i < 32; i++, m++){
            spritesLeft[m] = new TextureRegion(new Texture(Gdx.files.internal("PlayerShotLEFT/player00" + i + ".png")));
        }
        animationPlayer[LEFT] = new Animation(spritesLeft, 1/16F);
        width = spritesCenter[0].getRegionWidth();
        height = spritesCenter[0].getRegionHeight();
        spritesCenter = null;
        spritesLeft = null;
        spritesRight = null;
    }
    private void createRockets(){
        rockets.add(new PlayerRockets(position.x + this.width/2 - 15, position.y + this.height - 30, "Rockets", "rocket_type_A00", 9));
        block = 0;
    }
    public static boolean blockShot(float delta){
         block += delta;
        if (block >= 1.8){
            return true;
        }
        return false;
    }

    private Animation getAnimation(int i){ return animationPlayer[i];}
    public float getWidth(){ return width;}
    public float getHeight(){ return height;}

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Rectangle getBody() {
        return super.getBody();
    }

    public class PlayerRockets extends Rockets{

        private Rectangle body;

        private Animation animationFire;
        public PlayerRockets(float x, float y, String pathFolder, String nameFile, int col) {
            super(x, y, pathFolder, nameFile, col);
            fire = new Fire("FireRockets/projectile_bolt_red.png", 16);
            animationFire = fire.getAnimation();
            body = new Rectangle(x, y, frames[0].getRegionWidth(), frames[0].getRegionHeight());
        }

        @Override
        public void update(float delta) {
            velocity.set(0, delta * 400);
            position.add(velocity);
            body.setPosition(position);
            fire.update(delta);
            animationFire.update(delta);
        }
        @Override
        public void render(SpriteBatch batch) {
            batch.draw(animationFire.getFrame(), position.x, position.y - animationFire.getFrame().getRegionHeight() + 5);
            batch.draw(frames[0], body.x, body.y, body.width, body.height);

        }
        public Rectangle getBody(){ return body;}
    }
    public Array<Rockets> getRocketBody(){ return rockets;}

}
