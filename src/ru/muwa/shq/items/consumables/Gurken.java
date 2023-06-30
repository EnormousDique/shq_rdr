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

public class Gurken extends Item {

    public static final int ID = 11, PRICE = 39;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\consumables\\gurken.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load gurken image");
        }
    }
    public Gurken() {
        super(ID, PRICE, WEIGHT, img);
        description = "агурьчик (GURKEN)";
        stackable = true;
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {
        if (Player.get().poo < 95) {
        if(amount <= 1) Inventory.getInstance().getItems().remove(this);
        else amount-=1;
        Player.get().setHp(Player.get().getHp() +2);
        Player.get().poo+=5;
        Renderer.addMessage("Заточил огурец (гуркен блтяь)");
        }else Renderer.addMessage("Не могу. Надо посрать.");

    }

    @Override
    public Gurken copy() {
        return new Gurken();
    }

}
