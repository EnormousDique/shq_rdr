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

public class Package extends Item {

    public static final int ID = 307, PRICE = 0;
    public static final double WEIGHT = 1.8;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\quest\\PACKAGE.png"));
        }
        catch(IOException e) {
            System.out.println("неудалось загрузить картинку посылки для атма");
        }
    }
    public Package() {
        super(ID, PRICE, WEIGHT, img);
        description = "ХУя... увесистая";
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {


        Renderer.addMessage(" Трясу и хуй пойму что там");
    }

    @Override
    public Operativca copy() {
        return null;
    }
}
