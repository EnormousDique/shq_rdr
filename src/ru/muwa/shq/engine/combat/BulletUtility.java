package ru.muwa.shq.engine.combat;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.guns.Bullet;
import ru.muwa.shq.items.guns.EnemyBullet;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;
import ru.muwa.shq.zones.GameZone;

import java.util.LinkedList;

public class BulletUtility
{

    public static void work()
    {
        for(GameObject o : Engine.getCurrentLevel().getObjects())
        {
             if(o instanceof Bullet) moveBullet((Bullet) o);
             if(o instanceof EnemyBullet) moveBullet((EnemyBullet) o);
        }
    }

    private static void moveBullet(Bullet b)
    {
        b.setTime(b.getTime() + 0.1);
        b.setX((int)(b.getInitialX() + b.getxVelocity() * b.getTime()));
        b.setY((int)(b.getInitialY() + b.getyVelocity() * b.getTime()));
    }


}
