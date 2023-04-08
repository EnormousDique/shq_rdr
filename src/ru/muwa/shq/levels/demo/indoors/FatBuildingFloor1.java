package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.bounds.Wall300;
import ru.muwa.shq.objects.bounds.Wall350;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.containers.WirelessPanel;
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
        startPosX = 300;
        startPosY = 200;
       // containers.add(new PostBox(100,100));
        zones.add(new EnterZone(290,192,70,70, DemoLevel0.getInstance(), 1900,1220,false));
        zones.add(new EnterZone(310,30,60,80, FatBuildingFloor1_5.getInstance(), 300,60,true));

        objects.add(new TestEntrance(10,10));
        objects.add(new Stairs(150,30));

        objects.add(new SexStairs(150,160));
        objects.add(new WirelessPanel(8,77));
        objects.add(new Wall350(10 ,-40));
        objects.add(new Wall350(10,280));
        objects.add(new Wall300(360,-20));
        objects.add(new Wall300(-40,-20));
        objects.add(new Perila(130,120));

    }
}
