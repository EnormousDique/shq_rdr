package ru.muwa.shq.player.controls;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.items.ItemPhysicalAppearance;

public class Grabber
{
    private static Grabber instance;
    private Grabber(){instance = this;}
    public static Grabber getInstance(){if(instance!=null) return instance; else return new Grabber();}

    public void grab()
    {
        //System.out.println("mouse keys[0]: " + MouseButtonListener.getInstance().getKeys()[0] );
        //System.out.println("mouse keys[1]: " + MouseButtonListener.getInstance().getKeys()[1] );
        if(MouseButtonListener.getInstance().getKeys()[0])
        {
            for(ItemPhysicalAppearance a : Engine.getCurrentLevel().getIcons())
                if(a.getBox().contains(MouseListener.getInstance().getX() + Camera.getInstance().getX(),
                        MouseListener.getInstance().getY() + Camera.getInstance().getY()))
                {
                    a.setIsGrabbed(true);
                }
        }
        if(MouseButtonListener.getInstance().getKeys()[1])
        {

        }
        for(ItemPhysicalAppearance a : Engine.getCurrentLevel().getIcons())
        {
            if(a.isGrabbed())
            {
                System.out.println("item grabbed");
                a.setX(MouseListener.getInstance().getX());
                a.setY(MouseListener.getInstance().getY());
            }
        }
    }
}
