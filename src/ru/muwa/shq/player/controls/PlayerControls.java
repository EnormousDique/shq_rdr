package ru.muwa.shq.player.controls;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.engine.utilities.InventoryManager;
import ru.muwa.shq.levels.dev.DevLevel0;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

import java.awt.*;
import java.util.Objects;

/**
 * Класс, отвечающий за управление игроком.
 */
public class PlayerControls
{
    private static PlayerControls instance;
    private PlayerControls(){instance = this;}
    public static PlayerControls getInstance(){if(instance == null) return new PlayerControls(); else return instance;}
    private  KeyListener keyboard = KeyListener.getInstance();
    private  Player player = Player.get();

    public  void controlPlayer()
    {
        // Клавиатура
        if(keyboard.getKeys()[keyboard.W]) w(); // Нажата W
        if(keyboard.getKeys()[keyboard.A]) a();// Нажата A
        if(keyboard.getKeys()[keyboard.S]) Player.get().moveDown();// Нажата S
        if(keyboard.getKeys()[keyboard.D]) Player.get().moveRight();// Нажата D
        if(keyboard.getKeys()[keyboard.SPACE]) space();// Нажата SPACE BAR
        if(keyboard.getKeys()[keyboard.E]) e(); // Нажата Е
        if(keyboard.getKeys()[keyboard.I]) i();/*Inventory.getInstance().setIsOpened(!Inventory.getInstance().isOpened()); keyboard.getKeys()[6] = false; */// Нажата I
        if(keyboard.getKeys()[keyboard.Q]) q();

        //Мышь
        if(MouseButtonListener.getInstance().keys[0]) lmb();
        if(MouseButtonListener.getInstance().keys[0]) rmb();

    }


    private void w() {
        Player.get().moveUp();
    }
    private void a()
    {
        Player.get().moveLeft();
    }
    private void s()
    {

    }
    private void d()
    {

    }
    private void e()
    {
        Interactor.getInstance().interact();
    }
    private void i()
    {

    }
    private void space()
    {

    }
    private void enter()
    {

    }

    private void q()
    {
        if(Player.get().isBusy() && Player.get().getCurrentObject() != null)
        {
            Player.get().getCurrentObject().setInUse(false);
            Player.get().setCurrentObject(null);
            Player.get().setIsBusy(false);
        }
        Inventory.getInstance().setIsOpened(false);
        for(Container c: Engine.getCurrentLevel().getContainers())c.setIsInUse(false);
    }
    private void lmb()
    {
        if(Inventory.getInstance().getBox().contains
                (new Point(MouseListener.getInstance().getX() + Camera.getInstance().getX(), MouseListener.getInstance().getY() + Camera.getInstance().getY())))
            InventoryManager.getInstance().eat();
        else InventoryManager.getInstance().grab();
        MouseButtonListener.getInstance().keys[0]=false;
    }
    private void rmb()
    {

    }
}
