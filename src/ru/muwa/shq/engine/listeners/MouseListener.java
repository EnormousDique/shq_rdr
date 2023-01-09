package ru.muwa.shq.engine.listeners;
import java.awt.event.MouseEvent;
/**
 * Класс, отвечающий за прослушку движений курсора мыши.
 */
public class MouseListener implements java.awt.event.MouseMotionListener
{
    private static MouseListener instance;
    /**
     * Констуктор
     */
    private MouseListener(){
        instance = this;
        System.out.println("mouse listener initiated");
    }
    public static MouseListener getInstance()
    {
        if(instance == null) return new MouseListener();
        else return instance;
    }
    /**
     * Поля - координаты курсора мыши.
     */
    private int x,y;

    /**
     * Методы - геттеры.
     * @return - значения полей.
     */
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }



    /**
     * Метод, срабатывающий при событии перетаскивания мышью.
     * @param e - само событие.
     */
    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

    /**
     * Метод, срабатывающий при движении мышью.
     * @param e - само событие
     */
    @Override
    public void mouseMoved(MouseEvent e)
    {
        x = e.getX();
        //System.out.println("mouse x: " + x);
        y = e.getY();
        //System.out.println("mouse y: " + y);
    }
}
