package ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
//import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.StairsFirst;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.Stairs.Stairs1_5;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class L1B6P1F1 extends Level
{

    private static L1B6P1F1 instance;
    public static L1B6P1F1 getInstance() throws IOException {
        if(instance == null) return new L1B6P1F1(); else return instance;
    }
    private L1B6P1F1() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        // containers.add(new PostBox(100,100));
        // objects.add(new GreyPadick(0,0));
        objects.add(new BG());
        zones.add(new EnterZone(505,739,60,80, DemoLevel0.getInstance(), 1800,2940,false));
        zones.add(new EnterZone(64,121,60,40, Stairs1_5.getInstance(),50,181,false));

        containers.add(new PostBox(445,115));



    }
    private static class BG extends GameObject{
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\newPadicki\\PadikiTexture\\падик4 800х600.png"));
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
