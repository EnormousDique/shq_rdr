package ru.muwa.shq.levels.demoLevel0.buildings.market;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VegetablesVendor extends GameObject {

    private static BufferedImage img;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "buildings\\indoors\\vegetables.png"));
        } catch (IOException e) {
            System.out.println("failed to load овощная палатка image");
        }
    }

    public VegetablesVendor(int x, int y) {
        super(x, y, img);
    }
}
