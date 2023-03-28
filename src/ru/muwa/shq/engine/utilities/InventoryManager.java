package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.combat.CombatUtility;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.ItemPanel;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;
import java.util.Arrays;
import java.util.Map;


public class InventoryManager
{

    static private InventoryManager instance;
    private InventoryManager(){
        instance = this;

    }
    public static InventoryManager getInstance()
    {if (instance == null) return new InventoryManager(); else return instance;}

    private void updateStatusWindow() throws IOException
    {

        BufferedImage face = null;
     if(Player.get().getHp() <= 100) {
         face = ImageIO.read(new File(IMG_PATH + "face/FACE.png"));
    }
     if(Player.get().getHp() <= 70) {
         face = ImageIO.read(new File(IMG_PATH + "face/DAMAGEDFACENOBLOOD.png"));
     }
        if(Player.get().getHp() <= 40) {
            face = ImageIO.read(new File(IMG_PATH + "face/DAMAGEDFACE.png"));
        }
        if(Player.get().getHp() <= 10) {
            face = ImageIO.read(new File(IMG_PATH + "face/DAMAGEDFACEBLOOD.png"));
        }
        String s = " " ;
        for(Map.Entry<EffectUtility.Effects,Long> entry :EffectUtility.getInstance().getCurrentEffects().entrySet()){
            switch (entry.getKey()){
                case SPEED :
                    //TODO добавить побольше проверок if на здоровье (типа когда он обутюженный и хп > 50 то на ебале кровь
                    if(entry.getValue() > System.currentTimeMillis()) {
                        s += "я под мефом";
                        face = ImageIO.read(new File(IMG_PATH + "face/FACEMEF.png"));
                    } break;
            }

        }

        JLabel faceIcon = new JLabel(new ImageIcon(face));
        Arrays.stream(HUD.getInstance().getStatusWindow().getComponents()).forEach(HUD.getInstance().getStatusWindow()::remove);
        HUD.getInstance().getStatusWindow().add(faceIcon);
        HUD.getInstance().getStatusWindow().add(new JLabel("ТВОЁ ЗДОРОВЬЕ! Друг!"));
        HUD.getInstance().getStatusWindow().add(new JLabel(String.valueOf(Player.get().getHp())));
        JLabel effectsLabel = new JLabel(s);
        HUD.getInstance().getStatusWindow().add(effectsLabel);
        HUD.getInstance().getStatusWindow().updateUI();


    }

