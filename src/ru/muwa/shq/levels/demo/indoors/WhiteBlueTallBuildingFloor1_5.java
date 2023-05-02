package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.bounds.Wall300;
import ru.muwa.shq.objects.bounds.Wall350;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoor;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoor2;
import ru.muwa.shq.objects.containers.WirelessPanel;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class WhiteBlueTallBuildingFloor1_5 extends Level
{

    private static WhiteBlueTallBuildingFloor1_5 instance;
    public static WhiteBlueTallBuildingFloor1_5 getInstance() throws IOException {
        if(instance == null) return new WhiteBlueTallBuildingFloor1_5(); else return instance;
    }
    private WhiteBlueTallBuildingFloor1_5() throws IOException {
        instance = this;
        startPosX = 300;
        startPosY = 300;
        // containers.add(new PostBox(100,100));
        objects.add(new WhiteBlueTallBuildingFLoor2(10,10));
        zones.add(new EnterZone(699,755,50,70,WhiteBlueTallBuildingFloor1.getInstance(),721,230,false));
        zones.add(new EnterZone(701,8,80,50,WhiteBlueTallBuildingFloor2.getInstance(),699,585,false));

    }
}
