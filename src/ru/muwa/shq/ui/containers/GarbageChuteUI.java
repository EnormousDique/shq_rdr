package ru.muwa.shq.ui.containers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class GarbageChuteUI
{
    public static BufferedImage img;

    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "inventory\\garbage chute.png"));
        }catch (IOException exception)
        {
            System.out.println("garbage chute UI img failed to load");
        }
    }
}
