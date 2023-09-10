package ru.muwa.shq.minigames;

import java.awt.*;
import java.util.ArrayList;

public class Lift extends MiniGame
{

    public int floorHeight;
    public int floors;
    public int floorNum;
    public int firstFloorX, firstFloorY;
    public ArrayList<String> floorCoords;

    public Lift(int floors, int floorHeight,int floorNum)
    {
        this.floorHeight=floorHeight;
        this.floors=floors;
        buttons = new ArrayList<>();
        this.floorNum=floorNum;
        this.firstFloorX=firstFloorX;this.firstFloorY=firstFloorY;

       // for(int i = 0; i < floors ; i++) buttons.add(new LiftButton((i+1)+""));
    }

    public ArrayList<LiftButton> buttons;

    public static class LiftButton extends Rectangle
    {
        public LiftButton(int x, int y,String text)
        {
            this.x=x;this.y=y;
            this.text = text;
            width=40;height=40;
        }
        public String text = "";

    }
}
