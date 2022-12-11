package ru.muwa.shq.engine.raycaster;
import ru.muwa.shq.entities.gameObjects.GameObject;
import java.awt.geom.Line2D;
import java.util.LinkedList;

/**
 * thnx vry much to brandon d's ray cast tutor
 * Класс, отвечающий за простоение лучей.
 */
public class RayCaster
{
    public static final int RAYS_AMOUNT = 13;

    private LinkedList<Line2D.Float> borders; // Список линий, через который лучи не могут пройти.
    int x,y; // Координаты центра, из которого строятся лучи
    int resolution = RayCaster.RAYS_AMOUNT; // Кол-во лучей
    int maxDist; // Максимальная длина луча

    public RayCaster(int x, int y, int maxDist)
    {
        this.x = x;
        this.y = y;
        this.maxDist = maxDist;
    }
    public void setBorders(LinkedList<Line2D.Float> borders){this.borders = borders;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}

    public LinkedList<Line2D.Float> calcRays()
    {
        LinkedList<Line2D.Float> rays = new LinkedList<>();
        for (int i = 0; i < resolution; i++)
        {
            double dir = ((Math.PI * 2)*((double) i/resolution));
            float minDist = maxDist;
            for(Line2D.Float l : borders)
            {
                float dist = getRayCast(x,y,x+(float)Math.cos(dir) * maxDist, y+(float)Math.sin(dir) * maxDist, + l.x1, l.y1, l.x2, l.y2);
                if (dist < minDist && dist > 0)
                {
                    minDist = dist;
                }
            }
            rays.add(new Line2D.Float(x,y,x+(float)Math.cos(dir) * minDist, y+(float)Math.sin(dir) * minDist));
        }
        return rays;
    }
    public static float dist(float x1, float y1, float x2, float y2)
    {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
    public static float getRayCast(float p0_x, float p0_y, float p1_x, float p1_y, float p2_x, float p2_y, float p3_x, float p3_y)
    {
        float s1_x, s1_y, s2_x, s2_y;
        s1_x = p1_x - p0_x;
        s1_y = p1_y - p0_y;
        s2_x = p3_x - p2_x;
        s2_y = p3_y - p2_y;
        float s, t;
        s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / (-s2_x * s1_y + s1_x * s2_y);
        t = (s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / (-s2_x * s1_y + s1_x * s2_y);
        if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
            // Collision detected
            float x = p0_x + (t * s1_x);
            float y = p0_y + (t * s1_y);
            return dist(p0_x, p0_y, x, y);
        }
        return -1; // No collision
    }
    public LinkedList<Line2D.Float> buildLines(LinkedList<GameObject> objects)
    {
        LinkedList<Line2D.Float> lines = new LinkedList<>();
        for(GameObject o : objects)
        {
            /*
                Цикл для формирования граней RayCaster'a на основании текстур игровых объектов.

         XY(0,0)***************************************X+
                *********А***********************B*****
                *********UUUUUUUUUUUUUUUUUUUUUUUUU*****
                *********U***********************U*****
                *********U***********************U*****
                *********UUUUUUUUUUUUUUUUUUUUUUUUU*****
                *********D***********************C*****
                ***************************************XY(1,1)
                Y+
             */
            lines.add(new Line2D.Float(o.getX(),o.getY(), o.getX()+o.getWidth(),o.getY()));//AB
            lines.add(new Line2D.Float(o.getX()+o.getWidth(),o.getY(), o.getX()+o.getWidth(),o.getY()+o.getHeight()));//BC
            lines.add(new Line2D.Float(o.getX()+o.getWidth(),o.getY()+o.getHeight(), o.getX(),o.getY()+o.getHeight()));//CD
            lines.add(new Line2D.Float(o.getX(),o.getY()+o.getHeight(),o.getX(),o.getY()));//DA
        }
        return lines;
    }

}
