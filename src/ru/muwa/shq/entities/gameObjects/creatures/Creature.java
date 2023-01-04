package ru.muwa.shq.entities.gameObjects.creatures;
import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.entities.gameObjects.GameObject;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static ru.muwa.shq.entities.gameObjects.GameObject.Direction.*;

/**
 * Класс, являющийся прародителем всех игровых существ.
 */
public abstract class Creature extends GameObject
{
    protected int agility; // Ловкость
    protected int jumpAx; // Ускорение прыжка
    protected int maxJumpAx; // Максимальное ускорение прыжка
    protected int speed; //Скорость



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
    public void jump()
    {
        if(!isFalling)
        {
            direction = UP;
            jumpAx = agility;
            setStanding(false);
        }
    }

    public void moveRight()
    {
        if (direction == LEFT || direction == NONE) direction = RIGHT;
        if(direction == DOWN || direction == DOWN_LEFT) direction = DOWN_RIGHT;
        if(direction == UP || direction == UP_LEFT) direction = UP_RIGHT;
        x += speed;
    }
    public void moveLeft()
    {

        if (direction == RIGHT || direction == NONE) direction = LEFT;
        if(direction == DOWN || direction == DOWN_RIGHT) direction = DOWN_LEFT;
        if(direction == UP || direction == UP_RIGHT) direction = UP_LEFT;
        x -= speed;
    }

    public int getJumpAx()
    {
        return jumpAx;
    }
    public  void setJumpAx(int jumpAx)
    {
        this.jumpAx = jumpAx;
    }
}
