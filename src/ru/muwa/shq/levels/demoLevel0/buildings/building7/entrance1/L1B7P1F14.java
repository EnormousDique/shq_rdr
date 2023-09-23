package ru.muwa.shq.levels.demoLevel0.buildings.building7.entrance1;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.minigames.Elevator;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.bounds.UniversalWall;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.MiniGameZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class L1B7P1F14 extends Level {
    private static L1B7P1F14 instance;
    public static L1B7P1F14 getInstance() throws IOException {
        if(instance == null) return new L1B7P1F14(); else return instance;
    }
    private L1B7P1F14() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;

        objects.add(new BG());
        zones.add(new EnterZone(771 ,8,50,70, L1B7P1F14_Stairs.getInstance(),348,182,false));

        zones.add(new MiniGameZone(300,315,15,25,new Elevator(L1B7P1F1.floors,300,290)));

        objects.add(new UniversalWall(0,0,900,60));
        objects.add(new UniversalWall(750,340,130,30));
        objects.add(new UniversalWall(840,0,60,360));
        objects.add(new UniversalWall(0,305,736,60));
        objects.add(new UniversalWall(0,0,55,360));


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
