package ru.muwa.shq.objects.car;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class HondaCivic extends Car{
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "streetFillers\\cars\\SnowDrop.png"));
        }catch (Exception e){
            System.out.println("Failed to load Flowers texture");
        }
    }

    public HondaCivic(int x, int y) {
        super(x, y,img);
    }
}
