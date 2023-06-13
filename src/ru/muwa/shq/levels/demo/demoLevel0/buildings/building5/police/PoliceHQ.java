package ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.police;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.creatures.npc.enemies.AimingGuy;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.market.MarketInteriors;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PoliceHQ extends Level {
    private static PoliceHQ instance;
    public static PoliceHQ getInstance()
    {
        return instance == null ? new PoliceHQ() : instance;
    }
    private PoliceHQ()
    {
        instance=this;
        this.isInDoors=true;
        objects.add(new BG());
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
    private static class BG extends GameObject {

        static BufferedImage img;
        static {
            try{
                img = ImageIO.read(new File(IMG_PATH + "buildings\\кабинет мента.png"));
            }catch (Exception e){
                System.out.println("Failed to load police hq bg texture");
            }
        }

        private BG() {
            super(0,0,img);
            isSolid=false;
        }
    }
}
