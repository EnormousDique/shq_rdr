package ru.muwa.shq.minigames.padiklock;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.minigames.MiniGame;
import ru.muwa.shq.zones.EnterZone;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PadikLock implements MiniGame {
    private boolean victory,isForceQuit;
    public String password = "321";
    public String currInput ="";
    public JLabel label;

    public List<JButton> buttons = new ArrayList<>();
    @Override
    public boolean victory() {
        if(victory) return victory;
        else  return checkPassword();
    }

    @Override
    public void game() {

        victory = false;
        isForceQuit = false;
        buttons = new ArrayList<>();
        HUD.getInstance().getActionWindow().setVisible(true); // включаем видимость панели мини игры
        Engine.pause = true;

        JButton b1 = new JButton("1");
        b1.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b1);
        JButton b2 = new JButton("2");
        b2.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b2);
        JButton b3 = new JButton("3");
        b3.addActionListener(PadikLockButtonListener.getInstance(this));
        buttons.add(b3);
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
