package ru.muwa.shq.items.zakladki;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.items.drugs.Hash;
import ru.muwa.shq.items.drugs.IceOlator;
import ru.muwa.shq.items.drugs.Weed;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class KladYellow extends Item {

    public static final int ID = 0, PRICE = 100;
    public static final double WEIGHT = 1.0;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "zakladki\\zakladYellow.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load zakladYellow image");
        }
    }
    public KladYellow() {
        super(ID, PRICE, WEIGHT, img);
        description = "Закладка,Изолента жёлтая. ";
    }
    @Override
    public void use() {
        double a = Math.random()*4;
        Inventory.getInstance().addItem(getRandomLoot().get((int) a));
        Inventory.getInstance().getItems().remove(this);
    }
    @Override
    public void equip() {
    }
    public ArrayList<Item> getRandomLoot() {

        ArrayList<Item> loot = new ArrayList<>();

        loot.add(new Flour());
        loot.add(new Hash());
        loot.add(new IceOlator());
        loot.add(new Weed());

        return loot;
    }
}
