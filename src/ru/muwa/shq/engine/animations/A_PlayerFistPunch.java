package ru.muwa.shq.engine.animations;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class A_PlayerFistPunch extends Animation{


    public A_PlayerFistPunch()
    {
        sprites = new ArrayList<>();
        try
        {
            sprites.add(ImageIO.read(new File(IMG_PATH + "player\\kulaginFist_a1.png")));
            sprites.add(ImageIO.read(new File(IMG_PATH + "player\\kulaginFist_a2.png")));
            sprites.add(ImageIO.read(new File(IMG_PATH + "player\\kulaginFist_a3.png")));
            System.out.println("произошла загрузка анимации удара");
        }
        catch(Exception e)
        {
            System.out.println("Произошла ошибка при загрузке анимации удара");
            System.out.println(e);
        }

    }
    @Override
    public ArrayList<BufferedImage> getSprites() {

        return sprites;
    }

}


