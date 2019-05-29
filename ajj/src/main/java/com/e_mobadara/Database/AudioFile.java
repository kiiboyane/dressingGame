package com.e_mobadara.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

    @Entity(tableName = "audiofiles")
    public class AudioFile {
        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "id")
        public int afId;
        @ColumnInfo(name = "name")
        public String afName;
        @ColumnInfo(name = "type")
        public String afType;
        @ColumnInfo(name = "langue")
        public String afLangue;
        public String afPath;

        public AudioFile(int s, String s1, String s2, String s3, String s4) {
            this.afId =s;
            this.afName = s1;
            this.afType = s2;
            this.afPath = s3;
            this.afLangue = s4;
        }

        public AudioFile() {

        }

        @NonNull
        public int getafId() {
            return afId;
        }

        public void setafId(@NonNull int afId) {
            this.afId = afId;
        }

        @Override
        public String toString() {
            return "AudioFile{" +
                    "afId='" + afId + '\'' +
                    ", afName='" + afName + '\'' +
                    ", afType='" + afType + '\'' +
                    ", afLangue='" + afLangue + '\'' +
                    ", afPath='" + afPath + '\'' +
                    '}';
        }

        public String getafName() {
            return afName;
        }

        public void setafName(String afName) {
            this.afName = afName;
        }

        public String getafType() {
            return afType;
        }

        public void setafType(String afType) {
            this.afType = afType;
        }

        public String getafLangue() {
            return afLangue;
        }

        public void setafLangue(String afLangue) {
            this.afLangue = afLangue;
        }

        public String getafPath() {
            return afPath;
        }

        public void setafPath(String afPath) {
            this.afPath = afPath;
        }



    }
