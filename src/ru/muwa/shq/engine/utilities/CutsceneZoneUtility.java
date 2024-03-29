package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.animations.Animator;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.CutsceneZone;
import ru.muwa.shq.zones.GameZone;

public class CutsceneZoneUtility
{
    public static void work()
    {
        for(GameZone z : Engine.getCurrentLevel().getZones())
        {
            if(z instanceof CutsceneZone && Player.get().getSolidBox().intersects(z) && !((CutsceneZone)z).isBeenPlayed()) {
                    Animator.playCutscene(((CutsceneZone) z).getCutscene());
                    ((CutsceneZone) z).setBeenPlayed(true);

            }


        }
    }
}
