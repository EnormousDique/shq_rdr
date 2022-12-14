package ru.muwa.shq.items.drugs;

import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.spec.ECField;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Flour extends Item {

    public static final int ID = 0, PRICE = 3000;
    public static final double WEIGHT = 1.0;
    private static BufferedImage img;

    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "drugs\\flour.png"));
        }
        catch(IOException e)
        {
            System.out.println("failed to load flour image");
        }
    }


    public Flour()
    {
        super(ID, PRICE, WEIGHT, img);
    }
}
