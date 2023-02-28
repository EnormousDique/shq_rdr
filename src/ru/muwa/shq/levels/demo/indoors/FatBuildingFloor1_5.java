package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.bounds.Wall300;
import ru.muwa.shq.objects.bounds.Wall350;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class FatBuildingFloor1_5 extends Level
{

    private static FatBuildingFloor1_5 instance;
    public static FatBuildingFloor1_5 getInstance() throws IOException {
        if(instance == null) return new FatBuildingFloor1_5(); else return instance;
    }
    private FatBuildingFloor1_5() throws IOException {
        instance = this;
        startPosX = 0;
        startPosY = 0;
        // containers.add(new PostBox(100,100));
        zones.add(new EnterZone(10,65,70,70, FatBuildingFloor1.getInstance(), 100,50,true));
        zones.add(new EnterZone(10,195,70,70, FatBuildingFloor2.getInstance(), 100,250,true));


        // objects.add(new ApartmentDoor(10,0,0));
        //  objects.add(new ApartmentDoor(80,0,0));
        objects.add(new Entrance1_5(10,10));
        objects.add(new Stairs(10,30));
        // objects.add(new Stairs(150,180));
        objects.add(new Stairs(10,160));

        objects.add(new Wall350(10 ,-40));
        objects.add(new Wall350(10,280));
        objects.add(new Wall300(360,10));
        objects.add(new Wall300(-40,10));
        objects.add(new Perila(10,120));


    }
}