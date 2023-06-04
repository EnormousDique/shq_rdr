package ru.muwa.shq.objects.buildings.NewBuildings;

import ru.muwa.shq.objects.Building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoongRedBrickBuildingTop extends Building {
    static BufferedImage img ,img2;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\longredbrickbuilding\\LONGBRICKTOP.png"));
            img2 = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\longredbrickbuilding\\LONGBRICKTOPTRANS.png"));
            System.out.println("LoongRedBrickBuildingfronttop texture loaded");
        } catch (IOException e) {
            System.out.println("failed to load LoongRedBrickBuildingfronttop roof texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public LoongRedBrickBuildingTop(int x, int y) {
        super(x, y, img);
        solidBox = new Rectangle(x,y+650,width,height-650);
        transTexture =  img2;

    }
}