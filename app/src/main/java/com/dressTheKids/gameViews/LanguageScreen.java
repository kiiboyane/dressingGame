package com.dressTheKids.gameViews;

import com.dressTheKids.Shuffle;
import com.dressTheKids.assets.GameSound;
import com.dressTheKids.assets.Language;
import com.dressTheKids.assets.OpeningScreenAsset;
import com.dressTheKids.assets.Tries;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.Sprite;
import com.dressTheKids.R;

public class LanguageScreen  extends Screen {

    Sprite arabic , french , english  , home ;

    Sprite background ;
    int h , w ;
    boolean ff  , isarabic , isenglish ,isfrench   ;
    Game g  ;
    public LanguageScreen(Game game){

        super(game);

        h = game.getGraphics().getHeight() ;
        w = game.getGraphics().getWidth() ;
        background = new Sprite(game ,Language.background,0,0,game.getGraphics().getHeight(),game.getGraphics().getWidth());
        english = new Sprite(game ,Language.english,343*w/1000,595*h/1000,15*game.getGraphics().getHeight()/100,30*game.getGraphics().getWidth()/100);
        french = new Sprite(game ,Language.french,343*w/1000,400*h/1000,15*game.getGraphics().getHeight()/100,30*game.getGraphics().getWidth()/100);
        arabic  = new Sprite(game ,Language.arabic,343*w/1000,218*h/1000,15*game.getGraphics().getHeight()/100,30*game.getGraphics().getWidth()/100);
        home = new Sprite(game , OpeningScreenAsset.home,90*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        GameSound.soundsprite = new Sprite(game , GameSound.sound,80*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);

        Tries.goodTry = 0 ;
        Tries.badTry = 0 ;

        addSprite(background);
        addSprite(arabic);
        addSprite(french);
        addSprite(english);
        addSprite(home);
        addSprite(GameSound.soundsprite);

        g = game  ;

    }

    @Override
    public void render(float deltaTime) {

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

    public void handleTouchDown(int x, int y, int pointer) {
        super.handleTouchDown(x, y, pointer);
        if(arabic.contain(x,y)){
            Language.chosenLanguage = 0 ;
            GameSound.Remplir(game);
            GameSound.Intro.play(1);
            game.setScreen(new LoadingScreen2(game));
            return;
        }


        if(french.contain(x,y)){
            Language.chosenLanguage = 1 ;
            GameSound.Remplir(game);
            GameSound.Intro.play(5);

            game.setScreen(new LoadingScreen2(game));
            return;
        }

        if(english.contain(x,y)){
            Language.chosenLanguage = 2 ;
            GameSound.Remplir(game);
            GameSound.Intro.play(3);
            game.setScreen(new LoadingScreen2(game));
            return;
        }

        if(home.contain(x,y)){
            game.setScreen(new OpeningScreen(game));

            return;
        }
        if(GameSound.soundsprite.contain(x,y)){
           GameSound.change(game);
        }
    }
}
