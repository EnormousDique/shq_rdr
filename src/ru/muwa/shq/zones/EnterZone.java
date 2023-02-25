package ru.muwa.shq.zones;

import ru.muwa.shq.levels.Level;

import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.security.PublicKey;

/**
 * Класс, представляющий из себя зону перехода на другую локацию.
 */
public class EnterZone extends GameZone
{
    private Level level;

    public Level getLevel()
    {
        return level;
    }

    public EnterZone(int x, int y, int width, int height, Level level)
    {// Конструктор
        super(x,y,width,height);
        this.level = level;
        System.out.println("enter zone created, level reference is " + level);
    }

}
