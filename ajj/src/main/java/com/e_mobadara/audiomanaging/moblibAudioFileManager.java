package com.e_mobadara.audiomanaging;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.e_mobadara.Database.AudioFile;
import com.e_mobadara.Database.MyDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class moblibAudioFileManager {

    private static AudioFile audio;
    private static MyDatabase dbInstance;
    private  static final String TAG="moblibAudioFileManager";


    public static MediaPlayer getRandomAudioFile(Context context, String type, String langue){
        dbInstance = Room.databaseBuilder(context,
                MyDatabase.class, "AudioFiles")
                .allowMainThreadQueries()
                .build();
        /* To query all records */
        Log.d(TAG, " fetching data from database :");
        List<AudioFile> afs = dbInstance.AudioFileDao().getAudioFilesType(type, langue);
        if(afs.size()>0){
            audio = afs.get(new Random().nextInt(afs.size()));
            return MediaPlayer.create(context,Uri.fromFile(new File(audio.getafPath())));
        }
        return null;
    }
}
