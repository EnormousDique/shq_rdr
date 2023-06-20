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

public class Cigarettes extends Item {

    public static final int ID = 0, PRICE = 169;
    public static final double WEIGHT = 0.1;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\consumables\\Sigi.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load Sigi image");
        }
    }
    public Cigarettes() {
        super(ID, PRICE, WEIGHT, img);
        description = "Те самые , как у ковбоя";
        stackable=true;
    }
    @Override
    public void use() {
        if(amount <= 1) Inventory.getInstance().getItems().remove(this);
        else amount-=1;
        Player.get().setHp(Player.get().getHp()-5);
        Player.get().setHighMeter(Player.get().getHighMeter()-1);
        Player.get().setHighMeterLock(Player.get().getHighMeterLock()-5);
        Player.get().poo+=2;
        EffectUtility.getCurrentEffects().put(EffectUtility.Effects.ODYSHKA,System.currentTimeMillis()+10_000);
        Renderer.addMessage("Закурил сигаретку");
    }

    @Override
    public Cigarettes copy() {
        return new Cigarettes();
    }

    @Override
    public void equip() {
    }
}
