package ru.muwa.shq.levels.demoLevel0.buildings.building1;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.spawner.Kladmen;
import ru.muwa.shq.factory.HouseFactory;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.indoors.HubHataIgoryana;
import ru.muwa.shq.levels.demoLevel0.DemoLevel0;
import ru.muwa.shq.minigames.Lift;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.InteractionZone;
import ru.muwa.shq.zones.MiniGameZone;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Building_1 extends Level {

    public final int padikXOffset = 1600;
    private static Building_1 instance;
    public static Building_1 getInstance() throws IOException {
        if(instance == null) return new Building_1(); else return instance;

    }
    public Building_1()
    {
        instance = this;
        isInDoors = true;

        int floors =  4;
        int padiks = 4;

        try {
            //Вызываем  методы  фабрики домов  для добавления дома на уровень.
            objects.addAll(HouseFactory.buildHouse(floors, padiks));
            containers.addAll(HouseFactory.getBuildingContainers(floors,padiks));
            zones.addAll(HouseFactory.getBuildingZones(floors,padiks));
            zones.addAll(HouseFactory.addLift(floors,padiks));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Вход в квартиру Шкипера
        try {
            zones.add(new EnterZone(760,25,40,40, HubHataIgoryana.getInstance(),40,760,false));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Выходы их падика

        for(int i = 0; i < padiks; i++) {
            int finalI = i;
            zones.add(new InteractionZone(296 + finalI * padikXOffset, 1945, 15, 15) {
                @Override
                public void use() {
                    try {
                        Engine.switchLevel(DemoLevel0.getInstance(), 1900 + finalI * 365, 730);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        Kladmen.register(this);
    }
}
