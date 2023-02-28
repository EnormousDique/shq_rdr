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
    private int whereToX, whereToY;

    private boolean isAuto;


    public Level getLevel()
    {
        return level;
    }

    public int getWhereToX() {
        return whereToX;
    }

    public int getWhereToY() {
        return whereToY;
    }

    public EnterZone(int x, int y, int width, int height, Level level, int whereToX, int whereToY,boolean isAuto)
    {// Конструктор
        super(x,y,width,height);
        this.level = level;
        this.whereToX = whereToX;
        this.whereToY = whereToY;
        System.out.println("enter zone created, level reference is " + level);
        this.isAuto =isAuto;

    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setWhereToX(int whereToX) {
        this.whereToX = whereToX;
    }

    public void setWhereToY(int whereToY) {
        this.whereToY = whereToY;
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void setAuto(boolean auto) {
        isAuto = auto;
    }
}
