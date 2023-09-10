package ru.muwa.shq.objects.containers;

import ru.muwa.shq.minigames.shquring.HeaterShq;
import ru.muwa.shq.minigames.shquring.PostBoxShq;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Heater extends Container
{
    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH +"containers\\heater.png"));
            System.out.println("crate0 texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load crate0 texture");
        }

    }
    public Heater(int x, int y) throws IOException {
        super(x,y,img);

        shqurable=true;
        items = new ArrayList<>();
        miniGame = new HeaterShq(this);
    }

    @Override
    public void setInUse(boolean b) {

    }

    @Override
    public boolean getInUse() {
        return false;
    }
}
