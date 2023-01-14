package ru.muwa.shq.levels.demo;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.buildings.ninefloor.NineFloorRoof;
import ru.muwa.shq.objects.buildings.ninefloor.NineFloorWall;

public class DemoLevel0 extends Level
{

    public DemoLevel0()
    {
        super();
        objects.add(new NineFloorWall(0,-2133 + 599));
        objects.add(new NineFloorRoof(0,-2133 + 599));
        objects.add(new NineFloorWall(objects.get(1).getWidth(),-2133 + 599));
        objects.add(new NineFloorRoof(0,objects.get(0).getY() + objects.get(0).getHeight()));
        startPosX = 100;
        startPosY = 200;
    }

}
