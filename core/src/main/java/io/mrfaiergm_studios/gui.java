package io.mrfaiergm_studios;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


public class gui {
    BitmapFont label_apples;
    BitmapFont record_label;
    BitmapFont labelMrFaiergm;
    BitmapFont GameoverLavel;
    BitmapFont Main;

    Texture fon_to_main;
    Texture apples;
    Texture gameover;

    public int applesx = 0;
    public int applesy = 400;
    public int applesx2 = 40;
    public int applesy2 = 720;
    public int applesx3 = 500;
    public int applesy3 = 600;

    public gui(){
        label_apples = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        record_label = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        labelMrFaiergm = new BitmapFont(Gdx.files.internal("Unnamed.fnt"));
        GameoverLavel = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        Main = new BitmapFont(Gdx.files.internal("myfont.fnt"));


        fon_to_main = new Texture("MainFon.png");
        apples = new Texture("apple.png");
        gameover = new Texture("gameover.png");
    }
    public void draw_labels(SpriteBatch batch, int Apples, int Record, int Lives){
        label_apples.getData().setScale(1f);
        record_label.getData().setScale(1f);
        labelMrFaiergm.getData().setScale(0.4f);
        GameoverLavel.getData().setScale(1f);

        label_apples.draw(batch, "Всего яблок: " + Apples, 50, 650);
        record_label.draw(batch, "Рекорд: " + Record, 50, 600);
        label_apples.draw(batch, "Жизни : " + Lives, 50, 550);
        labelMrFaiergm.draw(batch, "by MrFaiergm", 530, 20);

    }
    public void Main(Float deltaTime){
        applesy -= 100 * deltaTime;
        if(applesy <= -30){
            applesy = 740;
            applesx = MathUtils.random(0, 600);
        }
        applesy2 -= 120 * deltaTime;
        if(applesy2 <= -30){
            applesy2 = 740;
            applesx2 = MathUtils.random(0, 600);
        }
        applesy3 -= 110 * deltaTime;
        if(applesy3 <= -30){
            applesy3 = 740;
            applesx3 = MathUtils.random(0, 600);
        }

    }
    public void draw_main(SpriteBatch batch){
        Main.getData().setScale(1.f);
        batch.draw(fon_to_main,0,0,720,720);
        batch.draw(apples, applesx, applesy, 64, 64);
        batch.draw(apples, applesx2, applesy2, 64, 64);
        batch.draw(apples, applesx3, applesy3, 64, 64);
        Main.draw(batch, "Привет!", 100, 500);
        Main.draw(batch, "Добро пожаловать в игру!", 100, 450);
        Main.draw(batch, "Нажми на пробел чтобы начать!", 40, 300);

    }
    public void draw_gameover(SpriteBatch batch, int Record){
        GameoverLavel.getData().setScale(1f);
        batch.draw(gameover, 0, 0, 720, 720);
        GameoverLavel.draw(batch, "Вы проиграли \n потеряв все жизни! :(", 40, 500);
        GameoverLavel.draw(batch, "Рекорд : " + Record, 40, 400);
    }
    public void draw_paused(SpriteBatch batch){
        GameoverLavel.draw(batch, "Игра на \n паузе!", 40, 500);
    }

    public void dispouse(){
        label_apples.dispose();
        labelMrFaiergm.dispose();
        record_label.dispose();
        GameoverLavel.dispose();
        Main.dispose();
        fon_to_main.dispose();
        label_apples.dispose();
        apples.dispose();
        gameover.dispose();
    }
}
