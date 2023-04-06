package ru.muwa.shq.engine.launcher;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.listeners.ButtonListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

/**
 * Класс, реализующий логику кнопок лаунчера игры. Является прослушкой кнопок.
 */
public class LauncherButtonListener extends ButtonListener
{
    public void addButton(JButton save){
        buttons.add(save);
    }


    LinkedList<JButton> buttons; // Список кнопок.
    public LauncherButtonListener(LinkedList<JButton> buttons ) //Конструктор
    {
        super();
        this.buttons = buttons;

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {   //  Метод запускается, если была нажата одна из кнопок.
        if(e.getSource().equals(buttons.get(Launcher.START))) Engine.gameOn();
        if(e.getSource().equals(buttons.get(Launcher.SET)))  Launcher.showScreenSettings();
        if(e.getSource().equals(buttons.get(Launcher.EDIT))) System.out.println("editor");
        if(e.getSource().equals(buttons.get(Launcher.EXIT))) Launcher.quit();
        if(((JButton)e.getSource()).getText().equals("ПРименить настройки")){Launcher.hideScreenSettings();Launcher.showFrame();}

        e=null;
    }
    public void CheckButtonS(ActionEvent a){
        if(((JRadioButton)a.getSource()).getText().equals("800x600"))

        {
            System.out.println("выбраноо 800на 600");
        }

    }
}
