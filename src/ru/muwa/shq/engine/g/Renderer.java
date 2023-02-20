package ru.muwa.shq.engine.g;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.updaters.UseZoneUpdater;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.ItemPhysicalAppearance;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.engine.listeners.MouseListener;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
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
   private Player player = Player.get();
   private LinkedList<GameObject> objects;
   private LinkedList<NPC> npc;

   private LinkedList<Container> containers;
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
       containers = new LinkedList<> ();
       frame = GameScreen.getInstance();
       canvas = new Canvas();
       mouse = MouseListener.getInstance();
       keyboard = KeyListener.getInstance();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(canvas);
       frame.setSize(GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT + GameScreen.FRAME_TOP_HEIGHT);
       frame.setResizable(false);//false
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
       canvas.addMouseMotionListener(mouse);
       canvas.addMouseListener(MouseButtonListener.getInstance());
       canvas.addKeyListener(keyboard);
       thread = new Thread(this);
       canvas.setFocusable(true);
       canvas.transferFocus();
       thread.start();
       System.out.println("Graphics eng initialized.");
   }

    @Override
    public void run()
    {
        System.out.println("Render thread started");
        grabGameObjects(Engine.getCurrentLevel());
        grabNPC(Engine.getCurrentLevel());
        grabContainers (Engine.getCurrentLevel());
        while(true)
        {
            render();
        }
    }

    /**
     *
     * Метод, отвечающий за отрисовку изображения на экране.
     *
     * !!!!!!!!!!!!!
     * !!! ВАЖНО !!!
     * !!!!!!!!!!!!!
     *
     * Координаты отрисовки отличаются от координат в пространстве.
     * Координаты отрисовки соответствуют координатам отнисительно экрана.
     * Координаты объета относительно экрана получаются в результате учета координат камеры.
     *
     */
    private void render()
    {
        Camera.getInstance().update();
        int camX = Camera.getInstance().getX(), camY = Camera.getInstance().getY();

        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs==null)
        {
            canvas.createBufferStrategy(2);
            return;
        }
        g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,GameScreen.SCREEN_WIDTH,GameScreen.SCREEN_HEIGHT);

        g.drawImage(player.getTexture(), player.getX()-camX,player.getY()-camY,null);


        for(GameObject o : objects) g.drawImage(o.getTexture(), o.getX()-camX,o.getY()-camY,null);
        for(Container con : containers ) g.drawImage(con.getTexture(), con.getX()-camX,con.getY()-camY,null);
        g.setColor (Color.red);
        for(Container con : containers) g.drawRect(con.getX (), con.getY (), 10, 10);
        for(NPC c : npc) g.drawImage(c.getTexture(),c.getX()-camX,c.getY()-camY,c.getWidth(),c.getHeight(),null);
        g.setColor(Color.ORANGE);
        for(NPC c : npc) for (Line2D.Float line : c.getRayCaster().calcRays()) g.drawLine((int)line.x1-camX, (int) line.y1-camY, (int) line.x2-camX, (int) line.y2-camY);
        g.drawRect(player.getSolidBox().x-camX,player.getSolidBox().y-camY,(int)player.getSolidBox().getWidth(),(int)player.getSolidBox().getHeight());
        g.setColor(Color.MAGENTA);
        g.drawRect(player.getOnFeetBox().x-camX,player.getOnFeetBox().y-camY,(int)player.getOnFeetBox().getWidth(),(int)player.getOnFeetBox().getHeight());
        g.setColor(Color.ORANGE);
        for(NPC c: npc) if(c.getRayCaster().isPlayerInSight()) {g.setColor(Color.GREEN); for(Line2D.Float r : c.getRayCaster().calcRays()) g.drawLine((int)r.x1-camX,(int)r.y1-camY,(int)r.x2-camX,(int)r.y2-camY);}
        for(GameObject object : objects) g.drawRect(object.getSolidBox().x-camX,object.getSolidBox().y-camY,(int)object.getSolidBox().getWidth(),(int)object.getSolidBox().getHeight());
        //if(keyboard.getKeys()[keyboard.I]) g.drawImage(Inventory.getInstance().getImg(),Player.get().getX() + 100 - camX, Player.get().getY() - 100 - camY, null);
        for (Container c : containers) if(c.isInUse()) g.drawImage(c.getUI(),c.getX()-camX,c.getY()-camY,null);

        g.setColor(Color.green);

        //g.drawRect(int x - camX, int y,-camY int width, int height)
        for (NPC c:npc) g.drawRect ((int)c.getRightWallZone().getX ()-camX,(int) c.getRightWallZone ().getY ()-camY,(int)c.getRightWallZone ().getWidth (),(int)c.getRightWallZone ().getHeight ());//отрисовка правой зоны столкновений нпц
        for (NPC c:npc) g.drawRect ((int)c.getLeftWallZone().getX ()-camX,(int) c.getLeftWallZone ().getY ()-camY,(int)c.getLeftWallZone ().getWidth (),(int)c.getLeftWallZone ().getHeight ());//отриосвка левой зоны стоклновений нпц
        for(ItemPhysicalAppearance i : Engine.getCurrentLevel().getIcons()) g.drawRect(i.getBox().x-camX, i.getBox().y-camY,i.getBox().width,i.getBox().height);
        if(Inventory.getInstance().isOpened()) g.drawImage(Inventory.getInstance().getImg(), Inventory.getInstance().getX()-camX,Inventory.getInstance().getY()-camY,null);
         g.drawRect(Inventory.getInstance().getX()-camX,Inventory.getInstance().getY()-camY,Inventory.getInstance().getBox().width,Inventory.getInstance().getBox().height);
        g.drawRect((int)Inventory.getInstance().getItemIcons(0).getX()-camX,(int)Inventory.getInstance().getItemIcons(0).getY()-camY,(int)Inventory.getInstance().getItemIcons(0).getWidth(),(int)Inventory.getInstance().getItemIcons(0).getHeight());
        if(Inventory.getInstance().isOpened()) for (Item i : Inventory.getInstance().getItems()) if(i!=null) g.drawImage(i.getTexture(), Inventory.getInstance().getItemIcons(Inventory.getInstance().getItems().indexOf(i)).x-camX,Inventory.getInstance().getItemIcons(Inventory.getInstance().getItems().indexOf(i)).y-camY,null);
       // g.drawRect(Player.get().getUseZone().x-camX,Player.get().getUseZone().y-camY,Player.get().getUseZone().width,Player.get().getUseZone().height); // отрисовка зоны доступного использовиния
        for(ItemPhysicalAppearance i : Engine.getCurrentLevel().getIcons())  g.drawImage(i.getImg(), i.getX()-camX,i.getY()-camY,null );
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
    private  void grabContainers(Level level)
    {
        this.containers = level.getContainers ();
    }
}