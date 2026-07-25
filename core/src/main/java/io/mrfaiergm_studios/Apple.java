package io.mrfaiergm_studios;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Apple {
    public Rectangle appleColliision;

    public Texture appleTexture;
    public Texture golden_apple;
    public Texture current_texture;

    public float speed;
    public float timer = 0;
    public boolean isGold;

    public Apple(){
        appleColliision = new Rectangle();

        appleTexture = new Texture("apple.png");
        golden_apple = new Texture("apple_gold.png");
        current_texture = appleTexture;

        appleColliision.width = 80;
        appleColliision.height = 75;
        respawn();
    }

    public void respawn(){
        appleColliision.x = MathUtils.random(0, 700);
        appleColliision.y = 720;
        speed = MathUtils.random(200, 400);
        int gold = MathUtils.random(1, 10);
        if(gold == 10){
            current_texture = golden_apple;
            isGold = true;

        }else {
            current_texture = appleTexture;
            isGold = false;
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
        if(appleColliision.y < 0){
            respawn();
            return true;
        }else {
            return false;
        }
    }

    public void draw(SpriteBatch batch){
        batch.draw(current_texture, appleColliision.x, appleColliision.y, appleColliision.width, appleColliision.height);
    }
    public void dispouse(){
        appleTexture.dispose();
        golden_apple.dispose();
        current_texture.dispose();
    }
}
