package io.mrfaiergm_studios;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import jdk.internal.foreign.layout.StructLayoutImpl;


public class Bakset {
    public Rectangle basketRect;

    public Texture basketTextureDefault;
    public Texture BasketTexture2;
    public Texture basketTexture3;
    public Texture basketTexture4;
    public Texture basketCurrentTexture;

    public Sprite basketSprite;

    public BitmapFont reportLabel;

    public float deltaTime;
    public float speed = 200f;
    public float rotationBasket = 0;
    public float timeinBoost = 0;
    public float animationProggersScale = 0;


    public Bakset(){
        basketRect = new Rectangle();

        basketTextureDefault = Assets.basketDefaultTexture;
        BasketTexture2 = Assets.basketUpgradeTexture;
        basketTexture3 = Assets.basketUpgradeTexture2;
        basketTexture4 = Assets.basketUpgradeTexture3;

        basketCurrentTexture = basketTextureDefault;


        basketSprite = new Sprite(basketCurrentTexture);
        basketSprite.setScale(1.5f,1.5f);
        basketSprite.setOrigin(basketSprite.getWidth() / 2, basketSprite.getHeight() / 2);

        reportLabel = Assets.debugfont;

        basketRect.width = 100;
        basketRect.height = 40;
        basketRect.x = 400;
        basketRect.y = 30;

        speed = 300f;

    }

    public void update(int Apples){
        deltaTime = Gdx.graphics.getDeltaTime();
        if(animationProggersScale < 1.0f){
            animationProggersScale += (1.0f / 0.5f) * deltaTime;
        }
        rotationBasket = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            speed += 100 * deltaTime;
            rotationBasket = -10;
            basketRect.x += speed  * deltaTime;
            if(speed >= 450){
                speed = 450;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            speed += 100 * deltaTime;
            rotationBasket = 10;
            if(speed >= 450){
                speed = 450;
            }
            basketRect.x -= speed  * deltaTime;

        } else {
            speed -= 300 * deltaTime;
            if(speed <= 300){
                speed = 300;
                rotationBasket = 0;
            }
        }
        if(Apples == 0){
            basketCurrentTexture = basketTextureDefault;
            basketSprite.setRegion(basketCurrentTexture);
        }
        if(Apples >= 1){
            basketCurrentTexture = BasketTexture2;
            basketSprite.setRegion(basketCurrentTexture);
        }
        if(Apples >= 10){
            basketCurrentTexture = basketTexture3;
            basketSprite.setRegion(basketCurrentTexture);
        }
        if (Apples >= 30){
            basketCurrentTexture = basketTexture4;
            basketSprite.setRegion(basketCurrentTexture);
        }
        if(basketRect.x <= 0){
            basketRect.x = 0;
        }
        if(basketRect.x >= 720 - basketRect.width){
            basketRect.x = 590;

        }
    }

    public void draw(SpriteBatch batch){
        basketSprite.setPosition(basketRect.x, basketRect.y);
        basketSprite.setRotation(rotationBasket);
        float setScaleAnim = animationProggersScale * 1.5f;
        basketSprite.setScale(setScaleAnim, setScaleAnim);
        if(gui.isdebug){
            reportLabel.draw(batch, "Spd " + speed, basketRect.x + 50, basketRect.y + 100);
            reportLabel.draw(batch, "time " + timeinBoost, basketRect.x + 50, basketRect.y + 130);
            reportLabel.draw(batch, "rt " + rotationBasket, basketRect.x + 50, basketRect.y + 200);
        }
        basketSprite.draw(batch);
    }

}
