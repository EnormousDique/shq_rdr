package ru.muwa.shq.items.clothes.torso;

import org.w3c.dom.ls.LSOutput;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.clothes.Clothes;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class LeatherJacket extends Clothes {


    private static BufferedImage img = null;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH+"items\\clothes\\кожанка1.png"));
            System.out.println("l jacket icon texture load ok");
        }catch(Exception e)
        {
            System.out.println("l jacket icon texture load NOT ok");
        }
    }



    public LeatherJacket() {
        super(401, 10000, 5000, img);
        description = "Пиздатая кожанка. (из кожи обезьянки)";
        bonusHP = 30;
        type = TORSO;
    }

    @Override
    public Item copy() {
        return new LeatherJacket();
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {
        if(Player.get().torsoWear == null) Player.get().torsoWear = this;
        else if(Player.get().torsoWear == this) Player.get().torsoWear = null;
        else Renderer.addMessage("Сначала сними текущий шмот");
    }
}
