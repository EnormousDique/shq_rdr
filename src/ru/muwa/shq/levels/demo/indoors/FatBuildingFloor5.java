package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.LevelStorage;
import ru.muwa.shq.objects.bounds.Wall300;
import ru.muwa.shq.objects.bounds.Wall350;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.containers.WirelessPanel;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class FatBuildingFloor5 extends Level
{

    private static FatBuildingFloor5 instance;
    public static FatBuildingFloor5 getInstance() throws IOException {
        if(instance == null) return new FatBuildingFloor5(); else return instance;
    }
    private FatBuildingFloor5() throws IOException {
        instance = this;
        startPosX = 0;
        startPosY = 0;
          zones.add(new EnterZone(299,225,50,50, FatBuildingFloor4_5.getInstance(), 250,205,true));

        objects.add(new TestEntrance(10,10));
        objects.add(new Stairs(150,180));
        objects.add(new WirelessPanel(8,77));
        objects.add(new Wall350(10 ,-40));
        objects.add(new Wall350(10,280));
        objects.add(new Wall300(360,10));
        objects.add(new Wall300(-40,10));
        objects.add(new Perila(130,120));

        zones.add(new EnterZone(25,20,70,70, Hub.getInstance(),40,760,false));

    }
}


