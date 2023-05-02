package ru.muwa.shq.items.drugs;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Lyrica extends Item {

    public static final int ID = 0, PRICE = 450;
    public static final double WEIGHT = 0.2;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "drugs\\Lyrica.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load Lyrica image");
        }
    }
    public Lyrica() {
        super(ID, PRICE, WEIGHT, img);
        description = "Нармальна ебашит !!! ДА ???";
    }
    @Override
    public void give(Container c) {
        Inventory.getInstance().getItems().remove(this);
        c.addItem(this);
    }
    @Override
    public void use() {

            Player.get().setHp(Player.get().getHp()-5);
        Player.get().setHighMeter(Player.get().getHighMeter()+30);
        Player.get().setHighMeterLock(Player.get().getHighMeterLock()+50);
        Inventory.getInstance().getItems().remove(this);
    }
    @Override
    public void equip() {
    }
    @Override
    public void take(Container c){
        c.getItems().remove(this);
        Inventory.getInstance().addItem(this);
    }
}
