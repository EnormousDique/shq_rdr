package ru.muwa.shq.objects.buildings.newBuildings;

import ru.muwa.shq.objects.Building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoongGrayBuildingSide extends Building {
    static BufferedImage img ,img2;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\longgraybuilding\\ITOG3DOMBOCK2withpadick.png"));
            img2 = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\longgraybuilding\\ITOG3DOMBOCK2withpadick.png"));
            System.out.println("LoongGrayBuildingSide texture loaded");
        } catch (IOException e) {
            System.out.println("failed to load LoongGrayBuildingSide roof texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public LoongGrayBuildingSide(int x, int y) {
        super(x, y, img);
        solidBox = new Rectangle(x,y,width,height);
        transTexture =  img2;

    }
}