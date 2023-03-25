package ru.muwa.shq.items;

import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import java.awt.*;

public class ItemsInfo {
    public void itemInfoCheck (){

        //   System.out.println("кнопку мышки я нажал");
        if(Inventory.getInstance().isOpened()){
            // System.out.println("инвентарь при том открыл");
            for(Rectangle r :Inventory.getInstance().getItemIcons()){

                if(r.contains(new Point(MouseListener.getInstance().getX()+ Camera.getInstance().getX(),MouseListener.getInstance().getY()+Camera.getInstance().getY() ))){





                    System.out.println("ВЗЯЛ");
                    //System.out.println("не взял");
                    break;

                }
            }
        }
    }
}
