package ru.muwa.shq.creatures.npc.questnpc;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StarleyMahony extends NPC {
    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "creatures\\Mahony.png"));
            System.out.println("Mahony loaded ok");
        }
        catch(Exception e)
        {
            System.out.println("failed to load Mahony texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x       - х коррдината
     * @param y       - у коррдината
     *
     */
    public StarleyMahony(int x, int y) {
        super(x, y, img);
        this.name = "Махоуни";
        setRayCaster(new RayCaster(x,y,600));
        this.speed = 5;
        try
        {
            corpseimg = ImageIO.read(new File(IMG_PATH +"containers\\CORPSECOP.png"));
            System.out.println("Mahony texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load Mahony texture");
        }

    }

    @Override
    public ArrayList<Item> getRandomLoot() {
        return null;
    }
}