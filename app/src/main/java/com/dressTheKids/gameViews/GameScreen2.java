package com.dressTheKids.gameViews;

import android.content.Context;
import android.util.Log;

import com.dressTheKids.PopupActivity;
import com.dressTheKids.Shuffle;
import com.dressTheKids.assets.Background;
import com.dressTheKids.assets.Clothes;
import com.dressTheKids.assets.GameSound;
import com.dressTheKids.assets.HumanAsset;
import com.dressTheKids.assets.OpeningScreenAsset;
import com.dressTheKids.assets.Tries;
import com.dressTheKids.assets.WornClothes;
import com.dressTheKids.sprites.Chest;
import com.dressTheKids.sprites.Head;
import com.dressTheKids.sprites.Left_foot;
import com.dressTheKids.sprites.Left_shoes;
import com.dressTheKids.sprites.Legs;
import com.dressTheKids.sprites.Pants;
import com.dressTheKids.sprites.Right_foot;
import com.dressTheKids.sprites.Right_shoes;
import com.dressTheKids.sprites.Top;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.Sprite;
import com.example.ensias_auth_library.FoxyAuth;
import com.example.ensias_auth_library.models.GameStat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class GameScreen2 extends Screen  {
    private final String TAG = "GameScreen2: ";
    private Sprite backgroundSprite;
    private Sprite  home  ;

    //body parts
    private Chest chest;
    private Head head ;
    private Left_foot left_foot;
    private Right_foot right_foot ;
    private Legs legs ;
    // clothes
    private Top tops ;
    private Sprite top1 , top2 , top3 ;
    private Left_shoes left_shoes;
    private Sprite left_shoe1 , left_shoe2 ,left_shoe3 ;
    private Right_shoes right_shoes;
    private Sprite right_shoe1 , right_shoe2 ,right_shoe3 ;
    private Pants pants ;
    private Sprite pants1 , pants2 , pants3 ;
    private Image empty ;
    private ArrayList<Boolean> flag;
    private Boolean secondScreenReady ;
    private Date firstTime;

    private Boolean rflag , lflag , tflag , pflag;

    private int waitabit = -1 ; //used to get some time before re-rendering

    static Game g ;
    private long endTime , startTime ;
    public GameScreen2(Game game) {
        super(game);
        flag = new ArrayList<>() ;
        flag.add(false) ;
        flag.add(false) ;
        flag.add(false) ;
        secondScreenReady = rflag = lflag = tflag = pflag = false ;
        empty = Clothes.empty ;

        home = new Sprite(game , OpeningScreenAsset.home,90*game.getGraphics().getWidth()/100 , 90*game.getGraphics().getHeight()/100,11*game.getGraphics().getHeight()/100,8*game.getGraphics().getWidth()/100);
        GameSound.soundsprite = new Sprite(game , GameSound.sound,80*game.getGraphics().getWidth()/100 , 90*game.getGraphics().getHeight()/100,10*game.getGraphics().getHeight()/100,8*game.getGraphics().getWidth()/100);
        backgroundSprite = new Sprite(game , Background.back,0,0,game.getGraphics().getHeight(),game.getGraphics().getWidth());

        GameSound.Intro.play(5);
        // the human parts 3:)
        chest = new Chest (game , HumanAsset.chest,140*game.getGraphics().getWidth()/1000,53*game.getGraphics().getHeight()/100,60*game.getGraphics().getHeight()/230,11*game.getGraphics().getWidth()/30);
        legs = new Legs(game , HumanAsset.legs ,25*game.getGraphics().getWidth()/100,67*game.getGraphics().getHeight()/100,60*game.getGraphics().getHeight()/300,9*game.getGraphics().getWidth()/50);
        right_foot = new Right_foot(game , HumanAsset.right_foot,369*game.getGraphics().getWidth()/1000,859*game.getGraphics().getHeight()/1000,60*game.getGraphics().getHeight()/1000,game.getGraphics().getWidth()/20);
        left_foot = new Left_foot(game, HumanAsset.left_foot,211*game.getGraphics().getWidth()/1000,864*game.getGraphics().getHeight()/1000,60*game.getGraphics().getHeight()/1000,15*game.getGraphics().getWidth()/150);
        if(Shuffle.gender)head = new Head(game , HumanAsset.head,205*game.getGraphics().getWidth()/1000,300*game.getGraphics().getHeight()/1000,25*game.getGraphics().getHeight()/100,25*game.getGraphics().getWidth()/100);
        else head = new Head(game , HumanAsset.head,205*game.getGraphics().getWidth()/1000,320*game.getGraphics().getHeight()/1000,25*game.getGraphics().getHeight()/100,25*game.getGraphics().getWidth()/100);


        // tops
        tops = new Top(game,Clothes.tops.get(0) , Clothes.tops.get(1),Clothes.tops.get(2),WornClothes.tops.get(0),WornClothes.tops.get(1),WornClothes.tops.get(2));
        top1 = tops.group.get(0);
        top2 = tops.group.get(1);
        top3 = tops.group.get(2);
        //pants
        pants = new Pants(game , Clothes.bottom.get(0) , Clothes.bottom.get(1), Clothes.bottom.get(2) ,WornClothes.bottom.get(0),WornClothes.bottom.get(1),WornClothes.bottom.get(2));
        pants1 = pants.group.get(0);
        pants2 = pants.group.get(1);
        pants3 = pants.group.get(2);

        // leftshoes
        left_shoes = new Left_shoes(game  , Clothes.left_shoes.get(0),Clothes.left_shoes.get(1), Clothes.left_shoes.get(2) ,WornClothes.left_shoes.get(0),WornClothes.left_shoes.get(1),WornClothes.left_shoes.get(2));
        left_shoe1 = left_shoes.group.get(0) ;
        left_shoe2 = left_shoes.group.get(1) ;
        left_shoe3 = left_shoes.group.get(2) ;
        //right shoes
        right_shoes =  new Right_shoes (game , Clothes.right_shoes.get(0) ,  Clothes.right_shoes.get(1) , Clothes.right_shoes.get(2) ,WornClothes.right_shoes.get(0) ,WornClothes.right_shoes.get(1),WornClothes.right_shoes.get(2) ) ;
        right_shoe1 = right_shoes.group.get(0) ;
        right_shoe2 = right_shoes.group.get(1) ;
        right_shoe3 = right_shoes.group.get(2) ;

        addSprite(backgroundSprite);
        addSprite(home);
        addSprite(GameSound.soundsprite);

        addSprite(legs);
        addSprite(chest);
        addSprite(head);
        addSprite(right_foot);
        addSprite(left_foot);
        //adding pants
        addSprite(pants1);
        addSprite(pants2);
        addSprite(pants3);
        //adding tops
        addSprite(top1);
        addSprite(top2);
        addSprite(top3);
        // adding leftshoes
        addSprite(left_shoe1);
        addSprite(left_shoe2);
        addSprite(left_shoe3);
        // adding rightshoes
        addSprite(right_shoe1);
        addSprite(right_shoe2);
        addSprite(right_shoe3);

        firstTime =new Date();

        g = game;
        new Thread(new Runnable() {
            public void run() {
                Shuffle.getBackground(g , 4);
                Shuffle.getGender(g , true  , 2 );
                Shuffle.getPants(g ,4);
                Shuffle.getTops(g , 4 );
                Shuffle.getShoes(g,4);
                secondScreenReady= true  ;
            }
        }).start();
    }


    @Override
    public void render(float deltaTime) {
        Graphics g = game.getGraphics();


        if(rflag && lflag && pflag && tflag ) {
            waitabit++ ;
        }
        if(waitabit ==5 ){
            GameSound.ending.play(1);
        }
        if(rflag && lflag && pflag && tflag  && waitabit > 30 && secondScreenReady )
        {


            Date lastTime=  new Date();
            Tries.addDelay(firstTime , lastTime );
            Log.e(TAG, "render: "+ ((double)(startTime - endTime) )/1000);
            game.setScreen(new GameScreen3(game));
        }


        //Obstacles.voice.play(1);

    }

    @Override
    public void handleTouchUp(int x, int y, int pointer) {
        super.handleTouchUp(x, y, pointer);



        if(home.contain(x,y)){
            Tries.save((Context) game  ,  "1");
            game.setScreen(new OpeningScreen(game));
            return;
        }
        if(GameSound.soundsprite.contain(x,y)){
            GameSound.change(game);
            return;
        }
        for(int i=pants.group.size()-1;i>=0;i--) {
            if(pants.group.get(i).contain(x,y)){
                pflag = pants.wear(game , legs , empty , pflag , GameSound.success.get(1) ,i) ;

                pants.returntoplaces(game , i );
                Tries.badTry ++ ;
                return;
            }
        }
        for(int i=left_shoes.group.size()-1;i>=0;i--) {
            if(left_shoes.group.get(i).contain(x,y)){
                lflag = left_shoes.weardependly(game , left_foot , empty , flag , lflag ,i);
                left_shoes.returntoplaces(game ,i);
                Tries.badTry ++ ;
                return;
            }
        }
        for(int i=right_shoes.group.size()-1;i>=0;i--) {
            if(right_shoes.group.get(i).contain(x,y)){
                rflag = right_shoes.weardependly(game , right_foot , empty , flag , rflag ,i);
                right_shoes.returntoplaces(game,i);
                Tries.badTry ++ ;
                return;
            }
        }
        for(int i=tops.group.size()-1;i>=0;i--) {
            if(tops.group.get(i).contain(x,y)){
                tflag = tops.wear(game , chest , empty , tflag,GameSound.success.get(0) ,i);
                tops.returntoplaces(game ,i);
                Tries.badTry ++ ;
                return;
            }
        }

    }

    @Override
    public void handleDragging(int x, int y, int pointer) {
        super.handleDragging(x, y, pointer);
    }

    @Override
    public void pause() {
        //BackGroundMusic.bg_music.pause();
    }

    @Override
    public void resume() {
        //BackGroundMusic.bg_music.play();
    }

    //The handle dragging is activated anytime you drag on your screen.
    /*@Override
    public void handleDragging(int x, int y, int pointer) {
        super.handleDragging(x, y, pointer);
        Hero.voice.play(1);
    }*/

    @Override
    public void dispose() {
        super.dispose();
        System.gc();
    }


    @Override
    public void backButton() {
        Tries.save((Context) game  ,  "1");

        pause();
    }

}
