package ru.muwa.shq.minigames;

import ru.muwa.shq.engine.g.hud.MiniGameHUD;
import ru.muwa.shq.levels.Level;

import java.awt.*;
import java.util.ArrayList;

public class Elevator extends MiniGame{
    public ArrayList<Level> levels;
    public int distX,distY;
    public ArrayList<ElevatorButton> buttons = new ArrayList<>();

    public Elevator(ArrayList<Level> levels,int distX, int distY){
        this.distX=distX;
        this.distY=distY;
        this.levels=levels;
        for(int i =0; i< levels.size();i++)
        {
            buttons.add(new ElevatorButton((i%2 * 50)+ MiniGameHUD.x,(i/2 * 40) + MiniGameHUD.y,(i+1)+""));
        }
    }
    public static class ElevatorButton extends Rectangle {
        public String text = "";
        public ElevatorButton(int x, int y, String text){
            super(x,y,30,30);
            this.text=text;
        }

    }
}
