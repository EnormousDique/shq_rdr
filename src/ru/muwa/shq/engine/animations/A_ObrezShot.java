package ru.muwa.shq.engine.animations;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class A_ObrezShot extends Animation{
    public A_ObrezShot()
    {
        sprites = new ArrayList<>();
        try {
            sprites.add(ImageIO.read(new File(IMG_PATH + "player\\shotgun3.png")));
            sprites.add(ImageIO.read(new File(IMG_PATH + "player\\shotgun2.png")));
            System.out.println("произошла загрузка выстрела из обреза");
        }catch(Exception e){
            System.out.println("не удалось загрузить анимацию выстрела из обреза");
        }
    }
}
