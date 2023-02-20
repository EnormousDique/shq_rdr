package ru.muwa.shq.player;
import org.w3c.dom.css.Rect;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.Usable;
import ru.muwa.shq.objects.containers.Container;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static ru.muwa.shq.objects.GameObject.Direction.DOWN;

/**
 * Класс игрока.
 */
public class Player extends Creature
{
    private static Player instance;

    private Rectangle useZone;


    public Rectangle getUseZone() {
        return useZone;
    }

    public static Player get()
    {
        if (instance == null)
        {
        BufferedImage img;
        try {
            img = ImageIO.read(new File(IMG_PATH+"player\\kulagin.png"));
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
        direction = DOWN;
        useZone = new Rectangle();
        isStanding = false;
        velocity = 2;
        agility = 8;
        speed = 7;
        maxJumpAx = 50;
    }

    public boolean isBusy() {return isBusy;}
    public void setIsBusy(boolean isBusy){this.isBusy = isBusy;}
    public Usable getCurrentObject(){return currentObject;}
    public void setCurrentObject(Usable u){currentObject = u;}

    @Override
    public void moveLeft() {
        if(!isBusy)super.moveLeft();
        Camera.getInstance().setX(Player.get().getX() - (GameScreen.SCREEN_HEIGHT/2));

    }

    @Override
    public void moveRight() {
        if(!isBusy)super.moveRight();
        Camera.getInstance().setX(Player.get().getX() - (GameScreen.SCREEN_HEIGHT/2));
    }
    public void jump(){

    }
     @Override
    public void moveUp(){
         super.moveUp();
         Camera.getInstance().setY(Player.get().getY() - (GameScreen.SCREEN_WIDTH/2));

     }
    @Override
     public void moveDown (){
        super.moveDown();
        Camera.getInstance().setY(Player.get().getY() - (GameScreen.SCREEN_WIDTH/2));

     }
}


