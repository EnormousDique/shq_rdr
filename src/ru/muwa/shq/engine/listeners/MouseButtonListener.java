package ru.muwa.shq.engine.listeners;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;
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

    public MouseEvent event;
    private MouseButtonListener(){instance=this; keys = new boolean[2];} // Массив кнопок.
    // Каждой кнопке соответствует свой индекс массива.
    // Если кнопка нажата, в массиве под этим индексом хранится true
    public static MouseButtonListener getInstance()
    {
        if(instance!=null)return instance;
        else return new MouseButtonListener();
    }
    public   boolean[] keys;
    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

        event = e;
        System.out.println("mouse clicked. x: " + e.getX()/*+ Camera.getInstance().getX())*/ + " y : " + e.getY()/*+Camera.getInstance().getY()*/);
        switch (e.getButton()) {
            case 1:
                keys[0] = true;
                break;
            case 3:
                keys[1] = true;
                break;
        }

        //Сперва определяем, находится  ли игрок в режиме выкупа. Это влияет на дальнейшую логику.
        boolean isPlayerInBuyoutMode = false;
        BuyoutZone bz = null;
        for(GameZone z : Engine.getCurrentLevel().getZones())
            if(z instanceof BuyoutZone && ((BuyoutZone)z).isActive)
            {
                bz = (BuyoutZone) z; isPlayerInBuyoutMode=true;
                System.out.println("Player clicked in buyout mode");
            }
        //Определили

        // Код, отвечающий за логику, при нажатии на иконку в инвентаре
        if (e.getSource() instanceof ItemPanel && !isPlayerInBuyoutMode) {
            ((ItemPanel) e.getSource()).getItem().pick();
            HUD.getInstance().getItemWindow().updateUI();
        }
        //Код, отвечающий за логику, при нажатии на иконку в контейнере
        if (e.getSource() instanceof ContainerPanel) {
                ((ContainerPanel) e.getSource()).getItem().get();
                HUD.getInstance().getContainerWindow().updateUI();

        }
        // Код, отвечающий за логику, при нажатии на иконку в инвентаре в режиме выкупа
        if(e.getSource() instanceof  ItemPanel && isPlayerInBuyoutMode && bz != null)
        {
            System.out.println("Player clicked on item panel");
            Item pressedItem = ((ItemPanel)e.getSource()).getItem(); //Вещь, на которую нажали
            boolean isPressedOnItemInInventory = Inventory.getInstance().getItems().contains(pressedItem); //Определяем нажал игрок на свою вещь или на вещь в выкупе.

            if(isPressedOnItemInInventory)
            {
                Item copy = null;
                if(pressedItem.isStackable()) copy = pressedItem.copy();

                if(pressedItem.amount <=1) Inventory.getInstance().getItems().remove(pressedItem);
                else pressedItem.amount -=1;

                bz.buyout.goods.add(copy==null?pressedItem:copy);
            }else {
                Inventory.getInstance().addItem(pressedItem);
                bz.buyout.goods.remove(pressedItem);
            }
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
