package ru.muwa.shq.engine.g.hud;


import ru.muwa.shq.player.Player;

import javax.swing.*;
import java.awt.*;


public class HUD {

    private static HUD instance;


    public static HUD getInstance() {
        if(instance == null) return new HUD();else return instance;
    }

    private HUD (){
        instance = this;
        healthBar.setValue(Player.get().getHp());
        actionWindow.setBounds(100,100,100,100);

    }

    JProgressBar healthBar = new JProgressBar(0,100);
    public JProgressBar getHealthBar() {
        return healthBar;
    }

    JPanel actionWindow = new JPanel();

    public JPanel getActionWindow() {
        return actionWindow;
    }
}
