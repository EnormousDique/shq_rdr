package ru.muwa.shq.minigames.shquring;

import ru.muwa.shq.levels.dev.ShqurTestLevel;
import ru.muwa.shq.minigames.MiniGame;
import ru.muwa.shq.objects.containers.Container;

import java.awt.*;
import java.util.ArrayList;

public class GarbageShq extends ShquringMinigame {
    public int startX, startY;
    public boolean init;


    public GarbageShq(Container container)
    {
        this.container = container;
        startX = 10;
        startY = 10;
        stuff=new ArrayList<>();

    }
}
