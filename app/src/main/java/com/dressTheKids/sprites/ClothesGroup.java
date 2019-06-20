package com.dressTheKids.sprites;

import android.util.Log;

import com.dressTheKids.assets.GameSound;
import com.dressTheKids.assets.Tries;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Components.Sprite;
import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Implementation.AndroidSound;


import java.util.ArrayList;

public abstract class ClothesGroup {
    public ArrayList<Sprite> group;
    public ArrayList<Image> worn ;
    public int x1 , x2 ,x3 , y ;

    public ClothesGroup(Game game , int x1 ,  int x2 , int x3 ,int y , int h , int w ,Image  image1 , Image image2 , Image image3 , Image worn1 , Image worn2 , Image worn3){
        group = new ArrayList<Sprite>();
        worn = new ArrayList<Image>();
        this.x1=x1;
        this.x2=x2;
        this.x3=x3;
        this.y=y;
        group.add(new Sprite(game ,image1,x1*game.getGraphics().getWidth()/1000,y*game.getGraphics().getHeight()/1000,h*game.getGraphics().getHeight()/100,w*game.getGraphics().getWidth()/100));
        group.add(new Sprite(game , image2 ,x2*game.getGraphics().getWidth()/1000,y*game.getGraphics().getHeight()/1000,h*game.getGraphics().getHeight()/100,w*game.getGraphics().getWidth()/100));
        group.add(new Sprite(game , image3 ,x3*game.getGraphics().getWidth()/1000,y*game.getGraphics().getHeight()/1000,h*game.getGraphics().getHeight()/100,w*game.getGraphics().getWidth()/100));

        group.get(0).setStatic(false);
        group.get(1).setStatic(false);
        group.get(2).setStatic(false);

        worn.add(worn1);
        worn.add(worn2);
        worn.add(worn3);

    }
    public void returntoplaces(Game game){
            group.get(0).setX(x1*game.getGraphics().getWidth()/1000 ) ;
            group.get(0).setY(y*game.getGraphics().getHeight()/1000 ) ;
            group.get(1).setX(x2*game.getGraphics().getWidth()/1000 ) ;
            group.get(1).setY(y*game.getGraphics().getHeight()/1000 ) ;
            group.get(2).setX(x3*game.getGraphics().getWidth()/1000 ) ;
            group.get(2).setY(y*game.getGraphics().getHeight()/1000 ) ;

    }
    public void returntoplaces(Game game , int i ){
       // GameSound.playfailure();
        if(i==0){
            group.get(0).setX(x1*game.getGraphics().getWidth()/1000 ) ;
            group.get(0).setY(y*game.getGraphics().getHeight()/1000 ) ;
            return ;
        }if(i==1){
            group.get(1).setX(x2*game.getGraphics().getWidth()/1000 ) ;
            group.get(1).setY(y*game.getGraphics().getHeight()/1000 ) ;
            return ;
        }if(i==2){
            group.get(2).setX(x3*game.getGraphics().getWidth()/1000 ) ;
            group.get(2).setY(y*game.getGraphics().getHeight()/1000 ) ;
            return ;
        }

    }
    public void setStatic()
    {
        group.get(0).setStatic(true);
        group.get(1).setStatic(true);
        group.get(2).setStatic(true);
        group.get(0).setDragged(false);
        group.get(1).setDragged(false);
        group.get(2).setDragged(false);
    }
    public abstract  Boolean wearthis();

