package ru.muwa.shq.engine.launcher;

import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.listeners.ButtonListener;

import javax.print.attribute.standard.JobName;
import javax.swing.*;
import java.util.LinkedList;

/**
 * Пусковой механизм игры. Этот класс отвечает за запуск игры.
 */
//TODO: Необходимо вывести инициализацию компонентов в игровой движок.
//  В этом классе реализовать окно лаунчера с выбором режима игры.
public class Launcher
{

    private static JFrame launcherFrame = new JFrame();
    private static JButton startButton, settingsButton, editorButton,exitButton;
    final static int START = 0, SET = 1, EDIT = 2, EXIT = 3;
    private static LauncherButtonListener buttonListener;
    private static LinkedList<JButton> buttons;

    /**
     * Метод, в котором запускается лаунчер игры
     */
    private static void init()
    {
        launcherFrame.setVisible(true);
        launcherFrame.setLayout(null);
        launcherFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        launcherFrame.setBounds(330,200, Renderer.SCREEN_WIDTH,Renderer.SCREEN_HEIGHT);
        buttons = new LinkedList<>();
        launcherFrame.add(startButton = new JButton("Начать"));
        startButton.setBounds(100,100,600,50);
        buttons.add(startButton);
        launcherFrame.add(settingsButton = new JButton("Настройки"));
        settingsButton.setBounds(100,200,600,50);
        buttons.add(settingsButton);
        launcherFrame.add(editorButton = new JButton("Редактор"));
        editorButton.setBounds(100,300,600,50);
        buttons.add(editorButton);
        launcherFrame.add(exitButton = new JButton("Выход"));
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
    public static void hideFrame()
    {
        launcherFrame.setVisible(false);
    }

    public static void quit()
    {
        System.exit(0);
    }
}
