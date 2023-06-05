
package ru.muwa.shq.levels.demo.demoLevel0.buildings.building11.entrance3;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.bounds.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoor;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.objects.containers.WirelessPanel;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class L1B11P3F1 extends Level
{

    private static L1B11P3F1 instance;
    public static L1B11P3F1 getInstance() throws IOException {
        if(instance == null) return new L1B11P3F1(); else return instance;
    }
    private L1B11P3F1() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        // containers.add(new PostBox(100,100));
        objects.add(new GreyPadick(0,0));
        zones.add(new EnterZone(505,739,60,80, DemoLevel0.getInstance(), 7920  ,3100,false));
        containers.add(new PostBox(0,100));




    }
}