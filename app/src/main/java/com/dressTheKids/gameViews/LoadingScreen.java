package com.dressTheKids.gameViews;



import com.dressTheKids.R;
import com.dressTheKids.assets.Clothes;
import com.dressTheKids.assets.GameSound;
import com.dressTheKids.assets.Language;
import com.dressTheKids.assets.LevelAsset;
import com.dressTheKids.assets.OpeningScreenAsset;
import com.dressTheKids.assets.Tries;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.Sprite;

import java.util.ArrayList;

public class LoadingScreen extends Screen  {

    public Sprite background  , loading ;
    public Integer time = 0 ;

    public LoadingScreen(Game game){
        super(game);

        //assets for the opening screen
        OpeningScreenAsset.background =  game.getGraphics().newImage(R.mipmap.background,Graphics.ImageFormat.ARGB8888, game.getResources());
        OpeningScreenAsset.playButton =   game.getGraphics().newImage(R.mipmap.playbutton,Graphics.ImageFormat.ARGB8888, game.getResources());
        OpeningScreenAsset.home =   game.getGraphics().newImage(R.mipmap.home,Graphics.ImageFormat.ARGB8888, game.getResources());
        GameSound.sound =   game.getGraphics().newImage(R.mipmap.loud,Graphics.ImageFormat.ARGB8888, game.getResources());
        OpeningScreenAsset.Rs =   game.getGraphics().newImage(R.mipmap.setting,Graphics.ImageFormat.ARGB8888, game.getResources());
        GameSound.music =    game.getAudio().createMusic(R.raw.music);

        //the asset for the language screen
        Language.background =   game.getGraphics().newImage(R.mipmap.backlanguage,Graphics.ImageFormat.ARGB8888, game.getResources());
        Language.arabic =   game.getGraphics().newImage(R.mipmap.arabic,Graphics.ImageFormat.ARGB8888, game.getResources());
        Language.french =   game.getGraphics().newImage(R.mipmap.french,Graphics.ImageFormat.ARGB8888, game.getResources());
        Language.english =   game.getGraphics().newImage(R.mipmap.english,Graphics.ImageFormat.ARGB8888, game.getResources());
        GameSound.firston = true;
        GameSound.on = true;



        // the asset for the level screen
        LevelAsset.background =   game.getGraphics().newImage(R.mipmap.levelbackground,Graphics.ImageFormat.ARGB8888, game.getResources());
        LevelAsset.home =   game.getGraphics().newImage(R.mipmap.home,Graphics.ImageFormat.ARGB8888, game.getResources());
        LevelAsset.level1 =   game.getGraphics().newImage(R.mipmap.level1,Graphics.ImageFormat.ARGB8888, game.getResources());
        LevelAsset.level2 =   game.getGraphics().newImage(R.mipmap.level2,Graphics.ImageFormat.ARGB8888, game.getResources());
        LevelAsset.sound =   game.getGraphics().newImage(R.mipmap.loud,Graphics.ImageFormat.ARGB8888, game.getResources());
        LevelAsset.loading =   game.getGraphics().newImage(R.mipmap.loading1,Graphics.ImageFormat.ARGB8888, game.getResources());

        Clothes.empty = game.getGraphics().newImage(R.mipmap.empty,Graphics.ImageFormat.ARGB8888, game.getResources()) ;

        Tries.badTry =Tries.goodTry =0 ;
        Tries.duree = new ArrayList<>();

        background = new Sprite(game , LevelAsset.background,0,0,game.getGraphics().getHeight(),game.getGraphics().getWidth());
        //loading = new Sprite(game , LevelAsset.loading,0,0,game.getGraphics().getHeight(),game.getGraphics().getWidth());
        addSprite(background);
        //addSprite(loading);


    }

   public void gotoOpeninigScreen(Game game ){
        game.setScreen(new OpeningScreen(game));
    }
    @Override
    public void render(float deltaTime) {
            time ++ ;
            if(time ==  40 ){
                gotoOpeninigScreen(game);
            }
    }

    @Override
    public void resume() {

    }

    @Override
    public void backButton() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void handleTouchDown(int x, int y, int pointer) {
        super.handleTouchDown(x, y, pointer);


    }
}
