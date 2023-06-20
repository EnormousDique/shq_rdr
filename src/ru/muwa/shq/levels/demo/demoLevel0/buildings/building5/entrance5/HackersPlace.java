package ru.muwa.shq.levels.demo.demoLevel0.buildings.building5.entrance5;


import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.items.quest.PowerUnit;
import ru.muwa.shq.levels.Level;
import ru.muwa.shq.levels.demo.demoLevel0.DemoLevel0;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.objects.bounds.UniversalWall;
import ru.muwa.shq.objects.buildings.questBuidlings.HackerEntrance;
import ru.muwa.shq.objects.containers.PostBox;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.GameZone;
import ru.muwa.shq.zones.InteractionZone;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class HackersPlace extends Level {

    public static NPC hackerNPC;
    private static HackersPlace instance;

    public static HackersPlace getInstance() {
        if (instance == null) return new HackersPlace();
        else return instance;
    }

    private HackersPlace() {
        instance = this;
        objects.add(new BG());
        objects.add(new UniversalWall(-40,-30,40,600));
        objects.add(new UniversalWall(-40,-30,600,30));
        objects.add(new UniversalWall(-30,360,600,30));
        objects.add(new UniversalWall(451,-30,40,600));

        try {
            zones.add(new EnterZone(20,250,100,100,L1B5P5F1.getInstance(),100,100,false));
        } catch (IOException e) {
            System.out.println("ne mogu poluchit l1b5p5f1");
        }
        zones.add(new InteractionZone(300,50,100,50) {
            @Override
            public void use() {
                Inventory.getInstance().addItem(new PowerUnit());
                Renderer.addMessage("взял детали от компа");
                zones.remove(this);
            }
        });
    }

    private static class BG extends GameObject {
        static BufferedImage img;
        static {
            try {
                img = ImageIO.read(new File(IMG_PATH + "buildings\\хатахакера.png"));
            } catch (IOException e) {
                System.out.println("не могу загрузить картинку бг хакера");
            }
        }

        private BG() {
            super(0,0,img);
            this.isSolid=false;
        }

    }
}
