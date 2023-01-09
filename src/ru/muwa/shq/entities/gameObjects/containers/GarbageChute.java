package ru.muwa.shq.entities.gameObjects.containers;

import ru.muwa.shq.entities.gameObjects.Container;
import ru.muwa.shq.entities.gameObjects.GameObject;
import ru.muwa.shq.entities.items.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

public class GarbageChute extends Container
{
    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File("C:\\sosok\\Shq_rdr\\src\\ru\\muwa\\shq\\textures\\containers\\GarbageChute.png"));
            System.out.println("crate0 texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load crate0 texture");
        }

    }
    public GarbageChute(int x, int y) throws IOException {
        super(x,y,img);
        capacity = 1;
        items = new ArrayList<>(capacity);
    }
}