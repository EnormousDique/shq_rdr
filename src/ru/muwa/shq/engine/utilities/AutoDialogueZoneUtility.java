package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.GameZone;

import java.awt.*;

public class AutoDialogueZoneUtility {
    public static void checkAutoDialogueZone (){
        for(GameZone z:Engine.getCurrentLevel().getZones()){

            if(z instanceof DialogueZone && ((DialogueZone)z).isAuto() && z.contains(new Point(Player.get().getX(), Player.get().getY()))) {
                HUD.getInstance().getDialogueWindow().setVisible(true);
                ((DialogueZone)z).setActive(true);
                ((DialogueZone) z).setAuto(false);
            }
        }
    }
}
