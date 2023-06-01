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

public class Beetroot extends Item {

    public static final int ID = 30, PRICE = 38;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "consumables\\beetroot.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load свёкла image");
        }
    }
    public Beetroot() {
        super(ID, PRICE, WEIGHT, img);
        description = "ну че ты пялишься? свёкла";
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
        Renderer.addMessage("захавал свеклу");
    }

    @Override
    public Beetroot copy() {
        return new Beetroot();
    }
}
