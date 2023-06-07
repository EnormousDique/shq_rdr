package ru.muwa.shq.engine.animations.cutscenes;

import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.utilities.SolidBoxUpdater;

import java.awt.*;
import java.util.ArrayList;

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;

public abstract class Cutscene
{

    protected ArrayList<Movement> movements;

    public ArrayList<Movement> getMovements()
    {
        return movements;
    }

    public static class Movement
    {
        String targetCreatureName;
        int distX;
        int distY;
        public Movement(String targetCreatureName, int distX, int distY)
        {
            this.targetCreatureName = targetCreatureName;
            this.distX = distX;
            this.distY = distY;
        }
    }

    public void playMovement(Movement m)
    {
        Creature c = null;
        try {
            c = Engine.getCurrentLevel().getNPC()
                    .stream().map(cr->(Creature)cr)
                    .filter(cr-> cr.getName()!= null && cr.getName().equals(m.targetCreatureName)).findFirst().get();
            System.out.println("found  creature for script " + c);
        } catch (Exception e)
        {
            System.out.println("СЦЕНКА НЕ МОЖЕТ ПРОИГРАТЬСЯ КОРРЕКТНО ПРОТОМУ ЧТО НЕ НАЙДЕН ОДИН ИЗ АКТЁРОВ");
        }


        if(c != null)
        {
            double drawInterval = 1_000_000_000/16;
            double delta=0;
            long lastTime = System.nanoTime();
            long currTime;

            while (!c.getSolidBox().contains(new Rectangle(m.distX-10,m.distY-10,20,20))) {


                int xDistance = (int) Math.abs(c.getSolidBox().getCenterX() - m.distX);
                int yDistance = (int) Math.abs(c.getSolidBox().getCenterY() - m.distY);

                currTime = System.nanoTime();
                delta += (currTime - lastTime) / drawInterval;
                lastTime = currTime;
                if(delta >= 1) {
                    if (xDistance > yDistance) {
                        if (c.getSolidBox().getCenterX() >= m.distX) c.moveLeft();
                        else c.moveRight();

                    } else {
                        if (c.getSolidBox().getCenterY() >= m.distY) c.moveUp();
                        else c.moveDown();
                    }
                    Camera.getInstance().setX(c.getX()- (SCREEN_WIDTH/2));
                    Camera.getInstance().setY(c.getY()- (SCREEN_HEIGHT/2));

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    SolidBoxUpdater.updateSolidBox(c);

                }

            }
        }
    }
}
