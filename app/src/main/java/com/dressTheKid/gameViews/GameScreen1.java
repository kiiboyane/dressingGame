package com.dressTheKid.gameViews;


import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.dressTheKid.PopupActivity;
import com.dressTheKid.Shuffle;
import com.dressTheKid.assets.Background;
import com.dressTheKid.assets.Clothes;
import com.dressTheKid.assets.GameSound;
import com.dressTheKid.assets.HumanAsset;
import com.dressTheKid.assets.OpeningScreenAsset;
import com.dressTheKid.assets.Tries;
import com.dressTheKid.assets.WornClothes;
import com.dressTheKid.sprites.Chest;
import com.dressTheKid.sprites.Head;
import com.dressTheKid.sprites.Left_foot;
import com.dressTheKid.sprites.Left_shoes;
import com.dressTheKid.sprites.Legs;
import com.dressTheKid.sprites.Pants;
import com.dressTheKid.sprites.Right_foot;
import com.dressTheKid.sprites.Right_shoes;
import com.dressTheKid.sprites.Top;
import com.e_mobadara.audiomanaging.moblibAudioFileManager;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.Sprite;
import com.example.emobadaragaminglib.Implementation.AndroidSound;
import com.dressTheKid.R;
import com.example.ensias_auth_library.models.GameStat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import com.example.ensias_auth_library.FoxyAuth;

