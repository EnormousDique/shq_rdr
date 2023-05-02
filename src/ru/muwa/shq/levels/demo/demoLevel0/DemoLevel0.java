package ru.muwa.shq.levels.demo.demoLevel0;

import ru.muwa.shq.engine.animations.cutscenes.Cutscene0;
import ru.muwa.shq.engine.spawner.Spawner;
import ru.muwa.shq.items.bluntWeapons.BaseballBat;
import ru.muwa.shq.items.consumables.Water;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.items.guns.Makarov;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.indoors.WhiteBlueTallBuildingFloor1;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.minigames.padiklock.PadikLock;
import ru.muwa.shq.objects.buildings.TEST.Bazar;
import ru.muwa.shq.objects.buildings.TEST.FatBuilding;
import ru.muwa.shq.objects.buildings.TEST.TallFatBuilding;
import ru.muwa.shq.objects.containers.TrashCan;
import ru.muwa.shq.objects.obstacles.crates.Crate0;
import ru.muwa.shq.objects.street.Car;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.InteractiveEnterZone;
import ru.muwa.shq.zones.*;

import java.io.IOException;

public class DemoLevel0 extends Level
{
    private static DemoLevel0 instance;
    public static DemoLevel0 getInstance() throws IOException {
        if(instance == null) return new DemoLevel0(); else return instance;
    }

    private DemoLevel0() throws IOException
    {
        super();
        instance = this;
        this.isStreet = true;
        startPosX =641;
        startPosY = 1626;
        containers.add(new TrashCan(800,1900));
        containers.get(0).addItem(new Flour());
        containers.get(0).addItem(new Flour());
        containers.get(0).addItem(new Flour());
        containers.get(0).addItem(new Makarov());
        containers.get(0).addItem(new Makarov());
        Inventory.getInstance().addItem(new Flour());
        Inventory.getInstance().addItem(new Flour());
        objects.add(new DemoLevel0_BG(0,0));
        Inventory.getInstance().addItem(new BaseballBat());
        Inventory.getInstance().addItem(new Water());


        objects.add(new Crate0(100,100));
        objects.add(new FatBuilding(1690,680));
        objects.add(new FatBuilding(3630,680));
        objects.add(new FatBuilding(1690,1500));
        objects.add(new FatBuilding(3630,1500));
        objects.add(new TallFatBuilding(1730,2450));
        objects.add(new TallFatBuilding(2640,3045));
        objects.add(new Bazar(2200,6300));

        zones.add(new InteractiveEnterZone( new PadikLock("228К1488"),new EnterZone(1900,1200,70,70, WhiteBlueTallBuildingFloor1.getInstance(), 190,290,false)));
        zones.add(new CutsceneZone(-200,-200,50,50, Cutscene0.getInstance()));
        zones.add(new EnterZone(520,1800,70,70, HubHataIgoryana.getInstance(),290,705,false));
        // zones.add(new EnterZone(0,0,70,70, DemoHub.getInstance(), 0,0,false));
        objects.add(new Car(1350,1060));

            zones.add(new EnterZone(1800 ,3256, 100,70,WhiteBlueTallBuildingFloor1.getInstance(),522,809,false));

        Spawner.updateTimers();


    }
}
