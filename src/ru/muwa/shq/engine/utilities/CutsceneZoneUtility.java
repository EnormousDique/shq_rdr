package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.animations.Animator;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.CutsceneZone;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.GameZone;

public class CutsceneZoneUtility
{
    public static void work()
    {
        for(GameZone z : Engine.getCurrentLevel().getZones())
        {
            if(z instanceof CutsceneZone && Player.get().getSolidBox().intersects(z) && !((CutsceneZone)z).isBeenPlayed()) {
                //System.out.println("player in cutscene zone");
                Animator.playCutscene(((CutsceneZone) z).getCutscene());
                ((CutsceneZone) z).setBeenPlayed(true);
            }
            //TODO: КОД НУЖЕН НА ВРЕМЯ ТЕСТА.
            /*
            if(z instanceof CutsceneZone && !Player.get().getSolidBox().intersects(z))
            {
                ((CutsceneZone) z).setBeenPlayed(false);
            }
            */
            //TODO: КОД НУЖЕН НА ВРЕМЯ ТЕСТА. УДАЛИТЬ!

        }
    }
}
