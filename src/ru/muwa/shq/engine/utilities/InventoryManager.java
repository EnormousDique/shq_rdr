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

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
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

    public static void update()
    {
        try {
            convertMoney();
        } catch (Exception e) {
            System.out.println("обменник заболел");
        }
    }

    private static void convertMoney() throws Exception {
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
    public  static  int itemWindowY=SCREEN_HEIGHT-300;
    public static  ArrayList<Picktogram> itemWindowPicks = new ArrayList<>();
    public static void drawInventory() throws Exception
    {
        Color oldColor = Renderer.g.getColor();
        Renderer.g.setColor(new Color(250,250,250,150));
        Renderer.g.fillRect(itemWindowX,itemWindowY,300,300);
        itemWindowPicks = new ArrayList<>();

        //Цикл
        //  Проходимся по вещам из инвентаря, и добавляем панель под каждую на окно ItemWindow
        for(int i = 0 ; i < Inventory.getInstance().getItems().size(); i++)
        {
            Picktogram pic = new Picktogram();
            pic.item = Inventory.getInstance().getItems().get(i);

            itemWindowPicks.add(pic);

            pic.x = i%5 *50;
            pic.y = (i/5)*50 + itemWindowY;
            pic.width = 50;
            pic.height = 50;
            if (!pic.item.isEquipped()) {
                Renderer.g.drawImage(pic.item.getTexture(), pic.x, pic.y, null);
                if(pic.item.isStackable()) {
                    Font f = Renderer.g.getFont();
                    Renderer.g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));
                    Renderer.g.setColor(Color.CYAN);
                    Renderer.g.drawString(""+pic.item.amount,pic.x+10,pic.y+35);
                    Renderer.g.setFont(f);
                }
            }
            else {
                Renderer.g.setColor(new Color(150,10,10,100));

                Renderer.g.drawImage(pic.item.getTexture(),pic.x,pic.y,null);
                Renderer.g.fillRect(pic.x, pic.y, pic.width, pic.height);
            }
        }
        Renderer.g.setColor(oldColor);
    }

    static  int EWx = 0, EWy = 340, EWWidth = 80, EWHeight=80;
    public static Picktogram equipPic;
    public static void drawEquipWindow()
    {
        Color oldColor  = Renderer.g.getColor();
        Renderer.g.setColor(new Color(0,250,150,150));
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
             Renderer.g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,10));
             if(pic.item instanceof Firearm)
                 Renderer.g.drawString("Пули-с: \n"+((Weapon)pic.item).getCurrAmmo(),EWx+100,EWy+30);
             else if(pic.item instanceof Weapon)
                Renderer.g.drawString("Прочность: \n"+((Weapon)pic.item).getDurability(),EWx+100,EWy+30);

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

            Renderer.g.setColor(new Color(250,250,250,150));
            Renderer.g.fillRect(containerWindowX,containerWindowY,CWWidth,CWHeight);

            containerWindowPicks=new ArrayList<>();

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

}

