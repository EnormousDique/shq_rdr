package ru.muwa.shq.levels.demo.demoLevel0.buildings.testBuilding;

import ru.muwa.shq.engine.spawner.Kladmen;
import ru.muwa.shq.factory.HouseFactory;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.buildings.indoors.Stairs.GreyPadick;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestHouse extends Level {

    private static TestHouse instance;
    public static TestHouse getInstance() throws IOException {
        if(instance == null) return new TestHouse(); else return instance;

    }

    private TestHouse()
    {
        instance = this;
        isInDoors = true;

        try {
            objects.addAll(HouseFactory.buildHouse(5, 2));
            containers.addAll(HouseFactory.getBuildingContainers(5,2));
            zones.addAll(HouseFactory.getBuildingZones(5,2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Kladmen.register(this);

    }


}
