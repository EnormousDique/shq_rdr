package ru.muwa.shq.levels;

import ru.muwa.shq.levels.demo.DemoLevel0;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;

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
    static public FatBuildingFloor1 fatBuildingFloor1;
    static
    {
        try {
            fatBuildingFloor1 = FatBuildingFloor1.getInstance();
            System.out.println("fat buildinglevel floor1 done");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
