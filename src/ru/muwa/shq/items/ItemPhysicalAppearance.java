package ru.muwa.shq.items;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ItemPhysicalAppearance
{
    public ItemPhysicalAppearance(int x, int y, Item i){
        this.x=x;
        this.y=y;
        img = i.getTexture();
        box = new Rectangle(x,y,img.getWidth(),img.getHeight());
        item = i;
    }
    int x,y;
    BufferedImage img;
    Item item;
    boolean isGrabbed, isDropped;

    Rectangle box;
    public int getX(){return x;}
    public int getY(){return y;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public BufferedImage getImg(){return img;}
    public Rectangle getBox(){return box;}
    public void setIsGrabbed(boolean isGrabbed){this.isGrabbed = isGrabbed;}
    public boolean isGrabbed(){return isGrabbed;}


}
