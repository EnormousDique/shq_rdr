package ru.muwa.shq.items.bluntWeapons;

import ru.muwa.shq.items.guns.Weapon;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class BaseballBat extends Weapon {
    public static final int
            BaseballBat_Price = 599,
            BaseballBat_ID = 104;
    public static final double
            BaseballBat_WEIGHT =1000.0;
    public static  BufferedImage BaseballBat_IMG = null;
    public BaseballBat(){

        super(BaseballBat_ID,BaseballBat_Price,BaseballBat_WEIGHT,BaseballBat_IMG);
        damage = 15;
        this.description = "Битааа , ХУякс.... и башка пробитааа!";
        this.isAbleToEquip = true;
        this.throwback = 50;
        this.durability = 15;
    }
    static
    {
        try
        {
            BaseballBat_IMG = ImageIO.read(new File(IMG_PATH+"items\\combat\\basebalBat.png"));
            System.out.println("BaseballBat texture load ok");
        }catch(Exception e)
        {
            System.out.println("BaseballBat texture load NOT ok");
        }
    }

    @Override
    public void take(Container c) {
        c.getItems().remove(this);
        Inventory.getInstance().addItem(this);

    }


}
