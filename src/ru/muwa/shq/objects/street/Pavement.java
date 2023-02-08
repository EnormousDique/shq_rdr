package ru.muwa.shq.objects.street;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pavement extends GameObject {
    /**
     * Конструктор
     *
     * @param x
     * @param y
     * @param texture
     */
    public static BufferedImage img;

    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH +"street\\pavement.png"));
            System.out.println("pavement texture loaded");
        } catch(Exception e)
        {
            System.out.println("failed to load pavement texture");
        }
    }
    public Pavement(int x, int y) {
        super(x, y, img);
        isSolid = true;
        isStatic = true;

    }
}
