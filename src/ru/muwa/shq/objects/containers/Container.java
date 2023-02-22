package ru.muwa.shq.objects.containers;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.Usable;
import ru.muwa.shq.player.Inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public abstract class Container extends GameObject implements Usable
{
    boolean isContainer = true;// Объект может содержать в себе вещи
    protected ArrayList<Item> items = new ArrayList<Item>(); // список обьектов контейнера
    protected ArrayList<Rectangle> icons = new ArrayList<>(); // список квадратов для отображения иконок предметов.
    protected int capacity;  // вместительность

    /**
     * Конструктор
     *
     * @param x
     * @param y
     * @param texture
     */


    protected Container(int x, int y, BufferedImage texture) {
        super(x, y, texture);
    }


    protected int UIx,UIy;

    public ArrayList<Item> getItems(){return items;}

    public void setItems(ArrayList<Item> items) { this.items = items;

    }
    public void addItem (Item i) {
        icons.add(new Rectangle(this.x + (50 * (items.size()-1)),this.y,50,50));
        items.add(i);


    }
    public ArrayList<Rectangle> getIcons(){
        return icons;
    }
    public void grabItem(int a){
        Inventory.getInstance().addItem(this.getItems().get(a));
        removeItem(a);
    }
    public void removeItem(int a){
         icons.remove(a);
         items.remove(a);

    }

}
