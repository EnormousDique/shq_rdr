package ru.muwa.shq.economics.money;

import ru.muwa.shq.items.Item;
import ru.muwa.shq.objects.containers.Container;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Money_500 extends Money {

    private final int MONEY_500_PRICE = 500;
    //todo нарисовать текстурку

    private static BufferedImage img;
    static
    {
        try
        {
            img = ImageIO.read(new File(IMG_PATH+"money\\500.png"));
            System.out.println("money texture load ok");
        }catch(Exception e)
        {
            System.out.println("money texture load NOT ok");
        }
    }
    public Money_500() {
        super(0, 500, 0, img);
    }

    @Override
    public void give(Container c) {

    }

    @Override
    public void use() {

    }

    @Override
    public void equip() {

    }
}
