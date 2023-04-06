package ru.muwa.shq.engine.launcher;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.listeners.ButtonListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

/**
 * Класс, реализующий логику кнопок лаунчера игры. Является прослушкой кнопок.
 */
public class RadioButtonListener extends ButtonListener
{



    static LinkedList<JRadioButton> radioButtons; // Список кнопок.
    public  RadioButtonListener(LinkedList<JRadioButton> radioButtons ) //Конструктор
    {
        super();
        this.radioButtons = radioButtons;

    }
    @Override
    public  void actionPerformed(ActionEvent e)
    {   //  Метод запускается, если была нажата одна из кнопок.
        if (e.getSource().equals(radioButtons.get(Launcher.ScreenRes480)))  {GameScreen.SCREEN_WIDTH = 800;GameScreen.SCREEN_HEIGHT = 600;}
        if (e.getSource().equals(radioButtons.get(Launcher.ScreenRes720))) {GameScreen.SCREEN_WIDTH= 1280;GameScreen.SCREEN_HEIGHT = 720;}
        if (e.getSource().equals(radioButtons.get(Launcher.ScreenRes1080))) {GameScreen.SCREEN_WIDTH = 1920;GameScreen.SCREEN_HEIGHT = 1080;}



        e=null;
    }


}

