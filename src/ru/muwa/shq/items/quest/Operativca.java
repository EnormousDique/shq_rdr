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

public class Operativca extends Item {

    public static final int ID = 224, PRICE = 14990;
    public static final double WEIGHT = 0.1;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\quest\\RAM.png"));
        }
        catch(IOException e) {
            System.out.println("неудалось загрузить картинку Operativca");
        }
    }
    public Operativca() {
        super(ID, PRICE, WEIGHT, img);
        description = "2 плашки по 4 гига";
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {


        Renderer.addMessage("вот бы и мне памяти .");
    }

    @Override
    public Operativca copy() {
        return null;
    }
}
