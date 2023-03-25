package ru.muwa.shq.engine.g.hud;


import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.player.Player;

import javax.swing.*;
import java.awt.*;


public class HUD {

    public void clearActionWindow () {
       for(int i = 0; i < actionWindow.getComponents().length; i++ )
       {
           actionWindow.remove(actionWindow.getComponents()[i]);
       }
    }

    private static HUD instance;


    public static HUD getInstance() {
        if(instance == null) return new HUD();else return instance;
    }

    private HUD (){
        instance = this;
        healthBar.setValue(Player.get().getHp());
        actionWindow.setBounds(100,100,100,100);
        mainWindow.setBounds(620,500,50,100);
        statisticWindow.setBounds(500,300,100,100);
        itemWindow.setBounds(500,700,200,300);
        itemWindow.addMouseListener(MouseButtonListener.getInstance());
        itemWindow.setLayout(null);

    }

    JProgressBar healthBar = new JProgressBar(0,100); //создание хелзбара
    public JProgressBar getHealthBar() {
        return healthBar;
    }


    JPanel actionWindow = new JPanel(); //создание окна длья миниигры
    JPanel mainWindow = new JPanel();


    public JPanel getItemWindow() {
        return itemWindow;
    }

    JPanel itemWindow = new JPanel();// панель айтемов

    public JPanel getMainWindow() {
        return mainWindow;
    }

    public JPanel getActionWindow() {
        return actionWindow;
    }
    JPanel statisticWindow = new JPanel();// окна информации

    public JPanel getItemInfo() {
        return statisticWindow;
    }
}
