package ru.muwa.shq.levels.demo;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.indoors.FatBuildingFloor1;
import ru.muwa.shq.objects.buildings.TEST.FatBuilding;
import ru.muwa.shq.objects.buildings.TEST.TallFatBuilding;
import ru.muwa.shq.objects.buildings.TEST.TestBuilding;
import ru.muwa.shq.objects.containers.TrashCan;
import ru.muwa.shq.objects.obstacles.crates.Crate0;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;
import java.util.ArrayList;

public class DemoLevel0 extends Level
{
    public DemoLevel0() throws IOException {
        super();
        //objects.add(new Pavement(0, GameScreen.SCREEN_HEIGHT-Pavement.img.getHeight()));
        //objects.add(new Crate0( 200,200));
        startPosX = 10;
        startPosY = GameScreen.SCREEN_HEIGHT - 150;
        containers.add(new TrashCan(500,500));
        containers.get(0).addItem(new Flour());
        containers.get(0).addItem(new Flour());
        containers.get(0).addItem(new Flour());
        objects.add(new Crate0(100,100));
       // objects.add(new TestBuilding(300,799));
        objects.add(new FatBuilding(0,1500));
        objects.add(new TallFatBuilding(1000,2500));
        zones.add(new EnterZone(200,200,100,100,new FatBuildingFloor1()));
    }
}
