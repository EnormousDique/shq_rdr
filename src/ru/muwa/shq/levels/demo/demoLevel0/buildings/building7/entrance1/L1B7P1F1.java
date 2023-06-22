package ru.muwa.shq.levels.demo.demoLevel0.buildings.building7.entrance1;

import ru.muwa.shq.engine.spawner.Kladmen;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.L1B6P1F1;
import ru.muwa.shq.levels.demo.demoLevel0.buildings.building6.entrance1.Stairs.Stairs1_5;
import ru.muwa.shq.minigames.Elevator;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.bounds.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.*;
import ru.muwa.shq.objects.buildings.indoors.Stairs.WhiteBlueTallBuilding.WhiteBlueTallBuildingFLoor;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.objects.containers.WirelessPanel;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.MiniGameZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class L1B7P1F1 extends Level
{

    public static Elevator lift;
    public static ArrayList<Level> floors = new ArrayList<>();




    private static L1B7P1F1 instance;
    public static L1B7P1F1 getInstance() throws IOException {
        if(instance == null) return new L1B7P1F1(); else return instance;
    }
    private L1B7P1F1() throws IOException {
        instance = this;
        objects.add(new BG());
        zones.add(new EnterZone(505,739,60,80, DemoLevel0.getInstance(), 2717,3750,false));
        zones.add(new EnterZone(64,121,60,40, L1B7P1F1_5_Stairs.getInstance(),50,181,false));
        containers.add(new PostBox(445,115));

        objects.add(new UniversalWall(0,540,800,60));
        objects.add(new UniversalWall(0,0,800,60));
        objects.add(new UniversalWall(0,0,60,600));
        objects.add(new UniversalWall(740,0,60,600));
        objects.add(new UniversalWall(0,0,160,120));
        objects.add(new UniversalWall(0,108,280,12));
        objects.add(new UniversalWall(0,258,300,62));
        objects.add(new UniversalWall(400,60,40,250));
        objects.add(new UniversalWall(400,303,200,37));
        objects.add(new UniversalWall(60,120,240,41));
        objects.add(new UniversalWall(222,162,78,15));

        try {
            floors.add(L1B7P1F1.getInstance());
            floors.add(L1B7P1F2.getInstance());floors.add(L1B7P1F3.getInstance());
            floors.add(L1B7P1F4.getInstance());floors.add(L1B7P1F5.getInstance());floors.add(L1B7P1F6.getInstance());
            floors.add(L1B7P1F7.getInstance());floors.add(L1B7P1F8.getInstance());floors.add(L1B7P1F9.getInstance());
            floors.add(L1B7P1F10.getInstance());floors.add(L1B7P1F11.getInstance());floors.add(L1B7P1F12.getInstance());
            floors.add(L1B7P1F13.getInstance());floors.add(L1B7P1F14.getInstance());floors.add(L1B7P1F15.getInstance());
            floors.add(L1B7P1F16.getInstance());floors.add(L1B7P1F17.getInstance());floors.add(L1B7P1F18.getInstance());
            floors.add(L1B7P1F19.getInstance());
        } catch (IOException e) {
            System.out.println("ошибка при получении уровней-этажей. Инициализация лифта l1b7p1");
        }

        lift=new Elevator(floors,300,290);

        zones.add(new MiniGameZone(200,550,15,25,lift));

        Kladmen.register(this);
    }
    private static class BG extends GameObject {
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
