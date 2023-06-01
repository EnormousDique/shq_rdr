package ru.muwa.shq.objects.bounds;


import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class block580138 extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "InvisibleTextures\\580138block.png"));
        }catch (Exception e){

        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y

     */
    public block580138(int x, int y ) {
        super(x, y,img);
        // System.out.println("отработал конструктор супер классса лестница ");
        this.isSolid = true;
        // System.out.println("отработал конструктор непосредствеННО классса лестница ");

    }
}
