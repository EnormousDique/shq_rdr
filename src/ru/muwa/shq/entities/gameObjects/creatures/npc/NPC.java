package ru.muwa.shq.entities.gameObjects.creatures.npc;
import ru.muwa.shq.entities.gameObjects.creatures.Creature;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Класс, являющийся предком всех NPC в игре.
 */
public abstract class NPC extends Creature
{
    public boolean isNPC = true;
    /**
     * Конструктор
     *
     * @param x       - х коррдината
     * @param y       - у коррдината
     * @param texture - текстура (файл с изборажением)
     */
    protected NPC(int x, int y, BufferedImage texture)
    {
        super(x, y, texture);
    }


}
