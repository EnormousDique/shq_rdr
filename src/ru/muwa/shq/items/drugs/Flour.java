package ru.muwa.shq.items.drugs;

import ru.muwa.shq.engine.Engine;
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

public class Flour extends Item {

    public static final int ID = 0, PRICE = 3000;
    public static final double WEIGHT = 1.0;
    private static BufferedImage img;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "drugs\\flour.png"));
        } catch (IOException e) {
            System.out.println("failed to load flour image");
        }
    }

    public Flour() {
        super(ID, PRICE, WEIGHT, img);
        description = "мефчик бля , ебанул и нихуя";
    }

    @Override
    public void give(Container c) {
        Inventory.getInstance().getItems().remove(this);
        c.addItem(this);
    }

    @Override
    public void use() {

        EffectUtility.getCurrentEffects().put(EffectUtility.Effects.SPEED, System.currentTimeMillis() + 10_000L);
        Player.get().setHighMeterLock(Player.get().getHighMeterLock() + 30);
        Player.get().setThirst(Player.get().getThirst() - 10);
        Player.get().setHighMeter(Player.get().getHighMeter() + 30);
        Inventory.getInstance().getItems().remove(this);
       // if(Player.get().getHp()>=90) Player.get().setHp(100);
      //  else Player.get().setHp(Player.get().getHp()+10);
    }

    @Override
    public void equip() {
    }

    @Override
    public void take(Container c) {
        c.getItems().remove(this);
        Inventory.getInstance().addItem(this);
    }
}
