package ru.muwa.shq.objects.buildings.ninefloor;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NineFloorRoof extends GameObject {
    /**
     * Конструктор
     *
     * @param x
     * @param y
     * @param texture
     */

    static BufferedImage img;

    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH +"buildings\\9floor\\roof.png"));
            System.out.println("9 floor roof texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load nine floor building roof texture");
        }
    }


    public NineFloorRoof(int x, int y) {
        super(x, y, img);
        isFalling = false;
        isStatic = true;
    }
}
