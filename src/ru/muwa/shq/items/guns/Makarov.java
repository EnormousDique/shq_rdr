package ru.muwa.shq.items.guns;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.combat.BulletUtility;
import ru.muwa.shq.engine.s.Sounder;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;

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
            MAKAROV_IMG = ImageIO.read(new File(IMG_PATH+"items\\combat\\makarov.png"));
            System.out.println("makarov texture load ok");
        }catch(Exception e)
        {
            System.out.println("makarov texture load NOT ok");
        }
    }

    public Makarov(int id, int price, double weight, BufferedImage texture)
    {
        super(id, price, weight, texture);
    }
    public Makarov()
    {
        super(MAKAROV_ID,MAKAROV_PRICE,MAKAROV_WEIGHT,MAKAROV_IMG);
        this.description = "пистолет макарова";
        this.isAbleToEquip = true;
        this.maxAmmo = 8;
        this.currAmmo = maxAmmo;

    }

    @Override
    public void shot()
    {
        double angle = -(Aim.getInstance().calculateAngle() - 90);

        Engine.getCurrentLevel().getObjects().add(
                new Bullet((int)
                        Player.get().getAttackZone().getCenterX(),
                        (int) Player.get().getAttackZone().getCenterY(),
                        angle));

        Sounder.playSFX("src\\ru\\muwa\\shq\\sounds\\sfx\\vistrel05.wav");

        if(this.currAmmo > 0)
        {
            this.currAmmo = this.currAmmo -1;
        }
    }

    @Override
    public void reload() {
        currAmmo = maxAmmo;
    }
}
