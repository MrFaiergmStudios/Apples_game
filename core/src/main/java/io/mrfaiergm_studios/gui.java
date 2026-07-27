package io.mrfaiergm_studios;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;


public class gui {
    BitmapFont applesLabel;
    BitmapFont recordLabel;
    BitmapFont mrfaiergmLabel;
    BitmapFont gameOverlabel;
    BitmapFont mainLabel;
    BitmapFont livesLabel;
    BitmapFont debugFPS;
    BitmapFont debugJAVAJVM;
    BitmapFont debugJavaHeap;
    BitmapFont debugnativeHeap;
    BitmapFont versionGameLabel;

    Sprite appleSprite;
    Sprite appleSprite2;
    Sprite appleSprite3;
    Sprite appleSprite4;
    Sprite appleSprite5;
    Sprite appleGoldSprite;

    Sprite gameOverSprite;

    Texture fonMainTexture;
    Texture applesTexture;
    Texture gameOverTexture;
    Texture appleGoldTexture;
    Texture appleGreenTexture;

    public static boolean isdebug;
    long javaHeap;
    long nativeJavaHeap;
    long totalMemory;
    long freeMemory;
    long usageMemory;
    int debugJavaFps;
    float progeresAlpha = 0.0f;


    public gui(){
        applesLabel = Assets.ruFont;
        recordLabel = Assets.ruFont;
        mrfaiergmLabel = Assets.engFont;
        gameOverlabel = Assets.ruFont;
        mainLabel = Assets.ruFont;
        livesLabel = Assets.ruFont;
        versionGameLabel = Assets.engFont;

        debugJavaHeap = Assets.debugfont;
        debugnativeHeap = Assets.debugfont;
        debugJAVAJVM = Assets.debugfont;
        debugFPS = Assets.debugfont;

        Assets.engFont.getData().setScale(0.4f);


        fonMainTexture = Assets.mainFonTexture;
        applesTexture = Assets.appleTexture;
        appleGreenTexture = Assets.appleColorGreen;
        gameOverTexture = Assets.gameOverTexture;
        appleGoldTexture = Assets.appleGoldTexture;

        appleSprite = new Sprite(applesTexture);


        appleSprite2 = new Sprite(appleGreenTexture);
        appleSprite3  = new Sprite(applesTexture);
        appleGoldSprite = new Sprite(appleGoldTexture);
        appleSprite4 = new Sprite(appleGreenTexture);
        appleSprite5 = new Sprite(applesTexture);

        gameOverSprite = new Sprite(gameOverTexture);



        appleSprite.setPosition(MathUtils.random(50, 500),MathUtils.random(400, 750));

        appleSprite2.setPosition(MathUtils.random(50, 500),MathUtils.random(50, 750));

        appleSprite3.setPosition(MathUtils.random(50, 500),MathUtils.random(100, 750));

        appleSprite4.setPosition(MathUtils.random(50, 500),MathUtils.random(200, 750));

        appleSprite5.setPosition(MathUtils.random(50, 500),MathUtils.random(300, 750));

        appleGoldSprite.setPosition(MathUtils.random(50, 500),MathUtils.random(300, 750));

    }
    public void Main(Float deltaTime){
        appleSprite.translateY(- 100 * deltaTime);
        appleSprite2.translateY(- 100 * deltaTime);
        appleSprite3.translateY(- 100  * deltaTime);
        appleGoldSprite.translateY(- 100  * deltaTime);
        appleSprite4.translateY(- 100  * deltaTime);
        appleSprite5.translateY(- 100 * deltaTime);

        if(appleSprite.getY() <= -60){
            appleSprite.setPosition(MathUtils.random(0, 600), 740);
            appleSprite.setRotation(MathUtils.random(0, 180));
        }
        if(appleSprite2.getY() <= -60){
            appleSprite2.setPosition(MathUtils.random(0, 600), 740);
            appleSprite2.setRotation(MathUtils.random(0,180));
        }
        if(appleSprite3.getY() <= -60){
            appleSprite3.setPosition(MathUtils.random(0, 600), 740);
            appleSprite3.setRotation(MathUtils.random(0,180));
        }
        if(appleSprite4.getY() <= -60){
            appleSprite4.setPosition(MathUtils.random(0, 600), 740);
            appleSprite4.setRotation(MathUtils.random(0,180));
        }
        if(appleSprite5.getY() <= -60){
            appleSprite5.setPosition(MathUtils.random(0, 600), 740);
            appleSprite5.setRotation(MathUtils.random(0,180));
        }
        if(appleGoldSprite.getY() <= -60){
            appleGoldSprite.setPosition(MathUtils.random(0, 600), 740);
            appleGoldSprite.setRotation(MathUtils.random(0,180));
        }

    }

