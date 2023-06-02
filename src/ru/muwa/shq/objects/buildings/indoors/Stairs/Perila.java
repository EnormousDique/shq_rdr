package ru.muwa.shq.objects.buildings.indoors.Stairs;


import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Perila extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "buildings\\indoors\\mishaPieceOfArt.png"));
        }catch (Exception e){
            System.out.println("Failed to load perila texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y

     */
    public Perila(int x, int y ) {
        super(x, y,img);
       // System.out.println("отработал конструктор супер классса лестница ");
        this.isSolid = true;
      //  System.out.println("отработал конструктор непосредствеННО классса лестница ");

    }
}
