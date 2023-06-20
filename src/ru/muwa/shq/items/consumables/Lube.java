package ru.muwa.shq.items.consumables;

import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Lube extends Item {
    static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH+"items\\consumables\\lube.png"));
            System.out.println("lube texture load ok");
        }catch(Exception e)
        {
            System.out.println("lube texture load NOT ok");
        }
    }
    public Lube() {
        super(0, 100, 0, img);
        stackable=true;
        description="Анальный князь - жопная мазь.";
    }

    @Override
    public Lube copy() {
        return new Lube();
    }

    @Override
    public void use() {
        Renderer.addMessage("Скользкая");
    }

    @Override
    public void equip() {

    }
}
