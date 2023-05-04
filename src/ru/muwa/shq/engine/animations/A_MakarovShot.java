package ru.muwa.shq.engine.animations;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class A_MakarovShot extends Animation{
    public A_MakarovShot(){
        sprites = new ArrayList<>();
        try {
            sprites.add(ImageIO.read(new File(IMG_PATH + "player\\shoot.png")));
            sprites.add(ImageIO.read(new File(IMG_PATH + "player\\shoot1.png")));
            System.out.println("произошла загрузка выстрела из макарова");
        }catch(Exception e){
            System.out.println("не удалось загрузить анимацию выстрела из макарова");
        }
    }
}
