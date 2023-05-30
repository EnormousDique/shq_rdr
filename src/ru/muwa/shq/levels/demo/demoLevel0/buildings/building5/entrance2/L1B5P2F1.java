package ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance2;

import ru.muwa.shq.engine.spawner.Kladmen;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.bounds.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoor;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.objects.containers.WirelessPanel;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class L1B5P2F1 extends Level
{

    private static L1B5P2F1 instance;
    public static L1B5P2F1 getInstance() throws IOException {
        if(instance == null) return new L1B5P2F1(); else return instance;
    }
    private L1B5P2F1() throws IOException {
        instance = this;
        startPosX = 0;
        startPosY = 0;
        // containers.add(new PostBox(100,100));
        objects.add(new GreyPadick(0,0));
        containers.add(new PostBox(0,100));
        Kladmen.register(this);
        zones.add(new EnterZone(730,730,70,70,DemoLevel0.getInstance(),5990 ,900,true));


    }
}
