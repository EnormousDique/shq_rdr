package ru.muwa.shq.levels;

import ru.muwa.shq.entities.gameObjects.GameObject;
import ru.muwa.shq.entities.gameObjects.creatures.npc.NPC;

import java.util.LinkedList;

/**
 * Класс, являющийся прародителем всех игровых уровней.
 */
public abstract class Level
{
    /**
     * Поля
     */
    protected int startPosX, startPosY;
    protected Level()
    {
        objects = new LinkedList<>();
        npc = new LinkedList<>();
    }
    protected LinkedList<GameObject> objects;
    protected LinkedList<NPC> npc;

    public LinkedList<GameObject> getObjects()
    {
        return objects;
    }
    public LinkedList<NPC> getNPC()
    {
        return npc;
    }



    public int getStartPosX() {
        return startPosX;
    }

    public int getStartPosY() {
        return startPosY;
    }
}
