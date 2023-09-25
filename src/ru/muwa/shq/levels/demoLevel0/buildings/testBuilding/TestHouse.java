package ru.muwa.shq.levels.demoLevel0.buildings.testBuilding;

import ru.muwa.shq.engine.spawner.Kladmen;
import ru.muwa.shq.factory.HouseFactory;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.minigames.Lift;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.mom.regular.MQDrugs;
import ru.muwa.shq.quests.mom.regular.MQFood;
import ru.muwa.shq.zones.GameZone;
import ru.muwa.shq.zones.MiniGameZone;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class TestHouse extends Level {

    private static TestHouse instance;
    public static TestHouse getInstance() throws IOException {
        if(instance == null) return new TestHouse(); else return instance;

    }

    private TestHouse()
    {
        instance = this;
        isInDoors = true;

        int floors =  5;
        int padiks = 2;

        try {
            //Вызываем  методы  фабрики домов  для добавления дома на уровень.
            objects.addAll(HouseFactory.buildStairHouse(4,4));

        } catch (/*IO*/Exception e) {
            throw new RuntimeException(e);
        }
   //     Player.get().setY(-300);
   //     Player.get().setX(-300);

        Kladmen.register(this);

    }




}
