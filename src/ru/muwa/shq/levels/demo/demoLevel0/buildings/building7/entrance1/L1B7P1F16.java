package ru.muwa.shq.levels.demo.demoLevel0.buildings.building7.entrance1;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class L1B7P1F16 extends Level {
    private static L1B7P1F16 instance;
    public static L1B7P1F16 getInstance() throws IOException {
        if(instance == null) return new L1B7P1F16(); else return instance;
    }
    private L1B7P1F16() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;

        objects.add(new BG());
        zones.add(new EnterZone(771 ,8,50,70, L1B7P1F16_Stairs.getInstance(),348,182,false));


    }
    private static class BG extends GameObject {
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
