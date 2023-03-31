package ru.muwa.shq.engine.p.collisions;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.combat.CombatUtility;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.items.guns.Bullet;
import ru.muwa.shq.items.guns.EnemyBullet;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Player;

import java.util.LinkedList;
import java.util.List;

import static ru.muwa.shq.objects.GameObject.Direction.DOWN;

/**
 * Класс, отвечающий за проверку столкновений игровых объектов.
 */
public class CollisionsChecker {
    private static CollisionsChecker instance;

    private CollisionsChecker() {
        if (instance != null) return;

        instance = this;
    }

    public static CollisionsChecker getInstance() {
        if (instance == null) return new CollisionsChecker();
        return instance;
    }

    public void checkCollisions(GameObject o, List<GameObject> objects) {
        checkObjectCollisions(o, objects);
    }

    /**
     * Метод определения столкновений игрового объекта с остальными объектами.
     *
     * @param o       - Целевой обект. (Тот, кого проверяем и будем двигать)
     * @param objects - Остальные объекты
     */
    //TODO: Переписать с опорой на центры объектов.
    private void checkObjectCollisions(GameObject o, List<GameObject> objects) {
        List<GameObject> list = objects.stream().filter(ob -> !ob.equals(o)).filter(ob -> ob.getIsSolid()).toList();
        if (o != null) {

            for (GameObject obj : list) { //Перебираем объекты.
                if (o.getSolidBox().intersects(obj.getSolidBox())) // произошло столкновение
                {

                    if (o.getSolidBox().getCenterY() > obj.getSolidBox().getCenterY() && o.getX() + o.getWidth() < obj.getX() + obj.getWidth() && o.getX() > obj.getX()) {
                        o.setY(obj.getY() + obj.getHeight());
                        System.out.println("вниз");
                    }//Вниз
                    /*else*/



                    //я тупой дибил пидарас и сука крышу у людей последнее




                    if (o.getY() < obj.getSolidBox().getCenterY() && o.getX() + o.getWidth() < obj.getX() + obj.getWidth() && o.getX() > obj.getX()) {
                        o.setY(obj.getY() - o.getHeight());
                        System.out.println("вверх");
                    }//Вверх
                    /*else*/
                    if (o.getX() + o.getWidth() > obj.getX() + obj.getWidth() && o.getY() + o.getHeight() > obj.getY() && o.getY() < obj.getY() + obj.getHeight()) {
                        o.setX(obj.getX() + obj.getWidth());
                        System.out.println("право");
                    }//Вправо
                    /*else*/
                    if (o.getX() < obj.getX() && o.getY() + o.getHeight() > obj.getY() && o.getY() < obj.getY() + obj.getHeight()) {
                        o.setX(obj.getX() - o.getWidth());
                        System.out.println("vlevo");
                    }//Влево

                    //Код для уничтожения пуль после столкновения
                    if (obj instanceof Bullet) Engine.getCurrentLevel().getObjects().remove(obj);
                    if (o instanceof Bullet) Engine.getCurrentLevel().getObjects().remove(o);
                    if (obj instanceof Bullet && o.equals(Player.get())) {
                        CombatUtility.attack(Player.get(), 10);
                    }
                }
            }
        }
    }

    public void checkCollisionsNPC(NPC c, LinkedList<GameObject> objects) {
        if (c != null && !c.isStatic()) { //Неподвижные объекты мимо.
            for (GameObject obj : objects) { //Перебираем объекты.
                if (c.getSolidBox().intersects(obj.getSolidBox()) && obj.getIsSolid()) // произошло столкновение
                {

                    if (c.getY() + c.getHeight() > obj.getSolidBox().getCenterY() && c.getX() + c.getWidth() < obj.getX() + obj.getWidth() && c.getX() > obj.getX()) {
                        c.setY(obj.getY() + obj.getHeight());
                        System.out.println("вниз");
                    }//Вниз
                    /*else*/
                    if (c.getY() < obj.getSolidBox().getCenterY() && c.getX() + c.getWidth() < obj.getX() + obj.getWidth() && c.getX() > obj.getX()) {
                        c.setY(obj.getY() - c.getHeight());
                        System.out.println("вверх");
                    }//Вверх
                    /*else*/
                    if (c.getX() + c.getWidth() > obj.getX() + obj.getWidth() && c.getY() + c.getHeight() > obj.getY() && c.getY() < obj.getY() + obj.getHeight()) {
                        c.setX(obj.getX() + obj.getWidth());
                        System.out.println("право");
                    }//Вправо
                    /*else*/
                    if (c.getX() < obj.getX() && c.getY() + c.getHeight() > obj.getY() && c.getY() < obj.getY() + obj.getHeight()) {
                        c.setX(obj.getX() - c.getWidth());
                        System.out.println("vlevo");
                    }//Влево

                    //Код для уничтожения пуль после столкновения и нанесение урона
                    if (obj instanceof Bullet) {
                        Engine.getCurrentLevel().getObjects().remove(obj);
                        CombatUtility.attack(c, 10);
                        break;
                    }

                }




            /*
            for (GameObject obj : objects)
            { //Перебираем объекты.
                if (c.getSolidBox().intersects(obj.getSolidBox()) && obj.solid())
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
                    //Код для уничтожения пуль после столкновения и нанесение урона
                    if(obj instanceof Bullet)
                    {
                        Engine.getCurrentLevel().getObjects().remove(obj);
                        CombatUtility.attack(c, 10);
                        break;
                    }
                }
            }*/
            }
        }
    }
}
