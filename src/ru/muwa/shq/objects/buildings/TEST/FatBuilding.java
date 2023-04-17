package ru.muwa.shq.objects.buildings.TEST;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FatBuilding extends GameObject {
    static BufferedImage img ,img2;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "buildings\\FatBuilding_0.png"));
            img2 = ImageIO.read(new File(IMG_PATH + "buildings\\FatBuilding_0_trans.png"));
            System.out.println("TestBuilding texture loaded");
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
    public FatBuilding(int x, int y) {
        super(x, y, img);
        solidBox = new Rectangle(x,y+400,width,height-400);
        transTexture =  img2;

    }
}