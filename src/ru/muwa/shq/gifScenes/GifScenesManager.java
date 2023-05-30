package ru.muwa.shq.gifScenes;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.zones.GameZone;
import ru.muwa.shq.zones.GifSceneZone;

import javax.swing.*;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class GifScenesManager {
    public JButton button;
    public static GifScenesManager instance;
    public static GifScenesManager getInstance() {
        if(instance == null) return new GifScenesManager();
       else return instance;
    }
    private GifScenesManager(){instance = this;}

    public void work(){
        for (GameZone zone: Engine.getCurrentLevel().getZones()){
            if (zone instanceof GifSceneZone && (((GifSceneZone) zone).isGifSceneActive())){
                Engine.pause=true;
                JPanel gifwindow = HUD.getInstance().getGifScenesWindow();
                //   ImageIcon imagegif = new ImageIcon(IMG_PATH+"fantasticaGif\\FANTASTICA.gif");

              //  JLabel giflable = new JLabel(imagegif);

             //   gifwindow.add(giflable);


            }

        }
    }


}
