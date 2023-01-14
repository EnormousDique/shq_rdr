package ru.muwa.shq.engine;

import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.launcher.Launcher;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.p.Updater;
//import ru.muwa.shq.levels.dev.LevelVovka1010;
import ru.muwa.shq.levels.demo.DemoLevel0;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.dev.DevLevel0;

/**
 * Класс, запускающий и хранящий основные компоненты игрового движка.
 */
public class Engine
{
    /**
     * Статические поля.
     */
    private static Level currentLevel = new DevLevel0();
    public static Updater updater = Updater.getInstance();
    public static Renderer renderer = Renderer.getInstance();
    public static KeyListener keyboard = KeyListener.getInstance();
    public static Player player = Player.get(); // При получении игрока currentLevel уже должен быть инициализирован!
    /**
     * Метод смены уровня.
     * @param level - уровень, на который мы переходим.
     */
    public static void startLevel(Level level)
    {
        currentLevel = level;

    }
    public static Level getCurrentLevel()
    {
        return currentLevel;
    }

    /**
     * Метод запуска игры.
     *
     * При вызове данного метода из лаунчера происходит инициализация статик-блока класса, в котором содержатся
     * все компоненты игрового движка. После вызова метода, движок сразу начнинает обрабатывать обект текущего уровня.
     * Должен появиться игровой экран с активным обновлением и рендерингом.
     */
    public static void gameOn()
    {
        System.out.println("Game started");
        Launcher.hideFrame();
    }

}
