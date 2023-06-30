package ru.muwa.shq.items.consumables;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Renderer;
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

public class ChickFire extends Item {

    public static final int ID = 7, PRICE = 50;
    public static final double WEIGHT = 0.1;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\DicKK.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load ChickFire image");
        }
    }
    public ChickFire() {
        super(ID, PRICE, WEIGHT, img);
        description = "хей хей хей .... ФААААЙЙЙАААААААА";
    }

    @Override
    public Item copy() {
        return new ChickFire();
    }

    @Override
    public void use() {
        Renderer.addMessage(description);
    }
    @Override
    public void equip() {
    }
}
