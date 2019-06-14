package com.dressTheKid.gameViews;

import android.content.Context;
import android.content.Intent;

import com.dressTheKid.OpeningActivity;
import com.dressTheKid.Shuffle;
import com.dressTheKid.assets.GameSound;
import com.dressTheKid.assets.OpeningScreenAsset;
import com.dressTheKid.assets.Tries;
import com.e_mobadara.audiomanaging.AudioSettingsActivity;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Music;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.Sprite;
import com.example.emobadaragaminglib.Implementation.AndroidSound;
import com.dressTheKid.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OpeningScreen extends Screen {
    public Sprite background ;
    public Sprite playbutton ;
    public Sprite exit  , home , sound ,rs;
    int h , w ;
    boolean f , ff   ,ispressed ;
    static Game g;

    public OpeningScreen(Game game){
        super(game);
         f =false;
         ff = ispressed =  false;
        Tries.badTry =Tries.goodTry =0 ;
        Tries.duree = new ArrayList<>();
        SimpleDateFormat endingGame = new SimpleDateFormat("yyyy.MM.dd ' ' HH:mm:ss");
        Tries.creat = endingGame.getCalendar();
        background = new Sprite(game , OpeningScreenAsset.background,0,0,game.getGraphics().getHeight(),game.getGraphics().getWidth());
        playbutton = new Sprite(game , OpeningScreenAsset.playButton,40*game.getGraphics().getWidth()/100 , 30*game.getGraphics().getHeight()/100,40*game.getGraphics().getHeight()/100,20*game.getGraphics().getWidth()/100);
       // exit = new Sprite(game , OpeningScreenAsset.exit,90*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        sound = new Sprite(game , OpeningScreenAsset.sound,90*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        rs = new Sprite(game , OpeningScreenAsset.Rs,80*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        //home = new Sprite(game , OpeningScreenAsset.home,80*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        addSprite(background);
        addSprite(playbutton);
       // addSprite(exit);
        addSprite(sound);
        addSprite(rs);
        //addSprite(home);
        // for finding places , delete after
        h = game.getGraphics().getHeight() ;
        w = game.getGraphics().getWidth() ;

        if(GameSound.on || GameSound.firston ){
            GameSound.firston=false;
            GameSound.music.play();
            GameSound.music.setLooping(true);
        }
        g = game ;
        new Thread(new Runnable() {
            public void run() {

                Shuffle.shufflePantsstart(g);
                ff= true  ;
           }
        }).start();

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

    @Override
    public void handleTouchDown(int x, int y, int pointer) {
        super.handleTouchDown(x, y, pointer);

       /* if(exit.contain(x,y)){
            System.exit(0);
            return;
        }*/
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
        if(playbutton.contain(x,y)){
            ispressed = true ;
        }
        if( ispressed && ff ){
            game.setScreen(new LanguageScreen(game));
            return;
        }
        if(rs.contain(x,y) ){
            ((Context)game).startActivity(new Intent((Context)game,AudioSettingsActivity.class));
            return;
        }
    }
}
