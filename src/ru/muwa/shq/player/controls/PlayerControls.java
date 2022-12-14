package ru.muwa.shq.player.controls;

import ru.muwa.shq.engine.listeners.KeyListener;
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
        if(keyboard.getKeys()[0]) ;// Нажата W
        if(keyboard.getKeys()[1]) moveLeft();// Нажата A
        if(keyboard.getKeys()[2]) ;// Нажата S
        if(keyboard.getKeys()[3]) moveRight();// Нажата D
        if(keyboard.getKeys()[4]) jump() ;// Нажата SPACE BAR
        if(keyboard.getKeys()[5]) interact(); // Нажата Е
        Inventory.getInstance().setIsOpened(keyboard.getKeys()[6]); // Нажата I
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
            player.jump();
    }
    private void q()
    {
        if(Player.get().isBusy() && Player.get().getCurrentObject() != null)
        {
            Player.get().getCurrentObject().setInUse(false);
            Player.get().setCurrentObject(null);
            Player.get().setIsBusy(false);
        }
    }
}
