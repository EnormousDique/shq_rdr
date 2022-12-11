package ru.muwa.shq.engine.launcher;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.listeners.ButtonListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class LauncherButtonListener  extends ButtonListener
{
    LinkedList<JButton> buttons;
    private static LauncherButtonListener instance;
    public LauncherButtonListener(LinkedList<JButton> buttons)
    {
        super();
        this.buttons = buttons;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(buttons.get(Launcher.START))) Engine.gameOn();
        if(e.getSource().equals(buttons.get(Launcher.SET))) System.out.println("settings");;
        if(e.getSource().equals(buttons.get(Launcher.EDIT))) System.out.println("editor");;
        if(e.getSource().equals(buttons.get(Launcher.EXIT))) Launcher.quit();
        e=null;
    }
}
