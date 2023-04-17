package ru.muwa.shq.player;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.guns.Weapon;
import ru.muwa.shq.objects.Usable;
import ru.muwa.shq.quests.Quest;
import ru.muwa.shq.quests.QuestUtility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import static ru.muwa.shq.objects.GameObject.Direction.DOWN;
/**
 * Класс игрока.
 */
public class Player extends Creature
{
    private static Player instance;
    public static BufferedImage img;

    public ArrayList<Quest> quests = new ArrayList<>();
    public enum Direction{UP,DOWN,LEFT,RIGHT}
    public Direction direction;
    private Rectangle useZone;
    private double thirst;
    private double highMeter ;

    public int getRegSpeed() {
        return regSpeed;
    }

    public void setRegSpeed(int regSpeed) {
        this.regSpeed = regSpeed;
    }

    private int regSpeed = 5;

    public int getShiftSpeed() {
        return shiftSpeed;
    }

    public void setShiftSpeed(int shiftSpeed) {
        this.shiftSpeed = shiftSpeed;
    }

    private int shiftSpeed = 10;

    private double highMeterLock;
    private double Stamina;
    private static BufferedImage imgUp;
    private static BufferedImage imgDown;
    private static BufferedImage imgLeft;
    private static BufferedImage imgRight;
    public Rectangle getUseZone() {
        return useZone;
    }

    public Weapon currentWeapon ;

    public static Player get() {
        if (instance == null)
        {
            try { img = ImageIO.read(new File(IMG_PATH+"player\\kulaginBat2.png"));
        } catch (Exception e) {
            System.out.println("Failed to load player textures");
            return null;
        }
        return instance = new Player(0, 0, img);
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
        speed = 5;
        thirst = 100;
        maxJumpAx = 50;
        hp = 100;
        Stamina = 100; //todo: Вова пидор, переименуй нормально.
        attackZone.getBounds().setBounds(x,y-30,30,30);
        this.name = "Player";
        highMeter = 0;
        highMeterLock = 0;

    }

    public boolean isBusy() {return isBusy;}
    public void setIsBusy(boolean isBusy){this.isBusy = isBusy;}
    public Usable getCurrentObject(){return currentObject;}
    public void setCurrentObject(Usable u){currentObject = u;}


    //todo вынести в отдельный класс  PlayerStats
    public void staminaRegen(){
        if(Player.get().getStamina() < 100)
            Player.get().setStamina(Player.get().getStamina()+0.3);
        else {Player.get().setStamina(Player.get().getStamina()+0);}
    }

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
        // нессы еще взелтим-с (С)vov4que1010.@mail.com
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
     //геттеры и сеттеры ЫЫЫ))))
    public double getHighMeter() {return highMeter;}
    public void setHighMeter(double highMeter) {this.highMeter = highMeter;}
    public double getHighMeterLock() {return highMeterLock;}
    public void setHighMeterLock(double highMeterLock) {this.highMeterLock = highMeterLock;}
    public double getStamina() {return Stamina;}
    public void setStamina(double stamina){this.Stamina = stamina;}
    public double getThirst() {return thirst;}
    public void setThirst(double thirst) {this.thirst = thirst;}

}


