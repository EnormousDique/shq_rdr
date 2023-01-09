package ru.muwa.shq.engine.controls;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.entities.gameObjects.Container;
import ru.muwa.shq.entities.gameObjects.creatures.npc.NPC;

import java.awt.geom.Point2D;

/**
 * Класс, отвечающий за взаимодейстиве игрока с usable и container объектами
 */
public class Interactor
{

    public static void interact()
    {
        int x = MouseListener.getInstance().getX();
        int y = MouseListener.getInstance().getY();
        System.out.println( "мышь х: " + x + " у: " + y);
        for(Container c : Engine.getCurrentLevel().getContainers()) if(c.getSolidBox().inside(x,y))
            System.out.println("Курсор находится на обекте контейнер");
        for(NPC n : Engine.getCurrentLevel().getNPC()) if(n.getSolidBox().contains(x,y))
            System.out.println("Курсор находится на обекте нпц");
    }
}
