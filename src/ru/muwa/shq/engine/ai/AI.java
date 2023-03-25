package ru.muwa.shq.engine.ai;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Player;

import java.util.Random;
import static ru.muwa.shq.objects.GameObject.Direction.LEFT;
import static ru.muwa.shq.objects.GameObject.Direction.RIGHT;
/**
 * Класс, реализующий логику принятия решений NPC
 */

//TODO: Внедрить в ИИ алгоритм поиска пути, и отдавать команды нпц на основании его работы.

public class AI
{
    public enum Events // События внешнего мира, которые могут произойти с NPC
    {
        COLLISION, P_IN_SIGHT, P_OUT_SIGHT_, HURT
        //TODO: Проверить нужен ли в принипе этот enum или можно обойтись без него.
    }
    private static AI instance;
    public static AI getInstance()
    {
        if(instance == null) return new AI();
        else return instance;
    }
    private AI(){instance = this;}
    public void move(NPC npc) { // Метод, который отвечал за передвижение НПЦ по игровому миру.
        // Если игрока двигает ввод с клавиатуры, то НПЦ двиает этот класс (ИИ).
        if(npc != null)
        {
            npc.checkForPlayerInSight(); // Дергаем RayCater, чтобы тот обновил значение видимости игрока npc

            if (npc.isPlayerInSight())  // Если нпц сейчас видит игрока
            {
                                         // описано поведение НПЦ, в случае если тот видит игрока

                double r =Math.random();

                if(r<0.5d) {
                    npc.setX(npc.getX() > Player.get().getX() ? npc.getX() - npc.getSpeed() : npc.getX() + npc.getSpeed());
                    r =Math.random();
                    if(r>0.5) return;
                    npc.setY(npc.getY() > Player.get().getY() ? npc.getY() - npc.getSpeed() : npc.getY() + npc.getSpeed());

                }else {
                    npc.setY(npc.getY() > Player.get().getY() ? npc.getY() - npc.getSpeed() : npc.getY() + npc.getSpeed());
                    r =Math.random();
                    if(r>0.5) return;
                    npc.setX(npc.getX() > Player.get().getX() ? npc.getX() - npc.getSpeed() : npc.getX() + npc.getSpeed());

                }
            }
            // Раньше тут была некоторая логика, но теперь ее нет.
        }
    }
}