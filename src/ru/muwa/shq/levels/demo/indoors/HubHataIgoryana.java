package ru.muwa.shq.levels.demo.indoors;

import ru.muwa.shq.creatures.npc.enemies.BadGuy0;
import ru.muwa.shq.dialogues.demo.Conversation1;
import ru.muwa.shq.economics.money.Money_500;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.bounds.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.containers.HubChest;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.GifSceneZone;
import ru.muwa.shq.zones.SleepZone;


import java.io.IOException;


public class HubHataIgoryana extends Level
{


    private static HubHataIgoryana instance;
    public static HubHataIgoryana getInstance() throws IOException {
        if(instance == null) return new HubHataIgoryana(); else return instance;

    }
    private HubHataIgoryana() throws IOException {
        this.isInDoors = true;
        instance = this;
        startPosX = 300;
        startPosY = 300;
        // текстура хаба
        objects.add(new HubBuild(0,0));
        // хабовый сундук
        containers.add(new HubChest(700,5));
        for (int i = 0; i<6; i++)containers.get(0).addItem(new Money_500());
        //выход в падик
        zones.add(new EnterZone(30, 740,100,100,FatBuildingFloor5.getInstance(), 30,30,false));
        // диалог с мамой
        zones.add(new DialogueZone(Conversation1.getInstance(),100,100,100,100,false));
       // zones.add(new GifSceneZone(200,200,70,70,true));
        zones.add(new SleepZone(740,5,50,100));
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
    }
}
