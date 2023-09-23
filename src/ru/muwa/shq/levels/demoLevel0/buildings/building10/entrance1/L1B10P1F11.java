package ru.muwa.shq.levels.demoLevel0.buildings.building10.entrance1;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class L1B10P1F11 extends Level
{

    private static L1B10P1F11 instance;
    public static L1B10P1F11 getInstance() throws IOException {
        if(instance == null) return new L1B10P1F11(); else return instance;
    }
    private L1B10P1F11() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        objects.add(new BG());
        //



        zones.add(new EnterZone(1439,812,40,70,L1B10P1F11_Stairs.getInstance(),50,333,false));
        containers.add(new PostBox(0,100));
        containers.add(new PostBox(0,300));



    }
    private static class BG extends GameObject {
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\building10\\ETABIGI.png"));
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
