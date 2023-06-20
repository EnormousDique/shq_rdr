package ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.Stairs;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.L1B6P1F1;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Stairs2_5 extends Level
{

    private static Stairs2_5 instance;
    public static Stairs2_5 getInstance() throws IOException {
        if(instance == null) return new Stairs2_5(); else return instance;
    }
    private Stairs2_5() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        objects.add(new BG());
        // containers.add(new PostBox(100,100));
        // objects.add(new GreyPadick(0,0));
        zones.add(new EnterZone(350,159,60,60, Stairs2.getInstance(), 6,153,false));
       // zones.add(new EnterZone(358,31,40,60, Stairs3.getInstance(),80 ,185,false));






    }
    private static class BG extends GameObject{
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
