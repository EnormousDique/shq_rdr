package ru.muwa.shq.levels.demo.demoLevel0;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DemoLevel0_BG extends GameObject {

    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "background\\LONO.png"));
            System.out.println("Level0 bg texture loaded");
        } catch (IOException e) {
            System.out.println("failed to load level0bg texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public DemoLevel0_BG(int x, int y) {
        super(x, y, img);
        this.isSolid = false;
        this.isStatic = true;
    }
}
