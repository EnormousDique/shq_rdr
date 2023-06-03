package ru.muwa.shq.items.quest;

import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class HardDrive extends Item {

    public static final int ID = 223, PRICE = 2990;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\quest\\HardDrive.png"));
        }
        catch(IOException e) {
            System.out.println("неудалось загрузить картинку HardDrive");
        }
    }
    public HardDrive() {
        super(ID, PRICE, WEIGHT, img);
        description = "HardDrive ";
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {


        Renderer.addMessage("Жесткий как и я");
    }

    @Override
    public HardDrive copy() {
        return null;
    }
}
