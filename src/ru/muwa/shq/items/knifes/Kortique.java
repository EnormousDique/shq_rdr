package ru.muwa.shq.items.knifes;

import ru.muwa.shq.items.guns.Weapon;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import static ru.muwa.shq.objects.GameObject.IMG_PATH;
public class Kortique extends Weapon {

    private static BufferedImage img = null;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH+"combat\\knife_icon.png"));
            System.out.println("knife icon texture load ok");
        }catch(Exception e)
        {
            System.out.println("knife icon texture load NOT ok");
        }
    }
    public Kortique()
    {
        super(0,399,0,img);
        damage = 30;
        isAbleToEquip=true;
        description = "Достал нож реж";
        durability = 10;
    }
    private Kortique(int id, int price, double weight, BufferedImage texture) {
        super(id, price, weight, texture);
    }
}
