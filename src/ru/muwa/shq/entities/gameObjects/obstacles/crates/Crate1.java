package ru.muwa.shq.entities.gameObjects.obstacles.crates;
import ru.muwa.shq.entities.gameObjects.GameObject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Класс, представляющий из себя ящик номер 0.
 */
public class Crate1 extends GameObject
{
    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File("c:\\sosok\\Shq_rdr\\src\\ru\\muwa\\shq\\textures\\obstacles\\crates\\crate1.png"));
            System.out.println("crate0 texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load crate0 texture");
        }

    }
    public Crate1(int x, int y) throws IOException {

    super(x,y,img);
    velocity = 5;
    isStanding = false;
}
}