    public Boolean wear( Game game , Sprite bodyPart ,  Image  empty , Boolean wflag   ,  AndroidSound sound  , Integer  i   ){
        /*int who = whoIsdragged() ;
        if(who==-1) return wflag ;*/
        int who = i;
        if(wflag) return wflag;
        Sprite sprite = group.get(who);
        if(sprite.contain(bodyPart.getX() , bodyPart.getY()) || bodyPart.contain(sprite.getX() , sprite.getY())){
            sprite.setImage(empty);
            bodyPart.setImage(worn.get(who));
            returntoplaces(game);
            setStatic();
            try{
                if(Tries.badTry==0) {
                    GameSound.excellent.start();
                }
                else {GameSound.good.start();}
            }catch(Exception e){
                sound.play(5);
            }
            Tries.goodTry ++;
            Log.i("heeeree",  " goodtry: " +Tries.goodTry);
            wflag = true;
        }else{
            try{
                GameSound.encouragement.start();
            }catch(Exception e){
                GameSound.failure.get(0).start();
            }
            Tries.badTry ++;
            Log.i("heeeree", " badtry: " +Tries.badTry);
        }
        return wflag ;
    }
    public Boolean wearlevel2( Game game , Sprite bodyPart ,  Image  empty , Boolean wflag   ,  AndroidSound sound  , Integer  i , Integer wanted  ){
        /*int who = whoIsdragged() ;
        if(who==-1) return wflag ;*/
        int who = i;
        if(wflag) return wflag;
        if(wanted != i ) {
            Tries.badTry++;
            return wflag;
        }
        Sprite sprite = group.get(who);
        if(sprite.contain(bodyPart.getX() , bodyPart.getY()) || bodyPart.contain(sprite.getX() , sprite.getY())){
            sprite.setImage(empty);
            bodyPart.setImage(worn.get(who));
            returntoplaces(game);
            setStatic();
            try{
                if(Tries.badTry==0) {
                    GameSound.excellent.start();
                }
                else {GameSound.good.start();}
            }catch(Exception e){
                sound.play(5);
            }
            Tries.goodTry ++;
            Log.i("heeeree",  " goodtry: " +Tries.goodTry);
            wflag = true;
        }else{
            try{
                GameSound.encouragement.start();
            }catch(Exception e){
                GameSound.failure.get(0).start();
            }
            Tries.badTry ++;
            Log.i("heeeree", " badtry: " +Tries.badTry);
        }
        return wflag ;
    }

    public  Boolean weardependly(Game game , Sprite bodyPart , Image empty , ArrayList<Boolean> flag , Boolean wflag, int i ){
       /* int who = whoIsdragged() ;
        if(who==-1) return wflag;*/
        if(wflag) return wflag;
        int who = i ;
        Sprite sprite = group.get(who);
        int a=0 , b=1 ;
        if(who == 0) a=2 ;
        if(who == 1) b=2 ;
        if(sprite.contain(bodyPart.getX() , bodyPart.getY()) || bodyPart.contain(sprite.getX() , sprite.getY())){
            if(flag.get(who) || !(flag.get(a) || flag.get(b))){
                sprite.setImage(empty);
                bodyPart.setImage(worn.get(who));
                setStatic();
                flag.set(who , true) ;
                flag.set(a , false) ;
                flag.set(b , false) ;
                wflag = true;
                try {
                    if(Tries.badTry==0)  GameSound.excellent.start();
                    else GameSound.good.start();
                }catch (Exception e){
                    GameSound.success.get(2).play(5);
                }
                Tries.goodTry ++;
                returntoplaces(game);
                Log.e("heeeree", "goodTry: " +Tries.goodTry);
            }else{
                try{
                    GameSound.encouragement.start();
                }catch(Exception e){
                    GameSound.failure.get(0).start();
                }
                Tries.badTry ++;
                returntoplaces(game);
                Log.i("heeeree", "badTry : " +Tries.badTry);
            }
        }
        return wflag;
    }

    Boolean isDragged(){
        return (group.get(0).isDragged() || group.get(1).isDragged() || group.get(2).isDragged());
    }

    int whoIsdragged(){
        if(group.get(0).isDragged() )return 0 ;
        if(group.get(1).isDragged() )return 1 ;
        if(group.get(2).isDragged() )return 2 ;
        return -1 ;
    }


}
