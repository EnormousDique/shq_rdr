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
        stackable=true;
    }
    @Override
    public void use() {
        if(Player.get().pee<95) {
        if(amount <= 1) Inventory.getInstance().getItems().remove(this);
        else amount-=1;
        Player.get().pee+=15;
        Player.get().setStamina(100.0);
        Renderer.addMessage("Бахнув Энергетика");
        }else Renderer.addMessage("Не могу. Надо поссать.");
    }

    @Override
    public EnergyDrink copy() {
        return new EnergyDrink();
    }

    @Override
    public void equip() {
    }
}
