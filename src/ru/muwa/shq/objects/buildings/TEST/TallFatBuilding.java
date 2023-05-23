package ru.muwa.shq.objects.buildings.TEST;

import ru.muwa.shq.objects.Building;
import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TallFatBuilding extends Building {
    static BufferedImage img , img2;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\bluewhitetallbuilding\\TallHouse.png"));
            img2 = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\bluewhitetallbuilding\\TallHouseTRANS50.png"));//сделай прозрачным в фотошопе жолбоеб
            System.out.println("bluewhitetallbuilding loaded");
        } catch (IOException e) {
            System.out.println("failed to load bluewhitetallbuilding roof texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public TallFatBuilding(int x, int y)
    {

        super(x, y, img);
        solidBox = new Rectangle(x,y+400,width,height-400);
        transTexture =  img2;
    }
}