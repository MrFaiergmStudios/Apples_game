package io.mrfaiergm_studios;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Apple {
    public Rectangle appleColliision;

    public Texture appleTexture;
    public Texture golden_AppleTexture;
    public Texture appleGreen;
    public Texture appleGeenList;
    public Texture appleList;
    public Texture current_AppleTexture;

    public float speed;
    public float timer = 0;
    public float rotationApple;
    public boolean isGold;
    public boolean isGreen;
    public float velocityY;

    Sprite appleSprite;

    public BitmapFont debugLabel;

    public Apple(){
        appleColliision = new Rectangle();

        appleTexture = Assets.appleTexture;
        appleGreen = Assets.appleColorGreen;
        appleGeenList = Assets.appleColorGreenList;
        golden_AppleTexture = Assets.appleGoldTexture;
        appleList = Assets.appleList;
        current_AppleTexture = appleTexture;

        appleSprite = new Sprite(current_AppleTexture);

        appleColliision.width = 80;
        appleColliision.height = 75;

        rotationApple = MathUtils.random(0, 360);

        debugLabel = Assets.debugfont;

        respawn();
    }

    public void respawn(){
        appleColliision.x = MathUtils.random(0, 550);
        appleColliision.y = 720;

        speed = MathUtils.random(300, 500);

        int gold = MathUtils.random(1, 10);
        int green = MathUtils.random(1, 5);
        int greenList = MathUtils.random(1, 5);

        rotationApple = MathUtils.random(0, 180);

        if(gold == 10){
            current_AppleTexture = golden_AppleTexture;
            appleSprite.setRegion(current_AppleTexture);
            isGold = true;
            speed = MathUtils.random(400, 600);

        } else if (green == 5) {
            isGreen = true;
            current_AppleTexture = appleGreen;
            appleSprite.setRegion(current_AppleTexture);
            speed = MathUtils.random(300, 500);
        }
        else if(greenList == 5){
            isGreen = true;
            current_AppleTexture = appleGeenList;
            appleSprite.setRegion(current_AppleTexture);
            speed = MathUtils.random(400, 600);
        }
        else {
            int applelist = MathUtils.random(1,2);
            if(applelist == 2){
                current_AppleTexture = appleList;
                appleSprite.setRegion(current_AppleTexture);
            }
            current_AppleTexture = appleTexture;
            appleSprite.setRegion(current_AppleTexture);
            isGold = false;
            isGreen = false;
            speed = MathUtils.random(300, 500);
        }

        timer = 0;
    }

    public void update(float deltaTime){
        timer += deltaTime;

        if(timer == 0f){
            appleColliision.y -= speed * deltaTime;
        }else if (timer >= 0.5f){
            appleColliision.y -= speed * 2 * deltaTime;
        } else {
            appleColliision.y -= speed  * deltaTime;
        }

        if(appleColliision.x >= 720 + appleColliision.width){
            appleColliision.x = 600;
        }
    }

    public boolean isfaling(){
        if(appleColliision.y < -60){
            respawn();
            return true;
        }else {
            return false;
        }
    }

    public void draw(SpriteBatch batch){
        appleSprite.setPosition(appleColliision.x, appleColliision.y);
        appleSprite.setRotation(rotationApple);
        appleSprite.draw(batch);
        if(gui.isdebug){
            debugLabel.draw(batch, "spd" + speed, appleColliision.x + 50, appleColliision.y);
            debugLabel.draw(batch, "isgld " + isGold, appleColliision.x + 50, appleColliision.y - 20);
            debugLabel.draw(batch, "rt " + rotationApple, appleColliision.x + 50, appleColliision.y - 40);
        }
    }
    public void dispouse(){

    }
}
