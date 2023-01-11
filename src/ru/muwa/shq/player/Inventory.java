package ru.muwa.shq.player;

import org.w3c.dom.css.Rect;
import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Inventory
{
    public static final int INVENTORY_CAPACITY = 8;
    private static Inventory instance;
    private Inventory()
    {
        System.out.println("inventory initiated");
        instance = this;
        items = new ArrayList<>();
        box = new Rectangle();
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "inventory\\inventory.png"));
        }catch (Exception e)
        {
            System.out.println("inventory texture load failed");
        }

    }
    public static Inventory getInstance(){if(instance!=null) return instance; else return new Inventory();}
    private ArrayList<Item> items;

    private BufferedImage img;
    private Rectangle box;
    private int x,y;
    private boolean isOpened;
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
        items.add(item);
        System.out.println("added item: " + item);
    }
    public ArrayList<Item> getItems(){return items;}
}
