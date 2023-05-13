package ru.muwa.shq.zones;

import ru.muwa.shq.economics.trading.Buyout;

public class BuyoutZone extends GameZone{
    public boolean isActive;
    public Buyout buyout;

    public BuyoutZone(int x, int y, int width, int height, Buyout buyout) {
        super(x, y, width, height);
        this.buyout=buyout;
        buyout.init();
    }
}
