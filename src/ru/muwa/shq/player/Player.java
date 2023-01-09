package ru.muwa.shq.player;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.entities.gameObjects.creatures.Creature;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
/**
 * Класс игрока.
 */
public class Player extends Creature
{
    private static Player instance;
    public static Player get()
    {
        if (instance == null)
        {
        BufferedImage img;
        try {
            img = ImageIO.read(new File("D:\\_projects\\Shq 2D\\src\\ru\\muwa\\shq\\textures\\player\\p_r.png"));
        } catch (Exception e) {
            System.out.println("Failed to load player textures");
            return null;
        }
        return instance = new Player(Engine.getCurrentLevel().getStartPosX(), Engine.getCurrentLevel().getStartPosY(), img);
        }
        else return instance;
    }
    /**
     * Конструктор
     *
     * @param x       - х коррдината
     * @param y       - у коррдината
     * @param texture - текстура (файл с изборажением)
     */
    protected Player(int x, int y, BufferedImage texture)
    {
        super(x, y, texture);
        isStanding = false;
        velocity = 2;
        agility = 20;
        speed = 5;
        maxJumpAx = 50;
    }
}