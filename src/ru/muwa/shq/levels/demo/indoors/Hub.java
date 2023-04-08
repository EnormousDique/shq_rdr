package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.bounds.Wall300;
import ru.muwa.shq.objects.bounds.Wall350;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.containers.HubChest;
import ru.muwa.shq.objects.containers.TrashCan;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.InDoorsSpawnZone;


import java.io.IOException;


public class Hub extends Level
{


    private static Hub instance;
    public static Hub getInstance() throws IOException {
        if(instance == null) return new Hub(); else return instance;

    }
    private Hub() throws IOException {

        this.isInDoors = true;

        instance = this;
        startPosX = 0;
        startPosY = 0;
        // containers.add(new PostBox(100,100));


        //текстура его величества падика
        objects.add(new HubBuild(0,0));
        containers.add(new HubChest(700,5));
        // zones.add(new EnterZone(1,743,120,60,DemoLevel0.getInstance(),520,1800,true));
        objects.add(new Wall350(0,-50));
       objects.add(new Wall350(-48,800));//нижнаяя
        objects.add(new Wall350(308,800));//нижнаяя
       objects.add(new Wall350(608,800));//нижнаяя
        zones.add(new InDoorsSpawnZone(2000,200,100,150));


        objects.add(new Wall350(351,-50));
        objects.add(new Wall350(701,-50));
        objects.add(new Wall300(-50,470));
        objects.add(new Wall300(795,470)); //правая
        objects.add(new Wall300(-50,95));
        objects.add(new Wall300(795,95)); //правая
        objects.add(new Wall300(795,160)); //правая
        objects.add(new Wall300(-50,160));
        objects.add(new Wall300(-50,-160));
        objects.add(new Wall300(795,-160)); //правая
       // objects.add(new Wall350(701,-50));
       // objects.add(new TrashCan(700,15));
      //  containers.get(1).addItem(new Flour());

        //objects.add(new InvisibleWall(379,919));





    }
}
