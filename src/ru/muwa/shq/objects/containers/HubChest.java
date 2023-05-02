package ru.muwa.shq.objects.containers;

import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.items.drugs.Flour;
import ru.muwa.shq.objects.Usable;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.ui.containers.GarbageChuteUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HubChest extends Container implements Usable
{
    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH +"InvisibleTextures\\4040empty.png"));
            System.out.println("crate0 texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load crate0 texture");
        }

    }
    public HubChest(int x, int y) throws IOException {
        super(x, y,img);
        this.isSolid = true;
        items = new ArrayList<>();

    }

    @Override
    public void setInUse(boolean b) {
        super.setIsInUse(b);
    }

    @Override
    public boolean getInUse() {
        return super.isInUse();
    }
}