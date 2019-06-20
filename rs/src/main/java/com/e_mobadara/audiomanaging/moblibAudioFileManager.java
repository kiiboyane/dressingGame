package com.e_mobadara.audiomanaging;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;

import com.e_mobadara.Data.AudioFile;
import com.e_mobadara.Data.AudioFilesTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class moblibAudioFileManager {

    private static AudioFile audio;

    public static MediaPlayer getRandomAudioFile(Context context, String type, String langue){
         Cursor cursor = context.getContentResolver()
                    .query(AudioFilesTable.AudioFilesEntry.CONTENT_URI, null,
                            AudioFilesTable.AudioFilesEntry.COLUMN_TYPE + " = ? AND "+
                                    AudioFilesTable.AudioFilesEntry.COLUMN_LANGUE + " = ?",
                            new String[]{type, langue},
                            null);
            List<AudioFile> afl = new ArrayList<AudioFile>();
            AudioFile af = new AudioFile();
            if (cursor != null && cursor.moveToFirst()) {
                // Loop in the cursor to get each row.
                do {
                    // Get columns value.
                    af.setafPath(cursor.getString(cursor.getColumnIndex("path")));
                    afl.add(af);
                } while (cursor.moveToNext());
                audio = afl.get(new Random().nextInt(afl.size()));
                return MediaPlayer.create(context,Uri.fromFile(new File(audio.getafPath())));
            }
        return null;
    }
}
