package ru.muwa.shq.engine.combat;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.guns.Bullet;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.zones.GameZone;

import java.util.LinkedList;

public class BulletUtility
{
    private LinkedList<Bullet> bullets = new LinkedList<>();
    private static BulletUtility instance;
    private BulletUtility()
    {
        instance = this;

    }
    public static BulletUtility getInstance()
    {
        if(instance == null) return new BulletUtility(); else return instance;
    }

    public void work()
    {
        for(GameObject o : Engine.getCurrentLevel().getObjects())
        {
           if(o instanceof Bullet ) moveBullet((Bullet) o);
        }
    }

    public LinkedList<Bullet> getBullets() {
        return bullets;
    }

    private void moveBullet(Bullet b)
    {
        b.setTime(b.getTime() + 0.1);
        b.setX((int)(b.getInitialX() + b.getxVelocity() * b.getTime()));
        b.setY((int)(b.getInitialY() + b.getyVelocity() * b.getTime()));
    }

    public void addBullet(Bullet b)
    {
        bullets.add(b);
    }
}
