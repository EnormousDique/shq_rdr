package ru.muwa.shq.player.controls;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.objects.Container;
import ru.muwa.shq.creatures.npc.NPC;

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
        System.out.println( "мышь с учетом камеры х: " + (x + Camera.getInstance().getX()) + " у: " + (y+ Camera.getInstance().getY()));
        for(Container c : Engine.getCurrentLevel().getContainers()) if(c.getSolidBox().inside(x,y))
            System.out.println("Курсор находится на обекте контейнер");
        for(NPC n : Engine.getCurrentLevel().getNPC()) if(n.getSolidBox().contains(x,y))
            System.out.println("Курсор находится на обекте нпц");
    }
}
