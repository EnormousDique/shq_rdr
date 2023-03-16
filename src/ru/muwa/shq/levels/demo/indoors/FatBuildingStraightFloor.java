package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.DemoLevel0;
import ru.muwa.shq.objects.bounds.InvisibleWall;
import ru.muwa.shq.objects.bounds.Wall300;
import ru.muwa.shq.objects.bounds.Wall350;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.objects.containers.WirelessPanel;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class FatBuildingStraightFloor extends Level
{

    private static FatBuildingStraightFloor instance;
    public static FatBuildingStraightFloor getInstance() throws IOException {
        if(instance == null) return new FatBuildingStraightFloor(); else return instance;
    }
    private FatBuildingStraightFloor() throws IOException {
        instance = this;
        startPosX = 0;
        startPosY = 0;
        // containers.add(new PostBox(100,100));
        zones.add(new EnterZone(290,192,70,70, DemoLevel0.getInstance(), 700,1800,false));

        //текстура его величества падика
        objects.add(new StraightEntrance(10,10));
        objects.add(new InvisibleWall(379,919));







    }
}
