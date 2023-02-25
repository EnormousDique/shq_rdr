package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.buildings.TEST.FatBuilding;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.player.Player;


public class FatBuildingFloor1 extends Level
{
    public FatBuildingFloor1()
    {
        startPosX = 0;
        startPosY = 0;
        containers.add(new PostBox(100,100));
    }
}
