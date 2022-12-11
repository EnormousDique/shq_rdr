package ru.muwa.shq.engine.p.updaters;
import ru.muwa.shq.entities.gameObjects.GameObject;

/**
 * Класс, отвечающий, за обновление solid box игровых объектов.
 */
public class SolidBoxUpdater
{
    static private SolidBoxUpdater instance;
    private SolidBoxUpdater(){}
    public static SolidBoxUpdater getInstance()
    {if (instance == null) return new SolidBoxUpdater(); else return instance;}
    public void updateSolidBox(GameObject o)
    {
        o.getSolidBox().setBounds(o.getX(),o.getY(),o.getWidth(),o.getHeight());
    }

}
