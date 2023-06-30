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

public class LeBottle extends Item {

    public static final int ID = 13, PRICE = 1;
    public static final double WEIGHT = 0.1;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\waterEmpty.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load emptyBottle image");
        }
    }
    public LeBottle() {
        super(ID, PRICE, WEIGHT, img);
        stackable = true;
        description = "Продырявь меня )";
    }
    @Override
    public void use() {
        if(Renderer.getMessages().contains("С бутылкой без гашла")){
            Renderer.addMessage("купи жику и гашла мудак");
        }

      else  { Renderer.addMessage("С бутылкой без гашла");
             Renderer.addMessage("и сижек не дунешь");}

    }

    @Override
    public LeBottle copy() {
        return new LeBottle();
    }

    @Override
    public void equip() {
    }
}
