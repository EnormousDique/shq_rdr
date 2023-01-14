package ru.muwa.shq.objects.buildings.ninefloor;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NineFloorWall extends GameObject {

    static BufferedImage img;

    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH +"buildings\\9floor\\up_wall.png"));
            System.out.println("9 floor wall texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load nine floor building  wall texture");
        }
    }


    /**
     * Конструктор
     *
     * @param x
     * @param y
     * @param texture
     */
    public NineFloorWall(int x, int y) {
        super(x, y, img);
        isFalling = false;
        isStatic = true;
    }
}
