package ru.muwa.shq.items.guns;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.s.Sounder;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Obrez extends Weapon implements Firearm{
    public static  BufferedImage OBREZ_IMG = null;

    static
    {
        try
        {
            OBREZ_IMG = ImageIO.read(new File(IMG_PATH+"combat\\makarov.png"));
            System.out.println("obrez texture load ok");
        }catch(Exception e)
        {
            System.out.println("obrez texture load NOT ok");
        }
    }
    public Obrez()
    {
        super(0, 1000, 0, OBREZ_IMG);
        this.description = "охуенный  бля обрез";
        this.isAbleToEquip = true;
        this.maxAmmo = 2;
        this.currAmmo = maxAmmo;
    }
    private Obrez(int id, int price, double weight, BufferedImage texture) {
        super(id,price,weight,texture);

    }

    @Override
    public void shot() {
        double angle = -(Aim.getInstance().calculateAngle() - 90);

        Engine.getCurrentLevel().getObjects().add(
                new Bullet((int)
                        Player.get().getAttackZone().getCenterX(),
                        (int) Player.get().getAttackZone().getCenterY(),
                        angle));
        Engine.getCurrentLevel().getObjects().add(
                new Bullet((int)
                        Player.get().getAttackZone().getCenterX(),
                        (int) Player.get().getAttackZone().getCenterY(),
                        angle-10));
        Engine.getCurrentLevel().getObjects().add(
                new Bullet((int)
                        Player.get().getAttackZone().getCenterX(),
                        (int) Player.get().getAttackZone().getCenterY(),
                        angle+10));

        Sounder.playSFX("src\\ru\\muwa\\shq\\sounds\\sfx\\vistrel05.wav");

        if(this.currAmmo > 0)
        {
            this.currAmmo = this.currAmmo -1;
        }
    }

    @Override
    public void reload() {

    }
}
