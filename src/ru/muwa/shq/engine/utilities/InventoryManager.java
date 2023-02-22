package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import java.awt.*;
import java.util.ArrayList;


public class InventoryManager
{
    static private InventoryManager instance;
    private InventoryManager(){instance = this;}
    public static InventoryManager getInstance()
    {if (instance == null) return new InventoryManager(); else return instance;}

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
        grab();

        for(Rectangle r:Inventory.getInstance().getItemIcons())
        {
            if(r!=null) r.setBounds(Inventory.getInstance().getX(),Inventory.getInstance().getY(),r.width,r.height);
        }
    }
    //
    public void grab(){
        if(MouseButtonListener.getInstance().getKeys()[0] ) {
            System.out.println("нажате левая кнопка мыши");
            for(Container c: Engine.currentLevel.getContainers()){
                if(c.isInUse() && c.getItems().size()>= 1){
                    System.out.println("есть открытый контейнер");

                    for(int i = 0; i < c.getIcons().size(); i++){
                        System.out.println("смотрим квадрат с координатама "+ c.getIcons().get(i).x +" "+ c.getIcons().get(i).y);
                        System.out.println("мышка бьет"+MouseListener.getInstance().getX()+ Camera.getInstance().getX()+" "+MouseListener.getInstance().getY()+Camera.getInstance().getY());
                        if (c.getIcons().get(i).contains(new Point(MouseListener.getInstance().getX()+ Camera.getInstance().getX(), MouseListener.getInstance().getY()+Camera.getInstance().getY()))){
                            System.out.println("есть пробитие");
                            c.grabItem(i);
                        }
                    }
                }
            }
            MouseButtonListener.getInstance().keys[0]=false;
        }

    }
}