public class GameScreen1 extends Screen {
    private final String TAG = "GameScreen1: ";
    private Sprite  backgroundSprite;
    private Sprite exit  , home , sound ;
    private Chest chest;
    private Head head ;
    private Left_foot left_foot;
    private Right_foot right_foot ;
    private Legs legs ;
    private Top tops ;
    private Sprite top1 , top2 , top3 ;
    private Left_shoes left_shoes;
    private Sprite left_shoe1 , left_shoe2 ,left_shoe3 ;
    private Right_shoes right_shoes;
    private Sprite right_shoe1 , right_shoe2 ,right_shoe3 ;
    private Pants  pants ;
    private Sprite pants1 , pants2 , pants3 ;
    private Image empty ;
    private ArrayList<Boolean> flag;
    private Boolean rflag , lflag , tflag , pflag , ff , introbool  ;
    private int waitabit = -1 ; //used to get some time before re-rendering
    private int h =0,w= 0 ;
    static Game g ;
    private Calendar firstTime , lastTime  ;
    private long endTime , startTime ;
    public GameScreen1(Game game) {
        //This is gonna handle other stuff for you under the hood.We will see more of that next time.
        super(game);
        //GameSound.music.play();
        //GameSound.music.setLooping(true);
        flag = new ArrayList<>() ;
        flag.add(false) ;
        flag.add(false) ;
        flag.add(false) ;
        SimpleDateFormat endingGame = new SimpleDateFormat("yyyy.MM.dd ' ' HH:mm:ss");
        firstTime = endingGame.getCalendar();
        Date endDate = Calendar.getInstance().getTime();
        startTime = endDate.getTime();
        //firstTime = new Date();
        rflag = lflag = tflag = pflag =   ff = false  ;
        Shuffle.shuffleShoesstart(game);
        //BackGroundMusic.bg_music.play();
        //BackGroundMusic.bg_music.setLooping(true);
        //Now that your Sprite is Ready, let's initialize it and control where we are going to put it
        empty = Clothes.empty ;
        //exit = new Sprite(game , OpeningScreenAsset.exit,90*game.getGraphics().getWidth()/100 , 90*game.getGraphics().getHeight()/100,10*game.getGraphics().getHeight()/100,8*game.getGraphics().getWidth()/100);
        home = new Sprite(game , OpeningScreenAsset.home,90*game.getGraphics().getWidth()/100 , 90*game.getGraphics().getHeight()/100,10*game.getGraphics().getHeight()/100,8*game.getGraphics().getWidth()/100);
        sound = new Sprite(game , OpeningScreenAsset.sound,80*game.getGraphics().getWidth()/100 , 90*game.getGraphics().getHeight()/100,10*game.getGraphics().getHeight()/100,8*game.getGraphics().getWidth()/100);

        backgroundSprite = new Sprite(game , Background.back,0,0,game.getGraphics().getHeight(),game.getGraphics().getWidth());
       // human = new Sprite(game , Background.human,16*game.getGraphics().getWidth()/100,33*game.getGraphics().getHeight()/100,60*game.getGraphics().getHeight()/100,game.getGraphics().getWidth()/3);
       // rick = new Rick(game,Hero.avatar,game.getScreenHeight()/2,game.getScreenWidth()/2,100,100);
       // morty = new Morty(game,Obstacles.avatar,mX,mY,100,100);

        // the human parts 3:)
        chest = new Chest (game , HumanAsset.chest,140*game.getGraphics().getWidth()/1000,53*game.getGraphics().getHeight()/100,60*game.getGraphics().getHeight()/230,11*game.getGraphics().getWidth()/30);
        legs = new Legs(game , HumanAsset.legs ,25*game.getGraphics().getWidth()/100,67*game.getGraphics().getHeight()/100,60*game.getGraphics().getHeight()/300,9*game.getGraphics().getWidth()/50);
        right_foot = new Right_foot(game , HumanAsset.right_foot,369*game.getGraphics().getWidth()/1000,859*game.getGraphics().getHeight()/1000,60*game.getGraphics().getHeight()/1000,game.getGraphics().getWidth()/20);
        left_foot = new Left_foot(game, HumanAsset.left_foot,211*game.getGraphics().getWidth()/1000,864*game.getGraphics().getHeight()/1000,60*game.getGraphics().getHeight()/1000,15*game.getGraphics().getWidth()/150);
        if(Shuffle.gender)head = new Head(game , HumanAsset.head,205*game.getGraphics().getWidth()/1000,300*game.getGraphics().getHeight()/1000,25*game.getGraphics().getHeight()/100,25*game.getGraphics().getWidth()/100);
        else head = new Head(game , HumanAsset.head,205*game.getGraphics().getWidth()/1000,320*game.getGraphics().getHeight()/1000,25*game.getGraphics().getHeight()/100,25*game.getGraphics().getWidth()/100);

        introbool = true;
        GameSound.Intro.play(5);

        // Tops in the clothes
        tops = new Top(game,Clothes.tops.get(0) , Clothes.tops.get(1),Clothes.tops.get(2),WornClothes.tops.get(0),WornClothes.tops.get(1),WornClothes.tops.get(2));
        top1 = tops.group.get(0);
        top2 = tops.group.get(1);
        top3 = tops.group.get(2);

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

        //pants
        pants = new Pants(game , Clothes.bottom.get(0) , Clothes.bottom.get(1), Clothes.bottom.get(2) ,WornClothes.bottom.get(0),WornClothes.bottom.get(1),WornClothes.bottom.get(2));
        pants1 = pants.group.get(0);
        pants2 = pants.group.get(1);
        pants3 = pants.group.get(2);


        left_shoe1.setStatic(false);
        left_shoe2.setStatic(false);
        left_shoe3.setStatic(false);
        right_shoe1.setStatic(false);
        right_shoe2.setStatic(false);
        right_shoe3.setStatic(false);
        pants1.setStatic(false);
        pants2.setStatic(false);
        pants3.setStatic(false);
        top1.setStatic(false);
        top2.setStatic(false);
        top3.setStatic(false);
        // for finding places , delete after
        h = game.getGraphics().getHeight() ;
        w = game.getGraphics().getWidth() ;

        addSprite(backgroundSprite);

        addSprite(legs);
        //adding pants

        //addSprite(exit);
        addSprite(home);
        addSprite(sound);

        addSprite(chest);
        addSprite(head);

        addSprite(pants1);
        addSprite(pants2);
        addSprite(pants3);
        //adding tops

        addSprite(top1);
        addSprite(top2);
        addSprite(top3);


        addSprite(right_foot);
        addSprite(left_foot);
        // adding leftshoes
        addSprite(left_shoe1);
        addSprite(left_shoe2);
        addSprite(left_shoe3);
        // adding rightshoes
        addSprite(right_shoe1);
        addSprite(right_shoe2);
        addSprite(right_shoe3);
        //GameSound.Intro.play(3);

        // tops
        g = game ;
        new Thread(new Runnable() {
            public void run() {
                Shuffle.shuffleBackground(g);
                Shuffle.shuffleGender(g);
                Shuffle.shufflePants(g);
                Shuffle.shuffleTops(g);
                Shuffle.shuffleShoes(g);
                ff= true  ;
            }
        }).start();

        Log.d(TAG, "Constructor Called");

    }


