package ru.muwa.shq.minigames.padiklock;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.listeners.ButtonListener;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PadikLockButtonListener extends ButtonListener {
    private static PadikLockButtonListener instanse;
    private PadikLock pl;
    private PadikLockButtonListener(PadikLock pl)
    {
        super();
        this.pl  = pl;
    }
    public static PadikLockButtonListener getInstance(PadikLock pl)
    {
        if (instanse==null)return new PadikLockButtonListener(pl);
        else
        {
            instanse.pl = pl;
            return instanse;
        }

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {   //  Метод запускается, если была нажата одна из кнопок.
        System.out.println("padik button listener. button pressed: " + ((JButton)e.getSource()).getText());
        if(((JButton)e.getSource()).getText().equals("x"))
        {
            Engine.pause=false;
            pl.forceExit();
            HUD.getInstance().getActionWindow().setVisible(false);
            HUD.getInstance().clearActionWindow();
        }
        else if(((JButton)e.getSource()).getText().equals("c"))
        {
            pl.label.setText("");
        }
        else if(pl.buttons.contains(e.getSource()))
        {
            pl.label.setText(pl.label.getText()+((JButton)e.getSource()).getText());
        }
    }
}
