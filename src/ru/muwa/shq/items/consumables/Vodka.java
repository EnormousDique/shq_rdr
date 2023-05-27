
package ru.muwa.shq.items.consumables;

        import ru.muwa.shq.engine.Engine;
        import ru.muwa.shq.engine.utilities.EffectUtility;
        import ru.muwa.shq.items.Item;
        import ru.muwa.shq.objects.containers.Container;
        import ru.muwa.shq.player.Inventory;
        import ru.muwa.shq.player.Player;

        import javax.imageio.ImageIO;
        import java.awt.image.BufferedImage;
        import java.io.File;
        import java.io.IOException;

        import static ru.muwa.shq.objects.GameObject.IMG_PATH;

public class Vodka extends Item {

    public static final int ID = 0, PRICE = 59;
    public static final double WEIGHT = 0.5;
    private static BufferedImage img;
    static {
        try {
            img = ImageIO.read(new File(IMG_PATH + "consumables\\vodka.png"));
        }
        catch(IOException e) {
            System.out.println("failed to load Vodka image");
        }
    }
    public Vodka() {
        super(ID, PRICE, WEIGHT, img);
        description = "Водочка для блатной походочка";
    }

    @Override
    public void take(Container c){
        c.getItems().remove(this);
        Inventory.getInstance().addItem(this);

    }
    @Override
    public void use() {

        Player.get().setHighMeter(Player.get().getHighMeter()+20);

        Inventory.getInstance().getItems().remove(this);
    }
    @Override
    public void equip() {
    }
}
