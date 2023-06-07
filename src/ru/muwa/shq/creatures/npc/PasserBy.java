package ru.muwa.shq.creatures.npc;

import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static ru.muwa.shq.objects.GameObject.Direction.RIGHT;

public class PasserBy extends NPC{

    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "creatures\\MAMASHA.png"));
            System.out.println("hach loaded ok");
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
     * @param texture - текстура (файл с изборажением)
     *
     */

    protected PasserBy(int x, int y) {
        super(x, y, img);
        velocity = 1;
        speed = 5;
        direction = RIGHT;
        hp = 10;
        //текстура трупа
        try {
            corpseimg = ImageIO.read(new File(IMG_PATH + "creatures\\MAMASHA.png"));
            System.out.println("dead hach texture loaded");
        } catch (IOException e) {
            System.out.println("failed to load dead hach texture");
        }
    }

    @Override
    public ArrayList<Item> getRandomLoot() {
        return new ArrayList<>();
    }
}
