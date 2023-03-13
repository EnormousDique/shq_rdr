package ru.muwa.shq.creatures.npc.enemies;

import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.creatures.npc.NPC;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static ru.muwa.shq.objects.GameObject.Direction.RIGHT;

public class BadGuy0 extends NPC
{
    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "creatures\\BadGuy0.png"));
            System.out.println("BadGuy0 loaded ok");
        }
        catch(Exception e)
        {
            System.out.println("failed to load BadGuy0 texture");
        }
    }
    public BadGuy0(int x, int y)
    {
        super(x,y,img);
        velocity = 2;
        speed = 1;
        direction = RIGHT;
        setRayCaster(new RayCaster(x,y,300));
        hp = 20;
    }
    protected BadGuy0(int x, int y, BufferedImage texture)
    {
        super(x, y, texture);
    }
}
