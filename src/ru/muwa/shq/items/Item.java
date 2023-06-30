package ru.muwa.shq.items;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.bluntWeapons.BaseballBat;
import ru.muwa.shq.items.consumables.*;
import ru.muwa.shq.items.drugs.*;
import ru.muwa.shq.items.guns.Bullet;
import ru.muwa.shq.items.guns.Makarov;
import ru.muwa.shq.items.guns.Obrez;
import ru.muwa.shq.items.guns.Weapon;
import ru.muwa.shq.items.guns.ammo.MakarovAmmo;
import ru.muwa.shq.items.knifes.Kortique;
import ru.muwa.shq.items.quest.*;
import ru.muwa.shq.items.quest.Package;
import ru.muwa.shq.items.zakladki.KladBlack;
import ru.muwa.shq.items.zakladki.KladBlue;
import ru.muwa.shq.items.zakladki.KladRed;
import ru.muwa.shq.items.zakladki.KladYellow;
import ru.muwa.shq.items.zakladki.fakeZakladki.BananaPeel;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

/**
 * Абстрактный класс, прародитель всех предметов.
 */
public abstract class Item
{
    //Мапа соответствий предметов их id
    public static Map<Integer,Item> items = new LinkedHashMap<>();

    /** Сей метод не надлежит использовать напрямую-с.
     * Напротив, его необходимо переопределять для предметов, у коих истинно stackable
     * Использовать следует исключительно переопределённый метод-с!**/
    public abstract Item copy();
    public String name;
    protected String description ;
    protected boolean stackable;
    public int amount = 1;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    protected Container myContainer;
    protected int id,price;

    protected boolean isEquipped;

    public boolean isAbleToEquip() {
        return isAbleToEquip;
    }

    protected boolean isAbleToEquip;
    protected boolean alwaysEquipped;

    public boolean isAlwaysEquipped() {
        return alwaysEquipped;
    }

    public int getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    protected double weight;
    protected BufferedImage texture; //Текстура (изображение) объекта
    public Item(int id,int price,double weight,BufferedImage texture)
    {
        this.id = id;
        this.price = price;
        this.weight = weight;
        this.texture = texture;
        items.put(id,this);
    }
    public static void addItemById(int id){
        Item item = null;
        System.out.println("получен айди :" + id);
        for (Map.Entry<Integer,Item> e : items.entrySet())
        {
            System.out.println("перебираем. айди :" + e.getKey() + " item :  "+e.getValue() );
            if(id == e.getKey())
            {
                item = e.getValue().copy();
            }
        }
        if(item!=null)
        {
            Inventory.getInstance().addItem(item);
        } else System.out.println("не нашлось нихуя по такому айди");
    }

    public BufferedImage getTexture() {return texture;}


    public void setMyContainer(Container c){myContainer = c;}
    public Container getMyContainer(){return myContainer;}

    public void pick() //
    {

        boolean isClickedOnEquipped = this.isEquipped;
        boolean isClickedOnAlwaysEquipped = this.alwaysEquipped;

        if(isClickedOnEquipped && !isClickedOnAlwaysEquipped){
            this.isEquipped = false;
            Player.get().currentWeapon = null;
            try {
                Player.get().setTexture(ImageIO.read(new File(IMG_PATH + "player\\kulaginFist_a3.png")));

            }catch (Exception e)
            {
                System.out.println("failed to load playerF");
            }

        } else {
            boolean isInContainer = false;
            Container container = null;
            for (Container c : Engine.getCurrentLevel().getContainers())
                if (c.isInUse()) {
                    isInContainer = true;
                    container = c;
                }
            if (isInContainer) give(container);
            else use();

        }
    }
    public void get() { // взять предмет с контейнера
        boolean isInContainer = false;
        Container container = null;
        for (Container c : Engine.getCurrentLevel().getContainers())
            if (c.isInUse()) {
                isInContainer = true;
                container = c;
            }
        if (isInContainer) take(container);
    }
    public void give(Container c){
        if(this.isStackable() && this.amount >1) amount -=1;
        else Inventory.getInstance().getItems().remove(this);
        c.addItem(stackable?copy():this);
    }

    public boolean isStackable() {
        return stackable;
    }

    public void take(Container c){
        c.getItems().remove(this);
        Inventory.getInstance().addItem(this);
    }
    public  void use(){
        if(this.isAbleToEquip) {
            boolean isSomeItemAlreadyEquipped = false;
            System.out.println("is smth already equiped = " + isSomeItemAlreadyEquipped);

            for (int i = 0; i < Inventory.getInstance().getItems().size(); i++) {
                if (Inventory.getInstance().getItems().get(i).isEquipped())
                    isSomeItemAlreadyEquipped = true;
            }
            if (!isSomeItemAlreadyEquipped) {
                setEquipped(true);
                Player.get().currentWeapon = (Weapon) this;
            }
        }
    }
    public abstract void equip();

    public boolean isEquipped() {
        return isEquipped;
    }

    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
    }


    static {
        new Flour();
        new BaseballBat();
        new Water();
        new Vodka();
        new Tomato();
        new Ring();
        new Potato();
        new Onion();
        new Lube();
        new LeBottle();
        new HomemadeAnuses();
        new Gurken();
        new EpicRing();
        new Cigarettes();
        new ChickFire();
        new CellPhone();
        new Carrot();
        new CannedSoup();
        new Cabbage();
        new BoobPill();
        new Beetroot();
        new Beer();
        new Hash();
        new IceOlator();
        new Lyrica();
        new Weed();
        new Zanax();
        new MakarovAmmo();
        new Makarov();
        new Obrez();
        new Kortique();
        new BlackCreditCard();
        new GraphicsCard();
        new GreenCreditCard();
        new HardDrive();
        new MotherBoard();
        new Operativca();
        new Pacanynok();
        new Package();
        new PowerUnit();
        new Processor();
        new BananaPeel();
        new KladBlack();
        new KladBlue();
        new KladRed();
        new KladYellow();
    }
}
