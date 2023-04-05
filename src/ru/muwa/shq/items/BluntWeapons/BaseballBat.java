package ru.muwa.shq.items.BluntWeapons;

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
            BaseballBat_ID = 2;
    public static final double
            BaseballBat_WEIGHT =1000.0;
    public static  BufferedImage BaseballBat_IMG = null;
    public BaseballBat(){

        super(BaseballBat_ID,BaseballBat_Price,BaseballBat_WEIGHT,BaseballBat_IMG);
        damage = 20;
        this.description = "Битааа , ХУякс.... и башка пробитааа!";
        this.isAbleToEquip = true;
    }
    static
    {
        try
        {
            BaseballBat_IMG = ImageIO.read(new File(IMG_PATH+"combat\\basebalBat.png"));
            System.out.println("BaseballBat texture load ok");
        }catch(Exception e)
        {
            System.out.println("BaseballBat texture load NOT ok");
        }
    }
    @Override
    public void use() {
        boolean isSomeItemAlreadyEquipped = false;
        for(int i = 0; i < Inventory.getInstance().getItems().size(); i++){
            if(Inventory.getInstance().getItems().get(i).isEquipped())
                isSomeItemAlreadyEquipped = true;
        }
        if(!isSomeItemAlreadyEquipped)
        {
            setEquipped( true);
            Player.get().currentWeapon = this;
        }
    }

    @Override
    public void give(Container c) {
        Inventory.getInstance().getItems().remove(this);
        c.addItem(this);


    }


}
