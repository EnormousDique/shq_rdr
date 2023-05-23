package ru.muwa.shq.objects.buildings.TEST;

import ru.muwa.shq.objects.Building;
import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestBuilding extends Building {
    static BufferedImage img;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "buildings\\tallHouse.png"));
           // System.out.println("TestBuilding texture loaded");
        } catch (IOException e) {
            System.out.println("failed to load testbuilding roof texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public TestBuilding(int x, int y) {
        super(x, y, img);
    }
}