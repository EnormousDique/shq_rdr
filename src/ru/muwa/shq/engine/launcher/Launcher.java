package ru.muwa.shq.engine.launcher;

import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.listeners.ButtonListener;

import javax.print.attribute.standard.JobName;
import javax.swing.*;
import java.util.LinkedList;

/**
 * Пусковой механизм игры. Этот класс отвечает за запуск игры.
 */
public class Launcher
{

    private static JFrame frame = new JFrame();
    private static JButton startButton, settingsButton, editorButton,exitButton;
    final static int START = 0, SET = 1, EDIT = 2, EXIT = 3;
    private static LauncherButtonListener buttonListener;
    private static LinkedList<JButton> buttons;

    /**
     * Метод, в котором запускается лаунчер игры
     */
    private static void init()
    {
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(330,200, GameScreen.SCREEN_WIDTH,GameScreen.SCREEN_HEIGHT);
        buttons = new LinkedList<>();
        frame.add(startButton = new JButton("Начать"));
        startButton.setBounds(100,100,600,50);
        buttons.add(startButton);
        frame.add(settingsButton = new JButton("Настройки"));
        settingsButton.setBounds(100,200,600,50);
        buttons.add(settingsButton);
        frame.add(editorButton = new JButton("Редактор"));
        editorButton.setBounds(100,300,600,50);
        buttons.add(editorButton);
        frame.add(exitButton = new JButton("Выход"));
        exitButton.setBounds(100,400,600,50);
        buttons.add(exitButton);
        buttonListener = new LauncherButtonListener(buttons);
        for(JButton b : buttons) b.addActionListener(buttonListener);
    }
    /**
     * Заупуск программы. Точка входа.
     * @param args - передаваемые аргументы.
     */
    public static void main(String... args)
    {
        init(); //Старт.
    }
    public static void quit()
    {
        System.exit(0);
    }
    public static void hideFrame()
    {
        frame.setVisible(false);
    }
}
