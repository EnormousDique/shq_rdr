package ru.muwa.shq.engine.updaters;

import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

public class InventoryWindowUpdater
{
    static private InventoryWindowUpdater  instance;
    private InventoryWindowUpdater (){instance = this;}
    public static InventoryWindowUpdater  getInstance()
    {if (instance == null) return new InventoryWindowUpdater(); else return instance;}
    public void update()
    {
        Inventory.getInstance().setX(Player.get().getX() + 100);
        Inventory.getInstance().setY(Player.get().getY() - 50);
        Inventory.getInstance().getBox().setBounds
                (Player.get().getX() + 100,
                Player.get().getY() - 50,
                (int)Inventory.getInstance().getImg().getWidth(),
                (int)Inventory.getInstance().getImg().getHeight());
    }
}
