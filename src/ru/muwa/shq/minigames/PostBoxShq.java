package ru.muwa.shq.minigames;

import ru.muwa.shq.objects.containers.Container;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PostBoxShq extends MiniGame{
    public int startX, startY;
    public ArrayList<Rectangle> obstacles;
    public Rectangle destination;
    public ru.muwa.shq.objects.containers.Container container;

    public PostBoxShq(Container container)
    {
        this.container = container;
        startX = 10;
        startY = 10;
        obstacles = new ArrayList<Rectangle>(List.of(new Rectangle(200,100,50,50)));
        destination = new Rectangle(507,335,20,20);
        
    }
}
