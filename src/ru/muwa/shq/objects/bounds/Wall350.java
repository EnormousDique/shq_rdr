package ru.muwa.shq.objects.bounds;


import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Wall350 extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "background\\Wall350x50.png"));
        }catch (Exception e){
            System.out.println("Failed to load wall350 texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y

     */
    public Wall350(int x, int y ) {
        super(x, y,img);
       // System.out.println("отработал конструктор супер классса лестница ");
        this.isSolid = true;
      //  System.out.println("отработал конструктор непосредствеННО классса лестница ");
        System.out.println(getIsSolid());
    }
}
