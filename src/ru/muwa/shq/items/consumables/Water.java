package ru.muwa.shq.items.consumables;

import ru.muwa.shq.engine.utilities.EffectUtility;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Water extends Item {

    public static final int ID = 0, PRICE = 100;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "consumables\\water2.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load WAter image");
        }
    }
    public Water() {
        super(ID, PRICE, WEIGHT, img);
        stackable = false;
        description = "Дайте че нибудь,попить пожалуйста,Попить будде добры";
    }
    @Override
    public void use() {

        // System.out.println(System.currentTimeMillis()+" ВРЕМЯЯ!!!!!!!!!!!!");
        Player.get().setHighMeter(Player.get().getHighMeter()-5);
        Player.get().setHighMeterLock(Player.get().getHighMeterLock()-2);
        Player.get().setThirst(Player.get().getThirst()+50);
        EffectUtility.getCurrentEffects().put(EffectUtility.Effects.StaminaRegen,System.currentTimeMillis()+10_000);
        Inventory.getInstance().getItems().remove(this);
        Player.get().setHp(Player.get().getHp()+2);
        LeBottle bottle = new LeBottle();

        Inventory.getInstance().addItem(bottle);

    }
    @Override
    public void equip() {
    }


}
