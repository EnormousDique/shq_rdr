package ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.TamburFloor;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
//import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.StairsFirst;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.Stairs.Stairs1_5;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.Stairs.Stairs2;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TamburFloor2 extends Level
{

    private static TamburFloor2 instance;
    public static TamburFloor2 getInstance() throws IOException {
        if(instance == null) return new TamburFloor2(); else return instance;
    }
    private TamburFloor2() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        // containers.add(new PostBox(100,100));
        // objects.add(new GreyPadick(0,0));
        objects.add(new BG());
        zones.add(new EnterZone(505,739,60,80, DemoLevel0.getInstance(), 1800,2940,false));
        zones.add(new EnterZone(771 ,8,50,70, Stairs2.getInstance(),348,182,false));





    }
    private static class BG extends GameObject{
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\newPadicki\\PadikiTamburFloor\\Этаж 4 960x360.png"));
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
