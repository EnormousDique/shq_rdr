package ru.muwa.shq.items;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.objects.containers.Container;

import java.awt.image.BufferedImage;

/**
 * Абстрактный класс, прародитель всех предметов.
 */
public abstract class Item
{



    protected String description ;
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    protected Container myContainer;
    protected int id,price;

    public int getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    protected double weight;
    protected BufferedImage texture; //Текстура (изображение) объекта
    public Item(int id,int price,double weight,BufferedImage texture)
    {
        this.id = id;
        this.price = price;
        this.weight = weight;
        this.texture = texture;
    }
    public BufferedImage getTexture() {return texture;}


    public void setMyContainer(Container c){myContainer = c;}
    public Container getMyContainer(){return myContainer;}

    public void pick()
    {
        boolean isInContainer = false;
        Container container = null;
        for (Container c : Engine.getCurrentLevel().getContainers())
            if(c.isInUse()){ isInContainer = true; container = c;}
        if(isInContainer) give(container); else use();
    }
    public abstract void give(Container c);
    public abstract void use();
}
