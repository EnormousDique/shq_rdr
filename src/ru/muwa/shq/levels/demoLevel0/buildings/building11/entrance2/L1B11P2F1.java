
package ru.muwa.shq.levels.demoLevel0.buildings.building11.entrance2;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class L1B11P2F1 extends Level
{

    private static L1B11P2F1 instance;
    public static L1B11P2F1 getInstance() throws IOException {
        if(instance == null) return new L1B11P2F1(); else return instance;
    }
    private L1B11P2F1() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        // containers.add(new PostBox(100,100));
        objects.add(new GreyPadick(0,0));
        zones.add(new EnterZone(505,739,60,80, DemoLevel0.getInstance(), 7900  ,2777,false));
        containers.add(new PostBox(0,100));




    }
}
