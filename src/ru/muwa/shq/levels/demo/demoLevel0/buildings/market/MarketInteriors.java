package ru.muwa.shq.levels.demo.demoLevel0.buildings.market;

import ru.muwa.shq.levels.Level;

public class MarketInteriors extends Level {

    private static MarketInteriors instance;
    public static MarketInteriors getInstance()
    {
     return instance == null ? new MarketInteriors() : instance;
    }
    private  MarketInteriors(){
        instance = this;
        this.isInDoors = true;

    }
}
