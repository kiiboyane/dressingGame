package com.e_mobadara.Data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class AudioFilesTable implements BaseColumns {

    public static final String CONTENT_AUTHORITY = "com.e_mobadara.Data.AudioFilesProvider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class AudioFilesEntry implements BaseColumns{
        // table name
        public static final String TABLE_AUDIOFILES = "audiofiles";
        // columns
        public static final String _ID = "id";
        public static final String COLUMN_PATH = "path";
        public static final String COLUMN_LANGUE = "langue";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_NAME = "name";

        // create content uri
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_AUDIOFILES).build();
        // create cursor of base type directory for multiple entries
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_AUDIOFILES;
        // create cursor of base type item for single entry
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE +"/" + CONTENT_AUTHORITY + "/" + TABLE_AUDIOFILES;

        // for building URIs on insertion
        public static Uri buildAudioFilesUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


}