    @Override
    public void render(float deltaTime) {
        //With each time you interact with rick, we want to re Render it. Cz one face is just not enough.
        //We get the graphics which is like a wizard that will do what ever he knows. press ctr+space to see
        //other stuff that it can do.
        Graphics g = game.getGraphics();
        //Redrawing Rick multiple times
        /*Uncomment this line and see what happens */
        //g.drawImage(Background.back,0,0,100,0,game.getScreenWidth(),game.getScreenHeight());
        //g.drawARGB(255,0,0,0);

            //////////////////////

           /* Log.i(TAG, "right_shoe1 : " + 1000*right_shoe1.getX()/w  + "  : " +1000*right_shoe1.getY()/h  );
            Log.i(TAG, "right_shoe2: " + 1000*right_shoe2.getX()/w  + "  : " +1000*right_shoe2.getY()/h  );
            Log.i(TAG, "right_shoe3: " + 1000*right_shoe3.getX()/w  + "  : " +1000*right_shoe3.getY()/h  );

            Log.i(TAG, "left_shoe1 : " + 1000*left_shoe1.getX()/w  + "  : " +1000*left_shoe1.getY()/h  );
            Log.i(TAG, "left_shoe3: " + 1000*left_shoe2.getX()/w  + "  : " +1000*left_shoe2.getY()/h  );
            Log.i(TAG, "left_shoe3: " + 1000*left_shoe3.getX()/w  + "  : " +1000*left_shoe3.getY()/h  );


            Log.i(TAG, "top1 : " + 1000*top1.getX()/w  + "  : " +1000*top1.getY()/h  );
            Log.i(TAG, "top2: " + 1000*top2.getX()/w  + "  : " +1000*top2.getY()/h  );
            Log.i(TAG, "top3: " + 1000*top3.getX()/w  + "  : " +1000*top3.getY()/h  );


            Log.i(TAG, "pants1 : " + 1000*pants1.getX()/w  + "  : " +1000*pants1.getY()/h  );
            Log.i(TAG, "pants2: " + 1000*pants2.getX()/w  + "  : " +1000*pants2.getY()/h  );
            Log.i(TAG, "pants3: " + 1000*pants3.getX()/w  + "  : " +1000*pants3.getY()/h  );*/

        //wearTop();
        tflag = tops.wear(game , chest , empty , tflag,GameSound.success.get(0));
        pflag = pants.wear(game , legs , empty , pflag , GameSound.success.get(1)) ;
        rflag = right_shoes.weardependly(game , right_foot , empty , flag , rflag);
        lflag = left_shoes.weardependly(game , left_foot , empty , flag , lflag);
        if(rflag && lflag && pflag && tflag ) {
            waitabit++ ;
        }
        if(rflag && lflag && pflag && tflag  && waitabit > 2 && ff )
        {

            GameSound.ending.play(1);

            SimpleDateFormat endingGame = new SimpleDateFormat("yyyy.MM.dd ' ' HH:mm:ss");
            lastTime= endingGame.getCalendar();
            Date endDate = Calendar.getInstance().getTime();
            endTime = endDate.getTime();
            Tries.duree.add(((double)(startTime - endTime) )/1000) ;
           /* Shuffle.shuffleBackground(game);
            Shuffle.shuffleGender(game);
            Shuffle.shufflePants(game);
            Shuffle.shuffleTops(game);
            Shuffle.shuffleShoes(game);*/


            game.setScreen(new GameScreen1(game));
        }


            //Obstacles.voice.play(1);

    }

