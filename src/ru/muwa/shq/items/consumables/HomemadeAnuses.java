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

public class HomemadeAnuses extends Item {

    public static final int ID = 0, PRICE = 130;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\HomemadeAnus.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load HomemadeAnus image");
        }
    }
    public HomemadeAnuses() {
        super(ID, PRICE, WEIGHT, img);
        description = "Анусы домашние ,  свежие говяжьи , на вкус как кальмар.";
        stackable=true;
    }
    @Override
    public void use() {

        if (Player.get().poo < 95) {
        if(amount <= 1) Inventory.getInstance().getItems().remove(this);
        else amount-=1;
        Player.get().poo+=30;
        Player.get().setHp(Player.get().getHp()+30);
        Player.get().hunger+=45;
        }else Renderer.addMessage("Не могу. Надо посрать.");


        Renderer.addMessage("ты это заточил ЛОЛ?");
    }

    @Override
    public HomemadeAnuses copy() {
        return new HomemadeAnuses();
    }

    @Override
    public void equip() {
    }
}
