package ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance9;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;

import java.io.IOException;


public class L1B5P9F1 extends Level
{

    private static L1B5P9F1 instance;
    public static L1B5P9F1 getInstance() throws IOException {
        if(instance == null) return new L1B5P9F1(); else return instance;
    }
    private L1B5P9F1() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        // containers.add(new PostBox(100,100));
        objects.add(new GreyPadick(0,0));




    }
}
