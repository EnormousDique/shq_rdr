package ru.muwa.shq.items.guns;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;

import java.awt.image.BufferedImage;

public class Weapon extends Item {

    protected int damage;
    protected boolean isFireArm;
    protected int rateOfFire;

    public Weapon(int id, int price, double weight, BufferedImage texture) {
        super(id, price, weight, texture);
    }

    @Override
    public void give(Container c) {

    }

    @Override
    public void use() {
        //TODO: помещение объекта в экипирову
    }

}
