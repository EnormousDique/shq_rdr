package ru.muwa.shq.zones;

import java.awt.*;

/**
 * Класс, являющийся прародителем всех игровых зон.
 */
public abstract class GameZone extends Rectangle
{

    protected GameZone(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

}
