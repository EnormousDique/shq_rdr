package ru.muwa.shq.engine;

import ru.muwa.shq.engine.animations.Animator;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.camera.Camera;
//import ru.muwa.shq.levels.dev.LevelVovka1010;
import ru.muwa.shq.engine.launcher.Launcher;
import ru.muwa.shq.engine.s.Sounder;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.levels.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demoLevel0.buildings.testBuilding.TestHouse;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.zones.EnterZone;

import java.util.Arrays;

/**
 * Класс, запускающий и хранящий основные компоненты игрового движка.
 */
public class Engine
{
    /**
     * Статические поля.
     */

    public static boolean pause , cutscene ;
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
        Launcher.hideFrame();
        Renderer.showLoading();

        try {
            currentLevel = //HubHataIgoryana.getInstance();
                            // DemoLevel0.getInstance() ;
                               TestHouse.getInstance();
                            //    ShqurTestLevel.getInstance();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        Renderer.getInstance();
        Updater.getInstance();

        Sounder.getInstance();
        Animator.getInstance();
        Renderer.hideLoading();

    }

    public static void switchLevel(EnterZone z)
    {
        System.out.println("going to level " + z.getLevel());



        currentLevel = z.getLevel();
        Player.get().setX(z.getWhereToX());
        Player.get().setY(z.getWhereToY());

        Camera.getInstance().setY(Player.get().getY() - (GameScreen.SCREEN_WIDTH/2));
        Camera.getInstance().setX(Player.get().getX() - (GameScreen.SCREEN_HEIGHT/2));
    }
    public static void switchLevel(Level l, int x,int y)
    {
        System.out.println("going to level " + l);

        currentLevel = l;

        Player.get().setX(x);
        Player.get().setY(y);

        Camera.getInstance().setY(Player.get().getY() - (GameScreen.SCREEN_WIDTH/2));
        Camera.getInstance().setX(Player.get().getX() - (GameScreen.SCREEN_HEIGHT/2));
    }
}