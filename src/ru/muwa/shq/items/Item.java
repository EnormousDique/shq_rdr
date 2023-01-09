package ru.muwa.shq.items;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Абстрактный класс, прародитель всех предметов.
 */
public abstract class Item
{
    protected ItemPhysicalAppearance appearance;
    protected int id,price;
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
    public ItemPhysicalAppearance getAppearance(){return appearance;}
    public void setAppearance(ItemPhysicalAppearance appearance){this.appearance = appearance;}
}
