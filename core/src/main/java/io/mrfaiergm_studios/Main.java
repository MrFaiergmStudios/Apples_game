package io.mrfaiergm_studios;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;


public class Main extends ApplicationAdapter {
    SpriteBatch batch;

    Bakset bakset;
    Apple apple;
    gui GUI;
    Assets assets;

    public float deltaTime;

    public int Apples;
    public int RecordMass;
    public int Lives;

    Preferences prf;

    public enum GameState{
        PLAYING, MAIN, GAMEOVER, PAUSED
    }
    public GameState current_state = GameState.MAIN;


    @Override
    public void create() {
        prf = Gdx.app.getPreferences("Apple_Game_mrFaiergmSaves");
        batch = new SpriteBatch();

        assets = new Assets();

        bakset = new Bakset();
        apple = new Apple();
        GUI = new gui();

        Apples = 0;
        RecordMass = prf.getInteger("Record");
        Lives = 3;
    }

    @Override
    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(200/255f, 191/255f, 231/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gui.debugKey();

        if(current_state == GameState.PLAYING){
            bakset.update(Apples);
            apple.update(deltaTime);

            if(apple.appleColliision.overlaps(bakset.basketRect)){
                Apples++;
                if(RecordMass <= Apples){
                    RecordMass = Apples;
                    prf.putInteger("Record", RecordMass);
                }
                if(apple.isGold == true){
                    Apples += 5;
                    if(RecordMass <= Apples){
                        RecordMass = Apples;
                        prf.putInteger("Record", RecordMass);
                    }
                }
                if(apple.isGreen){
                    Apples += 2;
                    if(RecordMass <= Apples){
                        RecordMass = Apples;
                        prf.putInteger("Record", RecordMass);
                    }
                }
                apple.respawn();
            }

            if(apple.isfaling()){
                Lives -= 1;


            }
            if(Lives <= 0){
                current_state = GameState.GAMEOVER;
                Lives = 3;
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.P) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) ){
            if(current_state == GameState.PLAYING){
                current_state = GameState.PAUSED;
            } else if (current_state == GameState.PAUSED) {
                current_state = GameState.PLAYING;

            }
        }
        if(current_state == GameState.MAIN){
            GUI.Main(deltaTime);
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                current_state = GameState.PLAYING;
            }
        }
        if(current_state == GameState.GAMEOVER){
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                current_state = GameState.PLAYING;
                if(RecordMass <= Apples){
                    RecordMass = Apples;
                    prf.putInteger("Record", RecordMass);
                    prf.flush();
                }
                Apples = 0;
                Lives = 3;
            }
        }

        batch.begin();


        bakset.draw(batch);
        apple.draw(batch);

        if(current_state == GameState.PLAYING){
            GUI.draw_labels(batch, Apples, RecordMass, Lives);
        }
        if(current_state == GameState.MAIN){
            GUI.draw_main(batch);
        }
        if(current_state == GameState.GAMEOVER){
            GUI.gameOverDraw(batch, RecordMass, deltaTime);
            bakset.basketCurrentTexture = bakset.basketTextureDefault;
        }
        if (current_state == GameState.PAUSED) {
            GUI.draw_paused(batch);
        }
        if (gui.isdebug){
            GUI.debugDraw(batch);
        }
        GUI.versionGameDraw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        assets.assetsDispouse();
        batch.dispose();
        prf.flush();
    }
}
