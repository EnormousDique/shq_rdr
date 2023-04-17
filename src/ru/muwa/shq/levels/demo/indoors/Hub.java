package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.dialogues.demo.Conversation1;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.bounds.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.containers.HubChest;
import ru.muwa.shq.zones.DialogueZone;
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
        // текстура хаба
        objects.add(new HubBuild(0,0));
        // хабовый сундук
        containers.add(new HubChest(700,5));
        //выход в падик
        zones.add(new EnterZone(30, 740,100,100,FatBuildingFloor5.getInstance(), 30,30,false));
        // диалог с мамой
        zones.add(new DialogueZone(Conversation1.getInstance(),100,100,100,100,false));
        // стены хаюа
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
        objects.add(new Wall350(0,-50));
        objects.add(new Wall350(-48,800));//нижнаяя
        objects.add(new Wall350(308,800));//нижнаяя
        objects.add(new Wall350(608,800));//нижнаяя

        // невидимые стены дома
        objects.add(new InvisibleWall620x10( 182,450));
        objects.add(new InvisibleWall5x230(123,572));
        objects.add(new InvisibleWall5x230(344,572));
        objects.add(new InvisibleWall120x5(226,561));
        objects.add(new InvisibleWall120x5(-31,443));
        objects.add(new InvisibleWall120x5(270,795));

        objects.add(new InvisibleWall120x5(688,2));
        objects.add(new InvisibleWall120x5(360,2));
        objects.add(new InvisibleWall120x5(310,2));
        objects.add(new InvisibleWall120x5(5,8)); objects.add(new InvisibleWall5x230(5,3));
    }
}
