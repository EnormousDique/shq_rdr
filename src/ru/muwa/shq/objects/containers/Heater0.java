package ru.muwa.shq.objects.containers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Heater0 extends Container
{
    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH +"containers\\heater.png"));
            System.out.println("crate0 texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load crate0 texture");
        }

    }
    public Heater0(int x, int y) throws IOException {
        super(x,y,img);
        capacity = 1;
        items = new ArrayList<>(capacity);
    }
}
