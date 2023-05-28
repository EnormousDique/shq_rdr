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

public class BoobPill extends Item {

    public static final int ID = 138, PRICE = 899;
    public static final double WEIGHT = 0.1;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "consumables\\BOOBPill.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load BoobPill image");
        }
    }
    public BoobPill() {
        super(ID, PRICE, WEIGHT, img);
        description = "витаминка";
    }
    @Override
    public void use() {

        Inventory.getInstance().getItems().remove(this);
    }
    @Override
    public void equip() {
    }
}
