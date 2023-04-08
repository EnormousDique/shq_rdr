package ru.muwa.shq.items.consumables;

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

public class EnergyDrink extends Item {

    public static final int ID = 0, PRICE = 59;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "consumables\\Energetica.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load energy image");
        }
    }
    public EnergyDrink() {
        super(ID, PRICE, WEIGHT, img);
        description = "ЭНЕРГЕТИКА!";
    }
    @Override
    public void give(Container c) {
        Inventory.getInstance().getItems().remove(this);
        c.addItem(this);
    }
    @Override
    public void use() {
        EffectUtility.getCurrentEffects().put(EffectUtility.Effects.SPEED,System.currentTimeMillis()+10_000L);
        Player.get().setHighMeter(Player.get().getHighMeter()+0);
        Player.get().setHighMeterLock(Player.get().getHighMeterLock()-2);
        Inventory.getInstance().getItems().remove(this);
    }
    @Override
    public void equip() {
    }
}
