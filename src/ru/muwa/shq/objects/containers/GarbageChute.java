package ru.muwa.shq.objects.containers;

import ru.muwa.shq.items.ItemPhysicalAppearance;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.ui.containers.GarbageChuteUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GarbageChute extends Container
{
    private static BufferedImage img;
    public static final int GB_CH_Y_OFFSET = 210,GB_CH_X_OFFSET = 70 ;

    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH +"containers\\GarbageChute.png"));
            System.out.println("crate0 texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load crate0 texture");
        }

    }
    public GarbageChute(int x, int y) throws IOException {
        super(x,y,img);
        capacity = 1;
        items = new ArrayList<>(capacity);
        items.add(new Flour());
        items.get(0).setAppearance(new ItemPhysicalAppearance(this.x + GB_CH_X_OFFSET,this.y + GB_CH_Y_OFFSET,items.get(0)));
        UI = GarbageChuteUI.img;
    }
}