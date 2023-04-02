package ru.muwa.shq.engine;
import ru.muwa.shq.engine.ai.AI;
import ru.muwa.shq.engine.combat.BulletUtility;
import ru.muwa.shq.engine.combat.CombatUtility;
import ru.muwa.shq.engine.g.camera.CameraUpdateUtility;
import ru.muwa.shq.engine.spawner.Spawner;
import ru.muwa.shq.engine.utilities.*;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.aiming.Aim;
import ru.muwa.shq.player.controls.PlayerControls;
import ru.muwa.shq.engine.p.collisions.CollisionsChecker;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Player;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Класс, отвечающий за изменение положения игровых объектов и сущностей в пространстве.
 *  А так же за вызов соотвеетствующих обновляющих служб.
 */
public class Updater implements Runnable
{
    public static Updater instance;
    public Thread thread; // Поток движка игровых состояний.
    private Player player; // Ссылка на игрока.

    private Updater()
    {
        if(instance != null) return;
        instance = this;
        player = Player.get();
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run()
    {
        System.out.println("Physx engin initialized. Updater thread started.");


        double drawInterval = 1_000_000_000/60;
        double delta=0;
        long lastTime = System.nanoTime();
        long currTime;

        while(thread !=  null)
        {
                currTime = System.nanoTime();
                delta += (currTime - lastTime) / drawInterval;
                lastTime = currTime;


            if(delta>2)
            {
                System.out.println("delta: "+delta);
                System.out.println("pause: "+Engine.pause);
                delta = 1.1;
            }

            if(delta >= 1)
            {
                if(!Engine.pause) update();
                delta--;
            }
        }
        while (true)
        {
            System.out.println("pshx thread slomalsa");
        }
    }

    /**
     * Основной метод обработчика внутриигровых действий и состояний.
     * Вызывается каждый кадр, проверяет состояние внутриигровых объектов и обновляет их.
     */

    //TODO: Ранее проверка коллизий вызывалась дважды - в начале и в конце итерации.
    // Нужно проверить насколько это была необходимая мера и можно ли обойтись без неё.
    private void update()
    {

        long time = System.currentTimeMillis();
        long localTime = System.currentTimeMillis();
        //Спавнер
        Spawner.regularSpawn();
        //System.out.println("spawner counter : " + Spawner.getSpawnedNPCCounter());
        //System.out.println("spawner interval :  " + Spawner.getSpawnInterval());


        //Блок обработки игрока.
        // Проверяем были ли команды игроку через игровое управление.
        PlayerControls.controlPlayer(); //TODO: Тут должен быть весь код, который зависит от ввода.
        //Обновляем бокс игрока.
        SolidBoxUpdater.updateSolidBox(player);

        //Вызов службы обработки боя

        CombatUtility.work();

        //вызов службы эфектов
        EffectUtility.work();
        //Вызов службы обновления пуль
        BulletUtility.work();


        // Обновленци прицела

        Aim.getInstance().aim();

        //Обновление камеры.
        CameraUpdateUtility.getInstance().work();

        //System.out.println(Aim.getInstance().calculateAngle());

        // Проверяем столкновение игрока с объектами.
        CollisionsChecker.getInstance().checkCollisions(player, Engine.getCurrentLevel().getObjects());

        CollisionsChecker.getInstance().checkCollisions(player,  Engine.getCurrentLevel().getNPC().stream().map(c -> (GameObject) c ).collect(Collectors.toList()) );

        // обновляем текстурку игрока
        CombatUtility.updatePlayerTexture();
        //Обновляем окно инвентаря

        InventoryManager.update();

        // Обновление зоны доступного использования
        UseZoneUpdater.update();

        // Через службу проверки игровых зон смотрим взаимодействие игрока с той или иной зоной
        GameZoneUtility.work();
        // вован ЭКСПЕРЕМЕНТИРУЕТ С ОТТАЛКИВАНИЕ БИТЫ! !!!!!!!!!!!!!!!!!!!


        //Блок обработки обычных объектов из списка текущих.
        for(GameObject o : Engine.getCurrentLevel().getObjects())
        {
            // Проверяем столкновения
         //   CollisionsChecker.getInstance().checkCollisions(o, Engine.getCurrentLevel().getObjects());

            // Обновляем боксы
            SolidBoxUpdater.updateSolidBox(o);

        }

        //Блок обработки НПЦ из списка текущих.
        for(int i = 0; i< Engine.getCurrentLevel().getNPC().size(); i++)
        {

            NPC c = Engine.getCurrentLevel().getNPC().get(i);

            //Передаем нпц ии, чтобы тот решил что ему делать.
            AI.getInstance().move(c);
            // Обновляем бокс.
            SolidBoxUpdater.updateSolidBox(c);

            // Проверяем столкновения.
            CollisionsChecker.getInstance().checkCollisionsNPC(c, Engine.getCurrentLevel().getObjects());
            CollisionsChecker.getInstance().checkCollisionsNPC(c, new LinkedList<>( Engine.getCurrentLevel().getNPC().stream().map(npc->(GameObject)npc).collect(Collectors.toList())));

            // Обновляем стены рейкастера и сам рейкастер.
            // TODO: Проверить насколько необходимо обновление каждую итерацию и скорость при большом кол-ве нпц.
            c.getRayCaster().setBorders(c.getRayCaster().buildLines(Engine.getCurrentLevel().getObjects()));
            RayCasterUpdater.updateRayCaster(c.getRayCaster(),c);

        }
        //Для нпц вызываем службу обновления поля зрения
        NPCViewFieldUtility.work();

        //Блок обработки объектов контейнеров из списка текущих.
         for(Container con : Engine.getCurrentLevel().getContainers())
        {

          //  CollisionsChecker.getInstance().checkCollisions(con,Engine.getCurrentLevel().getObjects());

            // Обновляем бокс
            SolidBoxUpdater.updateSolidBox(con);
        }
        //Блок ПОСТ обработки

        //Очищаем нажатые клавиши. (Фиксим баг с мульти прожатием клавиш)
       // DropKeyUtility.getInstance().work(); //TODO: Убрать.
       // System.out.println("time : " + (System.currentTimeMillis() - time));
    }
    public static Updater getInstance()
    {
        if (instance == null) return new Updater();
        else return instance;
    }

}