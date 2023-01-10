package ru.muwa.shq.levels;

import ru.muwa.shq.items.ItemPhysicalAppearance;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.npc.NPC;

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
        containers = new LinkedList<>();
        icons = new LinkedList<>();
    }
    protected LinkedList<GameObject> objects;
    protected LinkedList<NPC> npc;

    protected LinkedList<Container> containers;
    protected LinkedList<ItemPhysicalAppearance> icons;

    public LinkedList<GameObject> getObjects()
    {
        return objects;
    }
    public LinkedList<NPC> getNPC()
    {
        return npc;
    }
    public LinkedList<Container> getContainers()
    {
        return containers;
    }
    public LinkedList<ItemPhysicalAppearance> getIcons()
    {
        return icons;
    }
    public void addIcon(ItemPhysicalAppearance icon)
    {
        icons.add(icon);
    }
    public void removeIcon(ItemPhysicalAppearance icon)
    {
        icons.remove(icon);
    }






    public int getStartPosX() {
        return startPosX;
    }

    public int getStartPosY() {
        return startPosY;
    }
}
