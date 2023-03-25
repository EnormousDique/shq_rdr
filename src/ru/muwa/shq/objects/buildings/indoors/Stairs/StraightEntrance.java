package ru.muwa.shq.objects.buildings.indoors.Stairs;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class StraightEntrance extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "buildings\\indoors\\500padik.png"));
        }catch (Exception e){
            System.out.println("Failed to load padik texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y

     */
    public StraightEntrance(int x, int y ) {
        super(x, y,img);
       // System.out.println("отработал конструктор супер классса padik  ");
        this.isSolid = false;
       // System.out.println("отработал конструктор непосредствеННО классса padik ");
        System.out.println(getIsSolid());
    }
}
