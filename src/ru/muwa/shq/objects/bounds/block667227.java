package ru.muwa.shq.objects.bounds;


import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class block667227 extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "InvisibleTextures\\667227block.png"));
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
    public block667227(int x, int y ) {
        super(x, y,img);
        // System.out.println("отработал конструктор супер классса лестница ");
        this.isSolid = true;
        // System.out.println("отработал конструктор непосредствеННО классса лестница ");

    }
}
