package ru.muwa.shq.minigames;

import ru.muwa.shq.objects.containers.Container;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PostBoxShq extends MiniGame{
    public int startX, startY;
    public boolean init;
    public ArrayList<Rectangle> stuff;
    public ru.muwa.shq.objects.containers.Container container;

    public PostBoxShq(Container container)
    {
        this.container = container;
        startX = 10;
        startY = 10;
        stuff=new ArrayList<>();

    }
}
