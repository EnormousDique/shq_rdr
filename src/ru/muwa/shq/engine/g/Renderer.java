package ru.muwa.shq.engine.g;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.entities.gameObjects.GameObject;
import ru.muwa.shq.entities.gameObjects.creatures.npc.NPC;
import ru.muwa.shq.entities.gameObjects.creatures.player.Player;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.engine.listeners.MouseListener;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.security.Key;
import java.util.LinkedList;
/**
 * Класс, отвечающий за отрисовку изображения на экране.
 */
public class Renderer implements Runnable
{
   private Graphics g;
    private GameScreen frame;
   private Canvas canvas;
   private Thread thread;
   private MouseListener mouse;
   private KeyListener keyboard;
   //private LinkedList<Line2D.Float> borders; // TODO: Удалить
   private Player player = Player.get();
   private LinkedList<GameObject> objects;
   private LinkedList<NPC> npc;
   private static Renderer instance;
   public static Renderer getInstance()
   {
       if(instance == null) return new Renderer();
       return instance;
   }
   public Renderer()
   {
       System.out.println("Starting graphics eng initialization, setting renderer up..");
       if(instance != null) return;
       instance = this;
       objects = new LinkedList<>();
       npc = new LinkedList<>();
       frame = GameScreen.getInstance();
       canvas = new Canvas();
       mouse = MouseListener.getInstance();
       keyboard = KeyListener.getInstance();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(canvas);
       frame.setSize(GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT + GameScreen.FRAME_TOP_HEIGHT);
       frame.setResizable(false);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
       canvas.addMouseMotionListener(mouse);
       canvas.addKeyListener(keyboard);
       thread = new Thread(this);
       thread.start();
       System.out.println("Graphics eng initialized.");
   }

    @Override
    public void run()
    {
        System.out.println("Render thread started");
        grabGameObjects(Engine.getCurrentLevel());
        grabNPC(Engine.getCurrentLevel());
        while(true)
        {
            render();
        }
    }
    private void render()
    {
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs==null)
        {
            canvas.createBufferStrategy(2);
            return;
        }
        g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,GameScreen.SCREEN_WIDTH,GameScreen.SCREEN_HEIGHT);

        g.drawImage(player.getTexture(), player.getX(),player.getY(),null);

        for(GameObject o : objects) g.drawImage(o.getTexture(), o.getX(),o.getY(),null);
        for(NPC c : npc) g.drawImage(c.getTexture(),c.getX(),c.getY(),c.getWidth(),c.getHeight(),null);
        g.setColor(Color.ORANGE);
        for(NPC c : npc) for (Line2D.Float line : c.getRayCaster().calcRays()) g.drawLine((int)line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
        g.drawRect(player.getSolidBox().x,player.getSolidBox().y,(int)player.getSolidBox().getWidth(),(int)player.getSolidBox().getHeight());
        g.drawRect(player.getOnFeetBox().x,player.getOnFeetBox().y,(int)player.getOnFeetBox().getWidth(),(int)player.getOnFeetBox().getHeight());
        for(NPC c: npc) if(c.getRayCaster().isPlayerInSight()) {g.setColor(Color.GREEN); for(Line2D.Float r : c.getRayCaster().calcRays()) g.drawLine((int)r.x1,(int)r.y1,(int)r.x2,(int)r.y2);}
        for(GameObject object : objects) g.drawRect(object.getSolidBox().x,object.getSolidBox().y,(int)object.getSolidBox().getWidth(),(int)object.getSolidBox().getHeight());

        g.dispose();
        canvas.getBufferStrategy().show();
    }
    private void grabGameObjects(Level level)
    {
        objects = level.getObjects();
    }
    private  void grabNPC(Level level)
    {
        this.npc = level.getNPC();
    }
}