package ru.muwa.shq.player;
import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.clothes.Clothes;
import ru.muwa.shq.items.guns.Weapon;
import ru.muwa.shq.objects.Usable;
import ru.muwa.shq.quests.Quest;
import ru.muwa.shq.quests.mom.regular.MQDrugs;
import ru.muwa.shq.quests.mom.regular.MQFood;

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
    public static BufferedImage img;


    public int
            money = 1000, shiftSpeed = 6,
            playerHearts = 3, momHearts = 3,
            baseSpeed = 3,hungrySpeed = 2,
            regSpeed = baseSpeed, baseShiftSpeed = 6;

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public int getBaseShiftSpeed() {
        return baseShiftSpeed;
    }

    public void setBaseShiftSpeed(int baseShiftSpeed) {
        this.baseShiftSpeed = baseShiftSpeed;
    }

    public ArrayList<Quest> momQuests = new ArrayList<>()
    , butcherQuests = new ArrayList<>()
    , hackerQuests = new ArrayList<>()
    , copQuests = new ArrayList<>()
    , drugstoreQuests = new ArrayList<>()
    , hachQuests = new ArrayList<>();

    public enum Direction{UP,DOWN,LEFT,RIGHT}
    public Direction direction;
    private Rectangle useZone;

    public double hp, maxHP, baseHP =100;
    private double thirst=100;
    public double hunger=100;
    public double pee;
    public double poo;
    public double awake=100;
    private double highMeter ;
    public Weapon currentWeapon ;

    private double highMeterLock;
    private double stamina, baseStamina = 100, maxStamina;

    public Clothes footWear, torsoWear, headWear;



    public int getRegSpeed() {
        return regSpeed;
    }

    public void setRegSpeed(int regSpeed) {
        this.regSpeed = regSpeed;
    }

    public int getShiftSpeed() {
        return shiftSpeed;
    }

    public void setShiftSpeed(int shiftSpeed) {
        this.shiftSpeed = shiftSpeed;
    }

    public Rectangle getUseZone() {
        return useZone;
    }

    public static Player get() {
        if (instance == null)
        {
            try { img = ImageIO.read(new File(IMG_PATH + "player\\kulaginFist.png"));
        } catch (Exception e) {
            System.out.println("Failed to load player textures");
            return null;
        }
        return instance = new Player(300, 300, img);
        }
        else return instance;
    }

    private boolean isBusy;
    public boolean isConfused;
    private Usable currentObject;

    @Override
    public ArrayList<Item> getRandomLoot() {
        return null;
    }

    public void setUseZone(Rectangle useZone) {
        this.useZone = useZone;
    }

    public double getBaseStamina() {
        return baseStamina;
    }

    public void setBaseStamina(double baseStamina) {
        this.baseStamina = baseStamina;
    }

    public double getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(double maxStamina) {
        this.maxStamina = maxStamina;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
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
        thirst = 100;
        maxJumpAx = 50;
        hp = baseHP;
        maxHP = baseHP;
        stamina = baseStamina;
        maxStamina = baseStamina;
        attackZone.getBounds().setBounds(x,y-30,30,30);
        this.name = "Player";
        highMeter = 0;
        highMeterLock = 0;

        momQuests.add(new MQDrugs()); momQuests.add(new MQFood());

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
        if(isConfused && Math.random() > 0.666){ moveRight();return;}
        if(!isBusy)super.moveLeft();
        if(direction!= Direction.LEFT) direction = Direction.LEFT;

    }
    @Override
    public void moveRight() {
        if(isConfused && Math.random() > 0.666){ moveLeft();return;}

        if(!isBusy)super.moveRight();
        if(direction!= Direction.RIGHT) direction = Direction.RIGHT;
    }
    public void jump(){
        //Да, когда-то мы умели прыгать..
        // нессы еще взелтим-с (С)vov4que1010.@mail.com
    }
     @Override
    public void moveUp(){
         if(isConfused && Math.random() > 0.666) {moveDown();return;}
         if(!isBusy)super.moveUp();
         if(direction!= Direction.UP) direction = Direction.UP;
     }
    @Override
     public void moveDown (){
        if(isConfused && Math.random() > 0.666){ moveUp();return;}

        if(!isBusy)super.moveDown();
        if(direction!= Direction.DOWN) direction = Direction.DOWN;
     }
     //геттеры и сеттеры ЫЫЫ))))
    public double getHighMeter() {return highMeter;}
    public void setHighMeter(double highMeter) {this.highMeter = highMeter;}
    public double getHighMeterLock() {return highMeterLock;}
    public void setHighMeterLock(double highMeterLock) {this.highMeterLock = highMeterLock;}
    public double getStamina() {return stamina;}
    public void setStamina(double stamina){this.stamina = stamina;}
    public double getThirst() {return thirst;}
    public void setThirst(double thirst) {this.thirst = thirst;}

}


