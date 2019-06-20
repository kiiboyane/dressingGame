package com.dressTheKids.assets;

import com.dressTheKids.GroupCord;
import com.dressTheKids.Shuffle;
import com.example.emobadaragaminglib.Base.Image;

public class Background {
    public static Image back;
    public static GroupCord pants ;
    public static GroupCord tops ;
    public static GroupCord left_shoes ;
    public static GroupCord right_shoes ;

    public static void fill (int num){
        switch (num){
            case 1 :
                pants = new GroupCord(540 ,680  ,810 ,550 , 20 ,  13) ;
                tops = new GroupCord( 531  , 653,796,310 ,24 ,14);
                if(Shuffle.gender){
                    right_shoes = new GroupCord(772, 901, 631, 772, 6, 4);
                    left_shoes = new GroupCord(681, 815, 541, 778, 6, 8) ;
                }else{
                    right_shoes = new GroupCord(760, 889, 619, 772, 6, 4);
                    left_shoes = new GroupCord(681, 815, 541, 772, 6, 8) ;
                }
                break;
            case 2 :
                pants = new GroupCord(501 ,650  ,781 ,490 , 18 ,  13);
                tops = new GroupCord(477  , 619,768,262,23,16);
                if(Shuffle.gender){
                    right_shoes = new GroupCord( 724, 863, 590, 759, 6, 4);
                    left_shoes = new GroupCord(639, 781, 501, 751, 6, 8) ;
                }else{
                    right_shoes = new GroupCord( 712, 851, 578, 751, 6, 5);
                    left_shoes = new GroupCord(639, 781, 501, 751, 6, 7) ;
                }

                break;
            case 3 :
                pants = new GroupCord( 533 ,681  ,831 ,508 , 14 ,  10);
                tops = new GroupCord(504  , 647,799,300,24 ,16);
                if(Shuffle.gender){
                    right_shoes = new GroupCord( 759, 894, 605, 701, 6, 5);
                    left_shoes = new GroupCord(673, 814, 521, 705, 6, 8) ;
                }else{
                    right_shoes = new GroupCord( 745, 881, 591, 701, 6, 8);
                    left_shoes = new GroupCord(673, 814, 521, 701, 6, 8) ;
                }
                break;
            case 4 :
                pants = new GroupCord(503 ,630  ,761 ,465 , 18 ,  13) ;
                tops = new GroupCord( 473  , 606 , 745,205,26 ,16);
                if(Shuffle.gender){
                    right_shoes = new GroupCord( 717, 844, 587, 747, 6, 4);
                    left_shoes = new GroupCord(632, 756, 500, 754, 6, 8) ;
                }else{
                    right_shoes = new GroupCord( 705, 835, 575, 747, 5, 5);
                    left_shoes = new GroupCord(632, 756, 500, 747, 6, 8) ;
                }
                break ;
            case 5 :
                pants = new GroupCord( 551 ,677  ,823 ,515 , 14 ,  9);
                tops = new GroupCord(512  , 646 , 789,300,23 ,16);
                if(Shuffle.gender){
                    right_shoes = new GroupCord( 750, 893, 611, 705, 6, 4);
                    left_shoes = new GroupCord(658, 802, 526, 701, 6, 8) ;
                }else{
                    right_shoes = new GroupCord( 728, 871, 590, 701, 6, 5);
                    left_shoes = new GroupCord(648, 792, 516, 701, 6, 8) ;
                }
                break;
            case 6 :
                pants = new GroupCord( 554 ,689  ,829 ,550 , 19 ,  9 );
                tops = new GroupCord(522  , 654 , 792,330,23 ,16);
                if(Shuffle.gender){
                    right_shoes = new GroupCord( 747, 878, 616, 762, 6, 4);
                    left_shoes = new GroupCord(662, 792, 536, 772, 6, 8) ;
                }else{
                    right_shoes = new GroupCord( 735, 865, 607, 762, 6, 8);
                    left_shoes = new GroupCord(662, 792, 536, 762, 6, 8) ;
                }
                break;

        }

    }




}
