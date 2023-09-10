package ru.muwa.shq.minigames.shquring;

import ru.muwa.shq.minigames.MiniGame;
import ru.muwa.shq.objects.containers.Container;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PostBoxShq extends ShquringMinigame {
    public int startX, startY;

    public PostBoxShq(Container container)
    {
        this.container = container;
        startX = 10;
        startY = 10;
        stuff=new ArrayList<>();

    }
}
