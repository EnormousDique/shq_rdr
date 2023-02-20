package ru.muwa.shq.objects;

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
    public static final String IMG_PATH = "C:\\Users\\darkm\\IdeaProjects\\shq_rdr\\src\\ru\\muwa\\shq\\textures\\";
    //TODO: Вован сделай такое же поле для себя, а мое закомментируй при работе в своей ветке.
    //public static final String IMG_PATH = "C:\\sosok\\shq_rdr\\src\\ru\\muwa\\shq\\textures\\";
    protected int x, y; //Координаты положения в пространстве
    protected int height; // Высота
    protected int width; // Широта
    protected int velocity; //Скорость

    protected double fallAx;
    protected int mass; //Масса объекта. Чем выше масса, тем объект быстрее падает и тяжелее двигается.
    protected boolean isSolid; //Объект твердый. Через него нельзя пройти.
    protected boolean isStatic; //Объект статичен. Он не двигается вообще. Не может двигаться.
    protected boolean isStanding; //Объект стоит на платформе или поверхности. Он устойчив и не падает. //TODO:  Проверить, возожно получится обойтись isFalling
    protected boolean onGround; // Объект стоит на земле. Нижняя часть экрана, почва.
    protected boolean isFalling; // Обект падает (на него действует гравитация
    protected boolean isInUse;
    protected BufferedImage ImgLeft; // !!! володя делает тексутру в лево.

    protected BufferedImage texture; //Текстура (изображение) объекта
    protected BufferedImage UI; //Текстура (изображение) объекта

    protected Rectangle solidBox; //Квадрат "жесткости" //TODO: Вероятно, можно обойтись текстурой.
    protected Rectangle onFeetBox; // Платформа под ногами существа, определяющая, стоит ли существо на твердом.
    public static final int ON_FEET_BOX_HEIGHT = 1;
    protected Rectangle hitBox; //Хитбокс  //TODO: Вероятно, можно обойтись текстурой.
    protected Direction direction; //Направление (см. enum в этом классе)
    /**
     * Конструктор
     */
    protected GameObject(int x, int y, BufferedImage texture)
    {
        this.texture = texture;
        this.x = x;
        this.y = y;

        solidBox = new Rectangle(x,y,width,height);
        hitBox = new Rectangle(x,y,width,height);
        height = texture.getHeight();
        width = texture.getWidth();
        direction = Direction.NONE;
        this.onFeetBox = new Rectangle();

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
    public void setIsFalling(boolean bool){this.isFalling = bool;}
    public void setFallAx(double fallAx)
    {
        this.fallAx = fallAx;
    }
    public void setIsInUse(boolean isInUse)
    {
        this.isInUse = isInUse;
    }
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

    public double getFallAx(){return fallAx;}

    public BufferedImage getImgLeft()
    {
        return ImgLeft;
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
    public boolean isFalling() {return isFalling;}
    public Rectangle getOnFeetBox()
    {
        return onFeetBox;
    }
    public BufferedImage getUI(){return UI;}
    public boolean isInUse() {return isInUse;}



}
