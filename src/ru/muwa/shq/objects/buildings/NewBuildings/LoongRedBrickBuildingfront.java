package ru.muwa.shq.objects.buildings.NewBuildings;

import ru.muwa.shq.objects.Building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoongRedBrickBuildingfront extends Building {
    static BufferedImage img ,img2;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\longredbrickbuilding\\BrickItogBOCK.png"));
          //  img2 = ImageIO.read(new File(IMG_PATH + "buildings\\newbuildings\\longredbrickbuilding\\BrickItogBOCK.png"));
            System.out.println("LoongRedBrickBuildingfront texture loaded");
        } catch (IOException e) {
            System.out.println("failed to load LoongRedBrickBuildingfront roof texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public LoongRedBrickBuildingfront(int x, int y) {
        super(x, y, img);
        solidBox = new Rectangle(x,y,width,height);
        transTexture =  img2;

    }
}