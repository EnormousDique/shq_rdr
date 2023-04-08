package ru.muwa.shq.engine.utilities1;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.player.Player;

/**
 * Класс, отвечающий, за обновление solid box игровых объектов.
 */
public class SolidBoxUpdater {
    public static void updateSolidBox(GameObject o) {
        o.getSolidBox().setBounds(o.getX(),o.getY(),o.getTexture().getWidth(),o.getTexture().getHeight());
        if(o.equals(Player.get())){
        }
    }

}
