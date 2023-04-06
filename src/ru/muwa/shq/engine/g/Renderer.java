package ru.muwa.shq.engine.g;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.dialogues.DialogueManager;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.items.ItemPanel;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;
import ru.muwa.shq.zones.GameZone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;

/**
 * Класс, отвечающий за отрисовку изображения на экране.
 */
public class Renderer implements Runnable {
    public static Graphics g; // Объект графики (Объект, который рисует)
    private GameScreen frame; // Игровой экран
    public GameScreen getFrame(){return frame;}
    public static JFrame loadingScreen ;
    private Canvas canvas; // Холст на игровом экране
    private Thread thread; // Поток графического движка
    private Player player = Player.get(); // Ссылка на игрока

    private static Renderer instance;

    public static Renderer getInstance() {
        if (instance == null) return new Renderer();
        return instance;
    }

    /**
     * Конструктор
     */
    public Renderer() {
        System.out.println("Starting graphics eng initialization, setting renderer up..");

        if (instance != null) return;
        instance = this;
        frame = GameScreen.getInstance();

        frame.add(HUD.getInstance().getMainWindow());              // вызываем к главному окну
        frame.add(HUD.getInstance().getItemWindow());              // вызываем к окошку предметов
        frame.add(HUD.getInstance().getStatusWindow());            // вызываем к  окну статуса
        frame.add(HUD.getInstance().getDialogueWindow()).setVisible(false);    // делаем окно диалогов невидимым
        frame.add(HUD.getInstance().getEquipWindow());                        // вызываем окно экипируемых предметов

        canvas = new Canvas();                                                  //создаем новое полотно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             // команда для зачершения работы программы при зкрытии окна.
        frame.add(canvas);                                              // добовляем полотно
        frame.setSize(GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT /* + GameScreen.FRAME_TOP_HEIGHT */);  // устонавливаем размер окна по нашим  переменныт высоты и широты SCREEN_WIDTH AND SCREEN_HEIGHT
        frame.setResizable(true);      //false                               // установка для изменения разрешения окна
        frame.setLocationRelativeTo(null);                    //установка экрана в пространстве если null то посередине
        frame.setVisible(true);                               // установка видимости основного фрейма.
        canvas.addMouseMotionListener(MouseListener.getInstance());  // добовляем службу отлсежки движения мыши
        canvas.addMouseListener(MouseButtonListener.getInstance());  // добовляем службу отлсежки нажатия кнопок мыши
        canvas.addKeyListener(KeyListener.getInstance());            // добовляем службу отлсежки нажатия клавы
        thread = new Thread(this);                   // хз новый поток создаем-с
        canvas.setFocusable(true);             // определение возможности фокусировки на обьекте
        canvas.transferFocus();                 // Переносит фокус на следующий компонент если  этот компонент был владельцем фокуса.
        thread.start();                         // хз запуск потока
        System.out.println("render thread is running in standart mode");
        System.out.println("Graphics eng initialized.");
    }

    /**
     * Метод run потока игрового движка. Метод запускается при старте потока.
     */
    @Override
    public void run() {
        System.out.println("Render thread started");
        /*while(true)
        {
            render();                                                                                                   // todo спросить мишгана надолиэто тут
        }*/
        double drawInterval = 1_000_000_000 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;

        while (thread != null) {
            currTime = System.nanoTime();
            delta += (currTime - lastTime) / drawInterval;
            lastTime = currTime;
            if (delta >= 1) {
                render();
                delta--;
            }
        }
    }

    /**
     * Метод, отвечающий за отрисовку изображения на экране.
     * <p>
     * !!!!!!!!!!!!!
     * !!! ВАЖНО !!!
     * !!!!!!!!!!!!!
     * <p>
     * Координаты отрисовки отличаются от координат в пространстве.
     * Координаты отрисовки соответствуют координатам отнисительно экрана.
     * Координаты объета относительно экрана получаются в результате учета координат камеры.
     */
    public void render() {
        // ======= инициализация
        int camX = Camera.getInstance().getX(), camY = Camera.getInstance().getY();
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(4);
            return;
        }
        g = bs.getDrawGraphics();

