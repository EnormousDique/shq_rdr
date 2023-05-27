package ru.muwa.shq.objects.apartments;

import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.zones.GameZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class L1B6P1F4_KidApartmentsInteriors extends GameObject {

    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "buildings\\indoors\\kidapartments.png"));
        }catch (Exception e){
            System.out.println("Failed to load kid apartments  bg texture");
        }
    }

    public L1B6P1F4_KidApartmentsInteriors(int x, int y) {
        super(x, y, img);
        this.isSolid = false;
    }
}
