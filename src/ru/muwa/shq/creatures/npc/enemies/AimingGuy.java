package ru.muwa.shq.creatures.npc.enemies;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.items.Item;

import javax.imageio.ImageIO;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AimingGuy extends NPC {

    private static BufferedImage img;
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
        setRayCaster(new RayCaster(x,y,10));
    }

    Line2D line1 = new Line2D.Double();
    Line2D line2 = new Line2D.Double();

    public List<Line2D> getLines() {
        return lines;
    }

    Line2D line3 = new Line2D.Double();
    List<Line2D> lines = List.of(line1,line2,line3);
    @Override
    public ArrayList<Item> getRandomLoot() {
        return null;
    }
}
