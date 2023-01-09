package ru.muwa.shq.objects.obstacles.crates;
import ru.muwa.shq.objects.GameObject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Класс, представляющий из себя ящик номер 0.
 */
public class Crate0 extends GameObject
{
    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH + "obstacles\\crates\\crate0.png"));
            System.out.println("crate0 texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load crate0 texture");
        }

    }  public Crate0(int x, int y) throws IOException {

        super(x,y,img);
        velocity = 5;
        isStanding = false;
    }
}
