package ru.muwa.shq.levels.demo.demoLevel0.buildings.building7.entrance1;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.bounds.UniversalWall;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class L1B7P1F16_Stairs extends Level {
    private static L1B7P1F16_Stairs instance;
    public static L1B7P1F16_Stairs getInstance() throws IOException {
        if(instance == null) return new L1B7P1F16_Stairs(); else return instance;
    }
    private L1B7P1F16_Stairs() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        objects.add(new BG());

        zones.add(new EnterZone(5,41,60,60, L1B7P1F15_5_Stairs.getInstance(), 361,35,false));
        zones.add(new EnterZone(3,154,46,60, L1B7P1F16_5_Stairs.getInstance(),355 ,165,false));
        zones.add(new EnterZone(360,221,46,40, L1B7P1F16.getInstance(),746 ,68,false));

        objects.add(new UniversalWall(0,0,440,30));
        objects.add(new UniversalWall(0,0,30,260));
        objects.add(new UniversalWall(404,0,40,260));
        objects.add(new UniversalWall(0,220,440,40));
        objects.add(new UniversalWall(0,102,306,47));



    }
    private static class BG extends GameObject {
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\newPadicki\\PadikiStairs\\shdow2ndfloor.png"));
            }catch (Exception e){
                System.out.println("несмог загрузщить лестницу новую ДВА");
            }
        }
        private BG() {
            super(0,0,img);
            isSolid=false;
        }
    }
}
