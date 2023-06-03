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

public class Processor extends Item {

    public static final int ID = 228, PRICE = 2281488;
    public static final double WEIGHT = 0.1;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\quest\\Processor.png"));
        }
        catch(IOException e) {
            System.out.println("неудалось загрузить картинку процессора");
        }
    }
    public Processor() {
        super(ID, PRICE, WEIGHT, img);
       description = "процессор 4 ядра";
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {


        Renderer.addMessage("Тоже камень но не покуришь.");
    }

    @Override
    public Processor copy() {
        return null;
    }
}
