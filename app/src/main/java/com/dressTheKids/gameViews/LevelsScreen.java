package com.dressTheKids.gameViews;

import android.util.Log;

import com.dressTheKids.R;
import com.dressTheKids.Shuffle;
import com.dressTheKids.assets.GameSound;
import com.dressTheKids.assets.LevelAsset;
import com.dressTheKids.levelsLogic;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.Sprite;



public class LevelsScreen  extends Screen {
    public Sprite background ;
    public Sprite level1 , level2  ;
    public Sprite  home;
    int h , w ;
    static Game g;

    public LevelsScreen(Game game){
        super(game);
        background = new Sprite(game , LevelAsset.background,0,0,game.getGraphics().getHeight(),game.getGraphics().getWidth());
        level1 = new Sprite(game , LevelAsset.level1,20*game.getGraphics().getWidth()/100 , 30*game.getGraphics().getHeight()/100,40*game.getGraphics().getHeight()/100,20*game.getGraphics().getWidth()/100);
        level2 = new Sprite(game , LevelAsset.level2,60*game.getGraphics().getWidth()/100 , 30*game.getGraphics().getHeight()/100,40*game.getGraphics().getHeight()/100,20*game.getGraphics().getWidth()/100);
        GameSound.soundsprite = new Sprite(game , GameSound.sound,80*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        home  = new Sprite(game , LevelAsset.home,90*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        addSprite(background);
        addSprite(GameSound.soundsprite);
        addSprite(home);
        addSprite(level1);
        addSprite(level2);


        h = game.getGraphics().getHeight() ;
        w = game.getGraphics().getWidth() ;

        if(GameSound.on){
            GameSound.music.setLooping(true);
            GameSound.music.setVolume((float)0.2);
        }else{
            GameSound.music.setVolume((float)0);
        }
        g = game ;

    }

    @Override
    public void render(float deltaTime) {

    }

    @Override
    public void resume() {
        if(GameSound.on){
            GameSound.music.setVolume((float) 0.2);
        }
    }
    @Override
    public void backButton() {
    }
    @Override
    public void pause(){
        GameSound.music.setVolume((float)0);

    }

    @Override
    public void handleTouchDown(int x, int y, int pointer) {
        super.handleTouchDown(x, y, pointer);


        if(GameSound.soundsprite.contain(x,y)){
            Log.i("Opening Screen ", "OpeningScreen:  GameSound.on" + GameSound.on);
            GameSound.change(game);
            return;
        }

        if(level1.contain(x , y)){
            Shuffle.level = 1 ;
            game.setScreen(new LanguageScreen(game));
            return;
        }
        if(level2.contain(x , y)){
            Shuffle.level = 2 ;
            game.setScreen(new LanguageScreen(game));
            return;
        }

        if(home.contain(x,y) ){
            game.setScreen(new OpeningScreen(game));
            return;
        }
    }
}
