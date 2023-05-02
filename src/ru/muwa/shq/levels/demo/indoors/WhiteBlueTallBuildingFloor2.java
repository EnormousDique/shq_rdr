package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.bounds.Wall300;
import ru.muwa.shq.objects.bounds.Wall350;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoor;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoor2;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoorNoNumber;
import ru.muwa.shq.objects.containers.WirelessPanel;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class WhiteBlueTallBuildingFloor2 extends Level
{

    private static WhiteBlueTallBuildingFloor2 instance;
    public static WhiteBlueTallBuildingFloor2 getInstance() throws IOException {
        if(instance == null) return new WhiteBlueTallBuildingFloor2(); else return instance;
    }
    private WhiteBlueTallBuildingFloor2() throws IOException {
        instance = this;
        startPosX = 527;
        startPosY = 762;
        // containers.add(new PostBox(100,100));
        objects.add(new WhiteBlueTallBuildingFLoorNoNumber(10,10));
        zones.add(new EnterZone(699,755,80,50,WhiteBlueTallBuildingFloor1_5.getInstance(),696,200,false));
        zones.add(new EnterZone(701,8,80,50,WhiteBlueTallBuildingFloor2_5.getInstance(),699,585,false));


    }
}
