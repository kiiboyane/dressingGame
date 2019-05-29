package com.dressTheKid;


import com.dressTheKid.assets.Clothes;
import com.dressTheKid.assets.Hero;
import com.dressTheKid.assets.Background;
import com.dressTheKid.assets.HumanAsset;
import com.dressTheKid.assets.Obstacles;
import com.dressTheKid.assets.WornClothes;
import com.dressTheKid.gameViews.GameScreen1;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Implementation.AndroidGame;
import com.example.emobadaragaminglib.Implementation.AndroidSound;
import com.dressTheKid.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Activity1 extends AndroidGame {
    ArrayList<Image> bpants , bshoesl , bshoesr , btop ,  gpants , gshoesl , gshoesr , gtop ;
    ArrayList<Image> bpantsw , bshoeslw , bshoesrw , btopw ,  gpantsw , gshoeslw , gshoesrw , gtopw ;
    @Override
    public Screen getInitScreen() {
        //Initialize the assets you will be working with on your screens here.
        //It is better to have all of them loaded, until you have performance problems then, umm,yeah
        //We will have to figure that out :')

        //ill();

        Background.back =  getGraphics().newImage(R.mipmap.room1,Graphics.ImageFormat.ARGB8888,getResources());
       // Background.human =  getGraphics().newImage(R.mipmap.boy,Graphics.ImageFormat.ARGB8888,getResources());
        Hero.avatar = getGraphics().newImage(R.mipmap.rick,Graphics.ImageFormat.ARGB8888,getResources());
        Hero.voice = (AndroidSound) getAudio().createSound(R.raw.tinyrick);
        Obstacles.avatar = getGraphics().newImage(R.mipmap.morty,Graphics.ImageFormat.ARGB8888,getResources());
        Obstacles.voice = (AndroidSound) getAudio().createSound(R.raw.morty);
        //human parts
        HumanAsset.legs = getGraphics().newImage(R.mipmap.boy_naked_legs,Graphics.ImageFormat.ARGB8888,getResources());
        HumanAsset.right_foot = getGraphics().newImage(R.mipmap.bare_right_foot,Graphics.ImageFormat.ARGB8888,getResources());
        HumanAsset.left_foot = getGraphics().newImage(R.mipmap.bare_left_foot,Graphics.ImageFormat.ARGB8888,getResources());
        HumanAsset.chest = getGraphics().newImage(R.mipmap.boy_naked_chest,Graphics.ImageFormat.ARGB8888,getResources());
        HumanAsset.head = getGraphics().newImage(R.mipmap.boy_head2,Graphics.ImageFormat.ARGB8888,getResources());

        // top clothes
        Clothes.tops = new ArrayList<Image>() ;
        Clothes.tops.add(getGraphics().newImage(R.mipmap.boy_top1,Graphics.ImageFormat.ARGB8888,getResources()));
        Clothes.tops.add(getGraphics().newImage(R.mipmap.boy_top2,Graphics.ImageFormat.ARGB8888,getResources()));
        Clothes.tops.add(getGraphics().newImage(R.mipmap.boy_top3,Graphics.ImageFormat.ARGB8888,getResources()));

        //top worn clothes
        WornClothes.tops = new ArrayList<Image>();
        WornClothes.tops.add(getGraphics().newImage(R.mipmap.boy_worn_top1,Graphics.ImageFormat.ARGB8888,getResources()));
        WornClothes.tops.add(getGraphics().newImage(R.mipmap.boy_worn_top2,Graphics.ImageFormat.ARGB8888,getResources()));
        WornClothes.tops.add(getGraphics().newImage(R.mipmap.boy_worn_top3,Graphics.ImageFormat.ARGB8888,getResources()));

        // leftshoes
        Clothes.left_shoes = new ArrayList<Image>() ;
        Clothes.left_shoes.add(getGraphics().newImage(R.mipmap.boy_left_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        Clothes.left_shoes.add(getGraphics().newImage(R.mipmap.boy_left_shoe2,Graphics.ImageFormat.ARGB8888,getResources()));
        Clothes.left_shoes.add(getGraphics().newImage(R.mipmap.boy_left_shoe3,Graphics.ImageFormat.ARGB8888,getResources()));

        //left shoes worn
        WornClothes.left_shoes = new ArrayList<Image>();
        WornClothes.left_shoes.add(getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        WornClothes.left_shoes.add(getGraphics().newImage(R.mipmap.boy_worn_left_shoe2,Graphics.ImageFormat.ARGB8888,getResources()));
        WornClothes.left_shoes.add(getGraphics().newImage(R.mipmap.boy_worn_left_shoe3,Graphics.ImageFormat.ARGB8888,getResources()));


        // rightshoes
        Clothes.right_shoes = new ArrayList<Image>() ;
        Clothes.right_shoes.add(getGraphics().newImage(R.mipmap.boy_right_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        Clothes.right_shoes.add(getGraphics().newImage(R.mipmap.boy_right_shoe2,Graphics.ImageFormat.ARGB8888,getResources()));
        Clothes.right_shoes.add(getGraphics().newImage(R.mipmap.boy_right_shoe3,Graphics.ImageFormat.ARGB8888,getResources()));

        //right shoes worn
        WornClothes.right_shoes = new ArrayList<Image>();
        WornClothes.right_shoes.add(getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        WornClothes.right_shoes.add(getGraphics().newImage(R.mipmap.boy_worn_right_shoe2,Graphics.ImageFormat.ARGB8888,getResources()));
        WornClothes.right_shoes.add(getGraphics().newImage(R.mipmap.boy_worn_right_shoe3,Graphics.ImageFormat.ARGB8888,getResources()));


        //pants
        Clothes.bottom = new ArrayList<Image>();
        Clothes.bottom.add(getGraphics().newImage(R.mipmap.boy_pants,Graphics.ImageFormat.ARGB8888,getResources()));
        Clothes.bottom.add(getGraphics().newImage(R.mipmap.boy_pants2,Graphics.ImageFormat.ARGB8888,getResources()));
        Clothes.bottom.add(getGraphics().newImage(R.mipmap.boy_pants3,Graphics.ImageFormat.ARGB8888,getResources()));

        //worn pants
        WornClothes.bottom = new ArrayList<Image>();
        WornClothes.bottom.add(getGraphics().newImage(R.mipmap.boy_worn_pants,Graphics.ImageFormat.ARGB8888,getResources())) ;
        WornClothes.bottom.add(getGraphics().newImage(R.mipmap.boy_worn_pants2,Graphics.ImageFormat.ARGB8888,getResources())) ;
        WornClothes.bottom.add(getGraphics().newImage(R.mipmap.boy_worn_pants3,Graphics.ImageFormat.ARGB8888,getResources())) ;

        Clothes.empty = getGraphics().newImage(R.mipmap.empty,Graphics.ImageFormat.ARGB8888,getResources()) ;




        // BackGroundMusic.bg_music = getAudio().createMusic(R.raw.shaunthesheep);
        //The method is going to
        return new GameScreen1(this);
    }

    @Override
    protected void onDestroy() {
        //Let's make life easy on your device and get rid of the memo we dont use
        //because Android system does not do that always.
        super.onDestroy();
        Hero.avatar.dispose();
        Hero.voice.dispose();
    }

    protected void fill (){

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

/*
        bpants.add(getGraphics().newImage(R.mipmap.boy_pants,Graphics.ImageFormat.ARGB8888,getResources()));
        bpants.add(getGraphics().newImage(R.mipmap.boy_pants2,Graphics.ImageFormat.ARGB8888,getResources()));
        bpants.add(getGraphics().newImage(R.mipmap.boy_pants3,Graphics.ImageFormat.ARGB8888,getResources()));
        bpantsw.add(getGraphics().newImage(R.mipmap.boy_worn_pants,Graphics.ImageFormat.ARGB8888,getResources()));
        bpantsw.add(getGraphics().newImage(R.mipmap.boy_worn_pants2,Graphics.ImageFormat.ARGB8888,getResources()));
        bpantsw.add(getGraphics().newImage(R.mipmap.boy_worn_pants3,Graphics.ImageFormat.ARGB8888,getResources()));

        gpants.add(getGraphics().newImage(R.mipmap.boy_pants,Graphics.ImageFormat.ARGB8888,getResources()));
        gpants.add(getGraphics().newImage(R.mipmap.boy_pants2,Graphics.ImageFormat.ARGB8888,getResources()));
        gpants.add(getGraphics().newImage(R.mipmap.boy_pants3,Graphics.ImageFormat.ARGB8888,getResources()));
        gpantsw.add(getGraphics().newImage(R.mipmap.boy_worn_pants,Graphics.ImageFormat.ARGB8888,getResources()));
        gpantsw.add(getGraphics().newImage(R.mipmap.boy_worn_pants2,Graphics.ImageFormat.ARGB8888,getResources()));
        gpantsw.add(getGraphics().newImage(R.mipmap.boy_worn_pants3,Graphics.ImageFormat.ARGB8888,getResources()));


        bshoesl.add(getGraphics().newImage(R.mipmap.boy_left_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        bshoesl.add(getGraphics().newImage(R.mipmap.boy_left_shoe2,Graphics.ImageFormat.ARGB8888,getResources()));
        bshoesl.add(getGraphics().newImage(R.mipmap.boy_left_shoe3,Graphics.ImageFormat.ARGB8888,getResources()));
        bshoeslw.add(getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        bshoeslw.add(getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        bshoeslw.add(getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));

        gshoesl.add(getGraphics().newImage(R.mipmap.boy_left_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        gshoesl.add(getGraphics().newImage(R.mipmap.boy_left_shoe2,Graphics.ImageFormat.ARGB8888,getResources()));
        gshoesl.add(getGraphics().newImage(R.mipmap.boy_left_shoe3,Graphics.ImageFormat.ARGB8888,getResources()));
        gshoeslw.add(getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        gshoeslw.add(getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        gshoeslw.add(getGraphics().newImage(R.mipmap.boy_worn_left_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));



        bshoesr.add(getGraphics().newImage(R.mipmap.boy_right_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        bshoesr.add(getGraphics().newImage(R.mipmap.boy_right_shoe2,Graphics.ImageFormat.ARGB8888,getResources()));
        bshoesr.add(getGraphics().newImage(R.mipmap.boy_right_shoe3,Graphics.ImageFormat.ARGB8888,getResources()));
        bshoesrw.add(getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        bshoesrw.add(getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        bshoesrw.add(getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));

        gshoesr.add(getGraphics().newImage(R.mipmap.boy_right_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        gshoesr.add(getGraphics().newImage(R.mipmap.boy_right_shoe2,Graphics.ImageFormat.ARGB8888,getResources()));
        gshoesr.add(getGraphics().newImage(R.mipmap.boy_right_shoe3,Graphics.ImageFormat.ARGB8888,getResources()));
        gshoesrw.add(getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        gshoesrw.add(getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));
        gshoesrw.add(getGraphics().newImage(R.mipmap.boy_worn_right_shoe1,Graphics.ImageFormat.ARGB8888,getResources()));


        btop.add(getGraphics().newImage(R.mipmap.boy_top1,Graphics.ImageFormat.ARGB8888,getResources()));
        btop.add(getGraphics().newImage(R.mipmap.boy_top2,Graphics.ImageFormat.ARGB8888,getResources()));
        btop.add(getGraphics().newImage(R.mipmap.boy_top3,Graphics.ImageFormat.ARGB8888,getResources()));
        btop.add(getGraphics().newImage(R.mipmap.boy_top44,Graphics.ImageFormat.ARGB8888,getResources()));
        btop.add(getGraphics().newImage(R.mipmap.boy_top4,Graphics.ImageFormat.ARGB8888,getResources()));
        btopw.add(getGraphics().newImage(R.mipmap.boy_worn_top1,Graphics.ImageFormat.ARGB8888,getResources()));
        btopw.add(getGraphics().newImage(R.mipmap.boy_worn_top2,Graphics.ImageFormat.ARGB8888,getResources()));
        btopw.add(getGraphics().newImage(R.mipmap.boy_worn_top3,Graphics.ImageFormat.ARGB8888,getResources()));
        btopw.add(getGraphics().newImage(R.mipmap.boy_worn_top4,Graphics.ImageFormat.ARGB8888,getResources()));
        btopw.add(getGraphics().newImage(R.mipmap.boy_worn_top4,Graphics.ImageFormat.ARGB8888,getResources()));



        gtop.add(getGraphics().newImage(R.mipmap.girl_top1,Graphics.ImageFormat.ARGB8888,getResources()));
        gtop.add(getGraphics().newImage(R.mipmap.girl_top3,Graphics.ImageFormat.ARGB8888,getResources()));
        gtop.add(getGraphics().newImage(R.mipmap.girl_top4,Graphics.ImageFormat.ARGB8888,getResources()));
        gtop.add(getGraphics().newImage(R.mipmap.girl_top5,Graphics.ImageFormat.ARGB8888,getResources()));
        gtop.add(getGraphics().newImage(R.mipmap.girl_top5,Graphics.ImageFormat.ARGB8888,getResources()));
        gtopw.add(getGraphics().newImage(R.mipmap.girl_worn_top1,Graphics.ImageFormat.ARGB8888,getResources()));
        gtopw.add(getGraphics().newImage(R.mipmap.girl_worn_top3,Graphics.ImageFormat.ARGB8888,getResources()));
        gtopw.add(getGraphics().newImage(R.mipmap.girl_worn_top4,Graphics.ImageFormat.ARGB8888,getResources()));
        gtopw.add(getGraphics().newImage(R.mipmap.girl_worn_top5,Graphics.ImageFormat.ARGB8888,getResources()));
        gtopw.add(getGraphics().newImage(R.mipmap.girl_worn_top5,Graphics.ImageFormat.ARGB8888,getResources()));*/

    }

     protected Set<Integer> chose(int  num , int sz ){
        Set<Integer> rep  = new HashSet<Integer>();
         Random rand = new Random();
         while(rep.size()<3){
              rep.add(rand.nextInt(sz));
         }
        return rep;

     }

}
