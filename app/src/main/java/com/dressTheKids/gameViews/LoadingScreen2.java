package com.dressTheKids.gameViews;

import com.dressTheKids.R;
import com.dressTheKids.Shuffle;
import com.dressTheKids.assets.GameSound;
import com.dressTheKids.assets.LevelAsset;
import com.dressTheKids.assets.Tries;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.Sprite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoadingScreen2  extends Screen {
    public Sprite background , loading  ;
    static Game g;
    public Integer time = 0 ;
    public Boolean secondScreenReady ;

    public LoadingScreen2(Game game){
        super(game);
        //statistic
        Tries.badTry =Tries.goodTry =0 ;
        Tries.duree = new ArrayList<>();
        Tries.creat = Tries.time();


        background = new Sprite(game , LevelAsset.background,0,0,game.getGraphics().getHeight(),game.getGraphics().getWidth());
        loading = new Sprite(game , LevelAsset.loading,0,45*game.getGraphics().getHeight()/100, 10*game.getGraphics().getHeight()/100,game.getGraphics().getWidth());
        addSprite(background);
        addSprite(loading);
        secondScreenReady = false;
        if(GameSound.on){
            GameSound.music.setLooping(true);
            GameSound.music.setVolume((float)0.2);
        }else{
            GameSound.music.setVolume((float)0);
        }

        g = game;
        if(Shuffle.level== 1){
            new Thread(new Runnable() {
                public void run() {
                    Shuffle.getBackground(g , 1);
                    loading.setImage(g.getGraphics().newImage(R.mipmap.loading2,Graphics.ImageFormat.ARGB8888, g.getResources()));
                    Shuffle.getGender(g , true , 1 );
                    loading.setImage(g.getGraphics().newImage(R.mipmap.loading3,Graphics.ImageFormat.ARGB8888, g.getResources()));
                    Shuffle.getPants(g , 1);
                    loading.setImage(g.getGraphics().newImage(R.mipmap.loading4,Graphics.ImageFormat.ARGB8888, g.getResources()));
                    Shuffle.getTops(g , 1);
                    loading.setImage(g.getGraphics().newImage(R.mipmap.loading5,Graphics.ImageFormat.ARGB8888, g.getResources()));
                    Shuffle.getShoes(g ,1);
                    loading.setImage(g.getGraphics().newImage(R.mipmap.loading6,Graphics.ImageFormat.ARGB8888, g.getResources()));
                    secondScreenReady = true ;
                }
            }).start();

        }else{
            new Thread(new Runnable() {
                public void run() {
                    Shuffle.getBackground(g , 5);//rbi3
                    loading.setImage(g.getGraphics().newImage(R.mipmap.loading2,Graphics.ImageFormat.ARGB8888, g.getResources()));
                    Shuffle.getGender(g , true , 3 );
                    loading.setImage(g.getGraphics().newImage(R.mipmap.loading3,Graphics.ImageFormat.ARGB8888, g.getResources()));
                    Shuffle.getPants(g , 4);
                    loading.setImage(g.getGraphics().newImage(R.mipmap.loading4,Graphics.ImageFormat.ARGB8888, g.getResources()));
                    Shuffle.getTops(g , 4);
                    loading.setImage(g.getGraphics().newImage(R.mipmap.loading5,Graphics.ImageFormat.ARGB8888, g.getResources()));
                    Shuffle.getShoes(g ,1);
                    loading.setImage(g.getGraphics().newImage(R.mipmap.loading6,Graphics.ImageFormat.ARGB8888, g.getResources()));
                    secondScreenReady = true ;
                }
            }).start();
        }

    }

    public void goNext(Game game ){
       if(Shuffle.level==1){
           game.setScreen(new GameScreen1(game));
       }else{
           game.setScreen(new Game2Screen1(game));
       }
    }
    @Override
    public void render(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawARGB(255,255,255,255);
        if(secondScreenReady){
            loading.setImage(game.getGraphics().newImage(R.mipmap.loading9,Graphics.ImageFormat.ARGB8888, game.getResources()));
            loading.setImage(game.getGraphics().newImage(R.mipmap.loading10,Graphics.ImageFormat.ARGB8888, game.getResources()));
            goNext(game);
        }
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
            GameSound.music.setVolume(0);

    }

    @Override
    public void handleTouchDown(int x, int y, int pointer) {
        super.handleTouchDown(x, y, pointer);


    }

}
