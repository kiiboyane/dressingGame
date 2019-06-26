package com.dressTheKids.assets;

import android.content.Context;

import com.e_mobadara.audiomanaging.moblibAudioFileManager;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Image;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Music;
import com.example.emobadaragaminglib.Components.Sprite;
import com.example.emobadaragaminglib.Implementation.AndroidSound;
import com.dressTheKids.R;

import java.util.ArrayList;

public class GameSound {
    public static ArrayList<AndroidSound> success  ;
    public static ArrayList<MediaPlayer> failure ;
    public static AndroidSound Intro , Introlevel  , failurelevel;
    public static AndroidSound ending ;
    public static MediaPlayer good ,  encouragement  , excellent;
    public static Music music ;
    public static boolean  on, firston=true ;
    public static Image sound  ;
    public static Sprite soundsprite;


    public static void Remplir (Game game){
           Integer i = Language.chosenLanguage;
           success = new ArrayList<AndroidSound>() ;
           failure = new ArrayList<MediaPlayer>() ;
           //on = true  ;

        ending =  (AndroidSound) game.getAudio().createSound(R.raw.ending);
        if(i==2){
            GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_english));
            GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_english2));
            GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_english3));
            GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_english4));
            encouragement= moblibAudioFileManager.getRandomAudioFile((Context)game,"encouragement","AN") ;
            excellent= moblibAudioFileManager.getRandomAudioFile((Context)game,"excellent","AN") ;
            good= moblibAudioFileManager.getRandomAudioFile((Context)game,"good","AN") ;

            failure.add( (MediaPlayer) MediaPlayer.create((Context)game,R.raw.failure_english));
            failure.add( (MediaPlayer) MediaPlayer.create((Context)game,R.raw.failure_english2));

            Intro =   (AndroidSound) game.getAudio().createSound(R.raw.intro_english) ;
            Introlevel = (AndroidSound) game.getAudio().createSound(R.raw.intro2_english) ;
            failurelevel = (AndroidSound) game.getAudio().createSound(R.raw.failurelevel_english) ;
        }else{
            if(i==1){
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_french));
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_french2));
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_french3));
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_french4));
                excellent= moblibAudioFileManager.getRandomAudioFile((Context)game,"excellent","FR") ;
                encouragement= moblibAudioFileManager.getRandomAudioFile((Context)game,"encouragement","FR") ;
                good= moblibAudioFileManager.getRandomAudioFile((Context)game,"good","FR") ;

                failure.add( (MediaPlayer) MediaPlayer.create((Context)game,R.raw.failure_french));
                // failure.add( (AndroidSound) game.getAudio().createSound(R.raw.failure_french2));

                Intro =   (AndroidSound) game.getAudio().createSound(R.raw.intro_french) ;
                Introlevel =   (AndroidSound) game.getAudio().createSound(R.raw.intro2_french) ;
                failurelevel = (AndroidSound) game.getAudio().createSound(R.raw.failurelevel_french) ;

            }else{
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_arabic));
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_arabic2));
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_arabic3));
                encouragement= moblibAudioFileManager.getRandomAudioFile((Context)game,"encouragement","AR") ;
                excellent= moblibAudioFileManager.getRandomAudioFile((Context)game,"excellent","AR") ;
                good= moblibAudioFileManager.getRandomAudioFile((Context)game,"good","AR") ;

                failure.add( (MediaPlayer) MediaPlayer.create((Context)game,R.raw.failure_arabic2));
                failure.add( (MediaPlayer) MediaPlayer.create((Context)game, R.raw.failure_arabic));

                Intro =   (AndroidSound) game.getAudio().createSound(R.raw.intro_arabic) ;
                Introlevel =   (AndroidSound) game.getAudio().createSound(R.raw.intro2_arabic) ;
                failurelevel = (AndroidSound) game.getAudio().createSound(R.raw.failurelevel_arabic) ;


            }
        }
    }
    public static void playfailure(){
        failure.get(0).setVolume(10,10);
        failure.get(0).start();

    }
    public static void change (Game game){
        if(!on){
            Log.i("Opening Screen ", "OpeningScreen:  GameSound.on" + GameSound.on);
            Log.i("Opening Screen ", "OpeningScreen:  GameSound.firston" + GameSound.firston);
            GameSound.on = true;
            //GameSound.music.play();
            GameSound.music.setVolume((float)0.2);
            GameSound.music.setLooping(true);
            GameSound.sound = game.getGraphics().newImage(R.mipmap.loud,Graphics.ImageFormat.ARGB8888,game.getResources());
            GameSound.soundsprite.setImage(GameSound.sound );
        }else{
            GameSound.sound = game.getGraphics().newImage(R.mipmap.mute,Graphics.ImageFormat.ARGB8888,game.getResources());
            GameSound.soundsprite.setImage(GameSound.sound );
            GameSound.music.setVolume(0);
            GameSound.on = false;
        }
    }


}
