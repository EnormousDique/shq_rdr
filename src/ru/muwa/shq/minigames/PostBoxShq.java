package ru.muwa.shq.minigames;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PostBoxShq extends MiniGame{
    public int startX, startY;
    public ArrayList<Rectangle> obstacles;
    public Rectangle destination;

    public PostBoxShq()
    {
        startX = 10;
        startY = 10;
        obstacles = new ArrayList<Rectangle>(List.of(new Rectangle(200,100,50,50)));
        destination = new Rectangle(200,200,100,100);
        
    }
}
