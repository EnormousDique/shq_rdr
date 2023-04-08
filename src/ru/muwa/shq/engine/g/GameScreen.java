package ru.muwa.shq.engine.g;

import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;

import javax.swing.*;
import java.awt.*;

/**
 * Класс, представляющий собой окно игры.
 */

public class GameScreen extends JFrame
{
    private static GameScreen instance;

    //private static Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
    public static  int SCREEN_HEIGHT= 600 , SCREEN_WIDTH = 800 , // Широта и высота экрана
            FRAME_TOP_HEIGHT = 37; // Высота шапки экрана. (Верхняя панель)
    private GameScreen(){
        instance = this;
        this.add(HUD.getInstance().getHealthBar());
        this.add(HUD.getInstance().getActionWindow());
        HUD.getInstance().getActionWindow().setVisible(false);
        this.add(HUD.getInstance().getDrugEffectBar());


    } // Конструктор. (Приватный)
    public static GameScreen getInstance()
    {
        if (instance == null) return new GameScreen();
        else return instance;
    }
}
