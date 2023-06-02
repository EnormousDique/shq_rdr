package ru.muwa.shq.items.quest;

import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Pacanynok extends Item {


    static private BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\quest\\kid.png"));
        } catch (IOException e) {
            System.out.println("failed to load kid image");
        }
    }
    public Pacanynok() {
        super(101, 0, 3000,img );
        description="цветок жизни";
        isAbleToEquip = true;
        alwaysEquipped = true;// снять нельзя продать?
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {
        Renderer.addMessage("Агу агу ебать.");
    }
}
