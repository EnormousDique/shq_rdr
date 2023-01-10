package ru.muwa.shq.engine.p.updaters;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.ItemPhysicalAppearance;

public class ItemPhysicalAppearanceBoxUpdater
{
    private static ItemPhysicalAppearanceBoxUpdater instance;

    private ItemPhysicalAppearanceBoxUpdater()
    {
        instance = this;
    }
    public static ItemPhysicalAppearanceBoxUpdater getInstance()
    {if(instance!=null)return instance; else return new ItemPhysicalAppearanceBoxUpdater();}

    public void update()
    {
        for(ItemPhysicalAppearance a : Engine.getCurrentLevel().getIcons())
            a.getBox().setBounds(a.getX(),a.getY(),(int)a.getBox().getWidth(),(int)a.getBox().getHeight());
    }
}
