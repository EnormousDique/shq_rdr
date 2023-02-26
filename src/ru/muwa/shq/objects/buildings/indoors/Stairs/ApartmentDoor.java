package ru.muwa.shq.objects.buildings.indoors.Stairs;


import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ApartmentDoor extends GameObject {
    private static BufferedImage img;
    private static BufferedImage img1;
    private static BufferedImage img2;  // TODO:создать текстурки и добавить их загрузку в статик блок.
    private static BufferedImage img3; //

    static{
        try{
            img1 = ImageIO.read(new File(IMG_PATH + "buildings\\indoors\\DOOR_MAN.png"));
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
    public ApartmentDoor(int x, int y,int imgNum ) {
        super(x,y,img1);

        switch (imgNum)
        {
            case 0:
                this.texture=img1;
                break;
            case 1:
                this.texture=img2;
                break;
        }

    }
}
