package ru.muwa.shq.creatures.npc.questnpc;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Hachique extends NPC {


    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "creatures\\Hachique.png"));
            System.out.println("BadGuy0 loaded ok");
        }
        catch(Exception e)
        {
            System.out.println("failed to load Hachique texture");
        }
    }


    /**
     * Конструктор
     *
     * @param x       - х коррдината
     * @param y       - у коррдината
     *
     */
    public Hachique(int x, int y) {
        super(x, y, img);
        this.name = "Hachique";
        setRayCaster(new RayCaster(x,y,600));
        this.speed = 5;

    }
    public Hachique(int x, int y, String name) {
        super(x, y, img);
        this.name = name;
        setRayCaster(new RayCaster(x,y,600));
        this.speed = 5;

    }

    @Override
    public ArrayList<Item> getRandomLoot() {
        return new ArrayList<>();
    }
}
