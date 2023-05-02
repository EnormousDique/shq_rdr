package ru.muwa.shq.objects.bounds;

import ru.muwa.shq.objects.GameObject;

import java.awt.image.BufferedImage;

public class UniversalWall extends GameObject {
    /**
     * Конструктор
     *
     * @param x
     * @param y
     * @param height
     * @param width
     */
    public UniversalWall(int x, int y, int width, int height) {
        super(x, y,width,height);
        isSolid = true;
    }
}
