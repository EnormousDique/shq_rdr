package ru.muwa.shq.levels.demoLevel0.buildings.building10.entrance1;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.bounds.*;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class L1B10P1F1 extends Level
{

    private static L1B10P1F1 instance;
    public static L1B10P1F1 getInstance() throws IOException {
        if(instance == null) return new L1B10P1F1(); else return instance;
    }
    private L1B10P1F1() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        objects.add(new BG());
        //

        zones.add(new EnterZone(505,739,60,80, DemoLevel0.getInstance(), 5714  ,2900,false));
        zones.add(new EnterZone(1439,812,40,70,L1B10P1F1_Stairs.getInstance(),50,333,false));


        objects.add(new UniversalWall(0,0,68,1200));
        objects.add(new UniversalWall(0,0,1600,126));
        objects.add(new UniversalWall(0,1126,1600,80));



    }
    private static class BG extends GameObject {
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\newPadicki\\PadikiTexture\\padikueVOLODY222222A.png"));
            }catch (Exception e){
                System.out.println("несмог загрузщить падик бг");
            }
        }
        private BG() {
            super(0,0,img);
            isSolid=false;
        }
    }
}
