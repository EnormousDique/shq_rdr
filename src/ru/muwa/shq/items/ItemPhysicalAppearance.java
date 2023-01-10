package ru.muwa.shq.items;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ItemPhysicalAppearance
{
    public ItemPhysicalAppearance(int x, int y, Item i){
        this.x=x;
        this.y=y;
        img = i.getTexture();
    }
    int x,y;
    Rectangle icon;
    BufferedImage img;

    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public BufferedImage getImg(){return img;}


}
