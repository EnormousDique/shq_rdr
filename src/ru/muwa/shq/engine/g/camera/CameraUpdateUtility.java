package ru.muwa.shq.engine.g.camera;

import ru.muwa.shq.engine.combat.CombatUtility;
import ru.muwa.shq.engine.utilities.EffectUtility;
import ru.muwa.shq.player.Player;

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;

public class CameraUpdateUtility {
    private static CameraUpdateUtility instance;
    public static boolean isShaking;
    public static CameraUpdateUtility getInstance() {
        if(instance == null) return new CameraUpdateUtility();else return instance;
    }
    private CameraUpdateUtility (){
        instance = this;
    }

    static boolean drunkDirection,anotherDrunkDirection;
    public void work()
    {
        if(EffectUtility.isDrunk)
        {
            if(drunkDirection) Camera.getInstance().setY(Camera.getInstance().getY()-3);
            else Camera.getInstance().setY(Camera.getInstance().getY()+3);

            if(Math.random()<0.95){
                return;
            }else drunkDirection = !drunkDirection;

        }
        Camera.getInstance().setX((int)Player.get().getSolidBox().getCenterX()-(SCREEN_WIDTH/2));
        Camera.getInstance().setY((int)Player.get().getSolidBox().getCenterY()-(SCREEN_HEIGHT/2));
        if(isShaking)
        {
            Camera.getInstance().setX(Camera.getInstance().getX() + (int)((Math.random() * (Player.get().getHighMeter()*10))/12));
            Camera.getInstance().setY(Camera.getInstance().getY() + (int)((Math.random() * (Player.get().getHighMeter()*10))/12));

        }

    }

}
