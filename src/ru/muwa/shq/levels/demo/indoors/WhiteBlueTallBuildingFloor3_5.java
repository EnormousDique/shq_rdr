package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoorNoNumberEnotherDoor2;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class WhiteBlueTallBuildingFloor3_5 extends Level
{

    private static WhiteBlueTallBuildingFloor3_5 instance;
    public static WhiteBlueTallBuildingFloor3_5 getInstance() throws IOException {
        if(instance == null) return new WhiteBlueTallBuildingFloor3_5(); else return instance;
    }
    private WhiteBlueTallBuildingFloor3_5() throws IOException {
        instance = this;
        startPosX = 527;
        startPosY = 762;
        // containers.add(new PostBox(100,100));
        objects.add(new WhiteBlueTallBuildingFLoorNoNumberEnotherDoor2(10,10));
        zones.add(new EnterZone(699,755,80,50,WhiteBlueTallBuildingFloor3.getInstance(),699,200,false));
        zones.add(new EnterZone(701,8,80,50,WhiteBlueTallBuildingFloor4.getInstance(),699,585,false));


    }
}
