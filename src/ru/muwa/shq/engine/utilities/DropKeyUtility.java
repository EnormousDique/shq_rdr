package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.listeners.MouseButtonListener;

public class DropKeyUtility
{
    static private DropKeyUtility instance;
    private DropKeyUtility(){instance = this;}
    public static DropKeyUtility getInstance(){if(instance==null) return new DropKeyUtility(); else return instance;}
    public void work()
    {
        for(Boolean b : KeyListener.getInstance().getKeys())
        {
            b = false;
        }
        for(Boolean b : MouseButtonListener.getInstance().getKeys())
        {
            b = false;
        }

    }
}
