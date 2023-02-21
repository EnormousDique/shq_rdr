package ru.muwa.shq.objects.containers;

import ru.muwa.shq.items.ItemPhysicalAppearance;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.objects.Usable;
import ru.muwa.shq.player.Inventory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TrashCan extends Container implements Usable {

    private static BufferedImage img;

    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH +"containers\\trashCan.png"));
            System.out.println("trash can texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load trash can texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public TrashCan(int x, int y) {
        super(x, y,img);

        items = new ArrayList<>();



        UI = Inventory.getInstance().getImg();

    }

    @Override
    public void setInUse(boolean b) {

    }

    @Override
    public boolean getInUse() {
        return false;
    }
}
