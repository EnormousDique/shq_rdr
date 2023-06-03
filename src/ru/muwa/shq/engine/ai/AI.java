package ru.muwa.shq.engine.ai;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.creatures.npc.enemies.AimingGuy;
import ru.muwa.shq.creatures.npc.questnpc.Hachique;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.guns.EnemyBullet;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;

import java.awt.geom.Line2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс, реализующий логику принятия решений NPC
 */

//TODO: Внедрить в ИИ алгоритм поиска пути, и отдавать команды нпц на основании его работы.

public class AI {
    public enum Events // События внешнего мира, которые могут произойти с NPC
    {
        COLLISION, P_IN_SIGHT, P_OUT_SIGHT_, HURT
        //TODO: Проверить нужен ли в принипе этот enum или можно обойтись без него.
    }

    private static AI instance;

    /**
     * Работа ИИ
     **/
    public static void work() {

    }

    public static AI getInstance() {
        if (instance == null) return new AI();
        else return instance;
    }

    private AI() {
        instance = this;
    }

    public void move(NPC npc) { // Метод, который отвечал за передвижение НПЦ по игровому миру.

        npc.checkForPlayerInSight(); // Проверяем в поле зряния ли игрок (иможно ли до него добраться)

        if (!npc.isEnemy) {

        } else {
            //Если НПЦ считает нас врагом.
            if (npc.isPlayerInSight())  // Првоеряем видит ли он игрока
            {
                npc.setpX(Player.get().getX());
                npc.setpY(Player.get().getY());
                //И если да, обновляем "положение игрока в памяти нпц
            }
            //Теперь, когда мы знаем, что НПЦ враг и знает/видит где игрок.
            //Смотрим, дошел ли НПЦ-враг до игрока, и как он должен себя вести.
            if (!npc.getSolidBox().intersects(npc.getpX() + 1, npc.getpY() + 1, 1, 1) && ((npc.getpX() != 0) && (npc.getpY() != 0))) {
                //Поверили, что НПЦ не дошел до точки, где он видит / помнит игрока
                double r = Math.random();//Записываем рандом. Потребуется позднее

                //Смотрим, стреляющий ли это НПЦ. И находится ли игрок в его уязвимой  "близкой" зоне.
                if (npc instanceof AimingGuy && ((AimingGuy) npc).getNearZone().intersects(Player.get().getSolidBox())) {
                    //Если НПЦ стреляет и игрок близко
                    //Определяем в какую сторону НПЦ отступать (от игрока)
                    //И даем ему отсупить от игрока в на безопасную дистанцию
                    if (r < 0.5d) {
                        npc.setX(npc.getX() > Player.get().getX() ? npc.getX() + npc.getSpeed() : npc.getX() - npc.getSpeed());
                        r = Math.random();
                        if (r > 0.5) return;
                        npc.setY(npc.getY() > Player.get().getY() ? npc.getY() + npc.getSpeed() : npc.getY() - npc.getSpeed());

                    } else {
                        npc.setY(npc.getY() > Player.get().getY() ? npc.getY() + npc.getSpeed() : npc.getY() - npc.getSpeed());
                        r = Math.random();
                        if (r > 0.5) return;
                        npc.setX(npc.getX() > Player.get().getX() ? npc.getX() + npc.getSpeed() : npc.getX() - npc.getSpeed());
                    }
                    //Отступили

                } else
                    //Если наш НПЦ не стреляющий и/или игрок не находится в его уязвимой "близкой" зоне.


                    //Блок движения НПЦ в стандартном порядке
                    r = Math.random();
                //Смотрим, в какой стороне от НПЦ игрок, и отправляем его туда,
                // внося элемент случайности для "живости".
                if (r < 0.5d) {
                    npc.setX(npc.getX() > npc.getpX() ? npc.getX() - npc.getSpeed() : npc.getX() + npc.getSpeed());
                    r = Math.random();
                    if (r > 0.5) return;
                    npc.setY(npc.getY() > npc.getpY() ? npc.getY() - npc.getSpeed() : npc.getY() + npc.getSpeed());

                } else {
                    npc.setY(npc.getY() > npc.getpY() ? npc.getY() - npc.getSpeed() : npc.getY() + npc.getSpeed());
                    r = Math.random();
                    if (r > 0.5) return;
                    npc.setX(npc.getX() > npc.getpX() ? npc.getX() - npc.getSpeed() : npc.getX() + npc.getSpeed());
                }

                //ЛОГИКА ДЛЯ ЦЕЛЯЩИСЯ (СТРЕЛЯЮЩИХ НПЦ)
                aimAI(); //Метод обновляет угол между НПЦ и игроком и заставляет того стрелять.
                if (npc instanceof AimingGuy) {
                    ((AimingGuy) npc).updateNearZone();
                    //Также обновляем нашему НЦП уязвимую "бизкую" зону, если наш НПЦ - стрелок.
                }
                return; // Закончили. Код дальше  только для "незрячих" или "потерявшихся".
            }

        }



        /*
        if(npc != null && !(npc  instanceof Hachique)) // Я не помню зачем условие с хачиком, но боюсь проверять. И так работает.
        {

                npc.checkForPlayerInSight(); // Проверяем в поле зряния ли игрок (иможно ли до него добраться)


            if (npc.isPlayerInSight())  // Если нпц сейчас видит игрока
            {
                npc.setpX( Player.get().getX() );
                npc.setpY( Player.get().getY() );
                //Обновляем "положение игрока в памяти нпц)
            }

            //Логика на случай когда НПЦ видит или  видел игрока где-то,то еще не дошел до него или до последнего места где его видел
            if(!npc.getSolidBox().intersects(npc.getpX()+1,npc.getpY()+1,1,1) && ( (npc.getpX()!=0) && (npc.getpY()!=0)  )) {
                double r = Math.random();

                //Блок для "отступания" целящихся нпц
                if (npc instanceof AimingGuy && ((AimingGuy) npc).getNearZone().intersects(Player.get().getSolidBox())) {


                    if (r < 0.5d) {
                        npc.setX(npc.getX() > Player.get().getX() ? npc.getX() + npc.getSpeed() : npc.getX() - npc.getSpeed());
                        r = Math.random();
                        if (r > 0.5) return;
                        npc.setY(npc.getY() > Player.get().getY() ? npc.getY() + npc.getSpeed() : npc.getY() - npc.getSpeed());

                    } else {
                        npc.setY(npc.getY() > Player.get().getY() ? npc.getY() + npc.getSpeed() : npc.getY() - npc.getSpeed());
                        r = Math.random();
                        if (r > 0.5) return;
                        npc.setX(npc.getX() > Player.get().getX() ? npc.getX() + npc.getSpeed() : npc.getX() - npc.getSpeed());

                    }
                } else


                    //Блок движения НПЦ в стандартном порядке

                    r = Math.random();

                if (r < 0.5d) {
                    npc.setX(npc.getX() > npc.getpX() ? npc.getX() - npc.getSpeed() : npc.getX() + npc.getSpeed());
                    r = Math.random();
                    if (r > 0.5) return;
                    npc.setY(npc.getY() > npc.getpY() ? npc.getY() - npc.getSpeed() : npc.getY() + npc.getSpeed());

                } else {
                    npc.setY(npc.getY() > npc.getpY() ? npc.getY() - npc.getSpeed() : npc.getY() + npc.getSpeed());
                    r = Math.random();
                    if (r > 0.5) return;
                    npc.setX(npc.getX() > npc.getpX() ? npc.getX() - npc.getSpeed() : npc.getX() + npc.getSpeed());
                }


                //ЛОГИКА ДЛЯ ЦЕЛЯЩИСЯ (СТРЕЛЯЮЩИХ НПЦ)
                aimAI();
                if (npc instanceof AimingGuy) {
                    ((AimingGuy) npc).updateNearZone();
                }
                return; // Закончили. Код дальше  только для "незрячих" или "потерявшихся".
            }
            if(!npc.isPlayerInSight())
            {
                int random = (int) (Math.random() * 4);
                switch (random)
                {
                    case 0:
                        npc.moveUp();
                        break;
                    case 1:
                        npc.moveDown();
                        break;
                    case 2:
                        npc.moveRight();
                        break;
                    case 3:
                        npc.moveLeft();
                        break;
                }
            }


        }

         */

    }
    private static void aimAI()
    {
        LinkedList<NPC> list = Engine.getCurrentLevel().getNPC();
        for(int i = 0 ; i < list.size(); i++)
        {
            if(list.get(i) instanceof AimingGuy)
            {
                aimAIMath(list.get(i));
                if(list.get(i).isPlayerInSight())
                {

                    if(((AimingGuy) list.get(i)).getLastTimeShot() + 1_000 < System.currentTimeMillis()) {
                        ((AimingGuy) list.get(i)).setLastTimeShot(System.currentTimeMillis());
                        EnemyBullet.enemyShot((int) list.get(i).getSolidBox().getCenterX(), (int) list.get(i).getSolidBox().getCenterY(), -1 *(Aim.getInstance().calculateAngleFoNpc(list.get(i)) - 90), list.get(i));
                    }

                }
            }else {
                aimAIMath(list.get(i));
            }
        }

    }
    private static void aimAIMath(NPC c)
    {
        List<Line2D> list = c.getLines();
       for (int i = 0; i<  list.size(); i++){
           switch (i)
           {
               case 0:
                   list.get(i).setLine(c.getSolidBox().getCenterX(), c.getY() + c.getHeight(), c.getSolidBox().getCenterX(),c.getSolidBox().getCenterY());
                   break;
               case 1:
                   list.get(i).setLine(c.getSolidBox().getCenterX(),c.getSolidBox().getCenterY(), Player.get().getSolidBox().getCenterX(), Player.get().getSolidBox().getCenterY());
                   break;
               case 2:
                   list.get(i).setLine(Player.get().getSolidBox().getCenterX(), Player.get().getSolidBox().getCenterY(), c.getSolidBox().getCenterX(), c.getY() +c.getHeight());


           }
       }

    }
}