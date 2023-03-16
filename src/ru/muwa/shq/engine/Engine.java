package ru.muwa.shq.engine;

import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.launcher.Launcher;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.p.Updater;
//import ru.muwa.shq.levels.dev.LevelVovka1010;
import ru.muwa.shq.levels.LevelStorage;
import ru.muwa.shq.levels.demo.DemoLevel0;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;
import ru.muwa.shq.objects.buildings.TEST.FatBuilding;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.dev.DevLevel0;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.GameZone;

import java.io.IOException;

/**
 * Класс, запускающий и хранящий основные компоненты игрового движка.
 */
public class Engine
{
    /**
     * Статические поля.
     */

    public static boolean pause ;
    private static Level currentLevel;


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
        currentLevel = LevelStorage.demoLevel0;
        Updater.getInstance();
        Renderer.getInstance();
        Launcher.hideFrame();
    }

    public static void switchLevel(EnterZone z)
    {
        System.out.println("going to level " + z.getLevel());

        Player.get().setX(z.getWhereToX());
        Player.get().setY(z.getWhereToY());

        Camera.getInstance().setY(Player.get().getY() - (GameScreen.SCREEN_WIDTH/2));
        Camera.getInstance().setX(Player.get().getX() - (GameScreen.SCREEN_HEIGHT/2));

        currentLevel = z.getLevel();
    }
}