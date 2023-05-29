package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.economics.money.Money;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Picktogram;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.ItemPanel;
import ru.muwa.shq.items.guns.Firearm;
import ru.muwa.shq.items.guns.Weapon;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.objects.containers.ContainerPanel;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.Quest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

import java.util.Map;
import java.util.stream.Collectors;


public class InventoryManager
{



    private static void updateStatusWindow()throws Exception
    {
        try {

            BufferedImage face = null;
            if (Player.get().getHp() <= 100) {
                face = ImageIO.read(new File(IMG_PATH + "face/FACE.png"));
            }
            if (Player.get().getHp() <= 70) {
                face = ImageIO.read(new File(IMG_PATH + "face/DAMAGEDFACENOBLOOD.png"));
            }
            if (Player.get().getHp() <= 40) {
                face = ImageIO.read(new File(IMG_PATH + "face/DAMAGEDFACE.png"));
            }
            if (Player.get().getHp() <= 10) {
                face = ImageIO.read(new File(IMG_PATH + "face/DAMAGEDFACEBLOOD.png"));
            }
            String s = " ";
            for (Map.Entry<EffectUtility.Effects, Long> entry : EffectUtility.getCurrentEffects().entrySet()) {
                switch (entry.getKey()) {
                    case StaminaRegen:
                        //TODO добавить побольше проверок if на здоровье (типа когда он обутюженный и хп > 50 то на ебале кровь
                        if (entry.getValue() > System.currentTimeMillis()) {
                            s += "я под мефом";
                            face = ImageIO.read(new File(IMG_PATH + "face/FACEMEF.png"));
                        }
                        break;
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

        }catch (Exception e){
            System.out.println("НЕ ГРУЗИТ КАРТИНКУ здоровья СУКА");
        }
    }

    public static void drawContainerItems(Graphics g, Container c) {

    }
    public static void update()
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

        try {
         //   updateItemWindow();
         //   updateEquipWindow();
        //    updateStatusWindow();
       //     updateQuestWindow();

        }catch (Exception e){
            System.out.println("инвентарь навернулся. все ок, едем дальше");
           // System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }

        convertMoney();

        //TODO: Нужно перенести логику так, чтобы она вызывалась из PlayerControls.
        //TODO: Хули до сих пор не сделано)))))
        //TODO:A?

    }

    private static void convertMoney() {
        for(int i = 0; i < Inventory.getInstance().getItems().size();i++)
        {
            if(Inventory.getInstance().getItems().get(i) instanceof Money)
            {
                Player.get().money+= Inventory.getInstance().getItems().get(i).getPrice();
                Inventory.getInstance().getItems().remove(i);
            }
        }
    }


    public static boolean isItemWindowVisible;
    public  static int itemWindowX;
    public  static  int itemWindowY;
    public static  ArrayList<Picktogram> itemWindowPicks = new ArrayList<>();
    public static void drawInventory() throws Exception
    {
        Color oldColor = Renderer.g.getColor();
        Renderer.g.setColor(Color.WHITE);
        Renderer.g.fillRect(itemWindowX,itemWindowY,300,300);

        //Цикл
        //  Проходимся по вещам из инвентаря, и добавляем панель под каждую на окно ItemWindow
        for(int i = 0 ; i < Inventory.getInstance().getItems().size(); i++)
        {
            Picktogram pic = new Picktogram();
            pic.item = Inventory.getInstance().getItems().get(i);

            itemWindowPicks.add(pic);

            pic.x = i%5 *50;
            pic.y = (i/5)*50;
            pic.width = 50;
            pic.height = 50;
            if (!pic.item.isEquipped())Renderer.g.drawImage(pic.item.getTexture(),pic.x,pic.y,null);
            else {

                Renderer.g.setColor(new Color(150,10,10,100));

                Renderer.g.drawImage(pic.item.getTexture(),pic.x,pic.y,null);
                Renderer.g.fillRect(pic.x, pic.y, pic.width, pic.height);




            }
        }
        Renderer.g.setColor(oldColor);
    }

    static  int EWx = 450, EWy = 30, EWWidth = 80, EWHeight=80;
    public static Picktogram equipPic;
    public static void drawEquipWindow()
    {
        Color oldColor  = Renderer.g.getColor();
        Renderer.g.setColor(Color.WHITE);
        Renderer.g.fillRect(EWx,EWy,EWWidth,EWHeight);


        Picktogram pic = new Picktogram();
        if(Inventory.getInstance().getItems().stream().anyMatch(i -> i.isEquipped()))
        {
             Item item = Inventory.getInstance().getItems().stream()
                     .filter(i->i.isEquipped()).collect(Collectors.toList())
                     .get(0);//Должно быть ок. Если код запустился, то хоть 1 вещь да взята.

             pic.setBounds(EWx +10, EWy+10,item.getTexture().getWidth(),item.getTexture().getHeight());
             pic.item = item;
             Renderer.g.drawImage(pic.item.getTexture(),pic.x,pic.y,null);
             equipPic = pic;

             Font oldFont=Renderer.g.getFont();
             Renderer.g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));
             if(pic.item instanceof Firearm)
                 Renderer.g.drawString("Пули-с: \n"+((Weapon)pic.item).getCurrAmmo(),EWx,EWy+10);
             else if(pic.item instanceof Weapon)
                Renderer.g.drawString("Прочность: \n"+((Weapon)pic.item).getDurability(),EWx,EWy+10);



             Renderer.g.setFont(oldFont);
        }
        Renderer.g.setColor(oldColor);
    }


