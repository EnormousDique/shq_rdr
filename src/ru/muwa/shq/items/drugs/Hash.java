package ru.muwa.shq.items.drugs;

import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.utilities.EffectUtility;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.consumables.ChickFire;
import ru.muwa.shq.items.consumables.Cigarettes;
import ru.muwa.shq.items.consumables.LeBottle;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Hash extends Item {

    public static final int ID = 0, PRICE = 900;
    public static final double WEIGHT = 1.0;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "drugs\\hash.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load hash image");
        }
    }
    public Hash() {
        super(ID, PRICE, WEIGHT, img);
        description = "Хэщ , хэш они курят хеш!!!";
        stackable = true;
    }

    @Override
    public void use() {

        LeBottle bottle = null;
        Cigarettes smokes = null;
        ChickFire fire = null;
        //TODO: Добавить проверку на сигареты и зажигалку.
        try {
            bottle = (LeBottle) Inventory.getInstance().getItems().stream().filter(i -> i instanceof LeBottle).collect(Collectors.toList()).get(0);
            smokes = (Cigarettes) Inventory.getInstance().getItems().stream().filter(i -> i instanceof Cigarettes).collect(Collectors.toList()).get(0);
            fire = (ChickFire) Inventory.getInstance().getItems().stream().filter(i -> i instanceof ChickFire).collect(Collectors.toList()).get(0);

        }catch (Exception e)
        {
            Renderer.addMessage("Нужна бутылка и сижки! и жига");
        }

        if(bottle != null && smokes!=null && fire!=null) {

            Player.get().setHighMeter(Player.get().getHighMeter() + 20);
            Player.get().setHighMeterLock(Player.get().getHighMeterLock() - 15);
            EffectUtility.getCurrentEffects().put(EffectUtility.Effects.STONED, System.currentTimeMillis() + 10_000);


            Inventory.getInstance().getItems().remove(bottle);
            Inventory.getInstance().getItems().remove(smokes);

            if (Player.get().getHp() >= 95) Player.get().setHp(100);
            else
                Player.get().setHp(Player.get().getHp() + 5);
            if(amount<=1)Inventory.getInstance().getItems().remove(this);
            else amount-=1;
            Renderer.addMessage("Плюшку сладко затянул");
        }


    }
    @Override
    public void equip() {
    }
    @Override
    public Hash copy() {
        return new Hash();
    }


}
