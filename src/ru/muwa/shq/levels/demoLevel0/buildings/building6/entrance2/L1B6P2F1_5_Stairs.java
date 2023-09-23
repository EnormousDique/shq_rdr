package ru.muwa.shq.levels.demoLevel0.buildings.building6.entrance2;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demoLevel0.buildings.building6.entrance1.L1B6P1F1;
import ru.muwa.shq.levels.demoLevel0.buildings.building6.entrance1.L1B6P1F2_Stairs;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.bounds.UniversalWall;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class L1B6P2F1_5_Stairs extends Level
{

    private static L1B6P2F1_5_Stairs instance;
    public static L1B6P2F1_5_Stairs getInstance() throws IOException {
        if(instance == null) return new L1B6P2F1_5_Stairs(); else return instance;
    }
    private L1B6P2F1_5_Stairs() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        objects.add(new BG());
        // containers.add(new PostBox(100,100));
        // objects.add(new GreyPadick(0,0));
        zones.add(new EnterZone(382,33,60,80, L1B6P1F2_Stairs.getInstance(), 4,41,false));
        zones.add(new EnterZone(48,220,46,50, L1B6P1F1.getInstance(),80 ,185,false));


        objects.add(new UniversalWall(0,0,440,30));
        objects.add(new UniversalWall(0,0,40,250));
        objects.add(new UniversalWall(0,220,440,30));
        objects.add(new UniversalWall(410,0,40,250));
        objects.add(new UniversalWall(110,105,330,147));



    }
    private static class BG extends GameObject{
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\newPadicki\\PadikiStairs\\ShsdowStairs.png"));
            }catch (Exception e){
                System.out.println("несмог загрузщить лестницу новую");
            }
        }
        private BG() {
            super(0,0,img);
            isSolid=false;
        }
    }

}
