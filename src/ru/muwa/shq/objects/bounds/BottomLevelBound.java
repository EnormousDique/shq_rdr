package ru.muwa.shq.objects.bounds;
import ru.muwa.shq.objects.GameObject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Класс, представляющий из себя ящик номер 0.
 */
public class BottomLevelBound extends GameObject
{
    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH +"bounds\\bottom_bound.png"));
            System.out.println("crate0 texture loaded");
        }
        catch (IOException e)
        {
            System.out.println("failed to load crate0 texture");
        }

    }

    public BottomLevelBound(int x, int y) throws IOException
    {
        super(x,y,img);
        isStatic = true;
        isSolid = true;
    }
}
