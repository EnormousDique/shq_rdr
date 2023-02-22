package ru.muwa.shq.engine.listeners;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.ItemPhysicalAppearance;
import ru.muwa.shq.player.controls.Grabber;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

/**
 * Класс, отвечающий за прослушку кнопок мыши.
 */
public class MouseButtonListener implements MouseInputListener {

    private static MouseButtonListener instance;
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
        System.out.println("mouse clicked. x: " + e.getX() + " y : " + e.getY());
        switch (e.getButton())
        {
            case 1:
                keys[0] = true;
                break;
            case 3:
                keys[1] = true;
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
        System.out.println("mouse released " + e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

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
