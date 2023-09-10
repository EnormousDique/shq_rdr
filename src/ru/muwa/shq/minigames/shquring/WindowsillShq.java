package ru.muwa.shq.minigames.shquring;

import ru.muwa.shq.objects.containers.Container;

import java.util.ArrayList;

public class WindowsillShq extends ShquringMinigame{
    public int startX, startY;
    public boolean init;



    public WindowsillShq(Container container)
    {
        this.container = container;
        startX = 10;
        startY = 10;
        stuff=new ArrayList<>();

    }
}
