package ru.muwa.shq.engine.updaters;

import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.creatures.Creature;

/**
 * Класс, отвечающий за обновление всех RayCaster'ов
 */
public class RayCasterUpdater
{
    private static RayCasterUpdater instance;
    private RayCasterUpdater(){instance = this;}
    public static RayCasterUpdater getInstance(){if (instance == null) return new RayCasterUpdater(); else return instance;}
    public void updateRayCaster(RayCaster rayCaster, Creature creature)
    {
        rayCaster.setX(creature.getX());
        rayCaster.setY(creature.getY());
    }
}
