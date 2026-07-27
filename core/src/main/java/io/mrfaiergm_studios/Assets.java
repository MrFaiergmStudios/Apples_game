package io.mrfaiergm_studios;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
    public static Texture appleTexture;
    public static Texture appleGoldTexture;
    public static Texture basketDefaultTexture;
    public static Texture basketUpgradeTexture;
    public static Texture basketUpgradeTexture2;
    public static Texture basketUpgradeTexture3;
    public static Texture gameOverTexture;
    public static Texture hearthTexture;
    public static Texture mainFonTexture;
    public static Texture posterTexture;
    public static Texture appleColorGreen;
    public static Texture appleColorGreenList;
    public static Texture appleList;

    public static BitmapFont ruFont;
    public static BitmapFont engFont;
    public static BitmapFont debugfont;

    public static float gameVersion;


    public Assets(){
        appleTexture = new Texture("apple.png");
        appleGoldTexture = new Texture("apple_gold.png");
        basketDefaultTexture = new Texture("Basket.png");
        basketUpgradeTexture = new Texture("Basket2.png");
        basketUpgradeTexture2 = new Texture("Basket3.png");
        basketUpgradeTexture3 = new Texture("Basket4.png");
        mainFonTexture = new Texture("MainFon.png");
        gameOverTexture = new Texture("gameover.png");
        appleColorGreen = new Texture("apple_colorgreenlist.png");
        appleColorGreenList = new Texture("apple_greenlist.png");
        appleList = new Texture("appleList.png");


        ruFont = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        engFont = new BitmapFont(Gdx.files.internal("Unnamed.fnt"));
        debugfont = new BitmapFont(Gdx.files.internal("Unnamed.fnt"));
        debugfont.getData().setScale(0.5f);
        gameVersion = 1.2f;
    }

    public void assetsDispouse(){
        appleTexture.dispose();
        appleGoldTexture.dispose();
        appleColorGreenList.dispose();
        appleColorGreen.dispose();
        appleList.dispose();

        basketDefaultTexture.dispose();
        basketUpgradeTexture.dispose();
        basketUpgradeTexture2.dispose();
        basketUpgradeTexture3.dispose();

        mainFonTexture.dispose();
        gameOverTexture.dispose();

        ruFont.dispose();
        engFont.dispose();
        debugfont.dispose();
    }
}
