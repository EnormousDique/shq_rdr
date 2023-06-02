package ru.muwa.shq.objects.bounds;


import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class InvisibleWall620x10 extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "InvisibleTextures\\650x10.png"));
        }catch (Exception e){
            System.out.println("Failed to load InvisibleWall620x10 texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y

     */
    public InvisibleWall620x10(int x, int y ) {
        super(x, y,img);
        // System.out.println("отработал конструктор супер классса лестница ");
        this.isSolid = true;
        // System.out.println("отработал конструктор непосредствеННО классса лестница ");

    }
}
