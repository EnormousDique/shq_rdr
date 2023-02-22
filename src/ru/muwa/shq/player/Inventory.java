package ru.muwa.shq.player;

import org.w3c.dom.css.Rect;
import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Inventory
{
    public static final int INVENTORY_CAPACITY = 8;
    private Rectangle item0,item1,item2,item3,item4,item5,item6,item7;
    private ArrayList<Rectangle> itemIcons;
    private ArrayList<Item> items;

    private static BufferedImage img;

    public Rectangle getItemIcons(int i) {
        return itemIcons.get(i);
    }


    private Rectangle box;
    private int x,y;
    private boolean isOpened;
    private static Inventory instance;

    public ArrayList<Rectangle> getItemIcons() {
        return itemIcons;
    }

    static{
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "inventory\\inventory.png"));
        }catch (Exception e)
        {
            System.out.println("inventory texture load failed");
        }

    }
    private Inventory()

    {
        System.out.println("inventory initiated");
        instance = this;
        items = new ArrayList<>();
        item0 = new Rectangle(x,y,50,50);
        itemIcons = new ArrayList<>();
        itemIcons.add(item0);itemIcons.add(item1);itemIcons.add(item2);itemIcons.add(item3);itemIcons.add(item4);itemIcons.add(item5);itemIcons.add(item6);itemIcons.add(item7);

        box = new Rectangle(x,y,img.getWidth(),img.getHeight());


    }
    public static Inventory getInstance(){if(instance!=null) return instance; else return new Inventory();}

    public boolean isOpened(){return isOpened;}
    public void setIsOpened(boolean isOpened){this.isOpened = isOpened;}

    public BufferedImage getImg() {
        return img;
    }
    public int getX(){return x;}
    public void setX(int x){this.x = x;}
    public int getY(){return y;}
    public void setY(int y){this.y = y;}
    public Rectangle getBox(){return box;}


    public void addItem(Item item) {
         if(items.size()<=INVENTORY_CAPACITY)items.add(item);
        System.out.println("added item: " + item);
    }
    public ArrayList<Item> getItems(){return items;}
}
