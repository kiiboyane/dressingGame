package com.dressTheKid.sprites;

import com.dressTheKid.assets.Background;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Components.Sprite;

import java.util.ArrayList;

public class Left_shoes  extends ClothesGroup{

    public Left_shoes (Game game  , Image image  , Image image2 , Image image3 , Image worn1 , Image worn2 , Image worn3 ) {
        super(game, Background.left_shoes.getX1(), Background.left_shoes.getX2(), Background.left_shoes.getX3(), Background.left_shoes.getY(), Background.left_shoes.getH(), Background.left_shoes.getW(), image, image2, image3, worn1, worn2, worn3);


    }
}
