package ru.muwa.shq.entities.gameObjects.creatures;
import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.entities.gameObjects.GameObject;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
/**
 * Класс, являющийся прародителем всех игровых существ.
 */
public abstract class Creature extends GameObject
{
    protected RayCaster rayCaster;
    public void setRayCaster(RayCaster rayCaster)
    {
        this.rayCaster = rayCaster;
    }
    public void setRayCasterBorders(LinkedList<Line2D.Float> borders)
    {
        this.rayCaster.setBorders(borders);
    }
    public RayCaster getRayCaster()
    {
        return this.rayCaster;
    }
    /**
     *
     * Конструктор
     *
     * @param x - х коррдината
     * @param y - у коррдината
     * @param texture - текстура (файл с изборажением)
     */
    protected Creature(int x, int y, BufferedImage texture)
    {
        super(x, y, texture);
    }
}
