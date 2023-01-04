package ru.muwa.shq.engine.p.gravity;

import ru.muwa.shq.entities.gameObjects.GameObject;
import ru.muwa.shq.entities.gameObjects.creatures.Creature;
import ru.muwa.shq.entities.gameObjects.creatures.player.Player;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Класс, отвечающий за силу гравитации, применяемую к игровым объектам.
 */
public class GravityChecker
{
/*
    Gravity Checker проверяет должен ли объект падать, и если так, то двигает объект вниз
 */

    private static GravityChecker instance;

    private GravityChecker()
    {

    }
    public static GravityChecker getInstance()
    {
        if (instance == null) return new GravityChecker();
        else return instance;
    }
    public void checkGravity(GameObject o, LinkedList<GameObject> objects)
    {
        checkForFalling(o,objects);
        fall(o);
    }
    public void checkGravity(Creature c, LinkedList<GameObject> objects)
    {
        //System.out.println("player falling: " + Player.get().isFalling());
        fly(c);
        checkForFalling(c,objects);
        fall(c);
    }

    private void checkForFalling(GameObject o, LinkedList<GameObject> objects)
    {
        for(GameObject ob : objects) {
            if(o.getOnFeetBox().intersects(ob.getSolidBox()))
            {
                //System.out.println(ob);
                o.setIsFalling(false);
            }
        }
        if(objects.stream()
                .filter(obj -> o.getOnFeetBox().intersects(obj.getSolidBox()))
                .collect(Collectors.toList())
                .size()==0)
            o.setIsFalling(true);
    }

    private void fall(GameObject o)
    { //TODO: Добавить проверку на isFalling
        if(o != null && o.isFalling())
        {
            o.setY((o.getY()+o.getVelocity()));
        }
    }

    private void fly(Creature c)
    {
        if(c.getJumpAx() > 0)
        {
            System.out.println("jump ax for " + c + " is: " + c.getJumpAx() );
            c.setY(c.getY()-c.getJumpAx());
            c.setJumpAx(c.getJumpAx()-1);
        }
        if(c.getJumpAx() <=0) c.setJumpAx(0);
    }

}
