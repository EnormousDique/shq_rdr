package ru.muwa.shq.creatures.npc.enemies;

import ru.muwa.shq.economics.money.Money_500;
import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.items.knifes.Kortique;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static ru.muwa.shq.objects.GameObject.Direction.RIGHT;

public class BadGuy0 extends NPC {

    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "creatures\\TONYMANTA.png"));
            System.out.println("BadGuy0 loaded ok");
        }
        catch(Exception e)
        {
            System.out.println("failed to load BadGuy0 texture");
        }
    }
    public BadGuy0(int x, int y)
    {
        super(x,y,img);
        isEnemy=true;
        velocity = 2;
        speed = 5;
        direction = RIGHT;
        setRayCaster(new RayCaster(x,y,600));
        hp = 20;
        maxHp = hp;
        //текстура трупа
        try
        {
            corpseimg = ImageIO.read(new File(IMG_PATH +"containers\\gopNickCORPSE.png"));
            System.out.println("BadGuy0 texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load BadGuy0Coprse texture");
        }
    }

    @Override
    public ArrayList<Item> getRandomLoot() {

        ArrayList<Item> loot = new ArrayList<>();

        if(Math.random()>0.66) loot.add(new Money_500());
        if(Math.random()>0.75) loot.add(new Kortique());
        return loot;
    }

    protected BadGuy0(int x, int y, BufferedImage texture)
    {
        super(x, y, texture);
    }

}
