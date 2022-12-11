package ru.muwa.shq.engine.ai;
import ru.muwa.shq.entities.gameObjects.creatures.npc.NPC;
import java.util.Random;
import static ru.muwa.shq.entities.gameObjects.GameObject.Direction.LEFT;
import static ru.muwa.shq.entities.gameObjects.GameObject.Direction.RIGHT;
/**
 * Класс, реализующий логику принятия решений NPC
 */
public class AI
{
    private final double DIRECTION_CHANGE_RATE = 0.015;
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
    public void move(NPC npc)
    {
        switch (npc.getDirection())
        {
            case RIGHT:
                npc.setX(npc.getX()+npc.getVelocity());
                break;
            case LEFT:
                npc.setX(npc.getX()-npc.getVelocity());
                break;

        }
        if((new Random().nextDouble()) < DIRECTION_CHANGE_RATE) changeDirection(npc);
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