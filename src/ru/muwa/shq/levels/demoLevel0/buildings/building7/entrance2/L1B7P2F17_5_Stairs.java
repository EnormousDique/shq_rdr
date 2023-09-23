package ru.muwa.shq.levels.demoLevel0.buildings.building7.entrance2;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.bounds.UniversalWall;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class L1B7P2F17_5_Stairs extends Level {
    private static L1B7P2F17_5_Stairs instance;
    public static L1B7P2F17_5_Stairs getInstance() throws IOException {
        if(instance == null) return new L1B7P2F17_5_Stairs(); else return instance;
    }
    private L1B7P2F17_5_Stairs() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        objects.add(new BG());

        objects.add(new UniversalWall(0,0,440,30));
        objects.add(new UniversalWall(0,0,30,260));
        objects.add(new UniversalWall(404,0,40,260));
        objects.add(new UniversalWall(0,220,440,40));
        objects.add(new UniversalWall(110,102,306,47));

        zones.add(new EnterZone(350,159,60,60, L1B7P2F17_Stairs.getInstance(), 6,153,false));
        zones.add(new EnterZone(350,35,60,60, L1B7P2F18_Stairs.getInstance(), 4,41,false));

    }
    private static class BG extends GameObject {
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\newPadicki\\PadikiStairs\\StairsTOp.png"));
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
