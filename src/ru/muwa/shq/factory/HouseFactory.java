package ru.muwa.shq.factory;

import ru.muwa.shq.levels.demo.demoLevel0.buildings.testBuilding.TestHouse;
import ru.muwa.shq.minigames.Lift;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.bounds.UniversalWall;
import ru.muwa.shq.objects.containers.*;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.GameZone;
import ru.muwa.shq.zones.InteractionZone;
import ru.muwa.shq.zones.MiniGameZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HouseFactory {
   static int floorYOffset = 20;
    static int padikXOffset = 1600;

    public static List<GameObject> buildHouse(int floors, int padiks) throws IOException {


        List<GameObject> objects = new ArrayList<>();

        //ПЕРВЫЙ ЭТАЖ
        Pados pd = new Pados();
        pd.setX(-450);
        pd.setY(floors*380  +50);
        objects.add(pd);

        int xOff = pd.getX(), yOff = pd.getY();
        objects.add(new UniversalWall(0  + xOff,540+ yOff,800,60));
        objects.add(new UniversalWall(0+ xOff,0+ yOff,800,60));
        objects.add(new UniversalWall(0+ xOff,0+ yOff,60,600));
        objects.add(new UniversalWall(740+ xOff,0+ yOff,60,600));
        objects.add(new UniversalWall(0+ xOff,0+ yOff,160,120));
        objects.add(new UniversalWall(0+ xOff,108+ yOff,280,12));
        objects.add(new UniversalWall(0+ xOff,258+ yOff,300,62));
        objects.add(new UniversalWall(400+ xOff,60+ yOff,40,250));
        objects.add(new UniversalWall(400+ xOff,303+ yOff,200,37));
        objects.add(new UniversalWall(60+ xOff,120+ yOff,240,41));
        objects.add(new UniversalWall(222+ xOff,162+ yOff ,78,15));



        //ОСТАЛЬНЫЕ ЭТАЖИ
        for(int j = 0; j <padiks;j++)
        {
            for (int i = 0; i < floors; i++)
            {
                UniversalWall uw = new UniversalWall( j*padikXOffset,-50 + i*380, 1600,100 );
                objects.add(uw);
                uw = new UniversalWall( 870+j*padikXOffset, i*380, 80,450 );
                objects.add(uw);
                uw = new UniversalWall( j*padikXOffset, i*380, 50,450 );
                objects.add(uw);
                uw = new UniversalWall(-65 +j*padikXOffset, 165+i*380, 100,30 );
                objects.add(uw);
                uw = new UniversalWall(-350 +j*padikXOffset, 150+i*380, 290,110 );
                objects.add(uw);
                uw = new UniversalWall(-450 +j*padikXOffset, i*380, 390,60);
                objects.add(uw);
                uw = new UniversalWall(-460 +j*padikXOffset, i*380, 40,460);
                objects.add(uw);

                Floor f = new Floor();
                f.setY(i * f.getHeight() + floorYOffset * i);
                f.setX(padikXOffset * j);
                objects.add(f);

                Panelle p = new Panelle();
                p.setY(f.getY() + f.getHeight()/2);
                p.setX(f.getX() - p.getWidth());
                if(i != floors-1)
                objects.add(p);

                Stairs s = new Stairs();
                s.setX(p.getX() - s.getWidth());
                s.setY(f.getY());
                objects.add(s);

            }
        }
        return objects;
    }

    public static List<Container> getBuildingContainers(int floors, int padiks) throws IOException {

        List<Container> containers = new ArrayList<>();

        containers.add(new PostBox(130,floors * 380 + 70 ));

        for(int j = 0; j <padiks;j++)
        {
            for (int i = 0; i < floors; i++)
            {
                GarbageChute gc = new GarbageChute(780 + (j*padikXOffset),i*380 + 120);
                containers.add(gc);

                WindowSill ws = new WindowSill(-500 + j*padikXOffset, i*380 + 170);
                containers.add(ws);

                Heater ht = new Heater(115  + j*padikXOffset,25 + (380*i));
                containers.add(ht);

            }

        }
        return containers;
    }

    public static List<GameZone> getBuildingZones(int floors, int padiks)
    {
        List<GameZone> zones = new ArrayList<>();
        ArrayList<String> floorCoords = new ArrayList<>();

        for(int j = 0; j <padiks;j++)
        {
            for (int i = 0; i < floors; i++)
            {

                Lift lift = new Lift(floors,380,floors-i);
                MiniGameZone z  = new MiniGameZone(300 + j*padikXOffset,i*380+320,30,30,lift);
                zones.add(z);
                floorCoords.add(z.x + ":" + z.y);

                InteractionZone iz = new InteractionZone(20 + j * padikXOffset, 180 + i * 380, 40, 100)
                {
                    @Override
                    public void use()
                    {
                        if (Player.get().getX() > this.x) Player.get().setX(this.x - 80);
                        else Player.get().setX(this.x + 50);
                    }
                };
                zones.add(iz);
                zones.add(new MiniGameZone(150,floors * 380 + 500,30,30,new Lift(floors,380,1)));

            }
        }
        zones.add(new InteractionZone(-400, floors*380 + 150, 50,60) {
            @Override
            public void use() {
                if (Player.get().getY() > this.y) Player.get().setY(this.y - 200);
                else Player.get().setY(this.y + 80);
            }
        });
        zones.add(new InteractionZone(-400, floors*380, 50,60) {
            @Override
            public void use() {
                if (Player.get().getY() > this.y) Player.get().setY(this.y - 200);
                else Player.get().setY(this.y + 80);
            }
        });

        Collections.reverse(floorCoords);
        for(GameZone z : zones) if(z instanceof MiniGameZone && ((MiniGameZone)z).miniGame instanceof Lift) ((Lift)((MiniGameZone) z).miniGame).floorCoords = floorCoords;

        return zones;
    }



    private static class Floor extends GameObject {
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\newPadicki\\PadikiTamburFloor\\Этаж 4 960x360.png"));
            }catch (Exception e){
                System.out.println("несмог загрузщить падик бг");
            }
        }
        private Floor() {
            super(0,0,img);
            isSolid=false;
        }
    }

    private static class Stairs extends GameObject {
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\newPadicki\\PadikiStairs\\StairsTOp.png"));
            }catch (Exception e){
                System.out.println("несмог загрузщить лестницу новую");
            }
        }
        private Stairs() {
            super(0,0,img);
            isSolid=false;
        }
    }

    private static class Panelle extends GameObject {
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\newPadicki\\PadikiStairs\\panel.png"));
            }catch (Exception e){
                System.out.println("несмог загрузщить лестницу новую");
            }
        }
        private Panelle() {
            super(0,0,img);
            isSolid=false;
        }
    }

    private static class Pados extends GameObject{
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH+"buildings\\newPadicki\\PadikiTexture\\падик4 800х600.png"));
            }catch (Exception e){
                System.out.println("несмог загрузщить падик бг");
            }
        }
        private Pados() {
            super(0,0,img);
            isSolid=false;
        }
    }


}
