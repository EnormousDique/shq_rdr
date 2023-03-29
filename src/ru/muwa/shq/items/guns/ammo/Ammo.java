package ru.muwa.shq.items.guns.ammo;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.player.Inventory;

import java.awt.image.BufferedImage;

public abstract class Ammo extends Item {
    public Ammo(int id, int price, double weight, BufferedImage texture) {
        super(id, price, weight, texture);
    }



}
