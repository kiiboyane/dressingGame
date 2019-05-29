package com.dressTheKid.sprites;

import com.dressTheKid.assets.Background;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Components.Sprite;
import com.example.emobadaragaminglib.Base.Game;


import java.util.ArrayList;

public class Right_shoes extends ClothesGroup {

    public Right_shoes( Game game , Image image ,Image image2 , Image image3, Image worn1 , Image worn2 , Image worn3) {

        /// this is for room1
//        super(game, 772, 901, 631, 772, 6, 4, image, image2, image3, worn1, worn2, worn3);

        // this  is room2
        //super(game, 724, 863, 590, 759, 6, 4, image, image2, image3, worn1, worn2, worn3);



        // this  is room3
        //super(game, 759, 894, 605, 701, 6, 4, image, image2, image3, worn1, worn2, worn3);


        // this  is room4
       // super(game, 717, 844, 587, 747, 6, 4, image, image2, image3, worn1, worn2, worn3);


        // this  is room5
        //super(game, 740, 883, 601, 705, 6, 4, image, image2, image3, worn1, worn2, worn3);


        // this  is room6
        //super(game, 747, 878, 616, 762, 6, 4, image, image2, image3, worn1, worn2, worn3);

        super(game, Background.right_shoes.getX1(), Background.right_shoes.getX2(),Background.right_shoes.getX3() ,Background.right_shoes.getY(),Background.right_shoes.getH(), Background.right_shoes.getW(), image, image2, image3, worn1, worn2, worn3);

    }
}
