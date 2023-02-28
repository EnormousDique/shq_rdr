package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.DemoLevel0;
import ru.muwa.shq.objects.bounds.Wall300;
import ru.muwa.shq.objects.bounds.Wall350;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.containers.GarbageChute;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class FatBuildingFloor2 extends Level
{

    private static FatBuildingFloor2 instance;
    public static FatBuildingFloor2 getInstance() throws IOException {
        if(instance == null) return new FatBuildingFloor2(); else return instance;
    }
    private FatBuildingFloor2() throws IOException {
        instance = this;
        startPosX = 0;
        startPosY = 0;
        // containers.add(new PostBox(100,100));
        zones.add(new EnterZone(300,150,100,100, FatBuildingFloor1_5.getInstance(), 250,205,true));
        zones.add(new EnterZone(240,30,50,50, FatBuildingFloor2_5.getInstance(), 300,100,true));

        // objects.add(new ApartmentDoor(10,0,0));
        //  objects.add(new ApartmentDoor(80,0,0));
        objects.add(new TestEntrance(10,10));
        objects.add(new Stairs(150,30));
        // objects.add(new Stairs(150,180));
        objects.add(new Stairs(150,160));
        objects.add(new PostBox(10,80));
        objects.add(new Wall350(10 ,-40));
        objects.add(new Wall350(10,280));
        objects.add(new Wall300(360,10));
        objects.add(new Wall300(-40,10));
        objects.add(new Perila(130,120));

    }
}

