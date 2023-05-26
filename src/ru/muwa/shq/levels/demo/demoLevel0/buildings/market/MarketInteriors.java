package ru.muwa.shq.levels.demo.demoLevel0.buildings.market;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.zones.EnterZone;

import java.io.IOException;

public class MarketInteriors extends Level {

    private static MarketInteriors instance;
    public static MarketInteriors getInstance()
    {
     return instance == null ? new MarketInteriors() : instance;
    }
    private  MarketInteriors(){
        instance = this;
        this.isInDoors = true;
        objects.add(new MarketInteriorsBG(100,100));
        try {
            zones.add(new EnterZone(500,600,100,100, DemoLevel0.getInstance(),3000,6800, false));
        } catch (IOException e) {
            System.out.println("ошибка при попытке получить уровень 0");
        }
    }
}
