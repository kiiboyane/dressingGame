package com.dressTheKid.sprites;

import com.dressTheKid.GroupCord;
import com.dressTheKid.assets.Background;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Components.Sprite;

import java.util.ArrayList;

public class Pants extends ClothesGroup {

    GroupCord  pants ;
    public Pants (Game game , Image image , Image image2 , Image image3 , Image worn1 , Image worn2 , Image worn3){
        super(game ,Background.pants.getX1() ,Background.pants.getX2()   ,Background.pants.getX3()  ,Background.pants.getY() , Background.pants.getH() ,  Background.pants.getW() ,image , image2 , image3 , worn1 ,  worn2 ,  worn3);


    }

}
