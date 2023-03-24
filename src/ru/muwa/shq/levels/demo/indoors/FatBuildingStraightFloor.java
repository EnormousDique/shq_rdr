package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.DemoLevel0;
import ru.muwa.shq.objects.bounds.InvisibleWall;
import ru.muwa.shq.objects.bounds.StraightInvisibleWall;
import ru.muwa.shq.objects.bounds.Wall300;
import ru.muwa.shq.objects.bounds.Wall350;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.containers.GarbageChute;
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
        zones.add(new EnterZone(172,644,70,70, DemoLevel0.getInstance(), 700,1800,false));

        //текстура его величества падика
        objects.add(new StraightEntrance(10,10));
        //objects.add(new InvisibleWall(379,919));
        objects.add(new InvisibleWall(-166,626));
        objects.add(new InvisibleWall(300,626));
        objects.add(new InvisibleWall(12,10));
        objects.add(new InvisibleWall(526,55));
        objects.add(new InvisibleWall(289,9));
        objects.add(new InvisibleWall(289,10));
        objects.add(new InvisibleWall(289,11));
        objects.add(new InvisibleWall(638,9));
        objects.add(new GarbageChute(400,392));
        objects.add(new StraightInvisibleWall(9,9));
        objects.add(new StraightInvisibleWall(800,9));







    }
}
