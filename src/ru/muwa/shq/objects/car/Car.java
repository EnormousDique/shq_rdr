package ru.muwa.shq.objects.car;

import ru.muwa.shq.objects.GameObject;

import java.awt.image.BufferedImage;

public class Car extends GameObject {

    private int speed = 10 ; // сокрость такчки

    protected Car(int x, int y, BufferedImage texture) {
        super(x, y, texture);
    }

    public int getSpeed() {return speed;}

    public void setSpeed(int speed) {this.speed = speed;}

}
