package ru.muwa.shq.engine.g.camera;

import ru.muwa.shq.player.Player;

import static ru.muwa.shq.engine.g.camera.Camera.SCROLL_SPEED;

public class CameraUpdater
{
    private static CameraUpdater instance;
    private CameraUpdater(){instance = this;}
    public static CameraUpdater getInstance(){if(instance!=null) return instance; else return new CameraUpdater();}

    void scroll()
    {
        if(Player.get().getX() - Camera.getInstance().x < 30)
        {
            Camera.getInstance().moveLeft();
        }
        if(Player.get().getX() - Camera.getInstance().x > 750)
        {
            Camera.getInstance().moveRight();
        }
    }
}
