package com.dressTheKid;

import android.util.Log;

import com.dressTheKid.assets.Background;
import com.dressTheKid.assets.Clothes;
import com.dressTheKid.assets.HumanAsset;
import com.dressTheKid.assets.WornClothes;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Image;
import com.dressTheKid.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Shuffle {
    //public static ArrayList<Image> bpants , bshoesl , bshoesr , btop ,  gpants , gshoesl , gshoesr , gtop ;
   // public static ArrayList<Image> bpantsw , bshoeslw , bshoesrw , btopw ,  gpantsw , gshoeslw , gshoesrw , gtopw ;
    public static boolean gender ;
   /* public  static  void fill (Game game){
        bpants =  new ArrayList<Image>();
        gpants =  new ArrayList<Image>();
        bpantsw =  new ArrayList<Image>();
        gpantsw =  new ArrayList<Image>();
        btop =  new ArrayList<Image>();
        btopw =  new ArrayList<Image>();
        gtop =  new ArrayList<Image>();
        gtopw =  new ArrayList<Image>();
        gshoesl =  new ArrayList<Image>();
        gshoeslw =  new ArrayList<Image>();
        gshoesr =  new ArrayList<Image>();
        gshoesrw =  new ArrayList<Image>();
        bshoesl =  new ArrayList<Image>();
        bshoeslw =  new ArrayList<Image>();
        bshoesr =  new ArrayList<Image>();
        bshoesrw =  new ArrayList<Image>();
        gender = false;

        bpants.add(game.getGraphics().newImage(R.mipmap.boy_pants,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bpants.add(game.getGraphics().newImage(R.mipmap.boy_pants2,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bpants.add(game.getGraphics().newImage(R.mipmap.boy_pants3,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bpantsw.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bpantsw.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants2,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bpantsw.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants3,Graphics.ImageFormat.ARGB8888,game.getResources()));

        gpants.add(game.getGraphics().newImage(R.mipmap.boy_pants,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gpants.add(game.getGraphics().newImage(R.mipmap.boy_pants2,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gpants.add(game.getGraphics().newImage(R.mipmap.boy_pants3,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gpantsw.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gpantsw.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants2,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gpantsw.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants3,Graphics.ImageFormat.ARGB8888,game.getResources()));


        bshoesl.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bshoesl.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bshoesl.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bshoeslw.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bshoeslw.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bshoeslw.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));

        gshoesl.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gshoesl.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gshoesl.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gshoeslw.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gshoeslw.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gshoeslw.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));



        bshoesr.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bshoesr.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bshoesr.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bshoesrw.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bshoesrw.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        bshoesrw.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));

        gshoesr.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gshoesr.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gshoesr.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gshoesrw.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gshoesrw.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gshoesrw.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));


        btop.add(game.getGraphics().newImage(R.mipmap.boy_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        btop.add(game.getGraphics().newImage(R.mipmap.boy_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
        btop.add(game.getGraphics().newImage(R.mipmap.boy_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
        btop.add(game.getGraphics().newImage(R.mipmap.boy_top44,Graphics.ImageFormat.ARGB8888,game.getResources()));
        btop.add(game.getGraphics().newImage(R.mipmap.boy_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
        btopw.add(game.getGraphics().newImage(R.mipmap.boy_worn_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        btopw.add(game.getGraphics().newImage(R.mipmap.boy_worn_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
        btopw.add(game.getGraphics().newImage(R.mipmap.boy_worn_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
        btopw.add(game.getGraphics().newImage(R.mipmap.boy_worn_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
        btopw.add(game.getGraphics().newImage(R.mipmap.boy_worn_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));



        gtop.add(game.getGraphics().newImage(R.mipmap.girl_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gtop.add(game.getGraphics().newImage(R.mipmap.girl_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gtop.add(game.getGraphics().newImage(R.mipmap.girl_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gtop.add(game.getGraphics().newImage(R.mipmap.girl_top5,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gtop.add(game.getGraphics().newImage(R.mipmap.girl_top5,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gtopw.add(game.getGraphics().newImage(R.mipmap.girl_worn_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gtopw.add(game.getGraphics().newImage(R.mipmap.girl_worn_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gtopw.add(game.getGraphics().newImage(R.mipmap.girl_worn_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gtopw.add(game.getGraphics().newImage(R.mipmap.girl_worn_top5,Graphics.ImageFormat.ARGB8888,game.getResources()));
        gtopw.add(game.getGraphics().newImage(R.mipmap.girl_worn_top5,Graphics.ImageFormat.ARGB8888,game.getResources()));

    }*/
    public static Set<Integer> chose(int  num , int sz ){
        Set<Integer> rep  = new HashSet<Integer>();
        Random rand = new Random();
        while(rep.size()<num){
            rep.add(rand.nextInt(sz)+1);
        }
        return rep;

    }

    public static void shuffleBackground(Game game){
        Integer number = chose(1 , 6).iterator().next();
        Log.i("Number", "ShuffleBackground: "+number);
        switch (number) {
            case 1 :
                Background.back =  game.getGraphics().newImage(R.mipmap.room1,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
            case 2 :
                Background.back =  game.getGraphics().newImage(R.mipmap.room2,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
            case 3 :
                Background.back =  game.getGraphics().newImage(R.mipmap.room3,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
            case 4 :
                Background.back =  game.getGraphics().newImage(R.mipmap.room4,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
            case 5 :
                Background.back =  game.getGraphics().newImage(R.mipmap.room5,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
            case 6 :
                Background.back =  game.getGraphics().newImage(R.mipmap.room6,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
        }
        Background.fill(number);
    }
    public static void shuffleBackgroundstart(Game game){
        Background.back =  game.getGraphics().newImage(R.mipmap.room1,Graphics.ImageFormat.ARGB8888,game.getResources());
        Background.fill(1);
    }

    public static void shuffleGender(Game game ){
        Integer number = chose(1 , 4).iterator().next();
        if(number >1 ) gender = true ;
        else gender = false;
        if(gender){
            Integer n = chose(1 ,3).iterator().next();
            switch (n) {
                case 1 :
                    HumanAsset.head =  game.getGraphics().newImage(R.mipmap.boy_head1,Graphics.ImageFormat.ARGB8888, game.getResources());
                    break;
                case 2 :
                    HumanAsset.head =  game.getGraphics().newImage(R.mipmap.boy_head2,Graphics.ImageFormat.ARGB8888, game.getResources());
                    break;
                case 3 :
                    HumanAsset.head =  game.getGraphics().newImage(R.mipmap.boy_head3,Graphics.ImageFormat.ARGB8888, game.getResources());
                    break;
            }
        }else {
            HumanAsset.head =  game.getGraphics().newImage(R.mipmap.girl_head,Graphics.ImageFormat.ARGB8888, game.getResources());
        }
        HumanAsset.legs = game.getGraphics().newImage(R.mipmap.boy_naked_legs,Graphics.ImageFormat.ARGB8888, game.getResources());
        HumanAsset.right_foot = game.getGraphics().newImage(R.mipmap.bare_right_foot,Graphics.ImageFormat.ARGB8888, game.getResources());
        HumanAsset.left_foot =  game.getGraphics().newImage(R.mipmap.bare_left_foot,Graphics.ImageFormat.ARGB8888, game.getResources());
        HumanAsset.chest =  game.getGraphics().newImage(R.mipmap.boy_naked_chest,Graphics.ImageFormat.ARGB8888, game.getResources());
    }
    public static void shuffleGenderstart(Game game ){
       gender = true ;
        HumanAsset.head =  game.getGraphics().newImage(R.mipmap.boy_head1,Graphics.ImageFormat.ARGB8888, game.getResources());
        HumanAsset.legs = game.getGraphics().newImage(R.mipmap.boy_naked_legs,Graphics.ImageFormat.ARGB8888, game.getResources());
        HumanAsset.right_foot = game.getGraphics().newImage(R.mipmap.bare_right_foot,Graphics.ImageFormat.ARGB8888, game.getResources());
        HumanAsset.left_foot =  game.getGraphics().newImage(R.mipmap.bare_left_foot,Graphics.ImageFormat.ARGB8888, game.getResources());
        HumanAsset.chest =  game.getGraphics().newImage(R.mipmap.boy_naked_chest,Graphics.ImageFormat.ARGB8888, game.getResources());
    }

    public static void shufflePantsstart(Game game){
        Clothes.bottom = new ArrayList<Image>();
        WornClothes.bottom = new ArrayList<Image>();

        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants,Graphics.ImageFormat.ARGB8888,game.getResources()));
        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants2,Graphics.ImageFormat.ARGB8888,game.getResources()));
        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants2,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants3,Graphics.ImageFormat.ARGB8888,game.getResources()));
        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants3,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
    }

    public static void shufflePants(Game game){
        Clothes.bottom = new ArrayList<Image>();
        WornClothes.bottom = new ArrayList<Image>();

        if(gender){
            Set<Integer> chosen = chose(3 , 4);
            for(Integer a : chosen){
                switch(a){
                    case 1 :
                        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                        break ;
                    case 2 :
                        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants2,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                        break ;
                    case 3 :
                        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants3,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                        break ;
                    case 4 :
                        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants4,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                        break ;
                }
            }
        }else {
            Set<Integer> chosen = chose(3 , 3);
            for(Integer a : chosen ){
                switch(a){
                    case 1 :
                        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_pants1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_worn_pants1,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                        break;
                    case 2 :
                        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_pants2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_worn_pants2,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                        break;
                    case 3 :
                        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_pants3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_worn_pants3,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                        break;
                }
            }
        }
    }

    public static void shuffleTops( Game game){
        // top clothes
        Clothes.tops = new ArrayList<Image>() ;
        WornClothes.tops = new ArrayList<Image>();
        if(gender){
            Set<Integer> chosen = chose(3 , 4 );
            for (Integer a : chosen) {
                switch (a){
                    case 1 :
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                    case 2 :
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                    case 3 :
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                    case 4 :
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                }
            }
        }else {
            Set<Integer> chosen = chose(3 , 5);
            for (Integer a : chosen) {
                switch (a){
                    case 1 :
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                    case 2 :
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                    case 3 :
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                    case 4 :
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                    case 5 :
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top5,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top5,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                }
            }
        }
    }
    public static void shuffleTopsstart( Game game){
        // top clothes
        Clothes.tops = new ArrayList<Image>() ;
        WornClothes.tops = new ArrayList<Image>();
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
    }
    public static  void shuffleShoes(Game game){

        // leftshoes
        Clothes.left_shoes = new ArrayList<Image>() ;
        Clothes.right_shoes = new ArrayList<Image>() ;
        WornClothes.left_shoes = new ArrayList<Image>();
        WornClothes.right_shoes = new ArrayList<Image>();
        if(gender){
            Set<Integer> chosen = chose(3 , 4);
            for( Integer a : chosen){
                switch(a){
                    case 1 :
                        Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break ;
                    case 2 :
                        Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                    case 3 :
                        Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                    case 4 :
                        Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                }
            }
        }else {
            Set<Integer> chosen = chose(3 , 3);
            for( Integer a : chosen){
                switch(a){
                    case 1 :
                        Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break ;
                    case 2 :
                        Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_left_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_left_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_right_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_right_shoe2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;
                    case 3 :
                        Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_left_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_left_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_right_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_right_shoe3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        break;

                }
            }
        }
    }
    public static  void shuffleShoesstart(Game game) {

        // leftshoes
        Clothes.left_shoes = new ArrayList<Image>();
        Clothes.right_shoes = new ArrayList<Image>();
        WornClothes.left_shoes = new ArrayList<Image>();
        WornClothes.right_shoes = new ArrayList<Image>();
        Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
        WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
        Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
        WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
        Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
        WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
        Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
        WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
        Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));
        WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));
        Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));
        WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));
    }
}
