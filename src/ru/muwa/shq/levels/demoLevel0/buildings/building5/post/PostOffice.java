package ru.muwa.shq.levels.demoLevel0.buildings.building5.post;

import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.zones.EnterZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PostOffice extends Level {
    private static PostOffice instance;
    public static PostOffice getInstance()
    {
        return instance == null ? new PostOffice() : instance;
    }
    private PostOffice()
    {
        instance=this;
        this.isInDoors=true;
        objects.add(new BG());
        try {
            zones.add(new EnterZone(300,400,100,100, DemoLevel0.getInstance(),8400,1950,false));
        } catch (IOException e) {
            System.out.println("демо левел 0");
        }

    }
    private static class BG extends GameObject {

        static BufferedImage img;
        static {
            try{
                img = ImageIO.read(new File(IMG_PATH + "buildings\\почта.png"));
            }catch (Exception e){
                System.out.println("Failed to load post office bg texture");
            }
        }

        private BG() {
            super(0,0,img);
            isSolid=false;
        }
    }
}
