package ru.muwa.shq.player;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.guns.Firearm;
import ru.muwa.shq.objects.Usable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * Класс игрока.
 */
public class Player extends Creature
{
    private static Player instance;

    public enum Direction{UP,DOWN,LEFT,RIGHT}
    public Direction direction;
    private Rectangle useZone;

    private static BufferedImage img;
    private static BufferedImage imgUp;

    private static BufferedImage imgDown;
    private static BufferedImage imgLeft;
    private static BufferedImage imgRight;

    public Rectangle getUseZone() {
        return useZone;
    }

    public static Player get()
    {
        if (instance == null)
        {
            if(Inventory.getInstance().getItems().get())
        try {
            img = ImageIO.read(new File(IMG_PATH+"player\\kulaginDown.png"));
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

    @Override
    public ArrayList<Item> getRandomLoot() {
        return null;
    }

    /**
     * Конструктор
     *
     * @param x       - х коррдината
     * @param y       - у коррдината
     * @param texture - текстура (файл с изборажением)
     */
    protected Player(int x, int y, BufferedImage texture )
    {
        super(x, y, texture);
        useZone = new Rectangle();
        isStanding = false;
        velocity = 2;
        agility = 8;
        speed = 7;
        maxJumpAx = 50;
        hp = 100;
        attackZone.getBounds().setBounds(x,y-30,30,30);
    }

    public boolean isBusy() {return isBusy;}
    public void setIsBusy(boolean isBusy){this.isBusy = isBusy;}
    public Usable getCurrentObject(){return currentObject;}
    public void setCurrentObject(Usable u){currentObject = u;}

    @Override
    public void moveLeft() {
        if(!isBusy)super.moveLeft();
        if(direction!= Direction.LEFT) direction = Direction.LEFT;
    }

    @Override
    public void moveRight() {
        if(!isBusy)super.moveRight();
        if(direction!= Direction.RIGHT) direction = Direction.RIGHT;
    }
    public void jump(){
        //Да, когда-то мы умели прыгать..
    }
     @Override
    public void moveUp(){
         if(!isBusy)super.moveUp();
         if(direction!= Direction.UP) direction = Direction.UP;

     }
    @Override
     public void moveDown (){
        if(!isBusy)super.moveDown();
        if(direction!= Direction.DOWN) direction = Direction.DOWN;
     }
}


