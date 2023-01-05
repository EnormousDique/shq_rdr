package ru.muwa.shq.entities.gameObjects;
import ru.muwa.shq.entities.items.Item;

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
}
