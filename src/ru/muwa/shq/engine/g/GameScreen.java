package ru.muwa.shq.engine.g;

import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;

import javax.swing.*;
/**
 * Класс, представляющий из себя окно игры.
 */

public class GameScreen extends JFrame
{
    private static GameScreen instance;
    public static final int SCREEN_HEIGHT = 600, SCREEN_WIDTH = 800, // Широта и высота экрана
            FRAME_TOP_HEIGHT = 37; // Высота шапки экрана. (Верхняя панель)
    private GameScreen(){
        instance = this;
        this.add(HUD.getInstance().getHealthBar());
        HUD.getInstance().getHealthBar().setBounds(0+ Camera.getInstance().getX(),100+Camera.getInstance().getY(),400,50);
    } // Конструктор. (Приватный)
    public static GameScreen getInstance()
    {
        if (instance == null) return new GameScreen();
        else return instance;
    }
}
