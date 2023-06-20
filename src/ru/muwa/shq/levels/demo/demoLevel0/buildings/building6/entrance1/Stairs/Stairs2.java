package ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.Stairs;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.L1B6P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.TamburFloor.TamburFloor2;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Stairs2 extends Level
{

    private static Stairs2 instance;
    public static Stairs2 getInstance() throws IOException {
        if(instance == null) return new Stairs2(); else return instance;
    }
    private Stairs2() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        objects.add(new BG());
        // containers.add(new PostBox(100,100));
        // objects.add(new GreyPadick(0,0));
        zones.add(new EnterZone(5,41,60,60, Stairs1_5.getInstance(), 361,35,false));
        zones.add(new EnterZone(3,154,46,60, Stairs2_5.getInstance(),355 ,165,false));
        zones.add(new EnterZone(360,221,46,40, TamburFloor2.getInstance(),746 ,68,false));





    }
    private static class BG extends GameObject{
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
