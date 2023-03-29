package ru.muwa.shq.engine.animations.cutscenes;

import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.engine.Engine;

import java.util.ArrayList;

public abstract class Cutscene
{

    protected ArrayList<Movement> movements;

    public ArrayList<Movement> getMovements()
    {
        return movements;
    }

    public static class Movement
    {
        Creature targetCreature;
        int distX;
        int distY;
        public Movement(Creature targetCreature, int distX, int distY)
        {
            this.targetCreature = targetCreature;
            this.distX = distX;
            this.distY = distY;
        }
    }

    public void playMovement(Movement m)
    {

        Creature c = Engine.getCurrentLevel().getNPC()
                .stream().map(cr->(Creature)cr)
                .filter(cr->cr.equals(m.targetCreature)).findFirst().get();

        if(c != null)
        {

            int xDistance = Math.abs(c.getX()-m.distX);
            int yDistance = Math.abs(c.getY()-m.distY);

            if(xDistance > yDistance)
            {
                if(c.getX() > m.distX) c.moveLeft(); else c.moveRight();
            }
            else
            {
                if(c.getY() > m.distY) c.moveUp(); else c.moveDown();
            }
        }
    }
}