    public void drawContainerItems(Graphics g, Container c) {

    }
    public void update()
    {




        Inventory.getInstance().setX(Player.get().getX() + 100);
        Inventory.getInstance().setY(Player.get().getY() - 50);


        Inventory.getInstance().getBox().setBounds
                (Inventory.getInstance().getX(),
                 Inventory.getInstance().getY(),
                (int)Inventory.getInstance().getImg().getWidth(),
                (int)Inventory.getInstance().getImg().getHeight());


        for(int i = 0; i < Inventory.getInstance().getItemIcons().size();i++)
        {
            if(Inventory.getInstance().getItemIcons().get(i)!=null) {
                Inventory.getInstance().getItemIcons().get(i).setBounds(Inventory.getInstance().getX()+(50*(i)),Inventory.getInstance().getY(),50,50);
            }

        }
        updateItemWindow();
        // вызов морды персонажа
        try {//TODO: ВОВАН ПЕРЕНЕСИ БЛОК ТРАЙ КЕЧ В НУТЫРЬ МЕТОДА. НЕ надо тут его разводить (сделай так чтобы у апдейт статус виндоувс небыло ексептиона
            updateStatusWindow();
        } catch (IOException e) {
            System.out.println("НЕ ГРУЗИТ КАРТИНКУ СУКА");
            throw new RuntimeException(e);
        }
        //TODO: Нужно перенести логику так, чтобы она вызывалась из PlayerControls.

    }
    //
    public void grab(){

            // System.out.println("нажата левая кнопка мыши");
            for(Container c: Engine.getCurrentLevel().getContainers()){
                if(c.isInUse() && c.getItems().size()>= 1){
                    //System.out.println("есть открытый контейнер");

                    for(int i = 0; i < c.getIcons().size(); i++){


                        if (c.getIcons().get(i).contains(new Point(MouseListener.getInstance().getX()+ Camera.getInstance().getX(), MouseListener.getInstance().getY()+Camera.getInstance().getY()))){

                            c.grabItem(i);
                        }
                    }
                }
            }



    }
    public void eat (){

        if(MouseButtonListener.getInstance().event.getSource().equals(HUD.getInstance().getItemWindow()) )
        {
            int mx = MouseButtonListener.getInstance().event.getX();
            int my = MouseButtonListener.getInstance().event.getY();
            int cx = Camera.getInstance().getX();
            int cy = Camera.getInstance().getY();
            int x = mx - HUD.getInstance().getItemWindow().getX();
            int y = my - HUD.getInstance().getItemWindow().getY();
            for (Component c : HUD.getInstance().getItemWindow().getComponents()) {
                //System.out.println(c);
                if (c instanceof ItemPanel) {
                    Rectangle rect = new Rectangle(c.getX(), c.getY(), c.getWidth(), c.getHeight());
                    //System.out.println("ЕСТЬ ПАНЕЛЬ");

                    System.out.println("координаты панели " + rect);
                    System.out.println("координаты мыши " + mx + " " + my);

                    if (rect.contains(new Point(mx, my))) {
                        System.out.println("POPAL");
                        ((ItemPanel) c).getItem().use();
                        Inventory.getInstance().getItems().remove(((ItemPanel) c).getItem());

                    }
                }
            }
            HUD.getInstance().getItemWindow().updateUI();
        }

        /*
        for(Component c : HUD.getInstance().getItemWindow().getComponents())
        {
            if(c instanceof ItemPanel)
            {
                int mx = MouseListener.getInstance().getX();
                int cx =Camera.getInstance().getX();
                int my = MouseListener.getInstance().getY();
                int cy =Camera.getInstance().getY();
                Rectangle rect = new Rectangle(c.getX(),c.getY(),c.getWidth(),c.getHeight());
                if(rect.contains(new Point(mx+cx,my+cy)))
                {
                    System.out.println("HIT");
                }

            }
        }
        /*
         //   System.out.println("кнопку мышки я нажал");
            if(Inventory.getInstance().isOpened()){
               // System.out.println("инвентарь при том открыл");
                for(Rectangle r :Inventory.getInstance().getItemIcons()){

                    if(r.contains(new Point(MouseListener.getInstance().getX()+Camera.getInstance().getX(),MouseListener.getInstance().getY()+Camera.getInstance().getY() ))){
                        if(Inventory.getInstance().getItems().size() > Inventory.getInstance().getItemIcons().indexOf(r))
                        Inventory.getInstance().getItems().remove(Inventory.getInstance().getItemIcons().indexOf(r));
                        Player.get().setHp(Player.get().getHp()+10);// TODO исправить систему отхила так как сейчас хиляет от употребления любого предмета в инвентаре

                         System.out.println("ВЗЯЛ");
                        //System.out.println("не взял");
                        break;

                    }
                }
            }
            */
        }

        private void updateItemWindow(){

            //Получаем окно
            JPanel window = HUD.getInstance().getItemWindow();

            //Инициализация лок. переменных, которые будут нужны в цикле
            int itemsSize = Inventory.getInstance().getItems().size(); //Кол-во вещей
            ArrayList<ItemPanel> itemTiles = new ArrayList<>(); // Список панелек для вещей
            int yOffset = 50; // Смещение по оси у (для переноса строки каждые 4 панельки)


            //Цикл
            //  Проходимся по вещам из инвентаря, и добавляем панель под каждую на окно ItemWindow
            for(int i = 0; i<Inventory.getInstance().getItems().size(); i++)
            {
                //
                if(Inventory.getInstance().getItems().get(i) !=null) {
                    itemTiles.add(new ItemPanel(Inventory.getInstance().getItems().get(i)));
                    itemTiles.get(i).addMouseListener(MouseButtonListener.getInstance());
                }
                if(Inventory.getInstance().getItems().get(i).getTexture() !=null)
                itemTiles.get(i).setIcon(new ImageIcon( Inventory.getInstance().getItems().get(i).getTexture()));
            }

            Arrays.stream(window.getComponents()).forEach(window::remove);

            //Добавляем заголовок
            JLabel titleLabel = new JLabel("BEW,N");
            window.add(titleLabel);
            titleLabel.setBounds(10,10,100,20);

            for(int i = 0; i<itemTiles.size(); i++)
            {
                if(i != 0 && i % 4 == 0){ yOffset += 50; }//Перенос строки после 4-х панелек
                window.add(itemTiles.get(i));
                itemTiles.get(i).setBounds(i%4*50,yOffset,40,40);

            }
        }
}

