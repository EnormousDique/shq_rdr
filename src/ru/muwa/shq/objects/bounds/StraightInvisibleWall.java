package ru.muwa.shq.objects.bounds;


import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class StraightInvisibleWall extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "background\\720x20wall.png"));
        }catch (Exception e){
            System.out.println("Failed to load 720wall texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y

     */
    public StraightInvisibleWall(int x, int y ) {
        super(x, y,img);
        System.out.println("отработал конструктор супер классса прямаястена невидимая ");
        this.isSolid = true;
        System.out.println("отработал конструктор непосредствеННО классса прямаястена невидимая ");
        System.out.println(getIsSolid());
    }
}
