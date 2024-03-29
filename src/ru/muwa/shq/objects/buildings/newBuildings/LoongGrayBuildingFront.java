package ru.muwa.shq.objects.buildings.newBuildings;

import ru.muwa.shq.objects.Building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoongGrayBuildingFront extends Building {
    static BufferedImage img ,img2;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\longgraybuilding\\ITOG3DOMLONGWithPadikiLOW.png"));
            img2 = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\longgraybuilding\\ITOG3DOMLONGwithpadick40transLOW.png"));
            System.out.println("LoongGrayBuildingFront texture loaded");
        } catch (IOException e) {
            System.out.println("failed to load LoongGrayBuildingFront roof texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public LoongGrayBuildingFront(int x, int y) {
        super(x, y, img);
        solidBox = new Rectangle(x,y+400,width,height-400);
        transTexture =  img2;

    }
}