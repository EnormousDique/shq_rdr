package ru.muwa.shq.items.consumables;

import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Carrot extends Item {

    public static final int ID = 32, PRICE = 15;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "consumables\\carrot.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load carrot image");
        }
    }
    public Carrot() {
        super(ID, PRICE, WEIGHT, img);
        description = "любофь маркофь)))";
    }

    @Override
    public void equip() {

    }
}