    static int containerWindowX, containerWindowY, CWHeight=300, CWWidth=300;
    public static ArrayList<Picktogram> containerWindowPicks = new ArrayList<>();
    public static void drawContainerWindow()
    {
        Container c = null;
        for(int  i = 0; i < Engine.getCurrentLevel().getContainers().size();i++)
        {
            if(Engine.getCurrentLevel().getContainers().get(i).isInUse()) c = Engine.getCurrentLevel().getContainers().get(i);
        }
        if(c!=null)
        {

            Color oldColor = Renderer.g.getColor();

            containerWindowX = (int)c.getSolidBox().getCenterX()-Camera.getInstance().getX();
            containerWindowY = (int)c.getSolidBox().getCenterY()-Camera.getInstance().getY();

            Renderer.g.setColor(Color.WHITE);
            Renderer.g.fillRect(containerWindowX,containerWindowY,CWWidth,CWHeight);

            for(int i = 0 ; i < c.getItems().size(); i++) {
                Picktogram pic = new Picktogram();
                containerWindowPicks.add(pic);

                pic.x = (i%4 *50) + containerWindowX;
                pic.y = ((i/4)*50) + containerWindowY;

                pic.item = c.getItems().get(i);
                pic.width= pic.item.getTexture().getWidth();
                pic.height= pic.item.getTexture().getHeight();
                Renderer.g.drawImage(pic.item.getTexture(),pic.x,pic.y,null);
            }
            Renderer.g.setColor(oldColor);
        }
    }
    public static void updateContainerWindow() {
        JPanel containerWindow = HUD.getInstance().getContainerWindow();

        ArrayList<ContainerPanel> containerTiles = new ArrayList<>(); // Список панелек для вещей
        int yOffset = 50; // Смещение по оси у (для переноса строки каждые 4 панельки)

        for (int j = 0; j< Engine.getCurrentLevel().getContainers().size();j++) {
            Container c = Engine.getCurrentLevel().getContainers().get(j);
            if (c.isInUse()/* && c.getItems().size() >=  1*/) {
                //отрисовка инвентаря контейнера
                HUD.getInstance().getContainerWindow().setVisible(true);
                // g.drawImage(c.getUI(), c.getX() - camX, c.getY() - camY, null);
                // отрисовка предмета в контейнере.
                for (int i = 0; i < c.getItems().size(); i++) {
                    try {
                        if (!c.getItems().get(i).isEquipped()) {
                            if (c.getItems().get(i) != null) {
                                containerTiles.add(new ContainerPanel(c.getItems().get(i)));
                                containerTiles.get(i).addMouseListener(MouseButtonListener.getInstance());
                            }
                            if (c.getItems().get(i).getTexture() != null)
                                containerTiles.get(i).setIcon(new ImageIcon(c.getItems().get(i).getTexture()));
                        }
                    }catch (Exception e) {
                        System.out.println("Исключение при отрисовке шмона. жить можно, идем дальше");
                        return;
                    }
                }
            }
        }

        Arrays.stream(containerWindow.getComponents()).forEach(containerWindow::remove);
        JLabel shmonlabel = new JLabel("ШМОН");
        containerWindow.add(shmonlabel);
        shmonlabel.setBounds(10,10,100,20);
        for(int i = 0; i<containerTiles.size(); i++) {
            if(i != 0 && i % 4 == 0){ yOffset += 50; }//Перенос строки после 4-х панелек
            containerWindow.add(containerTiles.get(i));
            containerTiles.get(i).setBounds(i%4*50,yOffset,50,50);
        }


    }
        private static void updateEquipWindow()throws Exception
        {
            Arrays.stream(HUD.getInstance().getEquipWindow().getComponents()).forEach(HUD.getInstance().getEquipWindow()::remove);

            JLabel title = new JLabel("EQUIP");
            HUD.getInstance().getEquipWindow().add(title);
            title.setBounds(10,10,50,30);

            for(int i = 0; i<Inventory.getInstance().getItems().size(); i++)
            {
                Item item = Inventory.getInstance().getItems().get(i);

                if(item.isEquipped()){

                    ItemPanel panel = new ItemPanel(item);
                    HUD.getInstance().getEquipWindow().add(panel);
                    panel.setBounds(20,50,50,50);
                    panel.setIcon(new ImageIcon(panel.getItem().getTexture()));
                    panel.addMouseListener(MouseButtonListener.getInstance());

                    if(item instanceof Firearm){
                    String ammoString = "Пули-с : " + ((Weapon) item).getCurrAmmo() + "/" + ((Weapon) item).getMaxAmmo();
                    JLabel ammoLabel = new JLabel(ammoString);
                    HUD.getInstance().getEquipWindow().add(ammoLabel);
                    ammoLabel.setBounds(10,120,100,30);
                    }
                    else if(item instanceof Weapon)
                    {
                        String durabilityString = "Прочность: " + ((Weapon) item).getDurability();
                        JLabel durabilityLabel = new JLabel(durabilityString);
                        HUD.getInstance().getEquipWindow().add(durabilityLabel);
                        durabilityLabel.setBounds(10,120,100,30);
                    }

                }
            }
            HUD.getInstance().getEquipWindow().updateUI();
        }
        public static void updateQuestWindow() throws Exception
        {

           // Arrays.stream(HUD.getInstance().getQuestWindow().getComponents()).forEach(HUD.getInstance().getQuestWindow()::remove);}
            for(int i = 0; i < HUD.getInstance().getQuestWindow().getComponents().length;i++)
            {

                    HUD.getInstance().getQuestWindow().remove(i);


            }


            String s;
            ArrayList<Quest> activeQuests = new ArrayList<>(Player.get().quests.stream().filter(q-> !q.tasks.get(q.tasks.size()-1).isCompleted).collect(Collectors.toList()));

            for(int i =0 ; i< activeQuests.size(); i++)
            {

                s = "Квест " + (Player.get().quests.indexOf(activeQuests.get(i))+1);
                JLabel label = new JLabel(s);
                HUD.getInstance().getQuestWindow().add(label);
                label.setBounds(0,i*50,200,30);

                for(int j = 0 ; j < activeQuests.get(i).tasks.size(); j++)
                {
                    s = activeQuests.get(i).tasks.get(j).name + " : " + (activeQuests.get(i).tasks.get(j).isCompleted?"v":"x");
                    label = new JLabel(s);
                    HUD.getInstance().getQuestWindow().add(label);
                    label.setBounds(0,(i*50)+(j+1)*12,200,30);
                }
            }
            HUD.getInstance().getQuestWindow().updateUI();
        }
}

