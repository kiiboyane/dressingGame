package com.dressTheKid.assets;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;

import com.e_mobadara.Database.AudioFile;
import com.e_mobadara.audiomanaging.moblibAudioFileManager;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Music;
import com.example.emobadaragaminglib.Implementation.AndroidSound;
import com.dressTheKid.R;

import java.util.ArrayList;

public class GameSound {
    public static ArrayList<AndroidSound> success  ;
    public static ArrayList<AndroidSound> failure ;
    public static AndroidSound Intro ;
    public static AndroidSound ending ;
    public static MediaPlayer good ,  encouragement  , excellent;
    public static Music music ;
    public static boolean  on, firston=true ;


    public static void Remplir (Game game){
           Integer i = Language.chosenLanguage;
           success = new ArrayList<AndroidSound>() ;
           failure = new ArrayList<AndroidSound>() ;
           on = true  ;

        ending =  (AndroidSound) game.getAudio().createSound(R.raw.ending);
        if(i==2){
            GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_english));
            GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_english2));
            GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_english3));
            GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_english4));
            encouragement= moblibAudioFileManager.getRandomAudioFile((Context)game,"encouragement","AN") ;
            excellent= moblibAudioFileManager.getRandomAudioFile((Context)game,"excellent","AN") ;
            good= moblibAudioFileManager.getRandomAudioFile((Context)game,"good","AN") ;

            failure.add( (AndroidSound) game.getAudio().createSound(R.raw.failure_english));
            failure.add( (AndroidSound) game.getAudio().createSound(R.raw.failure_english2));

            Intro =   (AndroidSound) game.getAudio().createSound(R.raw.intro_english) ;
        }else{
            if(i==1){
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_french));
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_french2));
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_french3));
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_french4));
                excellent= moblibAudioFileManager.getRandomAudioFile((Context)game,"excellent","FR") ;
                encouragement= moblibAudioFileManager.getRandomAudioFile((Context)game,"encouragement","FR") ;
                good= moblibAudioFileManager.getRandomAudioFile((Context)game,"good","FR") ;

                failure.add( (AndroidSound) game.getAudio().createSound(R.raw.failure_french));
                // failure.add( (AndroidSound) game.getAudio().createSound(R.raw.failure_french2));

                Intro =   (AndroidSound) game.getAudio().createSound(R.raw.intro_french) ;
            }else{
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_arabic));
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_arabic2));
                GameSound.success.add ( (AndroidSound) game.getAudio().createSound(R.raw.success_arabic3));
                encouragement= moblibAudioFileManager.getRandomAudioFile((Context)game,"encouragement","AR") ;
                excellent= moblibAudioFileManager.getRandomAudioFile((Context)game,"excellent","AR") ;
                good= moblibAudioFileManager.getRandomAudioFile((Context)game,"good","AR") ;

                failure.add( (AndroidSound) game.getAudio().createSound(R.raw.failure_arabic));
                failure.add( (AndroidSound) game.getAudio().createSound(R.raw.failure_arabic2));

                Intro =   (AndroidSound) game.getAudio().createSound(R.raw.intro_arabic) ;

            }
        }
    }


}
