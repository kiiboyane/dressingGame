package com.dressTheKids.assets;

import android.content.Context;

import com.dressTheKids.PopupActivity;
import com.example.ensias_auth_library.FoxyAuth;
import com.example.ensias_auth_library.models.GameStat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Tries {
    public static  int goodTry=0  ,badTry =0 ;
    public static String creat ;
    public static ArrayList<Integer> duree=new ArrayList();
    public static String longitude,latitude;


    public static String time(){
        Date d = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(d);
    }

    public static void addDelay(Date time1,Date time2){
        duree.add((int)(time2.getTime()-time1.getTime())/1000);
    }

    public static String avg(){
        int c=0;
        int j=0;
        if(duree==null||duree.size()==0) return "0";
        else {
            for (int i : duree) {
                c++;
                j += i;
            }
            return String.valueOf(j / c);
        }
    }

    public static String min(){
        if(duree==null||duree.size()==0) return "0";
        else{
            Collections.sort(duree);
            return String.valueOf(duree.get(0));
        }
    }

    public static GameStat save(Context context, String level) {
        GameStat gameStat = new GameStat();
        gameStat.setApp_id("2019_3_8_2");
        gameStat.setExercise_id("T_5_33");
        gameStat.setLevel_id(level);
        gameStat.setCreated_at(Tries.creat);
        gameStat.setUpdated_at(time());
        gameStat.setSuccessful_attempts(String.valueOf(Tries.goodTry));
        gameStat.setFailed_attempts(String.valueOf(Tries.badTry));
        gameStat.setMin_time_succeed_sec(min());
        gameStat.setAvg_time_succeed_sec(avg());
        gameStat.setLongitude(Double.toString(PopupActivity.longitude));
        gameStat.setLatitude(Double.toString(PopupActivity.latitude));
        FoxyAuth.storeGameStat(context ,gameStat);
        System.out.println(gameStat.getApp_id() + " *******************************" +  gameStat.getExercise_id() +"\n" +
                "*******************************\n" +
                "*******************************\n" +
                "*******************************\n" +
                gameStat.getLevel_id() +"\n success: "+ gameStat.getSuccessful_attempts()+"\nbad: "+ gameStat.getFailed_attempts()+" \nmin: "+min()+" \n avg: "+ avg()+
                "\n*******************************\n" +
                "\n*******************************\n" +
                "*********************");

        goodTry=0;
        badTry=0;
        duree=null;
        duree=new ArrayList();
        return gameStat;

    }
}
