package ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.police;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.creatures.npc.enemies.AimingGuy;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.market.MarketInteriors;
import ru.muwa.shq.zones.EnterZone;

public class PoliceHQ extends Level {
    private static PoliceHQ instance;
    public static PoliceHQ getInstance()
    {
        return instance == null ? new PoliceHQ() : instance;
    }
    private PoliceHQ()
    {
        NPC ment = new AimingGuy(100,100);
        ment.isEnemy=false;
        ment.setHp(Double.MAX_VALUE);
        //добавить менту диалог.
        npc.add(ment);
        try {
            zones.add(new EnterZone(300, 300, 100,100,DemoLevel0.getInstance(),5490,1950,false));
        }catch (Exception e)
        {
            System.out.println("Не могу получить демло левел 0");
        }
    }
}
