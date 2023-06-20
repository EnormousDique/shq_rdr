package ru.muwa.shq.creatures.npc.enemies;

import ru.muwa.shq.economics.money.Money_500;
import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.drugs.Flour;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static ru.muwa.shq.objects.GameObject.Direction.RIGHT;

public class VelvetTank extends NPC {

    private static BufferedImage img;

    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "creatures\\BIGCOP.png"));
            System.out.println("VelvetTank loaded ok");
        } catch (Exception e) {
            System.out.println("failed to load VelvetTank texture");
        }
    }

    public VelvetTank(int x, int y) {
        super(x, y, img);
        isEnemy=   true;
        velocity = 1;
        speed = 5;
        direction = RIGHT;
        setRayCaster(new RayCaster(x, y, 600));
        hp = 50;
        maxHp = hp;
        //текстура трупа
        try {
            corpseimg = ImageIO.read(new File(IMG_PATH + "containers\\CORPSECOP.png"));
            System.out.println("CORPSECOP texture loaded");
        } catch (IOException e) {
            System.out.println("failed to load CORPSECOP texture");
        }
    }

    @Override
    public ArrayList<Item> getRandomLoot() {

        ArrayList<Item> loot = new ArrayList<>();

        loot.add(new Money_500());

        return loot;
    }
}

