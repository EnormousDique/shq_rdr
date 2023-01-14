package ru.muwa.shq.engine.g.camera;

public class Camera
{

    private static Camera instance;

    public final static int SCROLL_SPEED = 0;

    private Camera(){if (instance == null) instance = this;}
    public static Camera getInstance(){if (instance != null) return instance; else return new Camera();}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    int x , y;

    public void moveRight(){ x = x + SCROLL_SPEED;}
    public void moveLeft(){  x = x - SCROLL_SPEED;}
    public void moveUp(){    y = y + SCROLL_SPEED;}
    public void moveDown(){  y = y - SCROLL_SPEED;}

    public void update()
    {
        CameraUpdater.getInstance().scroll();
    }


    public int getX(){return x;}
    public int getY(){return y;}

}
