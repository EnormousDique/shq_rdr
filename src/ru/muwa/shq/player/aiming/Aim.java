package ru.muwa.shq.player.aiming;

import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.player.Player;

import javax.sound.sampled.Line;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Aim {
    private static Aim instance;
    private Aim()
    {
        instance = this;
    }
    public static Aim getInstance()
    {
        if(instance == null) return new Aim(); else return instance;
    }

    private double angle;

    private Line2D line1 = new Line2D.Double(), line2 = new Line2D.Double(), line3 = new Line2D.Double();

    public int x1,y1,x2,y2;

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }




    public void aim()
    {
        line1.setLine(Player.get().getSolidBox().getCenterX(), Player.get().getY() + Player.get().getHeight(), Player.get().getSolidBox().getCenterX(),Player.get().getSolidBox().getCenterY());
        line2.setLine(Player.get().getSolidBox().getCenterX(),Player.get().getSolidBox().getCenterY(), MouseListener.getInstance().getX()+Camera.getInstance().getX(), MouseListener.getInstance().getY()+Camera.getInstance().getY());
        line3.setLine(MouseListener.getInstance().getX()+Camera.getInstance().getX(), MouseListener.getInstance().getY()+Camera.getInstance().getY(), Player.get().getSolidBox().getCenterX(), Player.get().getY() + Player.get().getHeight());
    }
    private int getLineSize(Line2D l)
    {
        // По пифагору считаем длину линии
        return (int) Math.sqrt(((l.getX2()-l.getX1())*(l.getX2()-l.getX1()))+((l.getY2()-l.getY1())*(l.getY2()-l.getY1())));
    }
    public ArrayList<Line2D> getLines(){

        ArrayList<Line2D> list = new ArrayList<>();
        list.add(line1);list.add(line2);list.add(line3);
        return list;

    }

    public double calculateAngle()
    {
        boolean isMouseLeft = MouseListener.getInstance().getX()+Camera.getInstance().getX() < Player.get().getSolidBox().getCenterX();
        //System.out.println(Math.toDegrees(Math.acos( ((4*4) + (3*3) - (5*5)) / (2 * 4 * 3) )) );
        //System.out.println(Math.toDegrees(Math.acos( ((Math.pow(getLineSize(line2),2)) + (Math.pow(getLineSize(line1),2)) - (Math.pow(getLineSize(line3),2))) / (2 * getLineSize(line2) * getLineSize(line1)) )) );

        return isMouseLeft?

                360 - Math.toDegrees(Math.acos( ((Math.pow(getLineSize(line2),2)) + (Math.pow(getLineSize(line1),2)) - (Math.pow(getLineSize(line3),2))) / (2 * getLineSize(line2) * getLineSize(line1)) ))
                :
                Math.toDegrees(Math.acos( ((Math.pow(getLineSize(line2),2)) + (Math.pow(getLineSize(line1),2)) - (Math.pow(getLineSize(line3),2))) / (2 * getLineSize(line2) * getLineSize(line1)) ));
    }

}
