package com.dressTheKids;



import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.dressTheKids.assets.Clothes;
import com.dressTheKids.assets.GameSound;
import com.dressTheKids.assets.Language;
import com.dressTheKids.assets.LevelAsset;
import com.dressTheKids.assets.OpeningScreenAsset;
import com.dressTheKids.assets.Tries;
import com.dressTheKids.gameViews.LoadingScreen;
import com.dressTheKids.gameViews.OpeningScreen;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Implementation.AndroidGame;
import com.example.emobadaragaminglib.Implementation.AndroidSound;
import com.example.ensias_auth_library.FoxyAuth;
import com.example.ensias_auth_library.models.GameStat;


public class OpeningActivity extends AndroidGame {
    public static GameStat gamestat  ;
    public static AndroidGame thisone ;
    int j =0 ;

    @Override
    public void onCreate(Bundle savedInstanceState,  PersistableBundle persistentState) {
        thisone = this ;
        super.onCreate(savedInstanceState, persistentState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));

        //  this.setContentView(R.layout.activity_loading);
    }

    @Override
    public Screen getInitScreen() {


        //authentification
        FoxyAuth.emerge(this,PopupActivity.class);
      //  FoxyAuth.storeGameStat((Context) thisone ,gamestat);
       // Tries.save(this, "2");

        return new LoadingScreen(this);
    }
   /* public static void saveStat(String level ){
         FoxyAuth.storeGameStat((Context) thisone ,gamestat);
    }*/







}
