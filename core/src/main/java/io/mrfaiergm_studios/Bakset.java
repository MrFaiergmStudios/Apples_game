package io.mrfaiergm_studios;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Bakset {
    public Rectangle basketRect;

    public Texture basketTextureDefault;
    public Texture getBasketTexture2;
    public Texture basketTexture3;
    public Texture basketTexture4;
    public Texture basketCurrentTexture;

    public float deltaTime;
    public int speed;

    public Bakset(){
        basketRect = new Rectangle();

        basketTextureDefault = new Texture("Basket.png");
        getBasketTexture2 = new Texture("Basket2.png");
        basketTexture3 = new Texture("Basket3.png");
        basketTexture4 = new Texture("Basket4.png");
        basketCurrentTexture = basketTextureDefault;

        basketRect.width = 64;
        basketRect.height = 30;
        basketRect.x = 400;
        basketRect.y = 30;

        speed = 400;

    }

    public void update(int Apples){
        deltaTime = Gdx.graphics.getDeltaTime();

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
            basketRect.x += speed * deltaTime;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
            basketRect.x -= speed * deltaTime;
        }
        if(Apples >= 1){
            basketCurrentTexture = getBasketTexture2;
        }
        if(Apples >= 10){
            basketCurrentTexture = basketTexture3;
        }
        if (Apples >= 30){
            basketCurrentTexture = basketTexture4;
        }
    }

    public void draw(SpriteBatch batch){
        batch.draw(basketCurrentTexture, basketRect.x, basketRect.y, basketRect.width = 120, basketRect.height = 100);

    }
    public void dispouse() {
        basketTextureDefault.dispose();
        getBasketTexture2.dispose();
        basketTexture3.dispose();
        basketTexture4.dispose();
        basketCurrentTexture.dispose();
    }
}
