package ru.muwa.shq.engine.p.collisions;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.npc.NPC;

import java.util.LinkedList;
import java.util.List;

import static ru.muwa.shq.objects.GameObject.Direction.DOWN;

/**
 * Класс, отвечающий за проверку столкновений игровых объектов.
 */
public class CollisionsChecker
{
    private static CollisionsChecker instance;
    private CollisionsChecker()
    {
        if (instance != null) return;

        instance = this;
    }
    public static CollisionsChecker getInstance()
    {
        if(instance == null) return new CollisionsChecker();
        return instance;
    }
    public void checkCollisions(GameObject o, LinkedList<GameObject> objects)
    {
        checkObjectCollisions(o,objects);
        checkBottomCollisions(o);
    }

    /**
     * Метод определения столкновений игрового объекта с остальными объектами.
     * @param o - Целевой обект. (Тот, кого проверяем и будем двигать)
     * @param objects - Остальные объекты
     */
    private void checkObjectCollisions(GameObject o, LinkedList<GameObject> objects) {
        List<GameObject> list = objects.stream().filter(ob -> !ob.equals(o)).toList();
        if (o != null ) {
            for (GameObject obj : list) { //Перебираем объекты.
                if (o.getSolidBox().intersects(obj.getSolidBox())) // произошло столкновение
                {
                    if(o.getY() < obj.getY() //Голова выше крыши
                            &&
                            o.getX() > obj.getX()  // внутри по х
                            &&
                            o.getX() < obj.getX()+obj.getWidth()) // внутри по х
                        o.setY(obj.getY()-o.getHeight());//Вверх


                    if(o.getY() + o.getHeight() > obj.getY() + obj.getHeight() && o.getX() + o.getWidth() > obj.getX() && o.getX() < obj.getX()+obj.getWidth())
                        o.setY(obj.getY()+obj.getHeight());//Вниз

                    if(o.getX() + o.getWidth() > obj.getX() + obj.getWidth() && o.getY() + o.getHeight() > obj.getY() && o.getY() < obj.getY()+obj.getHeight())
                    {o.setX(obj.getX()+obj.getWidth());
                        System.out.println("право");}//Вправо
                    if(o.getX()  < obj.getX() && o.getY() + o.getHeight() > obj.getY() && o.getY() < obj.getY()+obj.getHeight())
                    {o.setX(obj.getX()-o.getWidth());
                        System.out.println("vlevo");}//Влево
                }
            }
        }
    }

    public void checkBottomCollisions(GameObject o)
    {
        if(o != null & !o.isStatic())
        {
            if(o.getY() + o.getHeight() >= GameScreen.SCREEN_HEIGHT)
            {
                o.setStanding();
                o.setY(o.getY() - (o.getY() + o.getHeight() - GameScreen.SCREEN_HEIGHT));
            }
        }
    }

    public void checkCollisionsNPC(NPC c, LinkedList<GameObject> objects)
    {

        if(c != null && !c.isStatic())
        { //Неподвижные объекты мимо.
            for (GameObject obj : objects)
            { //Перебираем объекты.
                if (c.getSolidBox().intersects(obj.getSolidBox()))
                { //Проверяем на пересечение.
                    //System.out.println("Обекты пересекаются");
                    //Если ноги выше крыши, то толкаем вверх.
                    if(c.getY() + c.getHeight() < obj.getSolidBox().getCenterY())
                    {
                        c.setY(obj.getY() - c.getHeight() - 2);
                        c.setStanding(true);
                    }
                        //Если голова ниже дна, толкаем вниз.
                    else if(c.getY() > obj.getY()+ obj.getHeight() -10) c.setY(obj.getY() + obj.getHeight() + 2);
                        //Иначе толкаем в бок.
                    else if(c.getSolidBox().getCenterX() < obj.getSolidBox().getCenterX()) c.setX(obj.getX() - c.getWidth());
                        //И в другой бок.
                    else if(c.getSolidBox().getCenterX() > obj.getSolidBox().getCenterX()) c.setX(obj.getX() + obj.getWidth());
                }
            }
        }
    }
}
