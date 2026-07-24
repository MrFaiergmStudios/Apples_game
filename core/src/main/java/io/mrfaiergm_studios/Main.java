package io.mrfaiergm_studios;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import jdk.internal.org.jline.terminal.TerminalBuilder;
import org.ietf.jgss.GSSContext;

import java.awt.*;
import java.io.Console;
import java.security.spec.ECGenParameterSpec;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;

    public Texture basketTexture;
    public Texture basketTexture2;
    public Texture basketTexture3;
    public Texture basketTexture4;
    public Texture appleTexture;
    public Texture heart;
    public Texture fon;

    public Preferences prf;


    public Rectangle basket;
    public Rectangle apple;

    public BitmapFont font;
    public BitmapFont MrFaiergmEmblem;

    public enum gameActives{
        GAME, PAUSE, GAMEOVER, MAIN
    }
    public gameActives current_state = gameActives.MAIN;

    public float basketSpeed = 300;
    public float appleSpeed = 200;
    public int cois = 0;
    public int hearts = 3;
    public int record = 0;

    @Override
    public void create() {
        prf = Gdx.app.getPreferences("Save_Apple_MrFaiergm");
        record = prf.getInteger("record", 0);


        batch = new SpriteBatch();
        appleTexture = new Texture("apple.png");
        basketTexture = new Texture("Basket.png");
        basketTexture2 = new Texture("Basket2.png");
        basketTexture3 = new Texture("Basket3.png");
        basketTexture4 = new Texture("Basket3.png");
        heart = new Texture("heart.png");
        fon = new Texture("fon.png");


        basket = new Rectangle();
        basket.width = 64;
        basket.height = 64;
        basket.x = 400;
        basket.y = 40;

        apple = new Rectangle();
        apple.height = 64;
        apple.width = 64;
        apple.y = 700;
        apple.x = 400;

        font = new BitmapFont(Gdx.files.internal("Unnamed.fnt"));
        MrFaiergmEmblem = new BitmapFont(Gdx.files.internal("Unnamed.fnt"));

    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(200/255f, 191/255f, 231/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(current_state == gameActives.GAME){
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
                basket.x += basketSpeed * deltaTime;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
                basket.x -= basketSpeed * deltaTime;
            }

            if(apple.overlaps(basket)){
                cois++;
                if(cois > record){
                    record = cois;
                    prf.putInteger("record", record);
                }
                apple.y = 700;
                apple.x = MathUtils.random(0, 600);
                appleSpeed = MathUtils.random(100, 400);
            }
            if (apple.y < 0){
                hearts--;
                apple.y = 700;
                apple.x = MathUtils.random(0, 600);
                appleSpeed = MathUtils.random(100, 400);
            }
            if (cois == 1){
                basketTexture = basketTexture2;
            }
            if (cois > 10 ){
                basketTexture = basketTexture3;
            }
            if (cois > 20){
                basketTexture = basketTexture4;
            }

            apple.y -=  appleSpeed * deltaTime;

        }
        if(hearts <= 0){
            current_state = gameActives.GAMEOVER;
        }

        if(current_state == gameActives.MAIN){
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                current_state = gameActives.GAME;
            }
        }
        if(current_state == gameActives.GAMEOVER){
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                current_state = gameActives.GAME;
                cois = 0;
                hearts = 3;
                prf.flush();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){
            if (current_state == gameActives.GAME){
                current_state = gameActives.PAUSE;
            } else if (current_state == gameActives.PAUSE) {
                current_state = gameActives.GAME;

            }
        }


        batch.begin();


        if (current_state != gameActives.MAIN){
            MrFaiergmEmblem.getData().setScale(0.3f);

            font.draw(batch, "Score " + cois, 40, 400);
            font.draw(batch, "Hearts " + hearts, 40, 300);
            font.draw(batch, "Record " + record, 40, 350);
            MrFaiergmEmblem.draw(batch, "By MrFaiergm", 500, 20);

            batch.draw(basketTexture, basket.x, basket.y, 100, 100);
            batch.draw(appleTexture, apple.x, apple.y, apple.width, apple.height);
        }

        if(current_state == gameActives.MAIN){
            batch.draw(heart, 100, 60, 369, 300);
            font.getData().setScale(0.5f);
            font.draw(batch, "Hello!", 100, 450 );
            font.draw(batch, "Welcome to game - appleDown", 100, 380 );
            font.draw(batch, "Click Spase to start!", 100, 340 );
            font.draw(batch, "Game dev by MrFaiergm", 100, 100 );
        }
        if (current_state == gameActives.PAUSE){
            font.draw(batch, "Game is Paused", 300, 400);

        }
        if(current_state == gameActives.GAMEOVER){
            font.getData().setScale(0.5f);

            batch.draw(fon, 0, 0, 800, 800);
            font.draw(batch, "Game over! \n click Space to reload", 100, 400);
            MrFaiergmEmblem.draw(batch, "By MrFaiergm", 500, 20);
            basketTexture = basketTexture;

        }

        batch.end();


    }

    @Override
    public void dispose() {
        batch.dispose();
        fon.dispose();
        font.dispose();
        basketTexture.dispose();
        basketTexture2.dispose();
        basketTexture3.dispose();
        basketTexture4.dispose();
        appleTexture.dispose();
        MrFaiergmEmblem.dispose();
        heart.dispose();
    }
}
