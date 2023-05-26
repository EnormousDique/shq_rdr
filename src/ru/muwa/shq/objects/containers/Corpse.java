package ru.muwa.shq.objects.containers;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.Usable;
import ru.muwa.shq.player.Inventory;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Corpse extends Container implements Usable {


    public long deathTime;


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
        deathTime = System.currentTimeMillis();
    }

    public void CorpseCleaner() {
        for (int i = 0; i < Engine.getCurrentLevel().getNPC().size(); i++) {
            NPC body = Engine.getCurrentLevel().getNPC().get(i);

            if (/*deathTime > 10 & */ Engine.getCurrentLevel().getContainers().get(i).getItems().isEmpty()) {
                Engine.getCurrentLevel().getContainers().remove(body);
            }
        }
    }


    @Override
    public void setInUse(boolean b) {}
    @Override
    public boolean getInUse() {
        return false;
    }
}
