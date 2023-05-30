package ru.muwa.shq.objects.buildings.indoors.Stairs;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
public class GreyPadick extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "buildings\\indoors\\GREYPADICK.png"));
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
    public GreyPadick(int x, int y ) {
        super(x, y,img);
        this.isSolid = false;
    }
}
