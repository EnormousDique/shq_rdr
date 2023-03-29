package ru.muwa.shq.items.guns;

import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.engine.Engine;

public class EnemyBullet extends Bullet{

    Creature owner;

    public Creature getOwner() {
        return owner;
    }

    public void setOwner(Creature owner) {
        this.owner = owner;
    }

    public EnemyBullet(int x, int y, double angle) {
        super(x, y, angle);

    }
    public static void  enemyShot(int x, int y, double angle, Creature owner){
        x += 50 *  Math.cos(Math.toRadians(angle));
        y += 50 *  Math.sin(Math.toRadians(angle));
        EnemyBullet b = new EnemyBullet(x,y,angle);
        b.setOwner(owner);
        Engine.getCurrentLevel().getObjects().add(b);
    }

}
