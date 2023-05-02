package ru.muwa.shq.zones;

import ru.muwa.shq.economics.trading.Trade;

public class TradeZone extends GameZone{

    public boolean isActive;
    public Trade trade;
    public TradeZone(int x, int y, int width, int height, Trade trade) {
        super(x, y, width, height);
        this.trade=trade;
        trade.setGoods();
    }

}
