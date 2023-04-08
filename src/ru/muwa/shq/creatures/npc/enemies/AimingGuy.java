package ru.muwa.shq.creatures.npc.enemies;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.items.Item;
import ru.muwa.shq.items.guns.ammo.MakarovAmmo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class AimingGuy extends NPC {
    private static BufferedImage img;

    private long lastTimeShot;
    private Rectangle nearZone = new Rectangle();

    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "creatures\\BadGuy0.png"));
            System.out.println("BadGuy0 loaded ok");
        }
        catch(Exception e)
        {
            System.out.println("failed to load BadGuy0 texture");
        }
    }

    /**
     * Конструктор
     *
     * @param x       - х коррдината
     * @param y       - у коррдината
     */
    public AimingGuy(int x, int y) {
        super(x, y, img);
        setRayCaster(new RayCaster(x,y,1000));
        speed = 4;
        hp=20;
        try
        {
            corpseimg = ImageIO.read(new File(IMG_PATH +"containers\\BadGuyCorpse.png"));
            System.out.println("BadGuyCorpse texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load BadGuyCoprse texture");
        }
        nearZone.setBounds(this.x - 150, this.y -150,300,300);
    }

    public Rectangle getNearZone() {
        return nearZone;
    }

    public void setNearZone(Rectangle nearZone) {
        this.nearZone = nearZone;
    }

    public void updateNearZone()
    {
        nearZone.setBounds(this.x - 250, this.y -250,500,500);
    }

    public long getLastTimeShot() {
        return lastTimeShot;
    }

    public void setLastTimeShot(long lastTimeShot) {
        this.lastTimeShot = lastTimeShot;
    }

    public List<Line2D> getLines() {
        return lines;
    }

    @Override
    public ArrayList<Item> getRandomLoot() {

        ArrayList<Item> drop = new ArrayList<>();
        drop.add(new MakarovAmmo());
        return drop;

    }
}
