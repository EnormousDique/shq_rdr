package ru.muwa.shq.engine;
import ru.muwa.shq.engine.ai.AI;
import ru.muwa.shq.engine.combat.BulletUtility;
import ru.muwa.shq.engine.combat.CombatUtility;
import ru.muwa.shq.engine.g.camera.CameraUpdateUtility;
import ru.muwa.shq.engine.spawner.Kladmen;
import ru.muwa.shq.engine.spawner.Spawner;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.engine.utilities.*;
import ru.muwa.shq.objects.Building;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.aiming.Aim;
import ru.muwa.shq.player.controls.PlayerControls;
import ru.muwa.shq.engine.p.collisions.CollisionsChecker;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.QuestUtility;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Класс, отвечающий за изменение положения игровых объектов и сущностей в пространстве.
 *  А так же за вызов соотвеетствующих обновляющих служб.
 */
public class Updater implements Runnable {
    public static Updater instance;
    public Thread thread; // Поток движка игровых состояний.
    private Player player; // Ссылка на игрока.
    private Updater() {
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
        TimeMachine.setStartTime();
        double drawInterval = 1_000_000_000/60;
        double delta=0;
        long lastTime = System.nanoTime();
        long currTime;
        while(thread !=  null) {

                String log = "";
                currTime = System.nanoTime();
                delta += (currTime - lastTime) / drawInterval;
                lastTime = currTime;
            if(delta>2) {
          //      System.out.println("delta: "+delta);
            //    System.out.println("pause: "+Engine.pause);
               // delta = 1.1;
                delta--;
            }

            if(delta >= 1)
            {
              //  System.out.println("ok delta");
                if(!Engine.pause && ! Engine.cutscene) update();
           //     System.out.println("delta  after update: "+ delta);
                delta--;
            }
        }
    }

    /**
     * Основной метод обработчика внутриигровых действий и состояний.
     * Вызывается каждый кадр, проверяет состояние внутриигровых объектов и обновляет их.
     */

    //TODO: Ранее проверка коллизий вызывалась дважды - в начале и в конце итерации.
    // Нужно проверить насколько это была необходимая мера и можно ли обойтись без неё.
    private void update() {

      //  Spawner.regularSpawn();
        Spawner.carSpawn();
        TraficUtility.work();
        Kladmen.mudak();  /** Кладмен мудак */
        QuestUtility.work();
        PlayerControls.controlPlayer();
        SolidBoxUpdater.updateSolidBox(player);
        CombatUtility.work();
        TimeMachine.work();
        CutsceneZoneUtility.work();
        AutoDialogueZoneUtility.checkAutoDialogueZone();
        AutoGifSceneYtility.checkAutoGifSceneUtilityZone();
        EffectUtility.work();
        DeathUtility.work();
        BulletUtility.work();
        Aim.getInstance().aim();
        CameraUpdateUtility.getInstance().work();
        CollisionsChecker.collide(player,Engine.getCurrentLevel().getObjects());
        InventoryManager.update();
        UseZoneUpdater.update();
        ActionZoneUtility.work();
        GameZoneUtility.work();
        CorpseCleanerUtility.work();
        for(GameObject o : Engine.getCurrentLevel().getObjects())
        {
            if (!(o instanceof Building))
                SolidBoxUpdater.updateSolidBox(o);
        }
        for (int i = 0; i < Engine.getCurrentLevel().getNPC().size(); i++)
        {
            NPC c = Engine.getCurrentLevel().getNPC().get(i);
            AI.getInstance().move(c);
            SolidBoxUpdater.updateSolidBox(c);
            CollisionsChecker.getInstance().checkCollisionsNPC(c, Engine.getCurrentLevel().getObjects());
            CollisionsChecker.getInstance().checkCollisionsNPC(c, new LinkedList<>(Engine.getCurrentLevel().getNPC().stream().map(npc -> (GameObject) npc).collect(Collectors.toList())));
        }
        NPCViewFieldUtility.work();
         for(Container con : Engine.getCurrentLevel().getContainers()) {
            SolidBoxUpdater.updateSolidBox(con);
        }

    }
    public static Updater getInstance()
    {
        if (instance == null) return new Updater();
        else return instance;
    }
}