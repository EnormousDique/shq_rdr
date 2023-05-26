package ru.muwa.shq.levels.demo.demoLevel0.buildings.market;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class MarketInteriorsBG extends GameObject {

    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "buildings\\indoors\\market.png"));
        }catch (Exception e){
            System.out.println("Failed to load market  bg texture");
        }
    }
    public MarketInteriorsBG(int x, int y) {
        super(x, y, img);
        this.isSolid = false;
    }
}

