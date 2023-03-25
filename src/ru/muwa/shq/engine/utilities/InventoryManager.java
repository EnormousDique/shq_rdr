package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;


public class InventoryManager
{
    static private InventoryManager instance;
    private InventoryManager(){instance = this;}
    public static InventoryManager getInstance()
    {if (instance == null) return new InventoryManager(); else return instance;}

    private void updateStatusWindow() throws IOException
    {

    BufferedImage face = ImageIO.read(new File(IMG_PATH+"face/FACE.png"));

        JLabel faceIcon = new JLabel(new ImageIcon(face));
        Arrays.stream(HUD.getInstance().getStatusWindow().getComponents()).forEach(HUD.getInstance().getStatusWindow()::remove);
        HUD.getInstance().getStatusWindow().add(faceIcon);
        HUD.getInstance().getStatusWindow().add(new JLabel("ТВОЁ ЗДОРОВЬЕ!"));
        HUD.getInstance().getStatusWindow().add(new JLabel(String.valueOf(Player.get().getHp())));
        HUD.getInstance().getStatusWindow().updateUI();
    }

    public void drawContainerItems(Graphics g, Container c) {

    }
    public void update()
    {
        // вызов морды персонажа
        try {
            updateStatusWindow();
        } catch (IOException e) {
            System.out.println("НЕ ГРУЗИТ КАРТИНКУ СУКА");
            throw new RuntimeException(e);
        }
        //TODO: Нужно перенести логику так, чтобы она вызывалась из PlayerControls.

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
        }

}

