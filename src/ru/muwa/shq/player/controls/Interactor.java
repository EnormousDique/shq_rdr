package ru.muwa.shq.player.controls;


import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import java.util.ArrayList;

/**
 * Класс, отвечающий за взаимодейстиве игрока с usable и container объектами
 */
public class Interactor
{

    private static Interactor instance;
    private Interactor(){instance = this;}
    public static Interactor getInstance(){if(instance!=null) return instance; else return new Interactor();}


    public void interact() throws Exception
    {
        int x = MouseListener.getInstance().getX();
        int y = MouseListener.getInstance().getY();

        //Блок обработки нажатий на контейнер

        ArrayList<Container> activeContainers = new ArrayList<>();

        for(Container c : Engine.getCurrentLevel().getContainers()) {

            if ( c.getSolidBox().inside(x + Camera.getInstance().getX(), y + Camera.getInstance().getY())
                    && Player.get().getUseZone().contains(x + Camera.getInstance().getX(), y + Camera.getInstance().getY()))
            {
                activeContainers.add(c);

            }
        }
            if(activeContainers.size() >0) {
                int i = 0;
                for(i = 0; i < activeContainers.size(); i++)
                {
                    if(activeContainers.get(i).getItems().size()>0)
                        break;
                }
                Container c = activeContainers.get( activeContainers.size() > 1? i : 0);  //todo выбрасывает ошибку идекса out of bounds при лутании трупов.
                c.setIsInUse(true);
                Inventory.getInstance().setIsOpened(true);
                Player.get().setIsBusy(true);
               // Player.get().setCurrentObject(c); // ВРОДЕ БЫ ЭТА ШТУКА НЕ НУЖНА
            }
            if(false){}//TODO: добавить возможность открыть несколько окон шмона или переключаться между ними, если activeContainers.size() > 1

               //======================================================

        for(NPC n : Engine.getCurrentLevel().getNPC()) if(n.getSolidBox().contains(x+Camera.getInstance().getX(),y+Camera.getInstance().getY()))
            System.out.println("Курсор находится на обекте нпц");
    }
}
