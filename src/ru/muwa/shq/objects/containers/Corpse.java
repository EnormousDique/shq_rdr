package ru.muwa.shq.objects.containers;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.Usable;
import ru.muwa.shq.player.Inventory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Corpse extends Container implements Usable {





    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public Corpse(int x, int y,BufferedImage corpse, ArrayList<Item> loot) {
        super(x, y,corpse);

        for(Item i : loot) this.addItem(i);
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
