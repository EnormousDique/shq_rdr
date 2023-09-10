package ru.muwa.shq.objects.containers;

import ru.muwa.shq.minigames.shquring.WindowsillShq;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WindowSill extends Container{


    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH +"containers\\windowsill.png"));
            System.out.println("windowsill texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load windowsill texture");
        }

    }

    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public WindowSill(int x, int y) {
        super(x, y, img);
        items = new ArrayList<>();
        shqurable = true;
        miniGame = new WindowsillShq(this);
    }

    @Override
    public void setInUse(boolean b) {

    }

    @Override
    public boolean getInUse() {
        return false;
    }
}
