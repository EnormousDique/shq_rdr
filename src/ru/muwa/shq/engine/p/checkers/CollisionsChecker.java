package ru.muwa.shq.engine.p.checkers;
import ru.muwa.shq.engine.ai.AI;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.entities.gameObjects.GameObject;
import ru.muwa.shq.entities.gameObjects.creatures.npc.NPC;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.muwa.shq.engine.ai.AI.Events.COLLISION;
import static ru.muwa.shq.entities.gameObjects.GameObject.Direction.*;
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
    private void checkObjectCollisions(GameObject o, LinkedList<GameObject> objects)
    {
        List<GameObject> list = objects.stream().filter(ob -> !ob.equals(o)).toList();
        if(o != null && !o.isStatic())
        { //Неподвижные объекты мимо
            for (GameObject obj : list)
            { //Перебираем объекты
                if (o.getSolidBox().intersects(obj.getSolidBox()))
                { //Проверяем на пересечение
                    System.out.println("Обекты пересекаются");
                    switch (o.getDirection())
                    {//Смотрим направление проверяемого объекта
                        case DOWN, DOWN_LEFT, DOWN_RIGHT: //Летит вниз.
                            if(!o.standing()) o.setY(obj.getY()-o.getHeight()); // Выталкиваем за грани объекта (вверх)
                            if(o.getDirection() == DOWN_LEFT) o.setDirection(LEFT); //Меняем направление
                            if(o.getDirection() == DOWN_RIGHT) o.setDirection(RIGHT);//В зависимости
                            if(o.getDirection() == DOWN) o.setDirection(NONE); //От текущего направления
                            o.setStanding(); //Обект теперь стоит (на "крыше" обекта,  с которым столкнулся)
                            break;
                        case UP,UP_LEFT,UP_RIGHT: //Летит вверх
                            o.setY(obj.getY()+ obj.getHeight()); // Выталкиваем за грани обекта (вниз)
                            o.setDirection(DOWN); // Теперь объект падает
                            o.setStanding(false);// Уж точно не стоит..
                            break;
                        case LEFT: // Двигается влево
                            o.setX(obj.getX() + obj.getWidth() + 5); // Выталкиваем за правую границу объекта
                            o.setDirection(NONE);
                            break;
                        case RIGHT:
                            o.setX(obj.getX() - o.getWidth() - 5); // Выталкиваем за левую границу объекта
                            o.setDirection(NONE);
                            break;
                    }
                }
            }
        }
        }
    public void checkBottomCollisions(GameObject o)
    {
        if(o != null)
        {
            if(o.getY() + o.getHeight() >= Renderer.SCREEN_HEIGHT)
            {
                //System.out.println(o.getY()+o.getHeight());
                o.setStanding();
                o.setY(o.getY() - (o.getY() + o.getHeight() - Renderer.SCREEN_HEIGHT));

            }
        }
    }

    public void checkCollisionsNPC(NPC c, LinkedList<GameObject> objects)
    {

        if(c != null && !c.isStatic())
        { //Неподвижные объекты мимо
            for (GameObject obj : objects)
            { //Перебираем объекты
                if (c.getSolidBox().intersects(obj.getSolidBox()))
                { //Проверяем на пересечение
                    System.out.println("Обекты пересекаются");
                    switch (c.getDirection())
                    {//Смотрим направление проверяемого объекта
                        case DOWN, DOWN_LEFT, DOWN_RIGHT: //Летит вниз.
                            if(!c.standing()) c.setY(obj.getY()-c.getHeight()); // Выталкиваем за грани объекта (вверх)
                            if(c.getDirection() == DOWN_LEFT) c.setDirection(LEFT); //Меняем направление
                            if(c.getDirection() == DOWN_RIGHT) c.setDirection(RIGHT);//В зависимости
                            if(c.getDirection() == DOWN) c.setDirection(NONE); //От текущего направления
                            c.setStanding(); //Обект теперь стоит (на "крыше" обекта,  с которым столкнулся)
                            break;
                        case UP,UP_LEFT,UP_RIGHT: //Летит вверх
                            c.setY(obj.getY()+ obj.getHeight()); // Выталкиваем за грани обекта (вниз)
                            c.setDirection(DOWN); // Теперь объект падает
                            c.setStanding(false);// Уж точно не стоит..
                            break;
                        case LEFT: // Двигается влево
                            c.setX(obj.getX() + obj.getWidth() + 5); // Выталкиваем за правую границу объекта
                            c.setDirection(NONE);
                            AI.getInstance().changeDirectionOnEvent(c, COLLISION);
                            break;
                        case RIGHT:
                            c.setX(obj.getX() - c.getWidth() - 5); // Выталкиваем за левую границу объекта
                            c.setDirection(NONE);
                            AI.getInstance().changeDirectionOnEvent(c, COLLISION);
                            break;
                    }
                }
            }
        }
    }
}
