package com.dressTheKids;

import android.util.Log;

import com.dressTheKids.assets.Background;
import com.dressTheKids.assets.Clothes;
import com.dressTheKids.assets.HumanAsset;
import com.dressTheKids.assets.WornClothes;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Image;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Shuffle {
    public static boolean gender ;
    public static int level = 0  ;
    public static void getBackground(Game game , Integer number){

        switch (number) {
            case 1 :
                Background.back =  game.getGraphics().newImage(R.mipmap.room1,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
            case 2 :// kharif
                Background.back =  game.getGraphics().newImage(R.mipmap.room2,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
            case 3 :
                Background.back =  game.getGraphics().newImage(R.mipmap.room3,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
            case 4 :
                Background.back =  game.getGraphics().newImage(R.mipmap.room4,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
            case 5 :// rbi3 o ljaw zin ha
                Background.back =  game.getGraphics().newImage(R.mipmap.room5,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
            case 6 :// chtatata
                Background.back =  game.getGraphics().newImage(R.mipmap.room6,Graphics.ImageFormat.ARGB8888,game.getResources());
                break;
        }
        Background.fill(number);
    }

    public static void getGender (Game game  , Boolean gendergiven , Integer head ){
        gender = gendergiven ;
        if(gender){
            switch (head) {
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
    public static void getPants(Game game  , int last ){
        Clothes.bottom = new ArrayList<Image>();
        WornClothes.bottom = new ArrayList<Image>();

        if(gender){
                       if(last ==4 ){
                           Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                           WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants3,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                           Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants,Graphics.ImageFormat.ARGB8888,game.getResources()));
                           WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                           Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                           WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants4,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                       } else{
                           if(last == 1){
                               Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants,Graphics.ImageFormat.ARGB8888,game.getResources()));
                               WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                               Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                               WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants2,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                               Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                               WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants3,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                           }else{
                               Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants,Graphics.ImageFormat.ARGB8888,game.getResources()));
                               WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                               Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                               WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants2,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                               Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_pants4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                               WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.boy_worn_pants4,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                           }
                       }
        }else {
                        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_pants1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_worn_pants1,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_pants2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_worn_pants2,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
                        Clothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_pants3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                        WornClothes.bottom.add(game.getGraphics().newImage(R.mipmap.girl_worn_pants3,Graphics.ImageFormat.ARGB8888,game.getResources())) ;
        }
    }


 public static void getTops( Game game , int last ){
     // top clothes
     Clothes.tops = new ArrayList<Image>() ;
     WornClothes.tops = new ArrayList<Image>();
     if(gender){
                     if(last ==  4 ){
                         Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                         WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                         Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                         WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                         Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                         WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     }else{
                         if(last == 1 ){
                             Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                             WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                             Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                             WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                             Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                             WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                         }else{
                             Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                             WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                             Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                             WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                             Clothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                             WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.boy_worn_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                         }
                     }
     }else {
                 if(last == 4 ){
                     Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                 }else if (last == 5){
                     Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top5,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top5,Graphics.ImageFormat.ARGB8888,game.getResources()));
                 }else {
                     Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top1,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top2,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     Clothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                     WornClothes.tops.add(game.getGraphics().newImage(R.mipmap.girl_worn_top3,Graphics.ImageFormat.ARGB8888,game.getResources()));
                 }

     }
 }

        public static void getShoes (Game game , int last ){

            // leftshoes
            Clothes.left_shoes = new ArrayList<Image>();
            Clothes.right_shoes = new ArrayList<Image>();
            WornClothes.left_shoes = new ArrayList<Image>();
            WornClothes.right_shoes = new ArrayList<Image>();
            if (gender) {

                if(last == 4){
                    Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                }else {

                    Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                    WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                    Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                    WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe4,Graphics.ImageFormat.ARGB8888,game.getResources()));
                    Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_left_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_left_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_right_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                    WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.boy_worn_right_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                }


            } else {
                Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_left_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_left_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_right_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_right_shoe1, Graphics.ImageFormat.ARGB8888, game.getResources()));
                Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_left_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_left_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_right_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_right_shoe2, Graphics.ImageFormat.ARGB8888, game.getResources()));
                Clothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_left_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));
                WornClothes.left_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_left_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));
                Clothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_right_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));
                WornClothes.right_shoes.add(game.getGraphics().newImage(R.mipmap.girl_worn_right_shoe3, Graphics.ImageFormat.ARGB8888, game.getResources()));

            }
        }
    }
