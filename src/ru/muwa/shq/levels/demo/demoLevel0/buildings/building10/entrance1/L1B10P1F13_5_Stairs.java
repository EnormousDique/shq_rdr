package ru.muwa.shq.levels.demo.demoLevel0.buildings.building10.entrance1;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class L1B10P1F13_5_Stairs extends Level
{

    private static L1B10P1F13_5_Stairs instance;
    public static L1B10P1F13_5_Stairs getInstance() throws IOException {
        if(instance == null) return new L1B10P1F13_5_Stairs(); else return instance;
    }
    private L1B10P1F13_5_Stairs() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        objects.add(new BG());
        //


        zones.add(new EnterZone(0,296,40,150,L1B10P1F13_Stairs.getInstance(),505,340,false));
        zones.add(new EnterZone(0,65,40,150,L1B10P1F14_Stairs.getInstance(),500,109,false));
        containers.add(new PostBox(0,100));
        containers.add(new PostBox(0,200));




    }
    private static class BG extends GameObject {
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\building10\\Stairs1_5big.png"));
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
