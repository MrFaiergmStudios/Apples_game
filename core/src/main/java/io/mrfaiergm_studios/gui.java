package io.mrfaiergm_studios;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;


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

    Sprite gameOverSprite;

    Texture fonMainTexture;
    Texture gameOverTexture;

    public static boolean isdebug;
    long javaHeap;
    long nativeJavaHeap;
    long totalMemory;
    long freeMemory;
    long usageMemory;
    int debugJavaFps;
    float progeresAlpha = 0.0f;
    Array<Sprite> myApplen = new Array<>();
    Array<Texture> appleCurrentTexture = new Array<>();





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

        appleCurrentTexture.add(Assets.appleColorGreen);
        appleCurrentTexture.add(Assets.appleGoldTexture);
        appleCurrentTexture.add(Assets.appleTexture);
        appleCurrentTexture.add(Assets.appleColorGreen);
        appleCurrentTexture.add(Assets.appleColorGreenList);

        fonMainTexture = Assets.mainFonTexture;
        gameOverTexture = Assets.gameOverTexture;


        for(int i = 0; i < 20; i++){
            Sprite appleSprite = new Sprite(appleCurrentTexture.get(MathUtils.random(0,4)));
            appleSprite.setPosition(MathUtils.random(0, 600),MathUtils.random(100, 750));
            myApplen.add(appleSprite);
        }

        gameOverSprite = new Sprite(gameOverTexture);




    }
    public void Main(Float deltaTime){
        for(int i = 0; i < myApplen.size; i++){
            Sprite currentApple = myApplen.get(i);
            currentApple.translateY(-100 * deltaTime);
            if(currentApple.getY() <= -60){
                currentApple.setPosition(MathUtils.random(0, 600),900);
                currentApple.setRotation(MathUtils.random(0, 180));
                currentApple.setRegion(appleCurrentTexture.get(MathUtils.random(0,4)));
            }
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

        for(int i = 0; i < myApplen.size; i++){
            Sprite currentApple = myApplen.get(i);
            currentApple.draw(batch);
        }

      mainLabel.draw(batch, "Привет!", 100, 500);
      mainLabel.draw(batch, "Добро пожаловать в игру!", 100, 450);
      mainLabel.draw(batch, "Нажми на пробел чтобы начать!", 40, 300);
        if(configMods.modIsAcrived){
            mainLabel.draw(batch, "Моды Включены", 100, 200);
        }

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
