package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.player.Player;

public class UseZoneUpdater
{

    public static final int USE_ZONE_WIDTH = 100, USE_ZONE_HEIGHT = 100;

    public static void update()
    {
        Player.get().getUseZone().setBounds(
                (int)Player.get().getSolidBox().getX() - 30,
                Player.get().getY() - 30,
                USE_ZONE_WIDTH,
                USE_ZONE_HEIGHT
                );
    }
}
