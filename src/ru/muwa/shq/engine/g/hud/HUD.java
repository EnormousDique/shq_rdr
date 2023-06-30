package ru.muwa.shq.engine.g.hud;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.launcher.Launcher;
import ru.muwa.shq.engine.listeners.ButtonListener;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;

import static java.awt.Frame.getFrames;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;
import static ru.muwa.shq.objects.GameObject.IMG_PATH;


public class HUD {

    public void clearActionWindow () {
        Arrays.stream(actionWindow.getComponents()).forEach(actionWindow::remove);
    }

    private static HUD instance;


    public static HUD getInstance() {
        if(instance == null) return new HUD();else return instance;
    }

    public JFrame getPauseMenuWindow() {
        return pauseMenuWindow;
    }

    JFrame pauseMenuWindow = new JFrame(); // создание окна менюпаузы на букву P
    JProgressBar thirstBar = new JProgressBar(0,100);
    JProgressBar staminaBar = new JProgressBar(0,100); //создание стаминабара
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
    private PauseMenuButtonListner listner = new PauseMenuButtonListner();

    private static class PauseMenuButtonListner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (((JButton) e.getSource()).getText().equals("продолжим?")) {
                HUD.getInstance().getPauseMenuWindow().setVisible(false);
                Renderer.getInstance().frame.setVisible(true);
                Engine.pause = false;
            }
            if (((JButton) e.getSource()).getText().equals("Смени СВОЙ Разрешение")) {
                Launcher.showScreenSettings();

                //Renderer.getInstance().frame.update();
            }

        }
    }

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
        staminaBar.setValue((int) Player.get().getStamina());
        thirstBar.setValue((int) Player.get().getThirst());
        thirstBar.setStringPainted(true);
        drugEffectBar.setValue((int) Player.get().getHighMeter());
        actionWindow.setBounds(SCREEN_WIDTH/2, SCREEN_HEIGHT/2,300,300);
        mainWindow.setBounds(SCREEN_WIDTH/2-25, SCREEN_HEIGHT/2-50,50,100);
        itemWindow.setBounds(SCREEN_WIDTH-500, SCREEN_WIDTH-700,200,300);
        itemWindow.addMouseListener(MouseButtonListener.getInstance());
        itemWindow.setLayout(null);
        equipWindow.setLayout(null);
        questWindow.setLayout(null);
        containerWindow.setLayout(null);
        mainWindow.setBounds(920,500,50,100);
        deathWindow.setBounds(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);
        deathWindow.setVisible(false);
        statusWindow.setBounds(1432,1,140,150);
        itemWindow.setBounds(500,700,200,500);
        containerWindow.setBounds(500,700,200,500);
        dialogueWindow.setBounds(610,765,700,700);
        equipWindow.setBounds(1430,161,90,140);
        containerWindow.setBounds(210, SCREEN_HEIGHT-400,200,300);
        GifScenesWindow.setBounds(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        questWindow.setBounds(500,500,100,100);
        // меню паузы мб
        //todo мб сделать отдельный класс.?!* 8======D

        pauseMenuWindow.setVisible(false);

        try {
            pauseMenuWindow.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(IMG_PATH+"background\\LABEL.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //pauseMenuWindow.setBounds(0,0,GameScreen.SCREEN_WIDTH,GameScreen.SCREEN_HEIGHT);
        pauseMenuWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel pause = new JLabel("ПАУЗА");
        pause.setBounds(SCREEN_WIDTH/2,10,100,100);
        pauseMenuWindow.add(pause);
        JButton contine = new JButton("продолжим?");
        pauseMenuWindow.add(contine);
        contine.addActionListener(listner);
        contine.setBounds(200,200,100,100);
        JButton settings = new JButton("Смени СВОЙ Разрешение");
        settings.addActionListener(listner);
        pauseMenuWindow.add(settings);
        settings.setBounds(300,200,100,100);
        JTextField cheatCodeLabel = new JTextField();
        JButton cheatCodeButton = new JButton("ввести чит");
        cheatCodeButton.setBounds(200,400,100,100);
        cheatCodeLabel.setBounds(300,400,200,50);
        pauseMenuWindow.add(cheatCodeButton);
        pauseMenuWindow.add(cheatCodeLabel);
        cheatCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(cheatCodeLabel.getText().equals("hub"))
                    {
                        Engine.switchLevel(HubHataIgoryana.getInstance(),100,100);
                        return;
                    }
                    Item.addItemById(Integer.parseInt(cheatCodeLabel.getText()));
                }catch (Exception ex){
                    System.out.println("неправильный чит");
                }
            }
        });

        pauseMenuWindow.pack();
        pauseMenuWindow.setLocationRelativeTo(null);
    }
    public  JPanel getDialogueWindow(){return dialogueWindow;}  // геттер для диалогового окна
    public JPanel getItemWindow() {
        return itemWindow;
    }
    public JProgressBar getStaminaBar() {
        return staminaBar;
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
    public JProgressBar getThirstBar() {return thirstBar;}
    public void setThirstBar(JProgressBar thirstBar) {
        this.thirstBar = thirstBar;}
    public JPanel getContainerWindow() {return containerWindow;}
    public void setContainerWindow(JPanel containerWindow) {this.containerWindow = containerWindow;}
}
