package ru.muwa.shq.engine.listeners;

import java.awt.event.KeyEvent;

/**
 *  Класс, отвечающий за прослушку клавиатуры.
 */
public class KeyListener implements java.awt.event.KeyListener
{
    /**
     * KEY CODES:
     * W - 87
     * A - 65
     * S - 83
     * D - 68
     * SPACE BAR - 32
     * E - 69
     * I - 73
     * Q - 81
     * W - 87
     * S - 83
     * ENTER - 10
     */
    private boolean[] keys; // Массив кнопок. Каждой кнопке соответствует свой индекс массива.
    // Если кнопка нажата, в массиве под этим индексом хранится true
    public final int
            W = 0,
            A = 1,
            S = 2,
            D = 3,
            SPACE = 4,
            E = 5,
            I = 6,
            Q = 7,
            ENTER = 8;
    private static KeyListener instance;

    private KeyListener()
    {
        instance = this;
        keys = new boolean[9];
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
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode())
        {
            case 32: // Пробел
                keys[SPACE] = true;
                break;
            case 87: // W
                keys[W] = true;
                break;
            case 65: // A
                keys[A] = true;
                break;
            case 83: // S
                keys[S] = true;
                break;
            case 68: // D
                keys[D] = true;
                break;
            case 69: // E
                keys[E] = true;
                break;
            case 73: // I
                keys[I] = true;
                break;
            case 81: // Q
                keys[Q] = true;
                break;
            case 10: // ENTER
                keys[ENTER] = true;
                break;


        }
        //System.out.println("key pressed: " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
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
            case 32: // Пробел
                keys[4] = false;
                break;
            case 69: // E
                keys[5] = false;
                break;
            case 73: // I
                keys[6] = false;
                break;
            case 81: // Q
                keys[7] = false;
                break;
            case 10: // ENTER
                keys[ENTER] = false;
                break;

        }
    }
    public boolean[] getKeys()
    {
        return keys;
    }
}
