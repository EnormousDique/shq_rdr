package ru.muwa.shq.engine.listeners;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

public class MouseButtonListener implements MouseInputListener {

    private static MouseButtonListener instance;
    private MouseButtonListener(){instance=this;}
    public static MouseButtonListener getInstance(){if(instance!=null)return instance; else return new MouseButtonListener();}
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse clicked. x: " + e.getX() + " y : " + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

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
}
