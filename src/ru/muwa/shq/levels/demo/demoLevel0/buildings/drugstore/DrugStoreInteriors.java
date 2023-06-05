package ru.muwa.shq.levels.demo.demoLevel0.buildings.drugstore;

import ru.muwa.shq.economics.trading.Trade;
import ru.muwa.shq.items.consumables.*;
import ru.muwa.shq.items.drugs.Lyrica;
import ru.muwa.shq.items.drugs.Zanax;
import ru.muwa.shq.items.knifes.Kortique;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.grocery.GroceryInteriors;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.TradeZone;

import java.io.IOException;

public class DrugStoreInteriors extends Level {

    private static DrugStoreInteriors instance;
    public static DrugStoreInteriors getInstance()
    {
        return instance == null ? new DrugStoreInteriors() : instance;
    }

    private DrugStoreInteriors()
    {
        instance=this;
        this.isInDoors = true;
        try {
            zones.add(new EnterZone(500,600,100,100, DemoLevel0.getInstance(),7920,6400, false));
        } catch (IOException e) {
            System.out.println("ошибка при попытке получить уровень 0");
        }
        zones.add(new TradeZone(400, 300, 100, 100, new Trade() {
            @Override
            public void setGoods() {
                goods.add(new Lyrica());
                goods.add(new BoobPill());
                goods.add(new Zanax());
                goods.add(new Water());


            }
        }));
    }

}
