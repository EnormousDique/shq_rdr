package ru.muwa.shq.engine.utilities1;

import ru.muwa.shq.engine.raycaster.RayCaster;
import ru.muwa.shq.creatures.Creature;

/**
 * Класс, отвечающий за обновление всех RayCaster'ов
 */
public class RayCasterUpdater
{
    public static void updateRayCaster(RayCaster rayCaster, Creature creature)
    {
        rayCaster.setX(creature.getX());
        rayCaster.setY(creature.getY());
    }
}
