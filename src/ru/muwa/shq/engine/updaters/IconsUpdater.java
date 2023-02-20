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

}
