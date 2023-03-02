package ru.muwa.shq.player;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.objects.Usable;

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



        try {
            img = ImageIO.read(new File(IMG_PATH+"player\\kulaginDown.png"));
            imgUp = ImageIO.read(new File(IMG_PATH+"player\\kulaginUP.png"));
            imgDown = ImageIO.read(new File(IMG_PATH+"player\\kulaginDown.png"));
            imgLeft = ImageIO.read(new File(IMG_PATH+"player\\kulaginLeft.png"));
            imgRight = ImageIO.read(new File(IMG_PATH+"player\\kulaginRight.png"));

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
    protected Player(int x, int y, BufferedImage texture )
    {
        super(x, y, texture);
        direction = DOWN;
        useZone = new Rectangle();
        isStanding = false;
        velocity = 2;
        agility = 8;
        speed = 7;
        maxJumpAx = 50;
        hp = 100;



    }

    public boolean isBusy() {return isBusy;}
    public void setIsBusy(boolean isBusy){this.isBusy = isBusy;}
    public Usable getCurrentObject(){return currentObject;}
    public void setCurrentObject(Usable u){currentObject = u;}

    @Override
    public void moveLeft() {
        if(!isBusy)super.moveLeft();
        Camera.getInstance().setX(Player.get().getX() + Player.get().getWidth() - (GameScreen.SCREEN_HEIGHT/2));
       // texture=imgLeft;


    }

    @Override
    public void moveRight() {
        if(!isBusy)super.moveRight();
        Camera.getInstance().setX(Player.get().getX() + Player.get().getWidth() - (GameScreen.SCREEN_HEIGHT/2));
     //   texture=imgRight;
    }
    public void jump(){

    }
     @Override
    public void moveUp(){
         if(!isBusy)super.moveUp();
         Camera.getInstance().setY(Player.get().getY() + Player.get().getHeight() - (GameScreen.SCREEN_WIDTH/2));
       //  texture=imgUp;

     }
    @Override
     public void moveDown (){
        if(!isBusy)super.moveDown();
        Camera.getInstance().setY(Player.get().getY() + Player.get().getHeight() - (GameScreen.SCREEN_WIDTH/2));
       // texture=imgDown;
     }
}


