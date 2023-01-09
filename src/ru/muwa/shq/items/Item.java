package ru.muwa.shq.items;

import java.awt.image.BufferedImage;

/**
 * Абстрактный класс, прародитель всех предметов.
 */
public abstract class Item
{
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
}
