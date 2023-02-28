package ru.muwa.shq.items.guns;

import ru.muwa.shq.engine.combat.BulletUtility;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Makarov extends Weapon implements Firearm{

    public static final int
            MAKAROV_PRICE = 5_000,
            MAKAROV_ID = 1,
            MAKAROV_MAX_AMMO=8;

    public static final double
            MAKAROV_WEIGHT = 730.0;

    public static  BufferedImage MAKAROV_IMG = null;
    public static  BufferedImage MAKAROV_EQUIP = null;

    static
    {
        try
        {
            MAKAROV_IMG = ImageIO.read(new File(IMG_PATH+"combat\\makarov.png"));
            System.out.println("makarov texture load ok");
        }catch(Exception e)
        {
            System.out.println("makarov texture load NOT ok");
        }
    }
    private int currAmmo; //Текущее кол-во патронов в магазине

    public Makarov(int id, int price, double weight, BufferedImage texture)
    {
        super(id, price, weight, texture);
    }
    public Makarov()
    {
        super(MAKAROV_ID,MAKAROV_PRICE,MAKAROV_WEIGHT,MAKAROV_IMG);

    }

    @Override
    public void shot()
    {
        if(this.currAmmo > 0)
        {
            this.currAmmo = this.currAmmo -1;
            BulletUtility.getInstance().addBullet(new Bullet(Player.get().getX(), Player.get().getY(),45));
        }
    }

    @Override
    public void reload()
    {
        //Тут будет код перезарядки

    }
}
