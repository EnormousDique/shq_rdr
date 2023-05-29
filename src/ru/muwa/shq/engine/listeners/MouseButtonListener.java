package ru.muwa.shq.engine.listeners;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Picktogram;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.utilities.InventoryManager;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.ItemPanel;
import ru.muwa.shq.items.ItemPhysicalAppearance;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.objects.containers.ContainerPanel;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.controls.Grabber;
import ru.muwa.shq.zones.BuyoutZone;
import ru.muwa.shq.zones.GameZone;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Класс, отвечающий за прослушку кнопок мыши.
 */
public class MouseButtonListener implements MouseInputListener {

    public MouseEvent highlight;
    private static MouseButtonListener instance;

    private MouseButtonListener(){instance=this; keys = new boolean[2];} // Массив кнопок.
    // Каждой кнопке соответствует свой индекс массива.
    // Если кнопка нажата, в массиве под этим индексом хранится true
    public static MouseButtonListener getInstance()
    {
        if(instance!=null)return instance;
        else return new MouseButtonListener();
    }
    public boolean[] keys;
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {


        System.out.println("mouse clicked. x: " + e.getX()/*+ Camera.getInstance().getX())*/ + " y : " + e.getY()/*+Camera.getInstance().getY()*/);
        switch (e.getButton()) {
            case 1:
                keys[0] = true;
                break;
            case 3:
                keys[1] = true;
                break;
        }
         //Проверяем по инвентарю ли клик.
                 // И то что нажата ЛКМ
        //И что окно инвентаря открыто.
        if(InventoryManager.itemWindowPicks.stream().anyMatch(p->p.contains(new Point(e.getX(),e.getY())))
                && keys[0] && InventoryManager.isItemWindowVisible)
        {
            //Если клик был по инвентарю.
            Picktogram pic =
                    InventoryManager.itemWindowPicks.stream()
                            .filter(p->p.contains(new Point(e.getX(),e.getY())))
                            .findFirst().get(); //Думаю ничего не будет, мы же знаем, что кликнули по инвентарю

            pic.item.pick();
        }
        //Проверяем по окну ли контейнера клик.
        // И то что нажата ЛКМ
        //И что окно контейнер открыт.
        if(InventoryManager.containerWindowPicks.stream().anyMatch(p->p.contains(new Point(e.getX(),e.getY())))
                && keys[0] && Engine.getCurrentLevel().getContainers().stream().anyMatch(c -> c.isInUse()))
        {
            //Если клик был по контейнеру.
            Picktogram pic =
                    InventoryManager.containerWindowPicks.stream()
                            .filter(p->p.contains(new Point(e.getX(),e.getY())))
                            .findFirst().get(); //Думаю ничего не будет, мы же знаем, что кликнули по инвентарю

            pic.item.get();
        }



        //После вызова необходимого кода отключаем нажатие во избежание "прокликивания".
        switch (e.getButton())
        {
            case 1:
                keys[0] = false;

                break;
            case 3:
                keys[1] = false;
                break;
        }



    }

    @Override
    public void mouseReleased(MouseEvent e) {

        switch (e.getButton())
        {
            case 1:
                keys[0] = false;

                break;
            case 3:
                keys[1] = false;
                break;
        }
        // System.out.println("mouse released " + e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    highlight = e;
    if(e.getSource() instanceof ItemPanel)
    {
       Renderer.getInstance().drawDescription(e);
    }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    public boolean[] getKeys(){return keys;}


}
