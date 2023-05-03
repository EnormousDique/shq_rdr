package ru.muwa.shq.items;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.guns.Weapon;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Абстрактный класс, прародитель всех предметов.
 */
public abstract class Item
{



    protected String description ;
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
    }
    public BufferedImage getTexture() {return texture;}


    public void setMyContainer(Container c){myContainer = c;}
    public Container getMyContainer(){return myContainer;}

    public void pick() // положить предмет с контейнера
    {

        boolean isClickedOnEquipped = this.isEquipped;
        if(isClickedOnEquipped){
            this.isEquipped = false;
            Player.get().currentWeapon = null;

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
        Inventory.getInstance().getItems().remove(this);
        c.addItem(this);
    }

    public void take(Container c){
        c.getItems().remove(this);
        Inventory.getInstance().addItem(this);
    }
    public  void use(){
        boolean isSomeItemAlreadyEquipped = false;
        System.out.println("is smth already equiped = " + isSomeItemAlreadyEquipped);

        for(int i = 0; i < Inventory.getInstance().getItems().size();i++){
            if(Inventory.getInstance().getItems().get(i).isEquipped())
                isSomeItemAlreadyEquipped = true;
        }
        if(!isSomeItemAlreadyEquipped)
        {
            setEquipped( true);
            Player.get().currentWeapon = (Weapon) this;
        }
    }
    public abstract void equip();

    public boolean isEquipped() {
        return isEquipped;
    }

    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
    }


}
