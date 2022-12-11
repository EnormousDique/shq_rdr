package ru.muwa.shq.engine.p;
import ru.muwa.shq.engine.ai.AI;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.p.checkers.CollisionsChecker;
import ru.muwa.shq.engine.p.checkers.GravityChecker;

import ru.muwa.shq.engine.p.updaters.RayCasterUpdater;
import ru.muwa.shq.engine.p.updaters.SolidBoxUpdater;
import ru.muwa.shq.entities.gameObjects.GameObject;
import ru.muwa.shq.entities.gameObjects.creatures.npc.NPC;
import ru.muwa.shq.entities.gameObjects.creatures.player.Player;
import ru.muwa.shq.levels.Level;

import javax.swing.text.PlainDocument;
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
    private AI ai = AI.getInstance();
    private Thread thread;
    private Player player;
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
        gravityChecker = GravityChecker.getInstance();
        renderer = Renderer.getInstance();
        collisionsChecker = CollisionsChecker.getInstance();
        solidBoxUpdater = SolidBoxUpdater.getInstance();
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

            if(delta >= 1)
            {
                update();
                delta--;
            }
        }
    }
    private void update()
    {
        System.out.println(player.getX()+" "+player.getY());
        gravityChecker.checkGravity(player);
        solidBoxUpdater.updateSolidBox(player);
        collisionsChecker.checkCollisions(player, objects);
        for(GameObject o : objects)
        {
            gravityChecker.checkGravity(o);
            solidBoxUpdater.updateSolidBox(o);
            collisionsChecker.checkCollisions(o, objects);
        }
        for(NPC c : npc)
        {
            ai.move(c);
            gravityChecker.checkGravity(c);
            solidBoxUpdater.updateSolidBox(c);
            collisionsChecker.checkCollisionsNPC(c, objects);
            collisionsChecker.checkBottomCollisions(c);
            c.getRayCaster().setBorders(c.getRayCaster().buildLines(Engine.getCurrentLevel().getObjects()));
            rayCasterUpdater.updateRayCaster(c.getRayCaster(),c);

        }

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
