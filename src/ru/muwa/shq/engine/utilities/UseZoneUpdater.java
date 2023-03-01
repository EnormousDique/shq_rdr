package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.player.Player;

public class UseZoneUpdater
{
    private static UseZoneUpdater instance;
    public static final int USE_ZONE_WIDTH = 100, USE_ZONE_HEIGHT = 100;
    private UseZoneUpdater(){instance = this;}
    public static UseZoneUpdater getInstance(){if(instance != null) return instance; else return new UseZoneUpdater();}

    public void update()
    {
        Player.get().getUseZone().setBounds(
                (int)Player.get().getSolidBox().getX() - 30,
                Player.get().getY() - 30,
                USE_ZONE_WIDTH,
                USE_ZONE_HEIGHT
                );
    }
}
