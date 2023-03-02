package ru.muwa.shq.levels.demo;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.creatures.npc.enemies.BadGuy0;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.LevelStorage;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;
import ru.muwa.shq.objects.buildings.TEST.FatBuilding;
import ru.muwa.shq.objects.buildings.TEST.TallFatBuilding;
import ru.muwa.shq.objects.buildings.TEST.TestBuilding;
import ru.muwa.shq.objects.containers.TrashCan;
import ru.muwa.shq.objects.obstacles.crates.Crate0;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;
import java.util.ArrayList;

public class DemoLevel0 extends Level
{
    private static DemoLevel0 instance;
    public static DemoLevel0 getInstance() throws IOException {
        if(instance == null) return new DemoLevel0(); else return instance;
    }

    private DemoLevel0() throws IOException {
        super();
        startPosX = 650;
        startPosY =1800;
        containers.add(new TrashCan(800,1900));
        containers.get(0).addItem(new Flour());
        containers.get(0).addItem(new Flour());
        containers.get(0).addItem(new Flour());
        Inventory.getInstance().addItem(new Flour());
        objects.add(new Crate0(100,100));
        objects.add(new FatBuilding(0,1500));
        objects.add(new TallFatBuilding(1000,2500));
        instance = this;
        zones.add(new EnterZone(520,1765,70,70, FatBuildingFloor1.getInstance(), 327,220,false));
        npc.add(new BadGuy0(800,1800));
        npc.get(0).setRayCasterBorders(npc.get(0).getRayCaster().buildLines(objects));
    }
}
