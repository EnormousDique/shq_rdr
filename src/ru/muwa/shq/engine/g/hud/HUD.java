package ru.muwa.shq.engine.g.hud;

import java.util.*;
import org.w3c.dom.ls.LSOutput;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.player.Player;

import javax.swing.*;
import java.awt.*;


public class HUD {

    public void clearActionWindow () {
        Arrays.stream(actionWindow.getComponents()).forEach(actionWindow::remove);
    }

    private static HUD instance;


    public static HUD getInstance() {
        if(instance == null) return new HUD();else return instance;
    }
    JProgressBar healthBar = new JProgressBar(0,100); //создание хелзбара
    JPanel actionWindow = new JPanel(); //создание окна длья миниигры
    JPanel mainWindow = new JPanel();
    JPanel dialogueWindow = new JPanel();  //создание даилогового окна
    JPanel itemWindow = new JPanel();// панель айтемов
    JPanel statusWindow = new JPanel();// окна информации
    JPanel equipWindow = new JPanel();

    private HUD (){
        instance = this;
        healthBar.setValue(Player.get().getHp());
        actionWindow.setBounds(100,100,300,300);
        mainWindow.setBounds(620,500,50,100);
        itemWindow.setBounds(500,700,200,300);
        itemWindow.addMouseListener(MouseButtonListener.getInstance());
        itemWindow.setLayout(null);
        equipWindow.setLayout(null);
        mainWindow.setBounds(920,500,50,100);
        statusWindow.setBounds(500,300,140,300);
        itemWindow.setBounds(500,700,200,500);
        dialogueWindow.setBounds(610,765,700,700);
        equipWindow.setBounds(10,10,100,150);
    }

    public  JPanel getDialogueWindow(){return dialogueWindow;}  // геттер для диалогового окна
    public JPanel getItemWindow() {
        return itemWindow;
    }

    public JProgressBar getHealthBar() {
        return healthBar;
    }

    public JPanel getEquipWindow() {
        return equipWindow;
    }
    public JPanel getMainWindow() {
        return mainWindow;
    }

    public JPanel getActionWindow() {
        return actionWindow;
    }

    public JPanel getStatusWindow() {
        return statusWindow;
    }
}
