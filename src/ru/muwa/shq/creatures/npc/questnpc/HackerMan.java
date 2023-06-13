package ru.muwa.shq.creatures.npc.questnpc;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HackerMan extends NPC {
    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "creatures\\BUSINESSMAN.png"));
            System.out.println("HACKER loaded ok");
        }
        catch(Exception e)
        {
            System.out.println("failed to load HacKER texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x       - х коррдината
     * @param y       - у коррдината
     *
     */
    public HackerMan(int x, int y) {
        super(x, y, img);
        this.name = "Hacker";
        setRayCaster(new RayCaster(x,y,600));
        this.speed = 5;
        hp = 50;
        maxHp = hp;
        try
        {
            corpseimg = ImageIO.read(new File(IMG_PATH +"containers\\BadGuyCorpse.png"));
            System.out.println("HACKER texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load HAcker texture");
        }

    }

    @Override
    public ArrayList<Item> getRandomLoot() {
        return null;
    }
}