package ru.muwa.shq.objects.containers;

import ru.muwa.shq.minigames.shquring.PostBoxShq;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PostBox extends Container
{

    private static BufferedImage img;
    static{
        try{
            img = ImageIO.read(new File(IMG_PATH + "buildings\\indoors\\postbox.png"));
        }catch (Exception e){
            System.out.println("Failed to load postbox texture");
        }
    }
    /**
     * Конструктор
     *
     * @param x
     * @param y
     */
    public PostBox(int x, int y) {
        super(x, y, img);
        shqurable=true;
        miniGame = new PostBoxShq(this);
    }

    @Override
    public void setInUse(boolean b) {

    }

    @Override
    public boolean getInUse() {
        return false;
    }
}
