package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.bounds.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoor;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class WhiteBlueTallBuildingFloor1 extends Level
{

    private static WhiteBlueTallBuildingFloor1 instance;
    public static WhiteBlueTallBuildingFloor1 getInstance() throws IOException {
        if(instance == null) return new WhiteBlueTallBuildingFloor1(); else return instance;
    }
    private WhiteBlueTallBuildingFloor1() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        // containers.add(new PostBox(100,100));
        objects.add(new WhiteBlueTallBuildingFLoor(10,10));
        zones.add(new EnterZone(522,749,80,50,DemoLevel0.getInstance(),190,200,false));
        zones.add(new EnterZone(701,8,80,50,WhiteBlueTallBuildingFloor1_5.getInstance(),699,585,false));

        objects.add(new UniversalWall(-90,60,100,1000));
        objects.add(new UniversalWall(809,0,100,1000));
        objects.add(new UniversalWall(10,-100,1000,100));//verh
        objects.add(new UniversalWall(10,810,1000,100));
        objects.add(new UniversalWall(10,10,676   ,223));

    }
}
