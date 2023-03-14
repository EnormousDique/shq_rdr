package ru.muwa.shq.levels.demo.hub;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.DemoLevel0;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;

public class DemoHub extends Level {

    private static DemoHub instance;
    public static DemoHub getInstance() throws IOException {
        if(instance == null) return new DemoHub(); else return instance;
    }

    public DemoHub()
    {
        instance = this;
        objects.add(new Hub(0,0));
        try {
            zones.add(new EnterZone(0,300,70,70, DemoLevel0.getInstance(), 0,0,false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
