package ru.muwa.shq.creatures.npc;
import ru.muwa.shq.creatures.Creature;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.GameZone;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Класс, являющийся предком всех NPC в игре.
 */
public abstract class NPC extends Creature
{

    protected boolean playerInSight;

    public boolean isEnemy;
    public boolean isScared;
    public GameZone goToZone;

    protected int pX, pY;

    public void setpX(int pX) {
        this.pX = pX;
    }

    public void setpY(int pY) {
        this.pY = pY;
    }

    public Rectangle getViewField() {
        return viewField;
    }

    public void setViewField(Rectangle viewField) {
        this.viewField = viewField;
    }

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
        viewField = new Rectangle();
    }
        protected Line2D line1 = new Line2D.Double();
        protected Line2D line2 = new Line2D.Double();
        protected Line2D line3 = new Line2D.Double();
        protected java.util.List<Line2D> lines = List.of(line1,line2,line3);


        public Line2D l = new Line2D.Double();
    public Line2D getLine1() {
        return line1;
    }

    public void setLine1(Line2D line1) {
        this.line1 = line1;
    }

    public Line2D getLine2() {
        return line2;
    }

    public void setLine2(Line2D line2) {
        this.line2 = line2;
    }

    public Line2D getLine3() {
        return line3;
    }

    public void setLine3(Line2D line3) {
        this.line3 = line3;
    }

    public List<Line2D> getLines() {
        return lines;
    }

    public void setLines(List<Line2D> lines) {
        this.lines = lines;
    }

    protected Rectangle  viewField;
    public boolean isPlayerInSight()
    {
        return playerInSight;
    } // Видит ли НПЦ игрока (значение)
    public void setPlayerInSight(boolean bool)
    {
        this.playerInSight = bool;
    } // Сеттер этого значения

    public int getpX() {
        return pX;
    }

    public int getpY() {
        return pY;
    }

    public void checkForPlayerInSight() // Метод проверки - видит ли НПЦ игрока
    {
        boolean playerInAFieldOfView = false;
        boolean playerInStraightLine = true;

        l = new Line2D.Double(solidBox.getCenterX(),solidBox.getCenterY(), Player.get().getSolidBox().getCenterX(),Player.get().getSolidBox().getCenterY());

        for(int i = 0; i < Engine.getCurrentLevel().getObjects().size(); i++)
            if(l.intersects(Engine.getCurrentLevel().getObjects().get(i).getSolidBox()) && Engine.getCurrentLevel().getObjects().get(i).getIsSolid() )
                playerInStraightLine = false;

        for(int i = 0; i < Engine.getCurrentLevel().getNPC().size(); i++)
            if(l.intersects(Engine.getCurrentLevel().getNPC().get(i).getSolidBox()) && !Engine.getCurrentLevel().getNPC().get(i).equals(this) )
                playerInStraightLine = false;

        playerInAFieldOfView = viewField.intersects(Player.get().getSolidBox());

        playerInSight = playerInAFieldOfView && playerInStraightLine;

    }




}
