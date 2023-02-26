package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.DemoLevel0;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class FatBuildingFloor1 extends Level
{
    private static FatBuildingFloor1 instance;
    public static FatBuildingFloor1 getInstance() throws IOException {
        if(instance == null) return new FatBuildingFloor1(); else return instance;
    }
    private FatBuildingFloor1() throws IOException {
        instance = this;
        startPosX = 0;
        startPosY = 0;
        containers.add(new PostBox(100,100));
        zones.add(new EnterZone(200,200,100,100, DemoLevel0.getInstance(), 600,1800));
    }
}