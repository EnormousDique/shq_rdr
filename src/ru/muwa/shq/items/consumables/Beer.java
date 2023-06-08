package ru.muwa.shq.items.consumables;

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

public class Beer extends Item {

    public static final int ID = 28, PRICE = 39;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "consumables\\BEER.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load BEER image");
        }
    }
    public Beer() {
        super(ID, PRICE, WEIGHT, img);
        description = "ПОчинчКИ Темное 18%. остальное по смс";
        stackable=true;
    }
    @Override
    public void use() {
        if(Player.get().pee<95) {
        if(amount <= 1) Inventory.getInstance().getItems().remove(this);
        else amount-=1;
        Player.get().setHp(Player.get().getHp()+3);
        Player.get().setHighMeter(Player.get().getHighMeter()-10);
        Player.get().awake-=5;
        Player.get().pee+=20;
        Player.get().setThirst(Player.get().getThirst()+15);
        Renderer.addMessage("Бахнул пивка");
        }else Renderer.addMessage("Не могу. Надо поссать.");
    }
    @Override
    public void equip() {
    }

    @Override
    public Beer copy() {
        return new Beer();
    }
}
