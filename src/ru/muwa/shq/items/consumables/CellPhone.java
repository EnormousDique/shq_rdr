package ru.muwa.shq.items.consumables;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Renderer;
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

public class CellPhone extends Item {
    @Override
    public Item copy() {
        return new CellPhone();
    }

    public static final int ID = 6, PRICE = 25000;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\CellPhone.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load CellPhone image");
        }
    }
    public CellPhone() {
        super(ID, PRICE, WEIGHT, img);
        description = "Хоть маме звони , хоть дороги черти";
    }
    @Override
    public void use() {
        Renderer.addMessage("telefon zablockirovan");

    }
    @Override
    public void equip() {
    }
}
