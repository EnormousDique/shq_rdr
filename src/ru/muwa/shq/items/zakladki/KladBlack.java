package ru.muwa.shq.items.zakladki;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Renderer;
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

public class KladBlack extends Item {

    public static final int ID = 27, PRICE = 100;
    public static final double WEIGHT = 1.0;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\zakladki\\KladDark.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load zakladkablack image");
        }
    }
    public KladBlack() {
        super(ID, PRICE, WEIGHT, img);
        description = "Закладка,Изолента Черная.Магнит есть.. ММмм). ";

    }
    @Override
    public void use() {

        double a = Math.random()*4;
        Item s = getRandomLoot().get((int) a);
        Inventory.getInstance().addItem(getRandomLoot().get((int) a));
        Inventory.getInstance().getItems().remove(this);
      //  Renderer.addMessage("Распаковал клад" +"" + name );
        Renderer.addMessage("И под изолентой" + s.toString().split("\\.")[5].split("@")[0]);
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
