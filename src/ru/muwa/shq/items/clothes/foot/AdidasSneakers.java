package ru.muwa.shq.items.clothes.foot;

import org.w3c.dom.ls.LSOutput;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.clothes.Clothes;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class AdidasSneakers extends Clothes {


    private static BufferedImage img = null;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH+"items\\clothes\\кросы1.png"));
            System.out.println("adidas icon texture load ok");
        }catch(Exception e)
        {
            System.out.println("adidas icon texture load NOT ok");
        }
    }

    public AdidasSneakers() {
        super(400, 5000, 1000, img);
        description = "Как у пацанов. Адики кросовки.";
        bonusStamina = 20;
        bonusSpeed = 1;
        bonusSprint = 1;
        type = FOOT;
    }

    @Override
    public Item copy() {
        return new AdidasSneakers();
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {
        if(Player.get().footWear == null) Player.get().footWear = this;
        else if(Player.get().footWear == this) Player.get().footWear = null;
        else Renderer.addMessage("Сначала сними текущие тапки");
    }
}
