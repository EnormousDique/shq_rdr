package ru.muwa.shq.engine.controls;

import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.player.Player;

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
    }

    private void interact()
    {
        Interactor.interact();
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
}
