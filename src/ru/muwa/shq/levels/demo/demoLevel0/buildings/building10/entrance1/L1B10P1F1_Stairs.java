package ru.muwa.shq.levels.demo.demoLevel0.buildings.building10.entrance1;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class L1B10P1F1_Stairs extends Level
{

    private static L1B10P1F1_Stairs instance;
    public static L1B10P1F1_Stairs getInstance() throws IOException {
        if(instance == null) return new L1B10P1F1_Stairs(); else return instance;
    }
    private L1B10P1F1_Stairs() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        objects.add(new BG());
        //

        zones.add(new EnterZone(30,330,30,60,L1B10P1F1.getInstance(),1400,815,false));
        zones.add(new EnterZone(505,300,40,150,L1B10P1F1_5_Stairs.getInstance(),0,347,false));

        containers.add(new PostBox(0,100));



    }
    private static class BG extends GameObject {
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\building10\\Stairs1srfloorHuge.png"));
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
