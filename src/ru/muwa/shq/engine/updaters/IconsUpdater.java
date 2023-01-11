package ru.muwa.shq.engine.updaters;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.controls.Interactor;

public class IconsUpdater
{
    private static IconsUpdater  instance;
    private IconsUpdater(){instance = this;}
    public static IconsUpdater getInstance(){if(instance!=null) return instance; else return new IconsUpdater();}
    public void update()
    {
        for(Container c : Engine.getCurrentLevel().getContainers()) if(!c.isInUse()) for(Item i : c.getItems()) if(Engine.getCurrentLevel().getIcons().contains(i)) Engine.getCurrentLevel().removeIcon(i.getAppearance());
    }
}
