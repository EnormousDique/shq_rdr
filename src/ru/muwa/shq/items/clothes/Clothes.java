package ru.muwa.shq.items.clothes;

import ru.muwa.shq.items.Item;

import java.awt.image.BufferedImage;

public abstract class Clothes extends Item {

    public Clothes(int id, int price, double weight, BufferedImage texture) {
        super(id, price, weight, texture);
    }

    public static final int
        FOOT = 0, TORSO = 1, HEAD =2;

    public int type;
    public int bonusHP, bonusSpeed, bonusStamina, bonusSprint;



}
