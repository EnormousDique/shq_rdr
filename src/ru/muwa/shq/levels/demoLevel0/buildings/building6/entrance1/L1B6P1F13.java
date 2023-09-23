package ru.muwa.shq.levels.demoLevel0.buildings.building6.entrance1;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.bounds.UniversalWall;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class L1B6P1F13 extends Level
{

    private static L1B6P1F13 instance;
    public static L1B6P1F13 getInstance() throws IOException {
        if(instance == null) return new L1B6P1F13(); else return instance;
    }
    private L1B6P1F13() throws IOException {
        instance = this;
        startPosX = 357;
        startPosY = 418;
        // containers.add(new PostBox(100,100));
        // objects.add(new GreyPadick(0,0));
        objects.add(new BG());

        zones.add(new EnterZone(771 ,8,50,70, L1B6P1F11_Stairs.getInstance(),348,182,false));



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
