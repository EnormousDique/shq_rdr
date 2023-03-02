package ru.muwa.shq.engine.g.hud;


import ru.muwa.shq.player.Player;

import javax.swing.*;


public class HUD {
    JProgressBar healthBar = new JProgressBar(0,100);
    private static HUD instance;



    public static HUD getInstance() {
        if(instance == null) return new HUD();else return instance;
    }

    private HUD (){
        instance = this;
        healthBar.setValue(Player.get().getHp());


    }
    public JProgressBar getHealthBar() {
        return healthBar;
    }

}
