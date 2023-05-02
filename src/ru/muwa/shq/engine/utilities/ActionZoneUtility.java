package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.ActionZone;
import ru.muwa.shq.zones.GameZone;

public class ActionZoneUtility {
    public static void work()
    {
        for(GameZone z : Engine.getCurrentLevel().getZones())
        {
            if(z instanceof ActionZone && Player.get().getSolidBox().intersects(z))
            {
                ActionZone az = (ActionZone) z;
                if(!az.expired) {
                    az.getAction().performAction();
                    az.expired = true;
                }
            }
        }
    }
}
