package ru.muwa.shq.levels.demoLevel0.buildings.building1.Entrance1;

import ru.muwa.shq.creatures.npc.enemies.AimingGuy;
import ru.muwa.shq.creatures.npc.questnpc.Hachique;
import ru.muwa.shq.engine.spawner.Kladmen;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.bounds.Wall300;
import ru.muwa.shq.objects.bounds.Wall350;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.objects.containers.WirelessPanel;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;


public class L1B1P1F1 extends Level
{
    private static L1B1P1F1 instance;
    public static L1B1P1F1 getInstance() throws IOException {
        if(instance == null) return new L1B1P1F1(); else return instance;
    }
    private L1B1P1F1() throws IOException {
        instance = this;
        startPosX = 0;
        startPosY = 0;
        npc.add(new Hachique(-100,-100));
        npc.add(new AimingGuy(-100,100));

        zones.add(new EnterZone(300,164,60,80, DemoLevel0.getInstance(), 1900,800,false));
        zones.add(new EnterZone(310,30,60,80, L1B1P1F1_5.getInstance(), 100,100,true));

        objects.add(new TestEntrance(10,10));
        objects.add(new Stairs(150,30));
        objects.add(new Stairs(150,160));
        objects.add(new WirelessPanel(8,77));
        objects.add(new Wall350(10 ,-40));
        objects.add(new Wall350(10,280));
        objects.add(new Wall300(360,10));
        objects.add(new Wall300(-40,10));
        objects.add(new Perila(130,120));
        containers.add(new PostBox(0,100));
        Kladmen.register(this);
    }
}


