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

public class Onion extends Item {
    public static final int ID = 35, PRICE = 10;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "consumables\\onion.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load onion image");
        }
    }
    public Onion() {
        super(ID, PRICE, WEIGHT, img);
        description = "Не надо плакать, друг. Я - лук.";
        stackable = true;
    }

    @Override
    public void equip() {
    }

    @Override
    public void use() {
        if(amount <= 1) Inventory.getInstance().getItems().remove(this);
        else amount-=1;
        Player.get().setHp(Player.get().getHp() +2);
        Player.get().poo+=5;
        Player.get().hunger+=1;
        Renderer.addMessage("Скушал луку");
    }

    @Override
    public Onion copy() {
        return new Onion();
    }
}
