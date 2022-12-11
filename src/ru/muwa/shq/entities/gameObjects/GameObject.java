package ru.muwa.shq.entities.gameObjects;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 * Абстрактный класс, прародитель всех игровых объектов.
 */
public abstract class GameObject
{
    /**
     * Поля
     */
    private int x, y; //Координаты положения в пространстве
    private int height; // Высота
    private int width; // Широта
    protected int velocity; //Скорость
    protected int mass; //Масса объекта. Чем выше масса, тем объект быстрее падает и тяжелее двигается.
    private boolean isSolid; //Объект твердый. Через него нельзя пройти.
    private boolean isStatic; //Объект статичен. Он не двигается вообще. Не может двигаться.
    private boolean isNPC = true; // Является ли неиграбельным персонажем NPC //TODO: Решить баг с isNPC. Сейчас ставим false в конструкторе
    protected boolean isStanding; //Объект стоит на платформе или поверхности. Он устойчив и не падает.
    protected BufferedImage texture; //Текстура (изображение) объекта
    private Rectangle solidBox; //Квадрат "жесткости" //TODO: Вероятно, можно обойтись текстурой.
    protected Rectangle hitBox; //Хитбокс  //TODO: Вероятно, можно обойтись текстурой.
    protected Direction direction; //Направление (см. enum в этом классе)
    /**
     * Конструктор
     */
    protected GameObject(int x, int y, BufferedImage texture)
    {
        isNPC = false;
        this.texture = texture;
        this.x = x;
        this.y = y;
        solidBox = new Rectangle(x,y,width,height);
        hitBox = new Rectangle(x,y,width,height);
        height = texture.getHeight();
        width = texture.getWidth();
        direction = Direction.NONE;
        System.out.println(this +" "+ this.isNPC);
    }
    /**
     * Перечисление возможных направлений.
     */
    public enum Direction
    {
        NONE,RIGHT,UP,DOWN,LEFT,UP_LEFT,UP_RIGHT,DOWN_LEFT,DOWN_RIGHT
    }
    /**
     * Методы - сеттеры
     */
    public void setX( int x)
    {
        this.x=x;
    }
    public void setY( int y)
    {
        this.y=y;
    }
    public void setStanding(){isStanding = true;}
    public void setStanding(boolean bool){isStanding = bool;}
    public void setDirection(Direction direction){this.direction = direction;}
    /**
     * Методы - геттеры
     */
    public int getHeight()
    {
        return  height;
    }
    public int getWidth()
    {
        return  width;
    }
    public boolean solid()
    {
        return isSolid;
    }
    public int getY()
    {
        return y;
    }
    public int getX()
    {
        return x;
    }
    public BufferedImage getTexture()
    {
        return texture;
    }
    public Rectangle getSolidBox()
    {
        return solidBox;
    }

    public Rectangle getHitBox()
    {
        return hitBox;
    }
    public GameObject.Direction getDirection()
    {
        return direction;
    }
    public int getVelocity()
    {
        return velocity;
    }
    public boolean standing()
    {
        return isStanding;
    }
    public boolean isStatic(){return isStatic;}
    public int getMass(){return mass;}
    public boolean isNPC(){return isNPC;}
}
