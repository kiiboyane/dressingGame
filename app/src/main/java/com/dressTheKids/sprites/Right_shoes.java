package com.dressTheKids.sprites;

import com.dressTheKids.assets.Background;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Base.Game;

public class Right_shoes extends ClothesGroup {

    public Right_shoes( Game game , Image image ,Image image2 , Image image3, Image worn1 , Image worn2 , Image worn3) {


        super(game, Background.right_shoes.getX1(), Background.right_shoes.getX2(),Background.right_shoes.getX3() ,Background.right_shoes.getY(),Background.right_shoes.getH(), Background.right_shoes.getW(), image, image2, image3, worn1, worn2, worn3);

    }

    @Override
    public Boolean wearthis() {
        return null;
    }
}
