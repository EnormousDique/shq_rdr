package ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
public class WhiteBlueTallBuildingFLoorNoNumberEnotherDoor2 extends GameObject {
    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "buildings\\indoors\\padikenotherdoor2.png"));
        }catch (Exception e){
            System.out.println("Failed to load ?????? padik??? texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public WhiteBlueTallBuildingFLoorNoNumberEnotherDoor2(int x, int y ) {
        super(x, y,img);
        this.isSolid = false;
    }
}
