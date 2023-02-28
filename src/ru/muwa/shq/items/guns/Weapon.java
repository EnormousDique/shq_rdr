package ru.muwa.shq.items.guns;

import ru.muwa.shq.items.Item;

import java.awt.image.BufferedImage;

public class Weapon extends Item {

    protected int damage;
    protected boolean isFireArm;
    protected int rateOfFire;

    public Weapon(int id, int price, double weight, BufferedImage texture) {
        super(id, price, weight, texture);
    }

}
