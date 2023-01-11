package ru.muwa.shq.player.condition;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Player;

public class BusyUpdater
{
    private static BusyUpdater instance;
    private BusyUpdater(){instance = this;}
    public static BusyUpdater getInstance(){if(instance!=null) return instance; else return new BusyUpdater();}
    public void update(){
        for(Container c : Engine.getCurrentLevel().getContainers()) if(c.isInUse()) Player.get().setIsBusy(true);
    }
}
