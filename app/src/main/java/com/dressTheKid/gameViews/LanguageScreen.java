package com.dressTheKid.gameViews;

import android.util.Log;

import com.dressTheKid.Shuffle;
import com.dressTheKid.assets.GameSound;
import com.dressTheKid.assets.Language;
import com.dressTheKid.assets.OpeningScreenAsset;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Music;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.Sprite;
import com.dressTheKid.R;

public class LanguageScreen  extends Screen {

    Sprite arabic , french , english  , exit , home , sound;

    Sprite background ;
    int h , w ;
    boolean ff  , isarabic , isenglish ,isfrench   ;
    Game g  ;
    public LanguageScreen(Game game){

        super(game);
        //GameSound.music.play();
        //GameSound.music.setLooping(true);
        ff  = isarabic = isenglish = isfrench = false;
        h = game.getGraphics().getHeight() ;
        w = game.getGraphics().getWidth() ;
        background = new Sprite(game ,Language.background,0,0,game.getGraphics().getHeight(),game.getGraphics().getWidth());
        english = new Sprite(game ,Language.english,343*w/1000,595*h/1000,15*game.getGraphics().getHeight()/100,30*game.getGraphics().getWidth()/100);
        french = new Sprite(game ,Language.french,343*w/1000,400*h/1000,15*game.getGraphics().getHeight()/100,30*game.getGraphics().getWidth()/100);
        arabic  = new Sprite(game ,Language.arabic,343*w/1000,218*h/1000,15*game.getGraphics().getHeight()/100,30*game.getGraphics().getWidth()/100);
        exit = new Sprite(game , OpeningScreenAsset.exit,90*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        home = new Sprite(game , OpeningScreenAsset.home,80*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        sound = new Sprite(game , OpeningScreenAsset.sound,70*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);



        addSprite(background);
        addSprite(arabic);
        addSprite(french);
        addSprite(english);
        addSprite(exit);
        addSprite(home);
        addSprite(sound);
        g = game  ;
        new Thread(new Runnable() {
            public void run() {
                Shuffle.shuffleTopsstart(g);
                ff= true  ;
            }
        }).start();


    }

    @Override
    public void render(float deltaTime) {
        /*Log.i("ll", "arabic : " + 1000*arabic.getX()/w  + "  : " +1000*arabic.getY()/h  );
        Log.i("ll", "french: " + 1000*french.getX()/w  + "  : " +1000*french.getY()/h  );
        Log.i("ff", "english: " + 1000*english.getX()/w  + "  : " +1000*english.getY()/h  );*/
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
            isarabic=true; isenglish = false ;  isfrench = false;
        }
        if(isarabic && ff ){
            Language.chosenLanguage = 0 ;
            GameSound.Remplir(game);
            GameSound.Intro.play(1);
            game.setScreen(new GameScreen1(game));
            return;
        }

        if(french.contain(x,y)){
            isarabic=false; isenglish = false ;  isfrench = true;
        }if(isfrench && ff){
            Language.chosenLanguage = 1 ;
            GameSound.Remplir(game);
            GameSound.Intro.play(5);

            game.setScreen(new GameScreen1(game));
            return;
        }

        if(english.contain(x,y)){
             isenglish = true;     isarabic=false;          isfrench = false;

        }
        if(isenglish && ff ){
            Language.chosenLanguage = 2 ;
            GameSound.Remplir(game);
            GameSound.Intro.play(3);
            game.setScreen(new GameScreen1(game));
            return;
        }
        if(exit.contain(x,y)){
            //new Activity1() ;
            //game.setScreen(new GameScreen1(game));
            System.exit(0);

            return;
        }
        if(home.contain(x,y)){
            //new Activity1() ;
            //game.setScreen(new GameScreen1(game));
            game.setScreen(new OpeningScreen(game));

            return;
        }
        if(sound.contain(x,y)){
            if(GameSound.on){
                GameSound.music.stop();
                OpeningScreenAsset.sound = game.getGraphics().newImage(R.mipmap.mute,Graphics.ImageFormat.ARGB8888,game.getResources());
                sound.setImage(OpeningScreenAsset.sound );
            }else{
                GameSound.music.play();
                GameSound.music.setLooping(true);
                OpeningScreenAsset.sound = game.getGraphics().newImage(R.mipmap.loud,Graphics.ImageFormat.ARGB8888,game.getResources());
                sound.setImage(OpeningScreenAsset.sound );

            }
            GameSound.on=!GameSound.on;
            return;
        }
    }
}
