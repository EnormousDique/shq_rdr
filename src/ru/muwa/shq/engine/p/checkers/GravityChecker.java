package ru.muwa.shq.engine.p.checkers;

import ru.muwa.shq.entities.gameObjects.GameObject;

import static ru.muwa.shq.entities.gameObjects.GameObject.Direction.DOWN;

/**
 * Класс, отвечающий за силу гравитации, применяемую к игровым объектам.
 */
public class GravityChecker
{
    private static GravityChecker instance;

    private GravityChecker()
    {

    }
    public static GravityChecker getInstance()
    {
        if (instance == null) return new GravityChecker();
        else return instance;
    }
    public void checkGravity(GameObject o)
    {
        fall(o);

    }
    private void fall(GameObject o)
    {
        if(o != null && !o.standing())
        {
            o.setDirection(DOWN);
            o.setY((o.getY()+o.getVelocity()));
        }
    }

}
