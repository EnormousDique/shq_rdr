package ru.muwa.shq.player.controls;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Player;

import javax.swing.text.html.HTMLDocument;

/**
 * Класс, отвечающий за взаимодейстиве игрока с usable и container объектами
 */
public class Interactor
{

    private static Interactor instance;
    private Interactor(){instance = this;}
    public static Interactor getInstance(){if(instance!=null) return instance; else return new Interactor();}


    public void interact()
    {
        int x = MouseListener.getInstance().getX();
        int y = MouseListener.getInstance().getY();
        System.out.println( "мышь х: " + x + " у: " + y);
        System.out.println( "мышь с учетом камеры х: " + (x + Camera.getInstance().getX()) + " у: " + (y+ Camera.getInstance().getY()));
        for(Container c : Engine.getCurrentLevel().getContainers()) if(c.getSolidBox().inside(x+Camera.getInstance().getX(),y+Camera.getInstance().getY())) {
            System.out.println("Курсор находится на обекте контейнер");
            c.setIsInUse(true);
            Player.get().setIsBusy(true);
            Player.get().setCurrentObject(c);

        }
        for(NPC n : Engine.getCurrentLevel().getNPC()) if(n.getSolidBox().contains(x+Camera.getInstance().getX(),y+Camera.getInstance().getY()))
            System.out.println("Курсор находится на обекте нпц");
    }
}
