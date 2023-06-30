package ru.muwa.shq.engine.listeners;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.hud.MiniGameHUD;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.zakladki.KladYellow;
import ru.muwa.shq.items.zakladki.fakeZakladki.BananaPeel;
import ru.muwa.shq.minigames.PostBoxShq;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.lang.module.InvalidModuleDescriptorException;

/**
 * Класс, отвечающий за прослушку движений курсора мыши.
 */
public class MouseListener implements java.awt.event.MouseMotionListener
{
    private static MouseListener instance;
    /**
     * Констуктор
     */
    private MouseListener(){
        instance = this;
        System.out.println("mouse listener initiated");
    }
    public static MouseListener getInstance()
    {
        if(instance == null) return new MouseListener();
        else return instance;
    }
    /**
     * Поля - координаты курсора мыши.
     */
    public int x,y;

    /**
     * Методы - геттеры.
     * @return - значения полей.
     */
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }



    /**
     * Метод, срабатывающий при событии перетаскивания мышью.
     * @param e - само событие.
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
        x = e.getX();
        //System.out.println("mouse x: " + x);
        y = e.getY();
        //System.out.println("mouse y: " + y);
    }

    /**
     * Метод, срабатывающий при движении мышью.
     * @param e - само событие
     */
    @Override
    public void mouseMoved(MouseEvent e)
    {
        x = e.getX();
        //System.out.println("mouse x: " + x);
        y = e.getY();
        //System.out.println("mouse y: " + y);
        if (MiniGameHUD.currentMiniGame != null && MiniGameHUD.currentMiniGame instanceof PostBoxShq) playPostBoxShq();

    }

    private static void playPostBoxShq()
    {
        PostBoxShq shq = ((PostBoxShq)MiniGameHUD.currentMiniGame);
        for (int i = 0; i < ((PostBoxShq)MiniGameHUD.currentMiniGame).stuff.size(); i++) {
            Rectangle r = shq.stuff.get(i);
            if(r.contains(new Point(getInstance().x, getInstance().y)))
            {
                Inventory.getInstance().addItem(shq.container.getItems().get(shq.stuff.indexOf(r)));
               shq.container.getItems().remove(shq.container.getItems().get(shq.stuff.indexOf(r)));
               shq.stuff.remove(r);
            }

        }
        if(shq.stuff.size()<1||shq.container.getItems().size()<1)
        {
            MiniGameHUD.initPostbox=false;
        }
    }
}
