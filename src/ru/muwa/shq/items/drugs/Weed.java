package ru.muwa.shq.items.drugs;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Weed extends Item {

    public static final int ID = 0, PRICE = 1500;
    public static final double WEIGHT = 1.0;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "drugs\\weeed.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load hash image");
        }
    }
    public Weed() {
        super(ID, PRICE, WEIGHT, img);
        description = "Травка , травки , бошки , шишки.\nНо я предпочитаю травки ";
    }
    @Override
    public void give(Container c) {
        Inventory.getInstance().getItems().remove(this);
        c.addItem(this);
    }
    @Override
    public void use() {
        if(Player.get().getHp() >= 90) Player.get().setHp(100);
        else
            Player.get().setHp(Player.get().getHp()+10);
        Player.get().setHighMeter(Player.get().getHighMeter()+15);
        Player.get().setHighMeterLock(Player.get().getHighMeterLock()-50);
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
