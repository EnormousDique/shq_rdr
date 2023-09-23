package ru.muwa.shq.items.clothes.hat;

import org.w3c.dom.ls.LSOutput;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.clothes.Clothes;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class GasMask extends Clothes {


    private static BufferedImage img = null;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH+"items\\clothes\\противогаз.png"));
            System.out.println("gazmask icon texture load ok");
        }catch(Exception e)
        {
            System.out.println("gazmask icon texture load NOT ok");
        }
    }



    public GasMask() {
        super(403, 10000, 5000, img);
        description = "иди своей дорогой сталкер";
        bonusHP = 30;
        bonusStamina = -100;
        type = HEAD;
    }

    @Override
    public Item copy() {
        return new GasMask();
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {
        System.out.println("lol");
        if(Player.get().headWear == null) Player.get().headWear = this;
        else if(Player.get().headWear == this) Player.get().headWear = null;
        else Renderer.addMessage("Сначала сними текущую шапку");
    }
}
