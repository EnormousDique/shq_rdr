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

    private static Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
    public static final int SCREEN_HEIGHT = sSize.height, SCREEN_WIDTH = sSize.width, // Широта и высота экрана
            FRAME_TOP_HEIGHT = 37; // Высота шапки экрана. (Верхняя панель)
    private GameScreen(){
        instance = this;
        this.add(HUD.getInstance().getHealthBar());
        this.add(HUD.getInstance().getActionWindow());
        HUD.getInstance().getActionWindow().setVisible(false);
        HUD.getInstance().getHealthBar().setBounds(100+ Camera.getInstance().getX(),300+Camera.getInstance().getY(),400,50);

    } // Конструктор. (Приватный)
    public static GameScreen getInstance()
    {
        if (instance == null) return new GameScreen();
        else return instance;
    }
}
