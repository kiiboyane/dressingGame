package com.dressTheKid.sprites;

import com.dressTheKid.assets.Background;
import com.dressTheKid.assets.Clothes;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Components.Sprite;

import java.util.ArrayList;

public class Top extends ClothesGroup {
    public Top(Game game , Image first , Image second , Image third, Image worn1 , Image worn2 , Image worn3 ){
        super(game , Background.tops.getX1(), Background.tops.getX2() , Background.tops.getX3(),Background.tops.getY(),Background.tops.getH() ,Background.tops.getW(), first , second , third,  worn1 ,  worn2 ,  worn3 );
    }

}
