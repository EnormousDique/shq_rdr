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
            System.out.println("crate0 texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load crate0 texture");
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
        capacity = 1;
        items = new ArrayList<>(capacity);
        items.add(new Flour());
        items.get(0).setAppearance(new ItemPhysicalAppearance(this.x ,this.y , items.get(0)));
        items.get(0).setMyContainer(this);
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
