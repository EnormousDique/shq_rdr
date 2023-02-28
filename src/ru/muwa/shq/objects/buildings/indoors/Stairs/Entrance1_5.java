package ru.muwa.shq.objects.buildings.indoors.Stairs;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Entrance1_5 extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "buildings\\indoors\\ENTRANCEfloor2.png"));
        }catch (Exception e){
            System.out.println("Failed to load postbox texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y

     */
    public Entrance1_5(int x, int y ) {
        super(x, y,img);
       // System.out.println("отработал конструктор супер классса лестница ");
        this.isSolid = false;
      //  System.out.println("отработал конструктор непосредствеННО классса лестница ");
        System.out.println(getIsSolid());
    }
}
