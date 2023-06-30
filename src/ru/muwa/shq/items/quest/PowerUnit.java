package ru.muwa.shq.items.quest;

import ru.muwa.shq.engine.g.Renderer;
        import ru.muwa.shq.items.Item;
        import ru.muwa.shq.player.Inventory;
        import ru.muwa.shq.player.Player;

        import javax.imageio.ImageIO;
        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.IOException;

        import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class PowerUnit extends Item {

    public static final int ID = 308, PRICE = 6900;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "items\\quest\\PowerSupplyUnit.png"));
        }
        catch(IOException e) {
            System.out.println("неудалось загрузить картинку Блока питания");
        }
    }
    public PowerUnit() {
        super(ID, PRICE, WEIGHT, img);
        description = "блок питания";
    }

    @Override
    public void equip() {

    }

    @Override
    public void use() {


        Renderer.addMessage("400 ват . Чо блят?");
    }

    @Override
    public PowerUnit copy() {
        return null;
    }
}

