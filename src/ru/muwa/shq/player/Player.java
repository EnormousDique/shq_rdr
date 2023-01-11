package ru.muwa.shq.player;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.objects.Usable;
import ru.muwa.shq.objects.containers.Container;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
/**
 * Класс игрока.
 */
public class Player extends Creature
{
    private static Player instance;
    public static Player get()
    {
        if (instance == null)
        {
        BufferedImage img;
        try {
            img = ImageIO.read(new File(IMG_PATH+"player\\p_r.png"));
        } catch (Exception e) {
            System.out.println("Failed to load player textures");
            return null;
        }
        return instance = new Player(Engine.getCurrentLevel().getStartPosX(), Engine.getCurrentLevel().getStartPosY(), img);
        }
        else return instance;
    }

    private boolean isBusy;
    private Usable currentObject;
    /**
     * Конструктор
     *
     * @param x       - х коррдината
     * @param y       - у коррдината
     * @param texture - текстура (файл с изборажением)
     */
    protected Player(int x, int y, BufferedImage texture)
    {
        super(x, y, texture);
        isStanding = false;
        velocity = 2;
        agility = 20;
        speed = 5;
        maxJumpAx = 50;
    }

    public boolean isBusy() {return isBusy;}
    public void setIsBusy(boolean isBusy){this.isBusy = isBusy;}
    public Usable getCurrentObject(){return currentObject;}
    public void setCurrentObject(Usable u){currentObject = u;}

    @Override
    public void moveLeft() {
        if(!isBusy)super.moveLeft();
    }

    @Override
    public void moveRight() {
        if(!isBusy)super.moveRight();
    }
}
