package com.dressTheKids;

public class GroupCord {

    int x1 , x2 , x3 , y , w , h ;

    public GroupCord(int x1 , int x2 , int x3 , int y , int h , int w){
        this.x1 = x1 ;
        this.x2 = x2 ;
        this.x3 = x3 ;
        this.y = y ;
        this.h = h ;
        this.w = w ;
        }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getX3() {
        return x3;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }
}
