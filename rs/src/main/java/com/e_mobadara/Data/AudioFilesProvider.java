package com.e_mobadara.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class AudioFilesProvider extends ContentProvider {

    private static final String LOG_TAG = AudioFilesProvider.class.getSimpleName();
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private AudioFileDBHelperClass mOpenHelper;

    // Codes for the UriMatcher //////
    private static final int AUDIOFILE = 100;
    private static final int AUDIOFILE_WITH_ID = 200;
    ////////

    private static UriMatcher buildUriMatcher(){
        // Build a UriMatcher by adding a specific code to return based on a match
        // It's common to use NO_MATCH as the code for this case.
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = AudioFilesTable.CONTENT_AUTHORITY;

        // add a code for each type of URI you want
        matcher.addURI(authority, AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES, AUDIOFILE);
        matcher.addURI(authority, AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES + "/#", AUDIOFILE_WITH_ID);

        return matcher;
    }
    @Override
    public boolean onCreate(){
        mOpenHelper = new AudioFileDBHelperClass(getContext());

        return true;
    }

    @Override
    public String getType(Uri uri){
        final int match = sUriMatcher.match(uri);

        switch (match){
            case AUDIOFILE:{
                return AudioFilesTable.AudioFilesEntry.COLUMN_TYPE;
            }
            case AUDIOFILE_WITH_ID:{
                return AudioFilesTable.AudioFilesEntry.COLUMN_TYPE;
            }
            default:{
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        Cursor retCursor;
        Log.d("AudiofileProvider","helllo errors");
        switch(sUriMatcher.match(uri)){
            // All audiofiles selected
            case AUDIOFILE:{
                Log.d("AudiofileProvider","hello errors");
                retCursor = mOpenHelper.getReadableDatabase().query(
                        AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                return retCursor;
            }
            // Individual audio file based on Id selected
            case AUDIOFILE_WITH_ID:{
                retCursor = mOpenHelper.getReadableDatabase().query(
                        AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES,
                        projection,
                        AudioFilesTable.AudioFilesEntry._ID + " = ?",
                        new String[] {String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                return retCursor;
            }
            default:{
                // By default, we assume a bad URI
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;
        switch (sUriMatcher.match(uri)) {
            case AUDIOFILE: {
                Log.d("AudiofileProvider","inserting audiofile");
                long _id = db.insert(AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES, null, values);
                // insert unless it is already contained in the database
                if (_id > 0) {
                    returnUri = AudioFilesTable.AudioFilesEntry.buildAudioFilesUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into: " + uri);
                }
                break;
            }

            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);

            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int numDeleted;
        Log.d("AudiofileProvider","deleting audiofile");
        switch(match){
            case AUDIOFILE:
                numDeleted = db.delete(
                        AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES, selection, selectionArgs);
                // reset _ID
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
                        AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES + "'");
                break;
            case AUDIOFILE_WITH_ID:
                numDeleted = db.delete(AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES,
                        AudioFilesTable.AudioFilesEntry._ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                // reset _ID
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
                        AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES + "'");

                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return numDeleted;
    }

    @Override
    public int update(Uri uri,ContentValues contentValues,String s,String[] strings) {
        return 0;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values){
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        switch(match){
            case AUDIOFILE:
                // allows for multiple transactions
                db.beginTransaction();

                // keep track of successful inserts
                int numInserted = 0;
                try{
                    for(ContentValues value : values){
                        if (value == null){
                            throw new IllegalArgumentException("Cannot have null content values");
                        }
                        long _id = -1;
                        try{
                            _id = db.insertOrThrow(AudioFilesTable.AudioFilesEntry.TABLE_AUDIOFILES,
                                    null, value);
                        }catch(SQLiteConstraintException e) {
                            Log.w(LOG_TAG, "Attempting to insert " +
                                    value.getAsString(
                                            AudioFilesTable.AudioFilesEntry.COLUMN_NAME)
                                    + " but value is already in database.");
                        }
                        if (_id != -1){
                            numInserted++;
                        }
                    }
                    if(numInserted > 0){
                        // If no errors, declare a successful transaction.
                        // database will not populate if this is not called
                        db.setTransactionSuccessful();
                    }
                } finally {
                    // all transactions occur at once
                    db.endTransaction();
                }
                if (numInserted > 0){
                    // if there was successful insertion, notify the content resolver that there
                    // was a change
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return numInserted;
            default:
                return super.bulkInsert(uri, values);
        }
    }

}
