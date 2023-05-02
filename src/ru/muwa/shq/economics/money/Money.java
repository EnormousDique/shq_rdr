package ru.muwa.shq.economics.money;

import ru.muwa.shq.items.Item;

import java.awt.image.BufferedImage;

public abstract class Money extends Item {
    public Money(int id, int price, double weight, BufferedImage texture) {
        super(id, price, weight, texture);
    }
}
