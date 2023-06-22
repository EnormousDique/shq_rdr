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

    }
    public static class ElevatorButton extends Rectangle {
        public String text = "";
        public ElevatorButton(int x, int y, String text){
            super(x,y,30,30);
            this.text=text;
        }

    }
}
