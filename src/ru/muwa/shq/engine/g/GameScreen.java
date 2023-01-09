package ru.muwa.shq.engine.g;

import javax.swing.*;

public class GameScreen extends JFrame
{
    private static GameScreen instance;
    public static final int SCREEN_HEIGHT = 600, SCREEN_WIDTH = 800, FRAME_TOP_HEIGHT = 37;
    private GameScreen(){instance = this;}
    public static GameScreen getInstance()
    {
        if (instance == null) return new GameScreen();
        else return instance;
    }
}
