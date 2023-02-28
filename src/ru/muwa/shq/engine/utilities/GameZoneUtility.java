package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.GameZone;

import java.awt.*;



public class GameZoneUtility
{
    private static GameZoneUtility instance;
    private GameZoneUtility(){instance = this;}
    public static GameZoneUtility getInstance(){if(instance == null) return new GameZoneUtility(); else return instance;}

    public void work()
    {


    }
}
