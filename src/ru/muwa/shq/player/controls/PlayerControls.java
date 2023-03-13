package ru.muwa.shq.player.controls;

import org.w3c.dom.ls.LSOutput;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.combat.CombatUtility;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.engine.utilities.InventoryManager;
import ru.muwa.shq.items.guns.Bullet;
import ru.muwa.shq.levels.dev.DevLevel0;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.GameZone;

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
        //проверка на открытие инвентаря
        Player.get().setIsBusy(Inventory.getInstance().isOpened());
        // Клавиатура
        if(keyboard.getKeys()[keyboard.W]) w(); // Нажата W
        if(keyboard.getKeys()[keyboard.A]) a();// Нажата A
        if(keyboard.getKeys()[keyboard.S]) s();// Нажата S
        if(keyboard.getKeys()[keyboard.D]) d();// Нажата D
        if(keyboard.getKeys()[keyboard.SPACE]) space();// Нажата SPACE BAR
        if(keyboard.getKeys()[keyboard.E]) e(); // Нажата Е
        if(keyboard.getKeys()[keyboard.I]) i();/* */// Нажата I
        if(keyboard.getKeys()[keyboard.Q]) q();
        if(keyboard.getKeys()[keyboard.ENTER]) enter();




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
        Player.get().moveDown();
    }
    private void d()
    {
        Player.get().moveRight();
    }
    private void e()
    {
        Interactor.getInstance().interact();
    }
    private void i()
    {
        Inventory.getInstance().setIsOpened(!Inventory.getInstance().isOpened());
        keyboard.getKeys()[6] = false;
    }
    private void space()
    {
        for(NPC npc : Engine.getCurrentLevel().getNPC())
        {
            if (npc.getSolidBox().intersects(Player.get().getAttackZone()))
            {
                //TODO: здесь должа передаваться атака текущего оружия героя как аргумент. Пока так.
                System.out.printf("hp " + npc.getHp());
                CombatUtility.getInstance().attack(npc, 5);
                System.out.printf("hp " + npc.getHp());
            }
        }
        Engine.getCurrentLevel().getObjects().add( new Bullet((int)Player.get().getAttackZone().getCenterX(), (int)Player.get().getAttackZone().getCenterY(), -1*(Aim.getInstance().calculateAngle() - 90)));
        keyboard.getKeys()[keyboard.SPACE] = false;
    }
    private void enter()
    {

        for(GameZone z : Engine.getCurrentLevel().getZones())
            if(z.contains(new Point(Player.get().getX(),Player.get().getY()))
                    &&
                    KeyListener.getInstance().getKeys()[KeyListener.getInstance().ENTER]
                    &&
                    z instanceof EnterZone)
                Engine.switchLevel((EnterZone) z);
        KeyListener.getInstance().getKeys()[KeyListener.getInstance().ENTER] = false;

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
