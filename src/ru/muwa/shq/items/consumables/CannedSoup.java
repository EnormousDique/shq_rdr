package ru.muwa.shq.items.consumables;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.utilities.EffectUtility;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class CannedSoup extends Item {

    public static final int ID = 0, PRICE = 79;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\consumables\\CannedSoup3.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load CannedSoup image");
        }
    }
    public CannedSoup () {
        super(ID, PRICE, WEIGHT, img);
        description = "суп из семи залуп.три покрошены , остальные так брошены";
        stackable=true;
    }

    @Override
    public void use() {
        if (Player.get().poo < 95) {
        if(amount <= 1) Inventory.getInstance().getItems().remove(this);
        else amount-=1;
        Player.get().poo+=20;
        Player.get().pee+=10;
        Player.get().hunger+=25;
        Player.get().setHp(Player.get().getHp()+25);
        Renderer.addMessage("Похавал супу");
        }else Renderer.addMessage("Не могу. Надо посрать.");

    }

    @Override
    public CannedSoup copy() {
        return new CannedSoup();
    }

    @Override
    public void equip() {
    }
}
