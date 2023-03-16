package ru.muwa.shq.objects.bounds;


import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class InvisibleWall extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "background\\invisibleWall.png"));
        }catch (Exception e){
            System.out.println("Failed to load Wall300 texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y

     */
    public InvisibleWall(int x, int y ) {
        super(x, y,img);
        System.out.println("отработал конструктор супер классса лестница ");
        this.isSolid = true;
        System.out.println("отработал конструктор непосредствеННО классса лестница ");
        System.out.println(getIsSolid());
    }
}
