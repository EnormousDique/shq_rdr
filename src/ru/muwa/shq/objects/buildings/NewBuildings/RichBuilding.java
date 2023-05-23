package ru.muwa.shq.objects.buildings.NewBuildings;

import ru.muwa.shq.objects.Building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RichBuilding extends Building {
    static BufferedImage img ,img2;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\verytallbuilding\\PremiumRichBuilding.png"));
            //  img2 = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\longgraybuilding\\ITOG3DOMBOCK.png"));
            System.out.println("RichBuilding texture loaded");
        } catch (IOException e) {
            System.out.println("failed to load RichBuilding roof texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public RichBuilding(int x, int y) {
        super(x, y, img);
        solidBox = new Rectangle(x,y,width,height);
        transTexture =  img2;

    }
}