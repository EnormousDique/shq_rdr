package ru.muwa.shq.levels.demo.hub;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Hub extends GameObject {



    static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "buildings\\hub.png"));
            System.out.println("OK OK to load door texture");
        }catch (Exception e){
            System.out.println("Failed to load door texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public Hub(int x, int y) {
        super(x, y, img);
        this.isSolid= false;
    }
}
