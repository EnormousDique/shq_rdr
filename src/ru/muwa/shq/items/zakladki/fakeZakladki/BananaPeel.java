package ru.muwa.shq.items.zakladki.fakeZakladki;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.utilities.EffectUtility;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.zakladki.KladRed;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class BananaPeel extends Item {
    @Override
    public Item copy() {
        return new BananaPeel();
    }

    public static final int ID = 0, PRICE = 1;
    public static final double WEIGHT = 0.1;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\fakeZakladki\\банан.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load шкурка банаа image");
        }
    }
    public BananaPeel() {
        super(ID, PRICE, WEIGHT, img);
        description = "кожура банана";
    }
    @Override
    public void use() {

        Inventory.getInstance().getItems().remove(this);
    }
    @Override
    public void equip() {
    }
}
