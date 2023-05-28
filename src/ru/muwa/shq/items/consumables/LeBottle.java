package ru.muwa.shq.items.consumables;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.utilities.EffectUtility;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class LeBottle extends Item {

    public static final int ID = 0, PRICE = 1;
    public static final double WEIGHT = 0.1;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\waterEmpty.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load emptyBottle image");
        }
    }
    public LeBottle() {
        super(ID, PRICE, WEIGHT, img);
        description = "Продырявь меня )";
    }
    @Override
    public void use() {

        Inventory.getInstance().getItems().remove(this);
    }
    @Override
    public void equip() {
    }
}