        // Закрашиваем задник черным
        g.setColor(Color.black);
        g.fillRect(0, 0, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);
        //отрисовка обьектов из списка текущих обьектов
        for (int i = 0;i<Engine.getCurrentLevel().getObjects().size();i++){
            GameObject o = Engine.getCurrentLevel().getObjects().get(i);
            g.drawImage(o.getTexture(), o.getX() - camX, o.getY() - camY, null);
        }
        //Отрисовка всех контейнеров из списка  текущих
        for (int i = 0; i< Engine.getCurrentLevel().getContainers().size(); i++) {
            Container con = Engine.getCurrentLevel().getContainers().get(i);
            g.drawImage(con.getTexture(), con.getX() - camX, con.getY() - camY, null);
        }
        // Также отрисовываем интерфейс тех контейнеров, которые сейчас используются.
        for (Container c : Engine.getCurrentLevel().getContainers())
            if (c.isInUse()/* && c.getItems().size() >=  1*/) {
                g.drawImage(c.getUI(), c.getX() - camX, c.getY() - camY, null);
                // отрисовка предмета в контейнере.
                for (int i = 0; i < c.getItems().size(); i++) {
                    g.drawImage(c.getItems().get(i).getTexture(), c.getIcons().get(i).x - camX, c.getIcons().get(i).y - camY, null);
                }
            }
        for(int i = 0;i<Engine.getCurrentLevel().getNPC().size();i++){
            NPC c = Engine.getCurrentLevel().getNPC().get(i);
            g.drawImage(c.getTexture(), c.getX() - camX, c.getY() - camY, c.getWidth(), c.getHeight(), null);
        }
        //Отрисовка лучей рейкастера для всех нпц из списка  текущих
        // (ОПЦИОНАЛЬНО) !!!                                                                                            // todo спросить мишгана надолиэто тут

        /*
        for (NPC c : Engine.getCurrentLevel().getNPC()
            for (Line2D.Float line : c.getRayCaster().calcRays())
                g.drawLine((int) line.x1 - camX, (int) line.y1 - camY, (int) line.x2 - camX, (int) line.y2 - camY);     // todo спросить мишгана надолиэто тут


        for(int i = 0;i<Engine.getCurrentLevel().getNPC().size();i++){
            NPC c = Engine.getCurrentLevel().getNPC().get(i);
            for (Line2D.Float line : c.getRayCaster().calcRays())                                                       // todo спросить мишгана надолиэто тут
                g.drawLine((int) line.x1 - camX, (int) line.y1 - camY, (int) line.x2 - camX, (int) line.y2 - camY);
        }
         */
        //Если игрок в поле зрения, цвет луей меняется.
        g.setColor(Color.red);
        for (int i = 0; i < Engine.getCurrentLevel().getNPC().size(); i++) {
            NPC c = Engine.getCurrentLevel().getNPC().get(i);
            if (c.isPlayerInSight()) {
                g.setColor(Color.green);
            }
        }
        // ОТИСОВКА HUD
            HUD.getInstance().getStatusWindow().setVisible(Inventory.getInstance().isOpened());
            HUD.getInstance().getItemWindow().setVisible(Inventory.getInstance().isOpened());
            HUD.getInstance().getMainWindow().setVisible(Inventory.getInstance().isOpened());
            HUD.getInstance().getEquipWindow().setVisible(Inventory.getInstance().isOpened());
            HUD.getInstance().getEquipWindow().updateUI();
            HUD.getInstance().getItemWindow().updateUI();
            HUD.getInstance().getStatusWindow().updateUI();
        // полоску здовроья видно только при открытии инвентаря
            HUD.getInstance().getHealthBar().setVisible(Inventory.getInstance().isOpened());
            HUD.getInstance().getHealthBar().setValue(Player.get().getHp());
            HUD.getInstance().getHealthBar().setString(Integer.toString(HUD.getInstance().getHealthBar().getValue()));
            // полоску упора видно ток из инвентаря
        HUD.getInstance().getDrugEffectBar().setVisible(Inventory.getInstance().isOpened());
        HUD.getInstance().getDrugEffectBar().setValue((int) Player.get().getHighMeter());
        HUD.getInstance().getDrugEffectBar().setString(Integer.toString(HUD.getInstance().getDrugEffectBar().getValue()));
            //Вызов службы диалогов.
            DialogueManager.getInstance().work();

