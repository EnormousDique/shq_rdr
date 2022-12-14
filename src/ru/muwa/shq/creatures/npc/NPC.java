package ru.muwa.shq.creatures.npc;
import ru.muwa.shq.creatures.Creature;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Класс, являющийся предком всех NPC в игре.
 */
public abstract class NPC extends Creature
{
    private boolean playerInSight;

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
        leftWallZone=new Rectangle ();
        rightWallZone= new Rectangle ();
    }
    public boolean isPlayerInSight()
    {
        return playerInSight;
    }
    public void setPlayerInSight(boolean bool)
    {
        this.playerInSight = bool;
    }
    public void checkForPlayerInSight()
    {
        playerInSight = rayCaster.isPlayerInSight();
    }

    protected Rectangle leftWallZone;

    protected Rectangle rightWallZone;

    public Rectangle getLeftWallZone(){
        return leftWallZone ;
    }
    public Rectangle getRightWallZone(){
        return  rightWallZone ;
    }

}
