package ru.muwa.shq.items;

import java.awt.*;

public class ItemPhysicalAppearance
{
    public ItemPhysicalAppearance(int x, int y){
        this.x=x;
        this.y=y;


    }
    int x,y;
    Rectangle icon;

    public int getX(){return x;}
    public int getY(){return y;}

}
