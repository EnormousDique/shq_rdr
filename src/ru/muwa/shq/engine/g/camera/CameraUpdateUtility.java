package ru.muwa.shq.engine.g.camera;

import ru.muwa.shq.engine.combat.CombatUtility;
import ru.muwa.shq.player.Player;

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;

public class CameraUpdateUtility {
    private static CameraUpdateUtility instance;
    public static CameraUpdateUtility getInstance() {
        if(instance == null) return new CameraUpdateUtility();else return instance;
    }
    private CameraUpdateUtility (){
        instance = this;
    }

    public void work()
    {
        Camera.getInstance().setX((int)Player.get().getSolidBox().getCenterX()-(SCREEN_WIDTH/2));
        Camera.getInstance().setY((int)Player.get().getSolidBox().getCenterY()-(SCREEN_HEIGHT/2));
    }
}
