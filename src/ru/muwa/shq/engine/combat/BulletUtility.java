package ru.muwa.shq.engine.combat;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.guns.Bullet;
import ru.muwa.shq.items.guns.EnemyBullet;
import ru.muwa.shq.objects.GameObject;


public class BulletUtility
{

    public static void work()
    {
        for(int i = 0; i< Engine.getCurrentLevel().getObjects().size(); i++)
        {
            GameObject o = Engine.getCurrentLevel().getObjects().get(i);
             if(o instanceof Bullet) moveBullet((Bullet) o);
             if(o instanceof EnemyBullet) moveBullet((EnemyBullet) o);
        }
    }

    private static void moveBullet(Bullet b)
    {
        b.setTime(b.getTime() + 0.1);
        b.setX((int)(b.getInitialX() + b.getxVelocity() * b.getTime()));
        b.setY((int)(b.getInitialY() + b.getyVelocity() * b.getTime()));
        if(b.getTime() > 30) Engine.getCurrentLevel().getObjects().remove(b);
    }


}
