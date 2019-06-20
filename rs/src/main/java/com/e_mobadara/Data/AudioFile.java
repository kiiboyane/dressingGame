package com.e_mobadara.Data;

public class AudioFile {


    public String getAfId() {
        return afId;
    }

    public void setAfId(String afId) {
        this.afId = afId;
    }

    public int getAfServerId() {
        return afServerId;
    }

    public void setAfServerId(int afServerId) {
        this.afServerId = afServerId;
    }

    int afServerId;
    String afId;
    String afPath;
    String afName;
    String afType;
    String afLangue;

    public AudioFile( String afName, String afType, String afLangue) {
        this.afName = afName;
        this.afType = afType;
        this.afLangue = afLangue;
    }

    public AudioFile(String afId,String afName, String afType, String afLangue) {
        this.afId = afId;
        this.afName = afName;
        this.afType = afType;
        this.afLangue = afLangue;
    }

    @Override
    public String toString() {
        return "AudioFile{" +
                "afPath='" + afPath + '\'' +
                ", afName='" + afName + '\'' +
                ", afType='" + afType + '\'' +
                ", afLangue='" + afLangue + '\'' +
                '}';
    }

    String auri;

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

    public AudioFile(){}

    public void setafPath(String afPath) {
        this.afPath = afPath;
    }
    public String getafName() {
        return afName;
    }
    public void setafName(String afName) {
        this.afName = afName;
    }
}