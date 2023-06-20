package ru.muwa.shq.levels.demo.demoLevel0.buildings.drugstore;

import ru.muwa.shq.economics.trading.Trade;
import ru.muwa.shq.items.consumables.*;
import ru.muwa.shq.items.drugs.Lyrica;
import ru.muwa.shq.items.drugs.Zanax;
import ru.muwa.shq.items.knifes.Kortique;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.grocery.GroceryInteriors;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.TradeZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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
        objects.add(new BG());
        this.isInDoors = true;
        try {
            zones.add(new EnterZone(500,400,100,100, DemoLevel0.getInstance(),7920,6400, false));
        } catch (IOException e) {
            System.out.println("ошибка при попытке получить уровень 0");
        }
        zones.add(new TradeZone(400, 100, 100, 100, new Trade() {
            @Override
            public void setGoods() {
                goods.add(new Lyrica());
                goods.add(new BoobPill());
                goods.add(new Zanax());
                goods.add(new Water());
                goods.add(new Lube());
            }
        }));
    }

    private static class BG extends GameObject {

        static BufferedImage img;
        static {
            try{
                img = ImageIO.read(new File(IMG_PATH + "buildings\\аптека.png"));
            }catch (Exception e){
                System.out.println("Failed to load apteka bg texture");
            }
        }

        private BG() {
            super(0,0,img);
            isSolid=false;

        }
    }

}