    public static boolean debugKey(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)){
            isdebug = !isdebug;
        }
        return isdebug;
    }

    public void draw_labels(SpriteBatch batch, int Apples, int Record, int Lives){

        applesLabel.draw(batch, "Всего яблок: " + Apples, 50, 650);
        recordLabel.draw(batch, "Рекорд: " + Record, 50, 600);
        livesLabel.draw(batch, "Жизни : " + Lives, 50, 550);
        mrfaiergmLabel.draw(batch, "by MrFaiergm", 530, 20);

    }
    public void draw_main(SpriteBatch batch){
        batch.draw(fonMainTexture,0,0,720,720);

        appleSprite.draw(batch);
        appleSprite2.draw(batch);
        appleSprite3.draw(batch);
        appleSprite4.draw(batch);
        appleSprite5.draw(batch);
        appleGoldSprite.draw(batch);

      mainLabel.draw(batch, "Привет!", 100, 500);
      mainLabel.draw(batch, "Добро пожаловать в игру!", 100, 450);
      mainLabel.draw(batch, "Нажми на пробел чтобы начать!", 40, 300);

    }

    public void gameOverDraw(SpriteBatch batch, int Record, float deltaTime){
        if(progeresAlpha < 1.0f){
            progeresAlpha += (1.0f / 1.0f) * deltaTime;
        }
        if(progeresAlpha > 1.0f){
            progeresAlpha = 1.0f;
        }
        gameOverSprite.setAlpha(progeresAlpha);
        gameOverSprite.setPosition(0, 0);
        gameOverSprite.setScale(2.0f);
        gameOverSprite.draw(batch);

        gameOverlabel.draw(batch, "Вы проиграли \n потеряв все жизни! :(", 40, 500);
        gameOverlabel.draw(batch, "Рекорд : " + Record, 40, 400);
    }

    public void draw_paused(SpriteBatch batch){
        gameOverlabel.draw(batch, "Игра на \n паузе!", 40, 500);
    }
    public void debugDraw(SpriteBatch batch){
         javaHeap = Gdx.app.getJavaHeap() / (1024 * 1024);
         nativeJavaHeap = Gdx.app.getNativeHeap() / (1024 * 1024);
         totalMemory = Runtime.getRuntime().totalMemory() / (1024 * 1024);
         freeMemory = Runtime.getRuntime().freeMemory() / (1024 * 1024);
         debugJavaFps = Gdx.graphics.getFramesPerSecond();
         usageMemory = totalMemory - freeMemory;

        debugJavaHeap.draw(batch, "Ram JAVA" + javaHeap + "MB", 490, 700);
        debugnativeHeap.draw(batch, "Ram Nt " + nativeJavaHeap + "MB", 510, 680);
        debugJAVAJVM.draw(batch, "totalRam " + totalMemory + "MB", 455, 660);
        debugJAVAJVM.draw(batch, "freeram" + freeMemory + "MB", 493, 640);
        debugJAVAJVM.draw(batch, "usageRAM " + usageMemory + "MB", 473, 620);
        debugJAVAJVM.draw(batch, "RenderCalls " + batch.renderCalls, 460, 580);
        debugFPS.draw(batch, "fps" + debugJavaFps, 620, 560);

    }
    public void versionGameDraw(SpriteBatch batch){
        versionGameLabel.draw(batch, "Version" + Assets.gameVersion, 10, 20);
    }


    public void dispouse(){

    }
}
