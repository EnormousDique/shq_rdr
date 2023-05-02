package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.bounds.Wall300;
import ru.muwa.shq.objects.bounds.Wall350;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoor;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoor2;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoorNoNumberEnotherDoor;
import ru.muwa.shq.objects.containers.WirelessPanel;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class WhiteBlueTallBuildingFloor2_5 extends Level
{

    private static WhiteBlueTallBuildingFloor2_5 instance;
    public static WhiteBlueTallBuildingFloor2_5 getInstance() throws IOException {
        if(instance == null) return new WhiteBlueTallBuildingFloor2_5(); else return instance;
    }
    private WhiteBlueTallBuildingFloor2_5() throws IOException {
        instance = this;
        startPosX = 527;
        startPosY = 762;
        // containers.add(new PostBox(100,100));
        objects.add(new WhiteBlueTallBuildingFLoorNoNumberEnotherDoor(10,10));
        zones.add(new EnterZone(699,755,80,50,WhiteBlueTallBuildingFloor2.getInstance(),699,200,false));
        zones.add(new EnterZone(701,8,80,50,WhiteBlueTallBuildingFloor3.getInstance(),699,585,false));


    }
}
