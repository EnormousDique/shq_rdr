package ru.muwa.shq.levels.demoLevel0.buildings.building6.entrance2;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demoLevel0.buildings.building6.L1B6P1F4_KidApartments;
import ru.muwa.shq.levels.demoLevel0.buildings.building6.entrance1.L1B6P1F4_Stairs;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.bounds.UniversalWall;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class L1B6P2F4 extends Level
{

    private static L1B6P2F4 instance;
    public static L1B6P2F4 getInstance() throws IOException {
        if(instance == null) return new L1B6P2F4(); else return instance;
    }
    private L1B6P2F4() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        // containers.add(new PostBox(100,100));
        // objects.add(new GreyPadick(0,0));
        objects.add(new BG());

        zones.add(new EnterZone(771 ,8,50,70, L1B6P1F4_Stairs.getInstance(),348,182,false));
        zones.add(new EnterZone(51,180,60,60, L1B6P1F4_KidApartments.getInstance(),0,0,false));


        objects.add(new UniversalWall(0,0,900,59));
        objects.add(new UniversalWall(0,0,50,363));
        objects.add(new UniversalWall(0,300,900,59));
        objects.add(new UniversalWall(838,0,50,363));



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
