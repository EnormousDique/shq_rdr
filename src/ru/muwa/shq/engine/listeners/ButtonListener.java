package ru.muwa.shq.engine.listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Класс, являющийся прародителем для всех классов, осуществляющих прослушку нажатий кнопок графического интерфейса.
 */
public class ButtonListener implements ActionListener
{
    private static ButtonListener instance;
    protected ButtonListener(){ instance = this;}
    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
    public static ButtonListener getInstance()
    {
        if (instance==null) return new ButtonListener();
        else return instance;
    }
}
