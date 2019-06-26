package com.dressTheKids.gameViews;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dressTheKids.Shuffle;
import com.dressTheKids.assets.GameSound;
import com.dressTheKids.assets.OpeningScreenAsset;
import android.support.v7.app.AppCompatActivity;
import com.dressTheKids.assets.Tries;
import com.e_mobadara.audiomanaging.AudioSettingsActivity;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.Sprite;
import com.dressTheKids.R;
import com.google.android.gms.games.GameBuffer;

import java.util.ArrayList;

public class OpeningScreen extends Screen {
    public Sprite background  ;
    public Sprite playbutton ;
    public Sprite exit  , home ,rs;
    int h , w ;
    boolean f , ff   ,ispressed ;
    static Game g;

    public OpeningScreen(Game game){
        super(game);


        if(GameSound.firston ){
            GameSound.firston= false;
            GameSound.music.play();
            GameSound.music.setLooping(true);
            GameSound.music.setVolume((float)0.2);
        }
        if(GameSound.on){
            GameSound.music.setVolume((float)0.2);
        }
        Tries.badTry =Tries.goodTry =0 ;
        Tries.duree = new ArrayList<>();

        background = new Sprite(game , OpeningScreenAsset.background,0,0,game.getGraphics().getHeight(),game.getGraphics().getWidth());
        playbutton = new Sprite(game , OpeningScreenAsset.playButton,40*game.getGraphics().getWidth()/100 , 30*game.getGraphics().getHeight()/100,40*game.getGraphics().getHeight()/100,20*game.getGraphics().getWidth()/100);
        GameSound.soundsprite = new Sprite(game , GameSound.sound,90*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        rs = new Sprite(game , OpeningScreenAsset.Rs,80*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);


        addSprite(background);
        addSprite(playbutton);
        addSprite(GameSound.soundsprite);
        addSprite(rs);

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
    public void pause() {
        Log.i("heellllllooo", "pause: i am in pause ");
        GameSound.music.setVolume(0);
    }

    @Override
    public void handleTouchDown(int x, int y, int pointer) {
        super.handleTouchDown(x, y, pointer);

        if(GameSound.soundsprite.contain(x,y)){
           GameSound.change(game);
            return;
        }
        if(playbutton.contain(x,y)){
            game.setScreen(new LevelsScreen(game));
            return;
        }
        if(rs.contain(x,y) ){
            ((Context)game).startActivity(new Intent((Context)game,AudioSettingsActivity.class));
            return;
        }
    }


}
