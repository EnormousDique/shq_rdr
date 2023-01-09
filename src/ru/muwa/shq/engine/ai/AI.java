package ru.muwa.shq.engine.ai;
import ru.muwa.shq.entities.gameObjects.creatures.npc.NPC;
import ru.muwa.shq.entities.gameObjects.creatures.player.Player;

import javax.swing.*;
import java.util.Random;
import static ru.muwa.shq.entities.gameObjects.GameObject.Direction.LEFT;
import static ru.muwa.shq.entities.gameObjects.GameObject.Direction.RIGHT;
/**
 * Класс, реализующий логику принятия решений NPC
 */
public class AI
{
    private final double DIRECTION_CHANGE_RATE = 0.013;
    public enum Events // События внешнего мира, которые могут произойти с NPC
    {
        COLLISION, P_IN_SIGHT, P_OUT_SIGHT_, HURT
    }
    private static AI instance;
    public static AI getInstance()
    {
        if(instance == null) return new AI();
        else return instance;
    }
    private AI(){instance = this;}
    public void move(NPC npc) {
        if(npc != null)
        {

            npc.checkForPlayerInSight(); // Дергаем RayCater, чтобы тот обновил значение видимости игрока npc

            if (npc.isPlayerInSight())  // Если нпц сейчас видит игрока
            {
                boolean isPlayerOnTheLeft = npc.getX() - Player.get().getX() > 0 ? true : false;
                if(isPlayerOnTheLeft) npc.setDirection(LEFT);
                else npc.setDirection(RIGHT);
            }
            switch (npc.getDirection())
            {
                case RIGHT:
                    npc.moveRight();
                    break;
                case LEFT:
                    npc.moveLeft();
                    break;
            }

            if ((new Random().nextDouble()) < DIRECTION_CHANGE_RATE) changeDirection(npc);
        }
    }
    public void changeDirection(NPC npc)
    {
        if (npc.getDirection() == LEFT) npc.setDirection(RIGHT);
        else if(npc.getDirection() == RIGHT) npc.setDirection(LEFT);
        else npc.setDirection(new Random().nextDouble() > 0.5? LEFT :RIGHT);
    }
    public void changeDirectionOnEvent(NPC npc, Events e)
    {
        if(e.equals(Events.COLLISION))
        {
            switch (npc.getDirection())
            {
                case RIGHT ->{if(new Random().nextDouble() > 0.5)npc.setDirection(LEFT);}
                case LEFT ->{if(new Random().nextDouble() < 0.5)npc.setDirection(LEFT);}
            }
        }
    }
}