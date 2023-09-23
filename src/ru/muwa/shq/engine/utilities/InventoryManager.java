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
    public static Picktogram equipPic, footwearPic, torsowearPic, headwearPic;
    public static void drawEquipWindow() {

        Color oldColor = Renderer.g.getColor();
        Font oldFont = Renderer.g.getFont();
        Picktogram pic = new Picktogram();
        Renderer.g.setColor(new Color(0, 250, 150, 150));

        if (Player.get().currentWeapon != null) {

            Renderer.g.fillRect(EWx, EWy, EWWidth, EWHeight);

            Item item = Player.get().currentWeapon;

            pic.setBounds(EWx + 10, EWy + 10, item.getTexture().getWidth(), item.getTexture().getHeight());
            pic.item = item;
            Renderer.g.drawImage(pic.item.getTexture(), pic.x, pic.y, null);
            equipPic = pic;

            Renderer.g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
            if (pic.item instanceof Firearm)
                Renderer.g.drawString("Пули-с: \n" + ((Weapon) pic.item).getCurrAmmo(), EWx + 100, EWy + 30);

            else if (pic.item instanceof Weapon)
                Renderer.g.drawString("Прочность: \n" + ((Weapon) pic.item).getDurability(), EWx + 100, EWy + 30);
        }
        if(Player.get().footWear != null)
        {
            Item item = Player.get().footWear;
            pic = new Picktogram();
            pic.item = item;
            pic.setBounds(EWx + 10, EWy + 10 - 100, item.getTexture().getWidth(), item.getTexture().getHeight());
            Renderer.g.fillRect(EWx,EWy - 100,EWWidth,EWHeight);
            Renderer.g.drawImage(pic.item.getTexture(),pic.x,pic.y,null);
            footwearPic = pic;
        }
        if(Player.get().torsoWear != null)
        {
            Item item = Player.get().torsoWear;
            pic = new Picktogram();
            pic.item = item;
            pic.setBounds(EWx + 10+ 70, EWy + 10 - 100, item.getTexture().getWidth(), item.getTexture().getHeight());
            Renderer.g.fillRect(EWx + 70,EWy - 100,EWWidth,EWHeight);
            Renderer.g.drawImage(pic.item.getTexture(),pic.x,pic.y,null);
            torsowearPic = pic;
        }
        if(Player.get().headWear != null)
        {
            Item item = Player.get().headWear;
            pic = new Picktogram();
            pic.item = item;
            pic.setBounds(EWx + 10+ 140, EWy + 10 - 100, item.getTexture().getWidth(), item.getTexture().getHeight());
            Renderer.g.fillRect(EWx + 140,EWy - 100,EWWidth,EWHeight);
            Renderer.g.drawImage(pic.item.getTexture(),pic.x,pic.y,null);
            headwearPic = pic;
        }


        Renderer.g.setFont(oldFont);
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

