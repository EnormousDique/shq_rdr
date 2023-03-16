package ru.muwa.shq.minigames;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.hud.HUD;

import javax.swing.*;

public class PadikLock implements MiniGame {
    private boolean victory;
    @Override
    public boolean victory() {
        Engine.pause = false;
        HUD.getInstance().clearActionWindow();
        HUD.getInstance().getActionWindow().setVisible(false);//красавчиг отключаем видимость панели мини игры
        return victory;
    }

    @Override
    public void game() {
        HUD.getInstance().getActionWindow().setVisible(true); // включаем видимость панели мини игры
        Engine.pause = true;

        JButton b1 = new JButton("1");
        HUD.getInstance().getActionWindow().add(b1);
    }
}
