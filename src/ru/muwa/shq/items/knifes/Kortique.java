package ru.muwa.shq.items.knifes;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.guns.Weapon;
import ru.muwa.shq.items.zakladki.KladRed;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import static ru.muwa.shq.objects.GameObject.IMG_PATH;
public class Kortique extends Weapon {
    @Override
    public Item copy() {
        return new Kortique();
    }

    private static BufferedImage img = null;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH+"items\\combat\\knife_icon.png"));
            System.out.println("knife icon texture load ok");
        }catch(Exception e)
        {
            System.out.println("knife icon texture load NOT ok");
        }
    }
    public Kortique()
    {
        super(102,399,0,img);
        damage = 30;
        isAbleToEquip=true;
        description = "Достал нож реж";
        durability = 10;
    }
    private Kortique(int id, int price, double weight, BufferedImage texture) {
        super(id, price, weight, texture);
    }
}
