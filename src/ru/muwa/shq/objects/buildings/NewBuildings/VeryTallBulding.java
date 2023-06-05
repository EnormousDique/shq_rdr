package ru.muwa.shq.objects.buildings.NewBuildings;

import ru.muwa.shq.objects.Building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VeryTallBulding extends Building {
    static BufferedImage img ,img2;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\verytallbuilding\\TALLBUILDINGTOP.png"));
              img2 = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\verytallbuilding\\TOPDOMTRANS.png"));
            System.out.println("VeryTallBulding texture loaded");
        } catch (IOException e) {
            System.out.println("failed to load VeryTallBulding roof texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public VeryTallBulding(int x, int y) {
        super(x, y, img);
        solidBox = new Rectangle(x,y+1600,width,height-1600);
        transTexture =  img2;

    }
}