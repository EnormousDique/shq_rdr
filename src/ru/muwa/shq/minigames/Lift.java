package ru.muwa.shq.minigames;

import java.awt.*;
import java.util.ArrayList;

public class Lift extends MiniGame
{

    public int firstFloorX, firstFloorY;
    public ArrayList<Point> floorCoords;

    public Lift(){}

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
