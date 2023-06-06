package ru.muwa.shq.items.consumables;

import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Tomato extends Item {
    public static final int ID = 37, PRICE = 150;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "consumables\\tomato.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load tomato image");
        }
    }
    public Tomato() {
        super(ID, PRICE, WEIGHT, img);
        description = "Охуенен и пиздат. К сожалению, томат.";
        stackable=true;
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {
        if(amount <= 1) Inventory.getInstance().getItems().remove(this);
        else amount-=1;
        Player.get().poo+=5;
        Player.get().setHp(Player.get().getHp() +2);
        Renderer.addMessage("скушал помидорку");
    }

    @Override
    public Tomato copy() {
        return new Tomato();
    }
}
