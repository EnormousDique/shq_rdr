package ru.muwa.shq.objects.street;


import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Flowers extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "streetFillers\\flowers\\Flowers.png"));
        }catch (Exception e){
            System.out.println("Failed to load Flowers texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y

     */
    public Flowers(int x, int y ) {
        super(x, y,img);

        this.isSolid = true;

        System.out.println(getIsSolid());
    }
}
