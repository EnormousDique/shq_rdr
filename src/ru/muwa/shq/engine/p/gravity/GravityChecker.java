package ru.muwa.shq.engine.p.gravity;

import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.Creature;

import java.util.LinkedList;
import java.util.stream.Collectors;

import static ru.muwa.shq.objects.GameObject.Direction.DOWN;

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
        if(!o.isStatic()) {
            checkForFalling(o, objects);
            if (!o.isStatic()) fall(o);
        }
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
    {
        if(o != null && o.isFalling())
        {
            o.setDirection(DOWN);
            o.setY((o.getY()+(int)o.getFallAx()));
            o.setFallAx(o.getFallAx()+0.7);
            //if(o.getFallAx() >= 30) o.setFallAx(30);
            if(o.getFallAx()>= o.getHeight()) o.setFallAx(o.getHeight()/2);
        }
        if(o != null && !o.isFalling()) o.setFallAx(0);
    }

    private void fly(Creature c)
    {
        if(c.getJumpAx() > 0)
        {
           // System.out.println("jump ax for " + c + " is: " + c.getJumpAx());
            c.setY(c.getY()-c.getJumpAx());
            c.setJumpAx(c.getJumpAx()-1);
        }
        if(c.getJumpAx() <=0) c.setJumpAx(0);
    }

}
