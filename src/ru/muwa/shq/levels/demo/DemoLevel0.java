package ru.muwa.shq.levels.demo;

import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.buildings.ninefloor.NineFloorRoof;
import ru.muwa.shq.objects.buildings.ninefloor.NineFloorWall;
import ru.muwa.shq.objects.buildings.ninefloor.TEST.TestBuilding;
import ru.muwa.shq.objects.containers.TrashCan;
import ru.muwa.shq.objects.obstacles.crates.Crate0;
import ru.muwa.shq.objects.street.Pavement;

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
        containers.add(new TrashCan(200,100));
        containers.get(0).setItems(new ArrayList<Item>());
        objects.add(new Crate0(100,100));
        objects.add(new TestBuilding(300,799));


    }

}
