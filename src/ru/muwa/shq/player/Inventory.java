package ru.muwa.shq.player;

import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
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
        instance = this;
        items = new ArrayList<>();
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
    private boolean isOpened;
    public boolean isOpened(){return isOpened;}
    public void setIsOpened(boolean isOpened){this.isOpened = isOpened;}

    public BufferedImage getImg() {
        return img;
    }
}
