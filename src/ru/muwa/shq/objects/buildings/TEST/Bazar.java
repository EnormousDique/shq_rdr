package ru.muwa.shq.objects.buildings.TEST;


import ru.muwa.shq.objects.Building;
import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bazar extends Building {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "buildings\\\\Rinok.png"));
        }catch (Exception e){
            System.out.println("Failed to load Bazar texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y

     */
    public Bazar(int x, int y ) {
        super(x, y,img);

        this.isSolid = false;

        System.out.println(getIsSolid());
    }
}
