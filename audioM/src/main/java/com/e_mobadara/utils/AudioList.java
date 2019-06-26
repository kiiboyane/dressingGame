package com.e_mobadara.utils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AudioList {

    @SerializedName("audiofiles")
    List<AudioFileDTO> audiofiles;

    public List<AudioFileDTO> getAudiofiles() {
        return audiofiles;
    }

    public void setAudiofiles(List<AudioFileDTO> audiofiles) {
        this.audiofiles = audiofiles;
    }


}
