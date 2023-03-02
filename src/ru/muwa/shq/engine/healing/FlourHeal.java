package ru.muwa.shq.engine.healing;

import ru.muwa.shq.player.Inventory;

import java.awt.*;

public class FlourHeal {
    private static FlourHeal instance;

    public static FlourHeal getInstance(){
        if(instance == null) return  new FlourHeal();else return instance;
    }
    private FlourHeal(){instance = this;}

    public void heal(){

    }
}