    @Override
    public void handleTouchUp(int x, int y, int pointer) {
        super.handleTouchUp(x, y, pointer);
        Log.i(TAG, "handleTouchUp: heeey ");
       /* if(exit.contain(x,y)){
            System.exit(0);
      *//*      return;
        }

        if(introbool==false){
            introbool = true;
            GameSound.Intro.play(5);

        }*/
        if(home.contain(x,y)){
            GameStat gameStat = new GameStat();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd ' ' HH:mm:ss");
            Calendar updated  = sdf.getCalendar();;
            gameStat.setApp_id("2019_3_8_2");
            gameStat.setExercise_id("T_5_33");

            gameStat.setUpdated_at(sdf.format(Tries.creat.getTime()));
            gameStat.setCreated_at(sdf.format(updated.getTime()));
            gameStat.setSuccessful_attempts(String.valueOf(Tries.goodTry));
            gameStat.setFailed_attempts(String.valueOf(Tries.badTry));
            Collections.sort(Tries.duree);
            gameStat.setMin_time_succeed_sec(String.valueOf(Tries.duree.get(0)));
            gameStat.setAvg_time_succeed_sec(String.valueOf(Tries.duree.get(Tries.duree.size()-1)));
            gameStat.setLongitude( String.valueOf(PopupActivity.longitude));
            gameStat.setLatitude( String.valueOf(PopupActivity.latitude));
            FoxyAuth.storeGameStat((Context) g ,gameStat);
            game.setScreen(new OpeningScreen(game));
            return;
        }if(sound.contain(x,y)){
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
        for(int i=pants.group.size()-1;i>=0;i--) {
            if(pants.group.get(i).contain(x,y)){
                //pants.group.get(i).setDragged(false);
                pants.returntoplaces(game);
                return;
            }
        }
        for(int i=left_shoes.group.size()-1;i>=0;i--) {
            if(left_shoes.group.get(i).contain(x,y)){
                //pants.group.get(i).setDragged(false);
                left_shoes.returntoplaces(game);
                return;
            }
        }
        for(int i=right_shoes.group.size()-1;i>=0;i--) {
            if(right_shoes.group.get(i).contain(x,y)){
                //pants.group.get(i).setDragged(false);
                right_shoes.returntoplaces(game);
                return;
            }
        }
        for(int i=tops.group.size()-1;i>=0;i--) {
            if(tops.group.get(i).contain(x,y)){
                //pants.group.get(i).setDragged(false);
                tops.returntoplaces(game);
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

        GameStat gameStat = new GameStat();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd ' ' HH:mm:ss");
        Calendar updated  = sdf.getCalendar();;
        gameStat.setApp_id("2019_3_8_2");
        gameStat.setExercise_id("T_5_33");

        gameStat.setUpdated_at(sdf.format(Tries.creat.getTime()));
        gameStat.setCreated_at(sdf.format(updated.getTime()));
        gameStat.setSuccessful_attempts(String.valueOf(Tries.goodTry));
        gameStat.setFailed_attempts(String.valueOf(Tries.badTry));
        Collections.sort(Tries.duree);
        gameStat.setMin_time_succeed_sec(String.valueOf(Tries.duree.get(0)));
        gameStat.setAvg_time_succeed_sec(String.valueOf(Tries.duree.get(Tries.duree.size()-1)));
        gameStat.setLongitude( String.valueOf(PopupActivity.longitude));
        gameStat.setLatitude( String.valueOf(PopupActivity.latitude));
        FoxyAuth.storeGameStat((Context)g,gameStat);
        pause();
    }





}
