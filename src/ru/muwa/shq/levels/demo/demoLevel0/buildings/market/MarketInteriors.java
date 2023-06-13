package ru.muwa.shq.levels.demo.demoLevel0.buildings.market;

import ru.muwa.shq.creatures.npc.enemies.AimingGuy;
import ru.muwa.shq.creatures.npc.questnpc.Hachique;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.creatures.npc.questnpc.Hachique;
import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.dialogues.demo.Q5T0_Conversation;
import ru.muwa.shq.economics.trading.Trade;
import ru.muwa.shq.items.consumables.HomemadeAnuses;
import ru.muwa.shq.items.quest.Pacanynok;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.Quest;
import ru.muwa.shq.quests.actions.QuestAction;
import ru.muwa.shq.zones.*;

import javax.swing.text.PlainDocument;
import java.io.IOException;
import java.util.stream.Collectors;

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
