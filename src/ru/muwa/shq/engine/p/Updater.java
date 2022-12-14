package ru.muwa.shq.engine.p;
import ru.muwa.shq.engine.ai.AI;
import ru.muwa.shq.engine.updaters.*;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.controls.Grabber;
import ru.muwa.shq.player.controls.PlayerControls;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.p.collisions.CollisionsChecker;
import ru.muwa.shq.engine.p.gravity.GravityChecker;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.levels.Level;

import java.util.LinkedList;
/**
 * Класс, отвечающий за изменение положения игровых объектов и сущностей в пространстве.
 *  А так же за вызов соотвеетствующих обновляющих служб.
 */
public class Updater implements Runnable
{
    public static Updater instance;
    private CollisionsChecker collisionsChecker;
    private GravityChecker gravityChecker;
    private SolidBoxUpdater solidBoxUpdater;
    private OnFeetBoxUpdater onFeetBoxUpdater;
    private AI ai = AI.getInstance();
    private Thread thread;
    private Player player;
    private PlayerControls controls;
    LinkedList<GameObject> objects;
    LinkedList<NPC> npc;
    Renderer renderer;
    RayCasterUpdater rayCasterUpdater;
    KeyListener keyboard = KeyListener.getInstance();
    private Updater()
    {
        if(instance != null) return;
        instance = this;
        player = Player.get();
        controls = PlayerControls.getInstance();
        gravityChecker = GravityChecker.getInstance();
        renderer = Renderer.getInstance();
        collisionsChecker = CollisionsChecker.getInstance();
        solidBoxUpdater = SolidBoxUpdater.getInstance();
        onFeetBoxUpdater = OnFeetBoxUpdater.getInstance();
        objects = new LinkedList<>();
        npc = new LinkedList<>();
        rayCasterUpdater = RayCasterUpdater.getInstance();
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run()
    {
        System.out.println("Physx engin initialized. Updater thread started.");
        grabGameObjects(Engine.getCurrentLevel());
        grabNPC(Engine.getCurrentLevel());
        System.out.println("Objects grabbed from lvl +  " + Engine.getCurrentLevel().getClass());

        double drawInterval = 1_000_000_000/60;
        double delta=0;
        long lastTime = System.nanoTime();
        long currTime;

        while(thread !=  null)
        {
            currTime = System.nanoTime();
            delta += (currTime - lastTime) / drawInterval;
            lastTime = currTime;

            for(GameObject o : objects) collisionsChecker.checkCollisions(o, objects);
            //Попытка вынести расчёты столновений за ограничение в 60 итераций в секунду для исправления бага с выталкиванием за текстуры
            //TODO: вероятно стоит запустить отдельный поток для провери столновений вне основного потоа updater'a
            //Баг удалось исправить.

            if(delta >= 1)
            {
                update();
                delta--;
            }
        }
    }
    private void update()
    {
       // System.out.println("Player standing: "+player.standing());
        for(GameObject o : objects)
        {
            collisionsChecker.checkCollisions(o, objects);
            gravityChecker.checkGravity(o,objects);
            solidBoxUpdater.updateSolidBox(o);
            onFeetBoxUpdater.updateOnFeetBox(o);
            collisionsChecker.checkCollisions(o, objects);
        }
        for(NPC c : npc)
        {
            collisionsChecker.checkCollisionsNPC(c, objects);
            collisionsChecker.checkBottomCollisions(c);
            gravityChecker.checkGravity(c,objects);
            solidBoxUpdater.updateSolidBox(c);
            onFeetBoxUpdater.updateOnFeetBox(c);
            c.getRayCaster().setBorders(c.getRayCaster().buildLines(Engine.getCurrentLevel().getObjects()));
            rayCasterUpdater.updateRayCaster(c.getRayCaster(),c);
            collisionsChecker.checkCollisionsNPC(c, objects);
            collisionsChecker.checkBottomCollisions(c);
            ai.move(c);
        }
        for(Container con : Engine.getCurrentLevel().getContainers())
        {
            solidBoxUpdater.updateSolidBox(con);
            if(con.isInUse())
                for(Item i : con.getItems())
                    if(!Engine.getCurrentLevel().getIcons().contains(i.getAppearance()))
                        Engine.getCurrentLevel().addIcon(i.getAppearance());
            if(!con.isInUse())
                for(Item i : con.getItems())
                    if(Engine.getCurrentLevel().getIcons().contains(i.getAppearance()))
                        Engine.getCurrentLevel().removeIcon(i.getAppearance());
        }
        controls.controlPlayer();
        collisionsChecker.checkCollisions(player, objects);
        gravityChecker.checkGravity(player,objects);
        solidBoxUpdater.updateSolidBox(player);
        onFeetBoxUpdater.updateOnFeetBox(player);
        collisionsChecker.checkCollisions(player, objects);
        Grabber.getInstance().grab();
        ItemPhysicalAppearanceBoxUpdater.getInstance() .update();
        WallZoneUpdater.getInstance ().update ();
        InventoryWindowUpdater.getInstance().update();
        IconsUpdater.getInstance().update();
        System.out.println(Inventory.getInstance().getItems());

    }
    public static Updater getInstance()
    {
        if (instance == null) return new Updater();
        else return instance;
    }
    private void grabGameObjects(Level level)
    {
        objects = level.getObjects();
    }
    private void grabNPC(Level level)
    {
        npc = level.getNPC();
    }
}