package ru.muwa.shq.objects.containers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class WirelessPanel extends Container
{

    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "buildings\\indoors\\WirelessPanel.png"));
        }catch (Exception e){
            System.out.println("Failed to load ElectricPanel texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public WirelessPanel(int x, int y) {
        super(x, y, img);
    }

    @Override
    public void setInUse(boolean b) {

    }

    @Override
    public boolean getInUse() {
        return false;
    }
}
