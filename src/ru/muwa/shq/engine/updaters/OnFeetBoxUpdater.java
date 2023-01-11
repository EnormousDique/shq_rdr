package ru.muwa.shq.engine.updaters;

import ru.muwa.shq.objects.GameObject;

public class OnFeetBoxUpdater
{

    private static OnFeetBoxUpdater instance;

    private OnFeetBoxUpdater()
    {
        instance = this;
    }
    public static OnFeetBoxUpdater getInstance() {if(instance!=null)return instance; else return new OnFeetBoxUpdater();}

    public void updateOnFeetBox(GameObject o)
    {
        o.getOnFeetBox().setBounds(o.getX() +10,o.getY()+o.getHeight()+1,o.getWidth()-20,GameObject.ON_FEET_BOX_HEIGHT);
    }
}
