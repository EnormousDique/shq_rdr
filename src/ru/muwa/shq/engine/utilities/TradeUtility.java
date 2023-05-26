package ru.muwa.shq.engine.utilities;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.zones.BuyoutZone;
import ru.muwa.shq.zones.GameZone;
import ru.muwa.shq.zones.TradeZone;

public class TradeUtility {
    public static void work()
    {
            for(GameZone z : Engine.getCurrentLevel().getZones())
            {
                if(z instanceof TradeZone && ((TradeZone)z).isActive)
                {
                    ((TradeZone)z).trade.trade();
                }
                if(z instanceof BuyoutZone && ((BuyoutZone)z).isActive)
                {
                    try {
                        ((BuyoutZone)z).buyout.trade();
                    } catch (Exception e) {
                        System.out.println("Да, говнокод. А иначе б вылетало :)))) \n Сломалась зона выкупа");
                    }
                }
            }
    }
}
