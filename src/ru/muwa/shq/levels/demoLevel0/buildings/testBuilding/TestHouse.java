package ru.muwa.shq.levels.demoLevel0.buildings.testBuilding;

import ru.muwa.shq.engine.spawner.Kladmen;
import ru.muwa.shq.factory.HouseFactory;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.minigames.Lift;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.mom.regular.MQDrugs;
import ru.muwa.shq.quests.mom.regular.MQFood;
import ru.muwa.shq.zones.MiniGameZone;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
            objects.addAll(HouseFactory.buildHouse(floors, padiks));
            containers.addAll(HouseFactory.getBuildingContainers(floors,padiks));
            zones.addAll(HouseFactory.getBuildingZones(floors,padiks));

            //лифт //TODO: обернуть красиво в метод

            Lift lift = new Lift();
            ArrayList<Point> floorCoordinates = new ArrayList<>();

            for(int i = 0; i < floors; i++) floorCoordinates.add(new Point(300,i * 380 + 320));
            floorCoordinates.add(new Point(-250,floors * 380 + 585));
            Collections.reverse(floorCoordinates);

            lift.floorCoords = floorCoordinates;

            for(Point p : floorCoordinates) zones.add(new MiniGameZone(p.x,p.y,30,30,lift));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Player.get().setY(-300);
        Player.get().setX(-300);
        Player.get().momQuests.add(new MQFood());
        Player.get().momQuests.add(new MQDrugs());

        Kladmen.register(this);

    }


}
