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

public class BlackCreditCard extends Item {

    public static final int ID = 300, PRICE = 0;
    public static final double WEIGHT = 0.1;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\quest\\CREDITVITKOOFF.png"));
        }
        catch(IOException e) {
            System.out.println("неудалось загрузить картинку черной кредитки");
        }
    }
    public BlackCreditCard() {
        super(ID, PRICE, WEIGHT, img);
        description = "витькоф .. опять бабушек наебывает";
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {


        Renderer.addMessage("SKAM THAT im ON THE FLOOR");
        Renderer.addMessage("SKAM THAT I need some mooore");
    }

    @Override
    public Processor copy() {
        return null;
    }
}
