package ru.muwa.shq.creatures.npc.questnpc;

import ru.muwa.shq.creatures.npc.NPC;
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
            img = ImageIO.read(new File(IMG_PATH + "creatures\\BadGuy0.png"));
            System.out.println("BadGuy0 loaded ok");
        }
        catch(Exception e)
        {
            System.out.println("failed to load BadGuy0 texture");
        }
    }


    /**
     * Конструктор
     *
     * @param x       - х коррдината
     * @param y       - у коррдината
     * @param texture - текстура (файл с изборажением)
     */
    protected Hachique(int x, int y) {
        super(x, y, img);
    }

    @Override
    public ArrayList<Item> getRandomLoot() {
        return new ArrayList<>();
    }
}
