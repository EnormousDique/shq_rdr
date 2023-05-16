package ru.muwa.shq.items.guns;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;

import java.awt.image.BufferedImage;
public abstract class Weapon extends Item {
    public int getDamage() {
        return damage;
    }
    protected int damage;
    protected int throwback;

    public int getThrowback() {
        return throwback;
    }

    protected boolean isFireArm;
    protected int rateOfFire;
    protected int currAmmo; //Текущее кол-во патронов в магазине
    protected int maxAmmo;
    public int getMaxAmmo() {
        return maxAmmo;
    }
    public int getCurrAmmo() {
        return currAmmo;
    }
    public void setCurrAmmo(int currAmmo) {
        this.currAmmo = currAmmo;
    }
    public Weapon(int id, int price, double weight, BufferedImage texture) {
        super(id, price, weight, texture);
    }
    @Override
    public void equip() {
    }
}