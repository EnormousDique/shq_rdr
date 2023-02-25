package ru.muwa.shq.engine.g;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.p.collisions.CollisionsChecker;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.zones.GameZone;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;

/**
 * Класс, отвечающий за отрисовку изображения на экране.
 */
public class Renderer implements Runnable
{
   private Graphics g; // Объект графики (Объект, который рисует)
   private GameScreen frame; // Игровой экран
   private Canvas canvas; // Холст на игровом экране
   private Thread thread; // Поток графического движка
   private Player player = Player.get(); // Ссылка на игрока

   private static Renderer instance;
   public static Renderer getInstance()
   {
       if(instance == null) return new Renderer();
       return instance;
   }

    /**
     * Конструктор
     */
   public Renderer()
   {
       System.out.println("Starting graphics eng initialization, setting renderer up..");
       if(instance != null) return;
       instance = this;
       frame = GameScreen.getInstance();
       canvas = new Canvas();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(canvas);
       frame.setSize(GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT + GameScreen.FRAME_TOP_HEIGHT);
       frame.setResizable(false);//false
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
       canvas.addMouseMotionListener(MouseListener.getInstance());
       canvas.addMouseListener(MouseButtonListener.getInstance());
       canvas.addKeyListener(KeyListener.getInstance());
       thread = new Thread(this);
       canvas.setFocusable(true);
       canvas.transferFocus();
       //thread.start();
       System.out.println("render thread is running in no-no mode");
       System.out.println("Graphics eng initialized.");
   }

    /**
     * Метод run потока игрового движка. Метод запускается при старте потока.
     */
    @Override
    public void run()
    {
        System.out.println("Render thread started");
        /*while(true)
        {
            render();
        }*/
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
                //render();
                delta--;
            }
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
    public void render()
    {
        // ======= инициализация
        int camX = Camera.getInstance().getX(), camY = Camera.getInstance().getY();
        BufferStrategy bs = canvas.getBufferStrategy();
        if(bs==null)
        {
            canvas.createBufferStrategy(2);
            return;
        }
        g = bs.getDrawGraphics();
        // =======

        // Закрашиваем задник черным
        g.setColor(Color.black);
        g.fillRect(0,0,GameScreen.SCREEN_WIDTH,GameScreen.SCREEN_HEIGHT);

        //Отрисовка текстуры игрока.
        g.drawImage(player.getTexture(), player.getX()-camX,player.getY()-camY,null);

        //Отрисовка всех обектов из списка  текущих
        for(GameObject o : Engine.getCurrentLevel().getObjects()) g.drawImage(o.getTexture(), o.getX()-camX,o.getY()-camY,null);

        //Отрисовка всех контейнеров из списка  текущих
        for(Container con : Engine.getCurrentLevel().getContainers() ) g.drawImage(con.getTexture(), con.getX()-camX,con.getY()-camY,null);
        // Также отрисовываем интерфейс тех контейнеров, которые сейчас используются.
        for (Container c : Engine.getCurrentLevel().getContainers()) if(c.isInUse() && c.getItems().size() >= 1){
            g.drawImage(c.getUI() ,c.getX()-camX,c.getY()-camY,null);
            // отрисовка предмета в контейнере.
             for (int i = 0; i < c.getItems().size();i++) {


                 g.drawImage(c.getItems().get(i).getTexture(), c.getIcons().get(i).x - camX, c.getIcons().get(i).y - camY, null);
             }


        }

        //Отрисовка всех нпц из списка  текущих
        for(NPC c : Engine.getCurrentLevel().getNPC()) g.drawImage(c.getTexture(),c.getX()-camX,c.getY()-camY,c.getWidth(),c.getHeight(),null);

        //Отрисовка лучей рейкастера для всех нпц из списка  текущих
        // (ОПЦИОНАЛЬНО) !!!
        g.setColor(Color.red);
        for(NPC c : Engine.getCurrentLevel().getNPC()) for (Line2D.Float line : c.getRayCaster().calcRays()) g.drawLine((int)line.x1-camX, (int) line.y1-camY, (int) line.x2-camX, (int) line.y2-camY);
        //Если игрок в поле зрения, цвет луей меняется.
        for(NPC c: Engine.getCurrentLevel().getNPC()) if(c.getRayCaster().isPlayerInSight()) {g.setColor(Color.GREEN); for(Line2D.Float r : c.getRayCaster().calcRays()) g.drawLine((int)r.x1-camX,(int)r.y1-camY,(int)r.x2-camX,(int)r.y2-camY);}


        // TODO: Проверить, нужны ли в прицнипе объекты класса ItemPhysicalAppearance
        //for(ItemPhysicalAppearance i : Engine.getCurrentLevel().getIcons()) g.drawRect(i.getBox().x-camX, i.getBox().y-camY,i.getBox().width,i.getBox().height);

        //Отрисовка инвентаря игрока, если тот открыт
        if(Inventory.getInstance().isOpened()) g.drawImage(Inventory.getInstance().getImg(), Inventory.getInstance().getX()-camX,Inventory.getInstance().getY()-camY,null);
        if(Inventory.getInstance().isOpened()) for (Item i : Inventory.getInstance().getItems()) if(i!=null) g.drawImage(i.getTexture(), Inventory.getInstance().getItemIcons(Inventory.getInstance().getItems().indexOf(i)).x-camX,Inventory.getInstance().getItemIcons(Inventory.getInstance().getItems().indexOf(i)).y-camY,null);

        // ОТРИСОКВА ТЕСТИРУЕМЫХ ФИЧ

        //Отрисовка полей под иконки у инвентаря
        for(Rectangle r:Inventory.getInstance().getItemIcons())g.drawRect(r.x-camX,r.y-camY, r.width, r.height);

        for (Container c : Engine.getCurrentLevel().getContainers()) if(c.isInUse()){
            // отрисовка иконок под предметы в контейнере.
            for (int i = 0; i < c.getItems().size();i++) {
                g.drawRect(c.getIcons().get(i).x-camX, c.getIcons().get(i).y - camY, c.getIcons().get(i).width, c.getIcons().get(i).height);
            }
        }

        // отрисоква бокса игрока
        g.drawRect(player.getX()-camX, player.getY()-camY, player.getWidth(), player.getHeight());

        //Отрисовка зон переходов по локациям
        g.setColor(Color.BLUE);
        for(GameZone z : Engine.getCurrentLevel().getZones()) g.drawRect(z.x-camX,z.y-camY,z.width,z.height);

        // Все вышесказанное рисуем на холст и показываем на экране.
        g.dispose();
        canvas.getBufferStrategy().show();


    }
}