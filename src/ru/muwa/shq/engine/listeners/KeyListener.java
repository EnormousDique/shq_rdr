package ru.muwa.shq.engine.listeners;

import java.awt.event.KeyEvent;

public class KeyListener implements java.awt.event.KeyListener
{
    /**
     * KEY CODES:
     * W - 87
     * A - 65
     * S - 83
     * D - 68
     * SPACE BAR - 32
     */
    private boolean[] keys;
    public final int
            W = 0,
            A = 1,
            S = 2,
            D = 3,
            SPACE = 4,
            E = 5;
    private static KeyListener instance;

    private KeyListener()
    {
        instance = this;
        keys = new boolean[6];
    }

    public static KeyListener getInstance()
    {
        if (instance == null) return new KeyListener();
        else return instance;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
       // System.out.println(e.getKeyCode());

        switch (e.getKeyCode())
        {
            case 32: // Пробел
                keys[4] = true;
                break;
            case 87: // W
                keys[0] = true;
                break;
            case 65: // A
                keys[1] = true;
            case 83: // S
                keys[2] = true;
                break;
            case 68: // D
                keys[3] = true;
                break;
            case 69: // E
                keys[5] = true;
                break;
        }

        //System.out.println("key pressed: " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case 32: // Пробел
                keys[4] = false;
                break;
            case 87: // W
                keys[0] = false;
                break;
            case 65: // A
                keys[1] = false;
            case 83: // S
                keys[2] = false;
                break;
            case 68: // D
                keys[3] = false;
                break;
        }
    }
    public boolean[] getKeys()
    {
        return keys;
    }
}
