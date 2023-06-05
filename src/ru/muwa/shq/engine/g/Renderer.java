package ru.muwa.shq.engine.g;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.dialogues.DialogueManager;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.g.hud.MiniGameHUD;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.engine.utilities.InventoryManager;
import ru.muwa.shq.engine.utilities.TradeUtility;
import ru.muwa.shq.items.ItemPanel;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0_BG;
import ru.muwa.shq.objects.Building;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;
import ru.muwa.shq.quests.QuestHUD;
import ru.muwa.shq.zones.GameZone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;

/**
 * Класс, отвечающий за отрисовку изображения на экране.
 */
public class Renderer implements Runnable {
    public static Graphics g; // Объект графики (Объект, который рисует)
    public GameScreen frame; // Игровой экран
    private boolean isSleeping =false;

    public static ArrayList<String> getMessages() {
        return messages;
    }

    private static ArrayList<String> messages = new ArrayList<>();
    private static long lastTimeMessageUpdated;
    public static void addMessage(String s){
        messages.add(s);
        lastTimeMessageUpdated  = System.currentTimeMillis();
    }
    private static void handleMessages(Graphics g){

        //Отрисовываем поле под сообщения
        if(messages.size()>0) {
            g.setColor(new Color(50, 25, 50, 100));
            g.fillRect(SCREEN_WIDTH-200, SCREEN_HEIGHT-200, 200, 200);
        }

        //Отрисовываем сообщения
        g.setColor(Color.GREEN);
        int y = SCREEN_HEIGHT-190;
        for(int i = 0; i < messages.size(); i++)
        {
            g.drawString(messages.get(i), SCREEN_WIDTH - 195,y);
            y+=15;
        }
        //Удаляем старые
        if(lastTimeMessageUpdated + 3_000 < System.currentTimeMillis() && messages.size()>0){
            messages.remove(0);
            lastTimeMessageUpdated=System.currentTimeMillis();
        }

    }

