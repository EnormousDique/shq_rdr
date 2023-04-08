package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.GameZone;

import java.awt.*;



public class GameZoneUtility
{
    public static void work() {
    for(GameZone z:Engine.getCurrentLevel().getZones()){
        if(z instanceof EnterZone ) {
            if(((EnterZone)z).isAuto() && z.contains(new Point(Player.get().getX(), Player.get().getY()))) {
            Engine.switchLevel((EnterZone) z);
        }}

    }
    }
}
