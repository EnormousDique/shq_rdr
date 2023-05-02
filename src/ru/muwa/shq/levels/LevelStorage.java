package ru.muwa.shq.levels;

import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demo.indoors.WhiteBlueTallBuildingFloor1;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;

import java.io.IOException;

public class LevelStorage
{
    static public DemoLevel0 demoLevel0;
    static
    {
        try {
            demoLevel0 = DemoLevel0.getInstance();
            System.out.println("demolevel0 done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static public WhiteBlueTallBuildingFloor1 WhiteBlueTallBuildingFloor1;
    static
    {
        try {
            WhiteBlueTallBuildingFloor1 = ru.muwa.shq.levels.demo.indoors.WhiteBlueTallBuildingFloor1.getInstance();
            System.out.println("WhiteBlueTallBuildingFloor1 floor1 done");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static public HubHataIgoryana DemoHub;
    static
    {
        try {
            DemoHub = HubHataIgoryana.getInstance();
            System.out.println("level HUb done");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
