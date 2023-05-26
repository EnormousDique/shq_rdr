package ru.muwa.shq.levels.demo.demoLevel0.buildings.market;

import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.dialogues.demo.Q5T0_Conversation;
import ru.muwa.shq.economics.trading.Trade;
import ru.muwa.shq.items.consumables.HomemadeAnuses;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.TradeZone;

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
        zones.add(new DialogueZone(Q5T0_Conversation.getInstance(),400,300,100,100,false));
        zones.add(new TradeZone(400, 300, 100, 100, new Trade() {
            @Override
            public void setGoods() {
                goods.add(new HomemadeAnuses());
                //TODO: продавать топор, побольше свежего мяса и молочко.
            }
        }));
    }
}
