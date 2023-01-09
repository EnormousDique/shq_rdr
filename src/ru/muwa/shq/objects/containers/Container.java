package ru.muwa.shq.objects.containers;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.GameObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
public abstract class Container extends GameObject
{
    boolean isContainer = true;// Объект может содержать в себе вещи
    protected ArrayList<Item> items = new ArrayList<Item>();
    protected int capacity;
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

    public ArrayList<Item> getItems(){return items;}
}
