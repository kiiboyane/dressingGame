package com.dressTheKid;



import android.media.MediaPlayer;

import com.dressTheKid.assets.Clothes;
import com.dressTheKid.assets.GameSound;
import com.dressTheKid.assets.Language;
import com.dressTheKid.assets.OpeningScreenAsset;
import com.dressTheKid.gameViews.OpeningScreen;
import com.e_mobadara.audiomanaging.moblibAudioFileManager;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Music;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Implementation.AndroidGame;
import com.example.emobadaragaminglib.Implementation.AndroidSound;
import com.example.ensias_auth_library.FoxyAuth;
import com.dressTheKid.R;

import java.util.ArrayList;

public class OpeningActivity extends AndroidGame {
    static Game g ;

    int j =0 ;

    @Override
    public Screen getInitScreen() {

        OpeningScreenAsset.background =  getGraphics().newImage(R.mipmap.background,Graphics.ImageFormat.ARGB8888,getResources());
        OpeningScreenAsset.playButton =  getGraphics().newImage(R.mipmap.playbutton,Graphics.ImageFormat.ARGB8888,getResources());
        OpeningScreenAsset.exit =  getGraphics().newImage(R.mipmap.exit,Graphics.ImageFormat.ARGB8888,getResources());
        OpeningScreenAsset.home =  getGraphics().newImage(R.mipmap.home,Graphics.ImageFormat.ARGB8888,getResources());
        OpeningScreenAsset.sound =  getGraphics().newImage(R.mipmap.loud,Graphics.ImageFormat.ARGB8888,getResources());
        OpeningScreenAsset.Rs =  getGraphics().newImage(R.mipmap.setting,Graphics.ImageFormat.ARGB8888,getResources());
        GameSound.music =   getAudio().createMusic(R.raw.music);

        Language.background =  getGraphics().newImage(R.mipmap.backlanguage,Graphics.ImageFormat.ARGB8888,getResources());
        Language.arabic =  getGraphics().newImage(R.mipmap.arabic,Graphics.ImageFormat.ARGB8888,getResources());
        Language.french =  getGraphics().newImage(R.mipmap.french,Graphics.ImageFormat.ARGB8888,getResources());
        Language.english =  getGraphics().newImage(R.mipmap.english,Graphics.ImageFormat.ARGB8888,getResources());
        GameSound.firston = true;
        GameSound.on = true;
     //   FoxyAuth.emerge(this,OpeningActivity.class);
        g = this ;
        Shuffle.shuffleBackgroundstart(g);
        Shuffle.shuffleGenderstart(g);
        Clothes.empty = getGraphics().newImage(R.mipmap.empty,Graphics.ImageFormat.ARGB8888,getResources()) ;

        return new OpeningScreen(g);
    }








}
