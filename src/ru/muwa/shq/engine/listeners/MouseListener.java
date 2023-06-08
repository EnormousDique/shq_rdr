package ru.muwa.shq.engine.listeners;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.g.hud.MiniGameHUD;
import ru.muwa.shq.engine.utilities.InventoryManager;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.minigames.PostBoxShq;
import ru.muwa.shq.player.Player;

import java.awt.*;
import java.awt.event.MouseEvent;
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
        for (int i = 0; i < ((PostBoxShq)MiniGameHUD.currentMiniGame).obstacles.size(); i++) {
            PostBoxShq shq = ((PostBoxShq)MiniGameHUD.currentMiniGame);
            Rectangle r = shq.obstacles.get(i);
            if(r.contains(new Point(getInstance().x, getInstance().y)))
            {
                try {
                    Robot robot = new Robot();
                    int xoff = GameScreen.getInstance().getX();
                    int yoff = GameScreen.getInstance().getY();
                    robot.mouseMove(shq.startX+xoff+MiniGameHUD.x, shq.startY+yoff+MiniGameHUD.y);
                }catch (Exception e){Renderer.addMessage("ты не должен это видеть");}
            }
            if(shq.destination.contains(new Point(getInstance().x, getInstance().y)))
            {
                shq.container.setIsInUse(true);
                Player.get().setIsBusy(true);
                MiniGameHUD.currentMiniGame=null;
                Engine.pause=false;
            }

        }
    }
}
