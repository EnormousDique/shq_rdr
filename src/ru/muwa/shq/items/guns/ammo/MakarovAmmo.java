package ru.muwa.shq.items.guns.ammo;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.guns.Makarov;
import ru.muwa.shq.items.guns.Weapon;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class MakarovAmmo extends Ammo{

    private final static int MAKAROV_AMMO_ID  = 0,
                      MAKAROV_AMMO_PRICE = 500;
    private final static double MAKAROV_AMMO_WEIGHT = 80d;
    private static BufferedImage img;
    static {
        try
        {
            img = ImageIO.read(new File(IMG_PATH +"combat\\makarov_ammo 2.png"));
            System.out.println("BadGuyCorpse texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load makarov ammo texture");
        }
    }

    public MakarovAmmo()
    {
        super(MAKAROV_AMMO_ID,MAKAROV_AMMO_PRICE,MAKAROV_AMMO_WEIGHT,img);
        stackable = true;
        this.description = "Патроны для макарыча. Вся обойма - штук.";
    }
    public MakarovAmmo(int id, int price, double weight, BufferedImage texture) {
        super(id, price, weight, texture);
    }



    @Override
    public void use() {
        Makarov gun = null;
        for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
        {
            if(Inventory.getInstance().getItems().get(i).isEquipped()
                    &&
               Inventory.getInstance().getItems().get(i)instanceof Makarov)
            {
                gun = (Makarov)Inventory.getInstance().getItems().get(i);
                if(amount>1) amount -=1; else Inventory.getInstance().getItems().remove(this);
                gun.reload();

            }
        }
    }

    @Override
    public void equip() {
    }
    @Override
    public Item copy() {
        return new MakarovAmmo();
    }
}
