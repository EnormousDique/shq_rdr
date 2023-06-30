package ru.muwa.shq.levels.demo.demoLevel0.buildings.building10.entrance1;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class L1B10P1F11_Stairs extends Level
{

    private static L1B10P1F11_Stairs instance;
    public static L1B10P1F11_Stairs getInstance() throws IOException {
        if(instance == null) return new L1B10P1F11_Stairs(); else return instance;
    }
    private L1B10P1F11_Stairs() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        objects.add(new BG());
        //
        zones.add(new EnterZone(30,330,30,60,L1B10P1F11.getInstance(),1400,815,false));
        zones.add(new EnterZone(500,68,40,150,L1B10P1F10_5_Stairs.getInstance(),0,96,false));
        zones.add(new EnterZone(510,302,30,150,L1B10P1F11_5_Stairs.getInstance(),0,326,false));

        containers.add(new PostBox(0,100));
        containers.add(new PostBox(0,160));
        containers.add(new PostBox(0,130));



    }
    private static class BG extends GameObject {
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\building10\\Stairs2huge.png"));
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
