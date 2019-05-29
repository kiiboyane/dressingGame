package com.dressTheKid.gameViews;

import com.dressTheKid.assets.GameSound;
import com.dressTheKid.assets.OpeningScreenAsset;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.Sprite;
import com.dressTheKid.R;


public class VoiceActivity extends Screen {
    public Sprite background ;
    public Sprite playbutton ;
    public Sprite exit  , home , sound ,rs;
    int h , w ;
    boolean f ;

    public VoiceActivity(Game game){
        super(game);
        f =false;
        background = new Sprite(game , OpeningScreenAsset.background,0,0,game.getGraphics().getHeight(),game.getGraphics().getWidth());
        playbutton = new Sprite(game , OpeningScreenAsset.playButton,40*game.getGraphics().getWidth()/100 , 30*game.getGraphics().getHeight()/100,40*game.getGraphics().getHeight()/100,20*game.getGraphics().getWidth()/100);
        exit = new Sprite(game , OpeningScreenAsset.exit,90*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        sound = new Sprite(game , OpeningScreenAsset.sound,80*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        rs = new Sprite(game , OpeningScreenAsset.Rs,70*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        //home = new Sprite(game , OpeningScreenAsset.home,80*game.getGraphics().getWidth()/100 , 80*game.getGraphics().getHeight()/100,20*game.getGraphics().getHeight()/100,10*game.getGraphics().getWidth()/100);
        addSprite(background);
        addSprite(playbutton);
        addSprite(exit);
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

        if(exit.contain(x,y)){
            game.setScreen(null);

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
        if(playbutton.contain(x,y) ){
            game.setScreen(new LanguageScreen(game));
            return;
        }
        if(rs.contain(x,y) ){
            game.setScreen(new LanguageScreen(game));

            return;
        }
    }
}
