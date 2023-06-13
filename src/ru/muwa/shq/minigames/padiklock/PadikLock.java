package ru.muwa.shq.minigames.padiklock;

import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.minigames.MiniGameOld;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PadikLock implements MiniGameOld {
    private boolean victory,isForceQuit;
    public String password;

    public String currInput ="";
    public JLabel label;

    public List<JButton> buttons = new ArrayList<>();
    @Override
    public boolean victory() {
        if(victory) return victory;
        else  return checkPassword();
    }

     public  PadikLock(String password){
        this.password = password;
     }

    @Override
    public void game() {


        victory = false;
        isForceQuit = false;
        buttons = new ArrayList<>();
        HUD.getInstance().getActionWindow().setVisible(true); // включаем видимость панели мини игры
        //Engine.pause = true;
        // добавление кнопок
        JButton b1 = new JButton("1");
        b1.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b1);
        JButton b2 = new JButton("2");
        b2.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b2);
        JButton b3 = new JButton("3");
        b3.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b3);
        JButton b4 = new JButton("4");
        b4.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b4);
        JButton b5 = new JButton("5");
        b5.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b5);
        JButton b6 = new JButton("6");
        b6.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b6);
        JButton b7 = new JButton("7");
        b7.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b7);
        JButton b8 = new JButton("8");
        b8.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b8);
        JButton b9 = new JButton("9");
        b9.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b9);
        JButton b0 = new JButton("0");
        b0.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b0);
        JButton b11 = new JButton("К");
        b11.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b11);
        JButton b12 = new JButton("*");
        b12.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b12);


        JButton bc = new JButton("c");
        bc.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(bc);
        JButton bx = new JButton("x");
        bx.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(bx);
        label = new JLabel(currInput);
        //HUD.getInstance().getActionWindow().add(label);

        HUD.getInstance().clearActionWindow();

        for(JButton b :buttons) {
            System.out.println("adding button : " + b.getText());
            HUD.getInstance().getActionWindow().add(b);
        }
        HUD.getInstance().getActionWindow().add(label);
        HUD.getInstance().getActionWindow().updateUI();
        boolean success = false;


    }
    private boolean checkPassword()
    {
        return password.equals(label.getText());
    }

    public boolean isForceQuit() {
        return isForceQuit;
    }

    void forceExit(){
        this.victory = true;
        this.isForceQuit=true;
    }

}
