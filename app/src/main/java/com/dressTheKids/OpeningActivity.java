package com.dressTheKids;



import android.os.Bundle;
import android.os.PersistableBundle;

import com.dressTheKids.assets.Clothes;
import com.dressTheKids.assets.GameSound;
import com.dressTheKids.assets.Language;
import com.dressTheKids.assets.LevelAsset;
import com.dressTheKids.assets.OpeningScreenAsset;
import com.dressTheKids.gameViews.LoadingScreen;
import com.dressTheKids.gameViews.OpeningScreen;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Implementation.AndroidGame;
import com.example.ensias_auth_library.FoxyAuth;


public class OpeningActivity extends AndroidGame {

    int j =0 ;

    @Override
    public void onCreate(Bundle savedInstanceState,  PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
      //  this.setContentView(R.layout.activity_loading);
    }

    @Override
    public Screen getInitScreen() {


        //authentification
        FoxyAuth.emerge(this,PopupActivity.class);
        return new LoadingScreen(this);
    }







}
