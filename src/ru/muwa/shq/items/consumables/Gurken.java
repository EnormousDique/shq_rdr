package ru.muwa.shq.items.consumables;

import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Gurken extends Item {

    public static final int ID = 34, PRICE = 39;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "consumables\\gurken.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load gurken image");
        }
    }
    public Gurken() {
        super(ID, PRICE, WEIGHT, img);
        description = "агурьчик (GURKEN)";
    }

    @Override
    public void equip() {

    }
}
