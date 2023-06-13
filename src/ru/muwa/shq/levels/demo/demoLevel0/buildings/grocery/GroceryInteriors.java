package ru.muwa.shq.levels.demo.demoLevel0.buildings.grocery;

import ru.muwa.shq.economics.trading.Trade;
import ru.muwa.shq.items.consumables.*;
import ru.muwa.shq.items.knifes.Kortique;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.market.MarketInteriors;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.TradeZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GroceryInteriors extends Level {

    private static GroceryInteriors instance;
    public static GroceryInteriors getInstance()
    {
        return instance == null ? new GroceryInteriors() : instance;
    }

    private GroceryInteriors()
    {
        instance=this;
        this.isInDoors = true;
        objects.add(new BG());
        try {
            zones.add(new EnterZone(500,600,100,100, DemoLevel0.getInstance(),7920,3800, false));
        } catch (IOException e) {
            System.out.println("ошибка при попытке получить уровень 0");
        }
        zones.add(new TradeZone(400, 300, 100, 100, new Trade() {
            @Override
            public void setGoods() {
          //      goods.add(new HomemadeAnuses());
           //     goods.add(new Water());
                goods.add(new Cigarettes());
              //  goods.add(new Kortique());
                goods.add(new Beer());
                goods.add(new CannedSoup());
                goods.add(new ChickFire());
       //         goods.add(new CellPhone());
                goods.add(new EnergyDrink());
                goods.add(new Vodka());
            }
        }));
    }

    private static class BG extends GameObject {

        static BufferedImage img;
        static {
            try{
                img = ImageIO.read(new File(IMG_PATH + "buildings\\магаз.png"));
            }catch (Exception e){
                System.out.println("Failed to load shop bg texture");
            }
        }

        private BG() {
            super(0,0,img);
            isSolid=false;
        }
    }

}
