package ru.muwa.shq.engine.p.collisions;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.combat.CombatUtility;
import ru.muwa.shq.items.guns.Bullet;
import ru.muwa.shq.items.guns.EnemyBullet;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        for(int i = 0; i < Engine.getCurrentLevel().getObjects().size();i++) {
            if (Engine.getCurrentLevel().getObjects().get(i) instanceof Bullet
                    ||
                    Engine.getCurrentLevel().getObjects().get(i) instanceof EnemyBullet) {
                bulletCollisions((Bullet) Engine.getCurrentLevel().getObjects().get(i));
            }
        }
    }

    private static void bulletCollisions(Bullet b)
    {
        for (int i = 0; i < Engine.getCurrentLevel().getObjects().size();i++)
        {
            GameObject o = Engine.getCurrentLevel().getObjects().get(i);
            if(!(o instanceof Bullet)  &&  o.getIsSolid() && o.getSolidBox().intersects(b.getSolidBox()))

            {
                Engine.getCurrentLevel().getObjects().remove(b);
                System.out.println("пуля врезалась вв стену и была удалена");
            }
        }
    }

    /**
     *
     * Метод определения столкновений игрового объекта с остальными объектами.
     *
     * @param o       - Целевой обект. (Тот, кого проверяем и будем двигать)
     * @param objects - Остальные объекты
     *
     */
    //TODO: Переписать с опорой на линии солид бокса
    private void checkObjectCollisions(GameObject o, List<GameObject> objects) {


        //Отффильтровываем объекты для работы
        List<GameObject> list = objects.stream().filter(ob -> !ob.equals(o)).filter(ob -> ob.getIsSolid()).collect(Collectors.toList());

        //Определяем ключевые точки и линии для проверяемого объекта
        if (o != null) {

            //TODO: Снова перелопатить логику.
            // За итерацию цикла делать следующие действия:
            // - Проходимся по объектам, и проверяем, какие стороны игрока "застряли в стене"
            // - Уже после определния сторон, "застрявших в стене" принимаем решение о направлении выталкивания.

            Point2D
                    lt = new Point(o.getX(), o.getY()),
                    rt = new Point(o.getX() + o.getWidth(), o.getY()),
                    lb = new Point(o.getX(), o.getY() + o.getHeight()),
                    rb = new Point(o.getX() + o.getWidth(), o.getY() + o.getHeight());

            Line2D
                    topLine = new Line2D.Double(lt, rt),
                    bottomLine = new Line2D.Double(lb, rb),
                    leftLine = new Line2D.Double(lt, lb),
                    rightLine = new Line2D.Double(rt, rb);


            //Определяем с каким кол-вом стен произошло пересечение
            ArrayList<GameObject> walls = new ArrayList<>();

            for (GameObject ob : list) {
                if (Player.get().getSolidBox().intersects(ob.getSolidBox())) walls.add(ob);
            }


                /** ПРОВЕРКИ ПО ТОЧКАМ **/

                //Если столкновение только с одной стеной
                for (GameObject obj : walls) {
                    //Перебираем объекты.
                    //Новый код проверки столкновений
                    //Проверка,что проверяемый объект зашел в стену "верхом"

                    if (obj.getSolidBox().contains(lt) &&
                            obj.getSolidBox().contains(rt) &&
                            !obj.getSolidBox().contains(lb) &&
                            !obj.getSolidBox().contains(rb)) {
                        o.setY((int) (obj.getSolidBox().getY() + obj.getSolidBox().getHeight()));
                    }
                    //Проверка,что проверяемый объект зашел в стену "низом"
                    if (obj.getSolidBox().contains(lb) &&
                            obj.getSolidBox().contains(rb) &&
                            !obj.getSolidBox().contains(lt) &&
                            !obj.getSolidBox().contains(rt)) {
                        o.setY((int) (obj.getSolidBox().getY() - o.getHeight()));
                    }
                    //Проверка,что проверяемый объект зашел в стену "левом"
                    if (obj.getSolidBox().contains(lb) &&
                            obj.getSolidBox().contains(lt) &&
                            !obj.getSolidBox().contains(rt) &&
                            !obj.getSolidBox().contains(rb)) {
                        o.setX((int) (obj.getSolidBox().getX() + obj.getSolidBox().getWidth()));
                    }
                    //Проверка,что проверяемый объект зашел в стену "правом"
                    if (obj.getSolidBox().contains(rb) &&
                            obj.getSolidBox().contains(rt) &&
                            !obj.getSolidBox().contains(lt) &&
                            !obj.getSolidBox().contains(lb)) {
                        o.setX((int) (obj.getSolidBox().getX() - o.getWidth()));
                    }


                    /** ПРОВЕРКИ ПО ЛИНИЯМ **/
                    //Проверка по 3-м линиям
                    //Столкновение со стеной верх + право + лево
                    if(topLine.intersects(obj.getSolidBox()) &&
                            leftLine.intersects(obj.getSolidBox())&&
                            rightLine.intersects(obj.getSolidBox())&&
                            !bottomLine.intersects(obj.getSolidBox()))
                    {
                        o.setY(obj.getSolidBox().y + obj.getHeight());
                    }
                    //Столкновение со стеной верхний левым углом
                    if(topLine.intersects(obj.getSolidBox()) &&
                            leftLine.intersects(obj.getSolidBox())&&
                            !rightLine.intersects(obj.getSolidBox())&&
                            !bottomLine.intersects(obj.getSolidBox()))
                    {
                        o.setY(obj.getSolidBox().y + (int)obj.getSolidBox().getHeight());
                        o.setX(obj.getSolidBox().x + (int) obj.getSolidBox().getWidth());
                    }
                    //Столкновение со стеной верхний правый угол
                    if(topLine.intersects(obj.getSolidBox()) &&
                            !leftLine.intersects(obj.getSolidBox())&&
                            rightLine.intersects(obj.getSolidBox())&&
                            !bottomLine.intersects(obj.getSolidBox()))
                    {
                        o.setY(obj.getSolidBox().y + (int) obj.getSolidBox().getHeight());
                        o.setX(obj.getSolidBox().x - o.getWidth());
                    }
                    //Столкновение со стеной низ + право + лево
                    if(!topLine.intersects(obj.getSolidBox()) &&
                            leftLine.intersects(obj.getSolidBox())&&
                            rightLine.intersects(obj.getSolidBox())&&
                            bottomLine.intersects(obj.getSolidBox()))
                    {
                        o.setY(obj.getSolidBox().y - o.getHeight());
                    }
                    //Столкновение со стеной нижним левым углом
                    if(!topLine.intersects(obj.getSolidBox()) &&
                            leftLine.intersects(obj.getSolidBox())&&
                            !rightLine.intersects(obj.getSolidBox())&&
                            bottomLine.intersects(obj.getSolidBox()))
                    {
                        o.setY(obj.getSolidBox().y - o.getHeight());
                        o.setX(obj.getSolidBox().x +(int) obj.getSolidBox().getWidth());
                    }
                    //Столкновение со стеной нижним правым углом
                    if(!topLine.intersects(obj.getSolidBox()) &&
                            !leftLine.intersects(obj.getSolidBox())&&
                            rightLine.intersects(obj.getSolidBox())&&
                            !bottomLine.intersects(obj.getSolidBox()))
                    {
                        o.setY(obj.getSolidBox().y + o.getHeight());
                        o.setX(obj.getSolidBox().x - o.getWidth());
                    }
                    //Проверка, что проверяемый обект зашел боковыми линиями
                    if(leftLine.intersects(obj.getSolidBox()) && rightLine.intersects(obj.getSolidBox()))
                    {
                       if(obj.getSolidBox().getY()+obj.getSolidBox().getHeight() > o.getY()) o.setY(obj.getSolidBox().y-o.getHeight());
                       if(obj.getSolidBox().getY() < o.getY() + o.getHeight()) o.setY((int) (obj.getSolidBox().y+obj.getSolidBox().getHeight()));
                    }
                    //Проверка, что проверяемый обект зашел верхней и нижней линиями
                    if(topLine.intersects(obj.getSolidBox()) && bottomLine.intersects(obj.getSolidBox()))
                    {
                        if(obj.getSolidBox().getX()+obj.getSolidBox().getWidth() > o.getX()) o.setX(obj.getX()-o.getWidth());
                        if(obj.getSolidBox().getX() < o.getX() + o.getWidth()) o.setX((int) (obj.getX()+obj.getSolidBox().getWidth()));
                    }
                    //Столкновение со стеной только верхней линией
                    if(topLine.intersects(obj.getSolidBox()) &&
                        !leftLine.intersects(obj.getSolidBox())&&
                        !rightLine.intersects(obj.getSolidBox())&&
                        !bottomLine.intersects(obj.getSolidBox()))
                    {
                        o.setY(obj.getSolidBox().y + obj.getHeight());
                    }
                    //Столкновение со стеной только левой линией
                    if(!topLine.intersects(obj.getSolidBox()) &&
                            leftLine.intersects(obj.getSolidBox())&&
                            !rightLine.intersects(obj.getSolidBox())&&
                            !bottomLine.intersects(obj.getSolidBox()))
                    {
                        o.setX(obj.getSolidBox().x + (int) obj.getSolidBox().getWidth());
                    }
                    //Столкновение со стеной только правой линией
                    if(!topLine.intersects(obj.getSolidBox()) &&
                            !leftLine.intersects(obj.getSolidBox())&&
                            rightLine.intersects(obj.getSolidBox())&&
                            !bottomLine.intersects(obj.getSolidBox()))
                    {
                        o.setX(obj.getSolidBox().x - o.getWidth());
                    }
                    //Столкновение со стеной только нижней линией
                    if(topLine.intersects(obj.getSolidBox()) &&
                            !leftLine.intersects(obj.getSolidBox())&&
                            !rightLine.intersects(obj.getSolidBox())&&
                            bottomLine.intersects(obj.getSolidBox()))
                    {
                        o.setY(obj.getSolidBox().y - (int) o.getSolidBox().getHeight());
                    }
                    //TODO: проверить верх + пр + лв ; проерить вер + лв ; проверить верх+ пр; все то е для низа



                    //Код для уничтожения пуль после столкновения
                    // if (obj instanceof Bullet) Engine.getCurrentLevel().getObjects().remove(obj);


                    if (obj instanceof Bullet && o.getSolidBox().intersects(obj.getSolidBox())) {
                        if (o.equals(Player.get()))
                            CombatUtility.attack(Player.get(), 5); // ТЕСТ. игрок получает 5,  не 10 урона от пуль
                        Engine.getCurrentLevel().getObjects().remove(obj);

                    }
                    if (o instanceof Bullet & o.getSolidBox().intersects(obj.getSolidBox()))
                        Engine.getCurrentLevel().getObjects().remove(o);
                    if (obj instanceof Bullet & obj.getSolidBox().intersects(o.getSolidBox()))
                        Engine.getCurrentLevel().getObjects().remove(obj);
                    //Тестируем удаление пуль при столкновении с твердыми объектами
                }
            }

        }

    public  void checkAttackCollisions(){
        for (int i = 0; i < Engine.getCurrentLevel().getNPC().size();i++){
            NPC npc = Engine.getCurrentLevel().getNPC().get(i);
           if (Engine.getCurrentLevel().getNPC().get(i).getSolidBox().intersects(Player.get().getAttackZone()))
           {
               double
                       velocity =
                               Player.get().currentWeapon == null? 20 : Player.get().currentWeapon.getThrowback(),

                       xVelocity =
                               velocity * Math.cos(-1*Math.toRadians(Aim.getInstance().calculateAngle() )+90),

                       yVelocity =
                               velocity * Math.sin(-1*Math.toRadians(Aim.getInstance().calculateAngle())+90);


               npc.setX((int) (npc.getX() + ( ( xVelocity  )*(4 ) )));
               npc.setY((int) (npc.getY() + ( (yVelocity )*(4) )));
           }
        }
        }


    public void checkCollisionsNPC(NPC c, LinkedList<GameObject> objects) {
        if (c != null && !c.isStatic()) { //Неподвижные объекты мимо.
            for (int i =0; i< objects.size() ; i++) { //Перебираем объекты.
                GameObject obj = objects.get(i);
                if (c.getSolidBox().intersects(obj.getSolidBox()) && obj.getIsSolid()) // произошло столкновение
                {

                    if(obj instanceof Bullet)
                    {
                        c.setX((int) (c.getX() + ( ( (Bullet)obj).getxVelocity() )*0.3 ) );
                        c.setY((int) (c.getY() + ( ((Bullet)obj).getyVelocity() )*0.3) );
                        Engine.getCurrentLevel().getObjects().remove(obj);
                        CombatUtility.attack(c, 10);
                        continue;
                    }


                        if (c.getY() + c.getHeight() > obj.getSolidBox().getCenterY() && c.getX() + c.getWidth() < obj.getX() + obj.getWidth() && c.getX() > obj.getX()) {
                        c.setY((int) (obj.getSolidBox().getY() + obj.getSolidBox().getHeight()));
                        System.out.println("вниз");
                    }//Вниз
                    /*else*/
                    if (c.getY() < obj.getSolidBox().getCenterY() && c.getX() + c.getWidth() < obj.getX() + obj.getWidth() && c.getX() > obj.getX()) {
                        c.setY((int) (obj.getSolidBox().getY() - c.getHeight()));
                        System.out.println("вверх");
                    }//Вверх
                    /*else*/
                    if (c.getX() + c.getWidth() > obj.getX() + obj.getWidth() && c.getY() + c.getHeight() > obj.getY() && c.getY() < obj.getY() + obj.getHeight()) {
                        c.setX((int) (obj.getSolidBox().getX() + obj.getWidth()));
                        System.out.println("право");
                    }//Вправо
                    /*else*/
                    if (c.getX() < obj.getX() && c.getY() + c.getHeight() > obj.getY() && c.getY() < obj.getY() + obj.getHeight()) {
                        c.setX((int) (obj.getSolidBox().getX() - c.getWidth()));
                        System.out.println("vlevo");
                    }//Влево

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
