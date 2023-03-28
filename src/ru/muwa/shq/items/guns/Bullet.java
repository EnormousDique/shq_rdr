package ru.muwa.shq.items.guns;

import ru.muwa.shq.objects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bullet extends GameObject
{

    public static final int BULLET_SPEED = 180;

    private int initialX;
    private int initialY;
    private double angle;
    private int velocity;
    private double xVelocity;
    private double yVelocity;
    private double time;


    private static BufferedImage img;
    static
    {
        try
        {
            img= ImageIO.read(new File(IMG_PATH+"combat\\bullet.png"));
            System.out.println("bullet texture loaded ok");
        }
        catch(Exception e)
        {
            System.out.println("bullet texture loaded NOT ok");
        }
    }

    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    public void setInitialY(int initialY) {
        this.initialY = initialY;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public static void setImg(BufferedImage img) {
        Bullet.img = img;
    }

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public int getVelocity() {
        return velocity;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public double getTime() {
        return time;
    }

    public Bullet(int x, int y, double angle)
    {
        super(x, y, img);
        initialX = x;
        initialY = y;
        this.angle = angle;
        velocity = BULLET_SPEED;
        xVelocity = velocity * Math.cos(Math.toRadians(angle));
        yVelocity = velocity * Math.sin(Math.toRadians(angle));
        time = 0;
        this.x =this.x+ (int) xVelocity*10;
        this.y=this.y+ (int) yVelocity*10;
        System.out.println("created bullet. y vel : "  + yVelocity + "  x vel : " + xVelocity + "  angle : " + angle);
    }
}
