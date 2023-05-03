package ru.muwa.shq.items.consumables;

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
        description = "ПОчинчКИ Темное 18% остальное по смс";
    }
    @Override
    public void give(Container c) {
        Inventory.getInstance().getItems().remove(this);
        c.addItem(this);
    }
    @Override
    public void take(Container c){
        c.getItems().remove(this);
        Inventory.getInstance().addItem(this);

    }
    @Override
    public void use() {
        Player.get().setHp(Player.get().getHp()-5);
        Player.get().setHighMeter(Player.get().getHighMeter()+5);
        EffectUtility.getCurrentEffects().put(EffectUtility.Effects.StaminaRegen,System.currentTimeMillis()+10_000);
        Inventory.getInstance().getItems().remove(this);
    }
    @Override
    public void equip() {
    }
}