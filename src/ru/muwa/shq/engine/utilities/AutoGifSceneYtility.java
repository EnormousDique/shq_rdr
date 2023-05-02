package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.GameZone;
import ru.muwa.shq.zones.GifSceneZone;

import java.awt.*;

public class AutoGifSceneYtility {
    public static void checkAutoGifSceneYtilityZone (){
        for(GameZone z: Engine.getCurrentLevel().getZones()){
            if(z instanceof GifSceneZone && ((GifSceneZone)z).isGifSceneAuto() && z.contains(new Point(Player.get().getX(),Player.get().getY()))){
                HUD.getInstance().getGifScenesWindow().setVisible(true);
                ((GifSceneZone)z).setGifSceneActive(true);
                ((GifSceneZone)z).setGifSceneAuto(false);
            }
        }
    }
}
