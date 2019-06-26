package com.e_mobadara.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface AudioFileDAO {

    @Insert(onConflict=OnConflictStrategy.IGNORE)
    void addAudioFile(AudioFile af);
    @Query("select * from audiofiles where id = :id")
    List<AudioFile> getAudioFiles(int id);
    @Query("select * from audiofiles")
    List<AudioFile> getAudioFiles();
    @Query("select * from audiofiles where type = :afType and langue = :aflangue ")
    List<AudioFile> getAudioFilesType(String afType, String aflangue);
    @Delete
    void deleteAudioFile(AudioFile af);
    @Update
    void updateAudioFile(AudioFile af);
}
