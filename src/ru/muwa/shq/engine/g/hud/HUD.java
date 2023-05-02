package ru.muwa.shq.engine.g.hud;

import java.awt.*;
import java.util.*;

import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.player.Player;

import javax.swing.*;

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;


public class HUD {

    public void clearActionWindow () {
        Arrays.stream(actionWindow.getComponents()).forEach(actionWindow::remove);
    }

    private static HUD instance;


    public static HUD getInstance() {
        if(instance == null) return new HUD();else return instance;
    }
    JProgressBar ThirstBar = new JProgressBar(0,100);
    JProgressBar StaminaBar = new JProgressBar(0,100); //создание стаминабара
    JPanel actionWindow = new JPanel(); //создание окна длья миниигры
    JPanel mainWindow = new JPanel();

    JPanel questWindow = new JPanel();
    JPanel dialogueWindow = new JPanel();  //создание даилогового окна
    JPanel itemWindow = new JPanel();// инвентарть айтемов
    JPanel statusWindow = new JPanel();// окна информации
    JPanel equipWindow = new JPanel();
    JPanel  deathWindow = new JPanel();


    JPanel containerWindow = new JPanel();// окно контейнеров
    JProgressBar drugEffectBar = new JProgressBar(0,100);

    public JPanel getQuestWindow() {
        return questWindow;
    }

    public JPanel getGifScenesWindow() {
        return GifScenesWindow;
    }

    public JPanel getDeathWindow() {
        return deathWindow;
    }

    JPanel GifScenesWindow = new JPanel();
    private HUD (){
        instance = this;
        StaminaBar.setValue((int) Player.get().getStamina());
        ThirstBar.setValue((int) Player.get().getThirst());
        ThirstBar.setStringPainted(true);
        drugEffectBar.setValue((int) Player.get().getHighMeter());
        actionWindow.setBounds(SCREEN_WIDTH/2,GameScreen.SCREEN_HEIGHT/2,300,300);
        mainWindow.setBounds(SCREEN_WIDTH/2-25,GameScreen.SCREEN_HEIGHT/2-50,50,100);
        itemWindow.setBounds(SCREEN_WIDTH-500, SCREEN_WIDTH-700,200,300);
        itemWindow.addMouseListener(MouseButtonListener.getInstance());
        itemWindow.setLayout(null);
        equipWindow.setLayout(null);
        questWindow.setLayout(null);
        containerWindow.setLayout(null);
        mainWindow.setBounds(920,500,50,100);
        deathWindow.setBounds(0,0, SCREEN_WIDTH,GameScreen.SCREEN_HEIGHT);
        deathWindow.setVisible(false);
        statusWindow.setBounds(1432,1,140,150);
        itemWindow.setBounds(500,700,200,500);
        containerWindow.setBounds(500,700,200,500);
        dialogueWindow.setBounds(610,765,700,700);
        equipWindow.setBounds(1430,161,90,140);
        containerWindow.setBounds(210,GameScreen.SCREEN_HEIGHT-400,200,300);
        GifScenesWindow.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        questWindow.setBounds(500,500,100,100);
     //   this.add(HUD.getInstance().getHealthBar());
     //   this.add(HUD.getInstance().getActionWindow());
     //   HUD.getInstance().getActionWindow().setVisible(false);
      //  HUD.getInstance().getHealthBar().setBounds(100+ Camera.getInstance().getX(),300+Camera.getInstance().getY(),400,50);
        //   healthBar.setBounds(SCREEN_WIDTH-1027, SCREEN_WIDTH-0,400,50);
        drugEffectBar.setBounds(SCREEN_WIDTH-1146, SCREEN_WIDTH-68,200,20);
//цвета полосок
        drugEffectBar.setForeground(Color.magenta);
        ThirstBar.setForeground(Color.BLUE);
        StaminaBar.setForeground(Color.green);
    }
    public  JPanel getDialogueWindow(){return dialogueWindow;}  // геттер для диалогового окна
    public JPanel getItemWindow() {
        return itemWindow;
    }
    public JProgressBar getStaminaBar() {
        return StaminaBar;
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
    public JProgressBar getDrugEffectBar() {return drugEffectBar;}
    public JProgressBar getThirstBar() {return ThirstBar;}
    public void setThirstBar(JProgressBar thirstBar) {ThirstBar = thirstBar;}
    public JPanel getContainerWindow() {return containerWindow;}
    public void setContainerWindow(JPanel containerWindow) {this.containerWindow = containerWindow;}
}
