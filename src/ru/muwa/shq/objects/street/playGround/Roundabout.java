package ru.muwa.shq.objects.street.playGround;


import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Roundabout extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "streetFillers\\kinderStaff\\Karusel.png"));
        }catch (Exception e){
            System.out.println("Failed to load Roundabout texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y

     */
    public Roundabout(int x, int y ) {
        super(x, y,img);

        this.isSolid = true;


    }
}
