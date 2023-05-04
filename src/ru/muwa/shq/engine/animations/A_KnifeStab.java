package ru.muwa.shq.engine.animations;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class A_KnifeStab extends Animation{
    public A_KnifeStab(){
        sprites=new ArrayList<>();
        try {
            sprites.add(ImageIO.read(new File(IMG_PATH + "player\\Knife3.png")));
            sprites.add(ImageIO.read(new File(IMG_PATH + "player\\KulaginKnife.png")));
            sprites.add(ImageIO.read(new File(IMG_PATH + "player\\KulaginKnife5.png")));
            sprites.add(ImageIO.read(new File(IMG_PATH + "player\\Knife2.png")));
            System.out.println("произошла загрузка выстрела из макарова");
        }catch(Exception e){
            System.out.println("не удалось загрузить анимацию выстрела из макарова");
        }
    }
}
