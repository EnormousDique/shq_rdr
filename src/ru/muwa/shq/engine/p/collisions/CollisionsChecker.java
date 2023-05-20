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
import java.util.LinkedList;
import java.util.List;

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
            if(!o.equals(b) &&  o.getIsSolid() && o.getSolidBox().intersects(b.getSolidBox()))

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


        List<GameObject> list = objects.stream().filter(ob -> !ob.equals(o)).filter(ob -> ob.getIsSolid()).toList();
        if (o != null) {

            Point2D
                    lt = new Point(o.getX(),o.getY()),
                    rt = new Point(o.getX()+o.getWidth(),o.getY()),
                    lb = new Point(o.getX(),o.getY()+o.getHeight()),
                    rb = new Point(o.getX()+o.getWidth(),o.getY()+o.getHeight());

            Line2D
                    topLine = new Line2D.Double(lt,rt),
                    bottomLine = new Line2D.Double(lb,rb),
                    leftLine = new Line2D.Double(lt,lb),
                    rightLine = new Line2D.Double(rt,rb);


            for (GameObject obj : list) {



                //Перебираем объекты.
                //todo если пересекает больше двух  обьектов написать другую логику??
//
//
//
//
//
//
//                if ( o.getSolidBox().intersects(obj.getSolidBox())) // произошло столкновение
  //              {
//
  //                  if (o.getSolidBox().getCenterY() > obj.getSolidBox().getCenterY() && o.getX() + o.getWidth() < obj.getX() + obj.getWidth() && o.getX() > obj.getX()) {
    //                    o.setY((int)obj.getSolidBox().getY() + (int)obj.getSolidBox().getHeight());
      //                  System.out.println("вниз");
                     //   continue;

        //            }//Вниз
                    /*else*/

                    //я тупой дибил пидарас и сука крышу у людей последнее

          //          if (o.getY() < obj.getSolidBox().getCenterY() && o.getX() + o.getWidth() < obj.getX() + obj.getWidth() && o.getX() > obj.getX()) {
            //            o.setY((int)obj.getSolidBox().getY() - o.getHeight());
              //          System.out.println("вверх");
                     //   continue;

      //              }//Вверх
                    /*else*/
     //               if (o.getX() + o.getWidth() > obj.getX() + obj.getWidth() && o.getY() + o.getHeight() > obj.getY() && o.getY() < obj.getY() + obj.getHeight()) {
    //                    o.setX((int)obj.getSolidBox().getX() + (int)obj.getSolidBox().getWidth());
    //                    System.out.println("право");
                       // continue;

    //                }//Вправо
                    /*else*/
    //                if (o.getX() < obj.getX() && o.getY() + o.getHeight() > obj.getY() && o.getY() < obj.getY() + obj.getHeight()) {
     //                   o.setX((int)obj.getSolidBox().getX() - o.getWidth());
    //                    System.out.println("vlevo");
                      //  continue;

  //                  }//Влево



                //Новый код проверки столкновений

                //Проверка,что проверяемый объект зашел в стену "верхом"
                if(obj.getSolidBox().contains(lt) &&
                   obj.getSolidBox().contains(rt) &&
                  !obj.getSolidBox().contains(lb) &&
                  !obj.getSolidBox().contains(rb) )
                {
                    o.setY((int) (obj.getSolidBox().getY()+obj.getHeight()));
                }
                //Проверка,что проверяемый объект зашел в стену "низом"
                if(obj.getSolidBox().contains(lb) &&
                        obj.getSolidBox().contains(rb) &&
                        !obj.getSolidBox().contains(lt) &&
                        !obj.getSolidBox().contains(rt) )
                {
                    o.setY((int) (obj.getSolidBox().getY()-o.getHeight()));
                }
                //Проверка,что проверяемый объект зашел в стену "левом"
                if(obj.getSolidBox().contains(lb) &&
                        obj.getSolidBox().contains(lt) &&
                        !obj.getSolidBox().contains(rt) &&
                        !obj.getSolidBox().contains(rb) )
                {
                    o.setX((int) (obj.getSolidBox().getX()+obj.getWidth()));
                }
                //Проверка,что проверяемый объект зашел в стену "правом"
                if(obj.getSolidBox().contains(rb) &&
                        obj.getSolidBox().contains(rt) &&
                        !obj.getSolidBox().contains(lt) &&
                        !obj.getSolidBox().contains(lb) )
                {
                    o.setX((int) (obj.getSolidBox().getX()-o.getWidth()));
                }


                    //Код для уничтожения пуль после столкновения
                   // if (obj instanceof Bullet) Engine.getCurrentLevel().getObjects().remove(obj);


                    if (obj instanceof Bullet && o.getSolidBox().intersects(obj.getSolidBox())) {
                        if(o.equals(Player.get())) CombatUtility.attack(Player.get(), 5); // ТЕСТ. игрок получает 5,  не 10 урона от пуль
                        Engine.getCurrentLevel().getObjects().remove(obj);

                    }
                    if(o instanceof Bullet & o.getSolidBox().intersects(obj.getSolidBox()))
                        Engine.getCurrentLevel().getObjects().remove(o);
                if(obj instanceof Bullet & obj.getSolidBox().intersects(o.getSolidBox()))
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
                        c.setY((int) (obj.getSolidBox().getY() + obj.getHeight()));
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