            // ОТРИСОВКА ПЕрСОНАЖА
            AffineTransform at = AffineTransform.getTranslateInstance(Player.get().getX() - camX, Player.get().getY() - camY);
            at.rotate(-Math.toRadians(Aim.getInstance().calculateAngle()), (Player.get().getTexture().getWidth() / 2), (Player.get().getTexture().getHeight() / 2));
            ((Graphics2D) g).drawImage(Player.get().getTexture(), at, null);
        // ОТРИСОКВА ТЕСТИРУЕМЫХ ФИЧ
        //
        //
            // отрисоква бокса игрока
            g.drawRect(player.getX() - camX, player.getY() - camY, (int)player.getSolidBox().getWidth(), (int) player.getSolidBox().getHeight());
            //Отрисовка зон переходов по локациям
            g.setColor(Color.BLUE);
            for (GameZone z : Engine.getCurrentLevel().getZones())
                g.drawRect(z.x - camX, z.y - camY, z.width, z.height);
            // Отрисовка линий прицела
             for (Line2D l : Aim.getInstance().getLines())
               g.drawLine((int) l.getX1() - camX, (int) l.getY1() - camY, (int) l.getX2() - camX, (int) l.getY2() - camY);
            //отрисовка координат мыши.
            g.setColor(Color.red);
            g.setFont(g.getFont().deriveFont(Font.BOLD));
            g.drawString(""+ MouseListener.getInstance().getX()+" "+ MouseListener.getInstance().getY(),100,100);
            g.setColor(Color.cyan);
            g.drawString(""+Camera.getInstance().getX()+" "+Camera.getInstance().getY(),100,130);
             // отрисовка информации о предмете при наводе мышки на онный
            g.setColor(Color.red);
            if(MouseButtonListener.getInstance().highlight != null && MouseButtonListener.getInstance().highlight.getSource() instanceof ItemPanel) {  // если мышкин хайлайтпредметов показывает нуль и пердмет подсвечивает предмент который айтем панел
                g.drawString(((ItemPanel) MouseButtonListener.getInstance().highlight.getSource()).getItem().getDescription(), HUD.getInstance().getMainWindow().getX(), HUD.getInstance().getMainWindow().getY()+200); // было по мышке сделал по маинвиндоуву// то рисуем по координатам мыши - 50 строку описания предмета.
            }
            g.setColor(Color.GREEN);
            g.drawString(""+(MouseListener.getInstance().getX()+camX)+" "+(MouseListener.getInstance().getY()+camY),100,160);
            // Все вышесказанное рисуем на холст и показываем на экране.
            g.dispose();
            canvas.getBufferStrategy().show();
        }
        public void drawDescription(MouseEvent e)
        {

        }
        public static void showLoading()
        {
            loadingScreen = new JFrame("ZAGRUZKA");
            JPanel p = new JPanel();
            loadingScreen.setBounds(400,400,300,150);
            p.add(new JLabel("ЗАГРУЗКА / ZAGRUZKA"));
            loadingScreen.add(p);
            loadingScreen.add(new JLabel("ЗАГРУЗКА / ZAGRUZKA"));
            loadingScreen.setVisible(true);
        }

        public static void hideLoading()
        {
            loadingScreen.setVisible(false);
        }


    }
