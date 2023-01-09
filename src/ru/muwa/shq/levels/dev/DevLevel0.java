package ru.muwa.shq.levels.dev;
import ru.muwa.shq.objects.bounds.BottomLevelBound;
import ru.muwa.shq.objects.containers.GarbageChute;
import ru.muwa.shq.creatures.npc.enemies.BadGuy0;
import ru.muwa.shq.objects.obstacles.crates.Crate0;
import ru.muwa.shq.objects.obstacles.crates.Crate1;
import ru.muwa.shq.levels.Level;

import java.io.IOException;

/**
 * Моя песочница.
 */
public class DevLevel0 extends Level
{

    public DevLevel0()
    {
        try
        {
            startPosX = 250;
            startPosY = 100;
            objects.add(new Crate0(10,20));
            objects.add(new Crate0(130,200));
            objects.add(new Crate0(300,300));
            objects.add(new Crate0(350,180));
            objects.add(new Crate0(750,450));
            objects.add(new Crate0(1000,450));
            objects.add(new Crate1(600,0));
            objects.add(new BottomLevelBound(0,590));
            containers.add(new GarbageChute (120,128));
            //objects.add(new Crate2(228,228));
            npc.add(new BadGuy0(600,200));
            npc.get(0).setRayCasterBorders(npc.get(0).getRayCaster().buildLines(objects));
        }
        catch (IOException e)
        {
            System.out.println("error creating DevLevel0");
        }
        System.out.println("DevLevel0 initialized");
    }
}
