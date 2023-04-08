package ru.muwa.shq.engine.launcher;

import ru.muwa.shq.engine.g.GameScreen;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Пусковой механизм игры. Этот класс отвечает за запуск игры.
 */
public class Launcher
{   private static JFrame screenRes = new JFrame();//окно разрешения
    private static JFrame frame = new JFrame(); // Окно настроек
    private static JButton startButton, settingsButton, editorButton,exitButton; // Кнопки.
    final static int START = 0, SET = 1, EDIT = 2, EXIT = 3; // Константы для кнопок и их действий.
    private static LauncherButtonListener buttonListener; // Объект прослушки кнопок.
    private static RadioButtonListener radioButtonListener;
    private static LinkedList<JRadioButton> radioButtons;

    public static int ScreenRes480 = 0, ScreenRes720 = 1,ScreenRes1080 = 2;
    private static LinkedList<JButton> buttons; // Список кнопок.
    private static LinkedList<JRadioButton> SreenResButtons;
    public static JRadioButton res800x600 = new JRadioButton("800x600");
    public static JRadioButton res1280x720 = new JRadioButton("1280x720");
    public static JRadioButton res1920x1080 = new JRadioButton("1920x1080");
    /**
     * Метод, в котором запускается лаунчер игры.
     *
     *     Его вызов происходит сразу же в методе main.
     */
    private static void init() {
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBounds(0,0, GameScreen.SCREEN_WIDTH,GameScreen.SCREEN_HEIGHT);
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
    public static void main(String... args) {

        init();
        hideFrame();//Старт.  //todo крч я закрстылил эту хуйню  тем что кнокпа вызова настроек не по новой создает а делает сет визибл настройки фалсе и в начале создаем меню его перекрывают настройки прячем настройки и сноваменю
        Launcher.screenSettings();
        hideScreenSettings();
        showFrame();
    }
    public static void quit()
    {
        System.exit(0);
    } // Метод выхода из игры.
    public static void hideFrame()
    {
        frame.setVisible(false);
    } // Метод, прячущий лаунчер после запуска самой игры.
    public static void showFrame(){frame.setVisible(true);}
 public static void hideScreenSettings(){screenRes.setVisible(false);} // метод который прячет окно настроек
    public static void showScreenSettings(){screenRes.setVisible(true);}
    public static void screenSettings() {
        hideFrame();  //todo крч еебааааать старый я так скажу пизда когда заходишь в настройки выбираешь разрешьение и сохраняешь и сновазаходишь в настройки то скрин настроек создаеться по новой и позоду накладываеться на старый
        JLabel nazva = new JLabel("ВЫБЕРИ СВОЁ РАЗРЕШЕНИЕ,а то как лох!");
        JButton save = new JButton("ПРименить настройки");

        screenRes.setVisible(true);
        screenRes.setLayout(null);
        screenRes.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screenRes.setBounds(0,0, GameScreen.SCREEN_WIDTH,GameScreen.SCREEN_HEIGHT);
        ButtonGroup G = new ButtonGroup();
        G.add(res800x600);
        G.add(res1280x720);
        G.add(res1920x1080);
        screenRes.add(nazva);// создаю надпись выбери свое раз....
        nazva.setBounds(10,10,1000,10);// границы надписи
        screenRes.add(res800x600);//добовляю фрейм с радиобаттоном 800 600
        res800x600.setBounds(10,60,100,30);// устанавливаю его границы
        screenRes.add(res1280x720);
        res1280x720.setBounds(10,100,100,30);
        screenRes.add(res1920x1080);
        res1920x1080.setBounds(10,140,100,30);
        screenRes.add(save);// добалляю кнопку сейв
        save.setBounds(GameScreen.SCREEN_WIDTH-200,GameScreen.SCREEN_HEIGHT-200,200,100);// границы кнопки сейф
        save.addActionListener(buttonListener); // добовление кнопки сейв в прослушку
        //радио батон листнер
        radioButtons = new LinkedList<>();// лист радиокнопок
        radioButtons.add(res800x600);//добовляю в список радиобаттонс кнопку 800 на 600
        radioButtons.add(res1280x720);
        radioButtons.add(res1920x1080);
        buttonListener.addButton(save);
        radioButtonListener = new RadioButtonListener(radioButtons);
        for (JRadioButton r : radioButtons) r.addActionListener(radioButtonListener); // форичем добовляем актионлистнер на радиокнопки




    }
}
