package ru.muwa.shq.player.controls;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.levels.dev.DevLevel0;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;

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
        if(keyboard.getKeys()[0]) Player.get().moveUp(); ;// Нажата W
        if(keyboard.getKeys()[1]) Player.get().moveLeft();// Нажата A
        if(keyboard.getKeys()[2]) Player.get().moveDown();// Нажата S
        if(keyboard.getKeys()[3]) Player.get().moveRight();// Нажата D
        if(keyboard.getKeys()[4]) jump() ;// Нажата SPACE BAR
        if(keyboard.getKeys()[5]) interact(); // Нажата Е
        if(keyboard.getKeys()[6]) Inventory.getInstance().setIsOpened(!Inventory.getInstance().isOpened()); keyboard.getKeys()[6] = false; // Нажата I
        if(keyboard.getKeys()[7]) q();
    }

    private void interact()
    {
        Interactor.getInstance().interact();
    }

    private void moveRight()
    {
        player.moveRight();
    }
    private void moveLeft()
    {
        player.moveLeft();
    }
    private void jump()
    {
            //player.jump();

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
}
