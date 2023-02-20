package ru.muwa.shq.engine.g.camera;

/**
 * Класс, хранящий в себе информацию о положении игровой камеры, а так же методы по работе с этими значениями.
 */
public class Camera
{

    int x , y; // Координаты положения камеры в игровом пространстве.

    private static Camera instance;

    public final static int SCROLL_SPEED = 0;

    private Camera(){if (instance == null) instance = this;}
    public static Camera getInstance(){if (instance != null) return instance; else return new Camera();}
    // Сеттеры
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    // Геттеры
    public int getX(){return x;}
    public int getY(){return y;}

}
