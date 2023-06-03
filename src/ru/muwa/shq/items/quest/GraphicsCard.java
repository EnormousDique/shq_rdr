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

public class GraphicsCard extends Item {

    public static final int ID = 227, PRICE = 26990;
    public static final double WEIGHT = 0.1;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\quest\\VideoCard.png"));
        }
        catch(IOException e) {
            System.out.println("неудалось загрузить картинку видяхи");
        }
    }
    public GraphicsCard() {
        super(ID, PRICE, WEIGHT, img);
        description = "видеокарта ";
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {


        Renderer.addMessage("а как биткойны достать.");
    }

    @Override
    public GraphicsCard copy() {
        return null;
    }
}
