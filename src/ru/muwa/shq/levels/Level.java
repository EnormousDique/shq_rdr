package ru.muwa.shq.levels;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.items.ItemPhysicalAppearance;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.objects.GameObject;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.zones.GameZone;
import ru.muwa.shq.zones.InDoorsSpawnZone;

import java.awt.*;
import java.util.LinkedList;
/**
 * Класс, являющийся прародителем всех игровых уровней.
 */
public abstract class Level
{
    /**
     * Поля
     */
    protected int InDoorsX;
    protected int InDoorsY;
    protected int startPosX, startPosY;
    protected boolean isInDoors;   // проверка для спавнера что локация внутри помещения
    public boolean isInDoors(){
        return isInDoors;
    }
    protected boolean isStreet; // проверка для спавнера что локация снаружи
    public boolean isStreet() {
        return isStreet;
    }
    protected Level() {
        objects = new LinkedList<>();
        npc = new LinkedList<>();
        containers = new LinkedList<>();
        icons = new LinkedList<>();
        zones = new LinkedList<>();
    }
    protected LinkedList<GameObject> objects;
    protected LinkedList<NPC> npc;
    protected LinkedList<Container> containers;
    protected LinkedList<ItemPhysicalAppearance> icons;
    protected LinkedList<GameZone> zones;
    public LinkedList<GameObject> getObjects() {
        return objects;
    }
    public LinkedList<NPC> getNPC() {
        return npc;
    }
    public LinkedList<Container> getContainers() {
        return containers;
    }
    public LinkedList<ItemPhysicalAppearance> getIcons() {
        return icons;
    }
    public void addIcon(ItemPhysicalAppearance icon) {
        icons.add(icon);
    }
    public void removeIcon(ItemPhysicalAppearance icon) {
        icons.remove(icon);
    }
    public LinkedList<GameZone> getZones() {
        return zones;
    }
    public int getStartPosX() {
        return startPosX;
    }
    public int getStartPosY() {
        return startPosY;
    }
    public int getInDoorsX(){
        return InDoorsX;
    }
    public int getInDoorsY(){
        return InDoorsY;
    }

}