    public static void playSleepyFilter()  {
            instance.isSleeping = true;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("ОШИБКА СНА-съ");
            }
            instance.isSleeping = false;
    }

    public GameScreen getFrame(){return frame;}
    public static JFrame loadingScreen ;
    private Canvas canvas; // Холст на игровом экране
    private Thread thread; // Поток графического движка
    private Player player = Player.get(); // Ссылка на игрока
    public boolean isDrawingBg = true;
    public boolean isShakingCamera = false;

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

        //блокк  кода в кторомом худу перезщапизываем коорды


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
        System.out.println("render thread is running in standard mode");
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
                if(!isSleeping) {
                    try {
                        render();
                    } catch (Exception e) {
                        System.out.println("С  рендерером чет не то, надеюсь отрисовка идет дальше");
                        System.out.println(Arrays.toString(e.getStackTrace()));
                        System.out.println(e.getMessage());
                    }
                }
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
    public void render() throws Exception {
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
        if(isDrawingBg ) g.fillRect(0, 0, GameScreen.SCREEN_WIDTH, GameScreen.SCREEN_HEIGHT);
        //отрисовка обьектов из списка текущих обьектов
        for (int i = 0;i<Engine.getCurrentLevel().getObjects().size();i++){
            GameObject o = Engine.getCurrentLevel().getObjects().get(i);
            if(o instanceof DemoLevel0_BG && !isDrawingBg) {
                g.setColor(new Color(200,200,200,40));
                g.fillRect(camX,camY,SCREEN_WIDTH,SCREEN_HEIGHT);
                continue;
            }

            if(o instanceof Building){

                if(/*Player.get().getSolidBox().intersects(rectangle) */ Player.get().getY() < o.getSolidBox().getY())
                {
                    g.drawImage(o.getTransTexture(), o.getX() - camX, o.getY() - camY, null);
                }
                else g.drawImage(o.getTexture(), o.getX() - camX, o.getY() - camY, null);

            }else{
                g.drawImage(o.getTexture(), o.getX() - camX, o.getY() - camY, null);
            }
        }

        //Отрисовка всех контейнеров из списка  текущих
        for (int i = 0; i< Engine.getCurrentLevel().getContainers().size(); i++) {
            ru.muwa.shq.objects.containers.Container con = Engine.getCurrentLevel().getContainers().get(i);
            g.drawImage(con.getTexture(), con.getX() - camX, con.getY() - camY, null);
        }

        //отрисовка персонажей
        for(int i = 0;i<Engine.getCurrentLevel().getNPC().size();i++){
            NPC c = Engine.getCurrentLevel().getNPC().get(i);
            AffineTransform at = AffineTransform.getTranslateInstance(c.getX() - camX, c.getY() - camY);
            at.rotate(-Math.toRadians((Aim.getInstance().calculateAngleFoNpc(c) -0)), (c.getTexture().getWidth() / 2), (c.getTexture().getHeight()/2.5));
            ((Graphics2D) g).drawImage(c.getTexture(), at, null);

            // g.drawImage(c.getTexture(), c.getX() - camX, c.getY() - camY, c.getWidth(), c.getHeight(), null);
        }
        //Если игрок в поле зрения, цвет луей меняется.
        g.setColor(Color.red);
        for (int i = 0; i < Engine.getCurrentLevel().getNPC().size(); i++) {
            NPC c = Engine.getCurrentLevel().getNPC().get(i);
            if (c.isPlayerInSight()) {
                g.setColor(Color.green);
            }
        }
        /**HUD. Отрисовка и обновление. **/
        //ТЕСТ НОВОГО  ИНВЕНТАРЯ
        try {
            if(InventoryManager.isItemWindowVisible){
                InventoryManager.drawInventory();
                InventoryManager.drawEquipWindow();
            }
            if(QuestHUD.opened) QuestHUD.drawJournal();
            InventoryManager.drawContainerWindow();
            MiniGameHUD.work();
        }  catch (Exception e) {
            System.out.println("тетовому инвентарю не оч");
            System.out.println(e.getMessage());
        }

        //ОТРИСОВКА СООБЩЕНИЙ НА ЭКРАНЕ
        handleMessages(g);
            //Вызов службы диалогов.
            DialogueManager.work();
            // Вызов службы торговли
            TradeUtility.work();
        InventoryManager.update();


        // ОТРИСОВКА ПЕрСОНАЖА
        AffineTransform at = AffineTransform.getTranslateInstance(Player.get().getX() - camX, Player.get().getY() - camY);
        at.rotate(-Math.toRadians(Aim.getInstance().calculateAngle()), (Player.get().getTexture().getWidth() / 2), (Player.get().getTexture().getHeight()/2.5));
        ((Graphics2D) g).drawImage(Player.get().getTexture(), at, null);




        //Отрисовка тени дня и ночи

            if(Engine.getCurrentLevel().isStreet() && isDrawingBg ) {
                switch (TimeMachine.getTimeOfTheDay())
                {

                    case MORNING:
                        g.setColor(new Color(250, 250,0 , 30));
                        g.fillRect(camX - Player.get().getX() + (SCREEN_WIDTH / 2) - 100, camY - Player.get().getY() + (SCREEN_HEIGHT / 2) - 100, SCREEN_WIDTH + 100, SCREEN_HEIGHT + 100);
                        break;
                case EVENING:
                    g.setColor(new Color(20, 5, 5, 80));
                    g.fillRect(camX - Player.get().getX() + (SCREEN_WIDTH / 2) - 100, camY - Player.get().getY() + (SCREEN_HEIGHT / 2) - 100, SCREEN_WIDTH + 100, SCREEN_HEIGHT + 100);
                    break;
                    case NIGHT:
                        g.setColor(new Color(0, 0, 0, 200));
                        g.fillRect(camX - Player.get().getX() + (SCREEN_WIDTH / 2) - 100, camY - Player.get().getY() + (SCREEN_HEIGHT / 2) - 100, SCREEN_WIDTH + 100, SCREEN_HEIGHT + 100);
                        break;

                    case SUNRISE:
                        g.setColor(new Color(50, 10, 0, 150));
                        g.fillRect(camX - Player.get().getX() + (SCREEN_WIDTH / 2) - 100, camY - Player.get().getY() + (SCREEN_HEIGHT / 2) - 100, SCREEN_WIDTH + 100, SCREEN_HEIGHT + 100);
                        break;
                }
        }



        // ОТРИСОКВА ТЕСТИРУЕМЫХ ФИЧ
        //
        //
            // отрисоква бокса игрока
            g.drawRect(player.getX() - camX, player.getY() - camY, (int)player.getSolidBox().getWidth(), (int) player.getSolidBox().getHeight());
            //Отрисовка зон
            g.setColor(Color.BLUE);
            for (GameZone z : Engine.getCurrentLevel().getZones())
                g.drawRect(z.x - camX, z.y - camY, z.width, z.height);
            // Отрисовка линий прицела
            // for (Line2D l : Aim.getInstance().getLines())
            //   g.drawLine((int) l.getX1() - camX, (int) l.getY1() - camY, (int) l.getX2() - camX, (int) l.getY2() - camY);

            //отрисовка координат мыши.
            g.setColor(Color.red);
            g.setFont(g.getFont().deriveFont(Font.BOLD));
            g.drawString(""+ MouseListener.getInstance().getX()+" "+ MouseListener.getInstance().getY(),100,100);
            g.setColor(Color.cyan);
            g.drawString(""+Camera.getInstance().getX()+" "+Camera.getInstance().getY(),100,130);
            //отрисовка времени
        g.setColor(Color.white);
        g.drawString(TimeMachine.getStringTime(),100,200);
        g.setColor(new Color(250,0,250,250));
        //отрисовка полосок
        String hp = "";
        try{(Player.get().getHp() +"").substring(0,4);}catch (Exception e){}
        g.drawString("хп : "+hp ,10,15);
        g.drawLine(10,30,10+(int)Player.get().getHp(),30);
        g.drawString("Вода в организме :" ,140,15);
       // g.drawString(Player.get().getThirst()+"" ,100,320);
        g.drawLine(140,30,140+(int)Player.get().getThirst(),30);
        g.drawString("психометр :" ,270,15);
        g.drawLine(270,30,270+(int)Player.get().getHighMeter(),30);
        g.setColor(Color.green);
        g.drawLine(270,50,270+(int)Player.get().getHighMeterLock(),50);
        g.setColor(new Color(250,0,250,250));
        g.drawString("запас сил :",400,15);
        g.drawLine(400,30, (int) (400+Player.get().getStamina()),30);
        g.drawString("$$$ : "+Player.get().money,10,120);
        g.drawString("Сытость :",530,15);
        g.drawLine(530,30, (int) (530+Player.get().hunger),30);
        g.drawString("Бодрость  :",660,15);
        g.drawLine(660,30, (int) (660+Player.get().awake),30);





        // отрисовка информации о предмете при наводе мышки на оный
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
