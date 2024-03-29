package ru.muwa.shq.items.drugs;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Renderer;
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

public class Zanax extends Item {
    @Override
    public Item copy() {
        return new Zanax();
    }

    public static final int ID =26, PRICE = 900;
    public static final double WEIGHT = 0.875;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\drugs\\zanax.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load Zanax image");
        }
    }
    public Zanax() {
        super(ID, PRICE, WEIGHT, img);
        description = "МНе нужен ZANAX";
    }
    @Override
    public void use() {
        Player.get().setHighMeter(Player.get().getHighMeter()-5);
        Player.get().setHighMeterLock(Player.get().getHighMeterLock()-20);
        Inventory.getInstance().getItems().remove(this);
        Renderer.addMessage("закинулся ксанексом");
    }
    @Override
    public void equip() {
    }
}
