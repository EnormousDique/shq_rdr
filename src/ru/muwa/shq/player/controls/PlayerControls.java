package ru.muwa.shq.player.controls;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.combat.CombatUtility;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.s.Sounder;
import ru.muwa.shq.engine.utilities.InventoryManager;
import ru.muwa.shq.items.guns.Bullet;
import ru.muwa.shq.items.guns.Firearm;
import ru.muwa.shq.items.guns.Weapon;
import ru.muwa.shq.minigames.padiklock.PadikLock;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.player.aiming.Aim;
import ru.muwa.shq.zones.DialogueZone;
import ru.muwa.shq.zones.EnterZone;
import ru.muwa.shq.zones.GameZone;
import ru.muwa.shq.zones.InteractiveEnterZone;

import java.awt.*;

/**
 * Класс, отвечающий за управление игроком.
 */
public class PlayerControls
{
    private static KeyListener keyboard = KeyListener.getInstance();
    private static Player player = Player.get();

    public static   void controlPlayer()
    {
        boolean p = Engine.pause;
        //проверка на открытие инвентаря
        Player.get().setIsBusy(Inventory.getInstance().isOpened());
        // Клавиатура
        if(keyboard.getKeys()[keyboard.W] ) w(); // Нажата W
        if(keyboard.getKeys()[keyboard.A]) a();// Нажата A
        if(keyboard.getKeys()[keyboard.S]) s();// Нажата S
        if(keyboard.getKeys()[keyboard.D]) d();// Нажата D
        if(keyboard.getKeys()[keyboard.SPACE]) space();// Нажата SPACE BAR
        if(keyboard.getKeys()[keyboard.E]) e(); // Нажата Е
        if(keyboard.getKeys()[keyboard.I]) i();/* */// Нажата I
        if(keyboard.getKeys()[keyboard.Q]) q();
        if(keyboard.getKeys()[keyboard.ENTER]) enter();
        if(keyboard.getKeys()[keyboard.T]) t(); // нважата T




        //Мышь
        if(MouseButtonListener.getInstance().keys[0]) lmb();
        if(MouseButtonListener.getInstance().keys[0]) rmb();

    }

    private static void t() {
        for(GameZone z : Engine.getCurrentLevel().getZones())
        {
            if(z instanceof DialogueZone && z.contains(Player.get().getX(),Player.get().getY()))
            {
                HUD.getInstance().getDialogueWindow().setVisible(true);
                ((DialogueZone)z).setActive(true);
            }
        }
    }
    private static void w() {
        Player.get().moveUp();
    }
    private static void a()
    {
        Player.get().moveLeft();
    }
    private static void s()
    {
        Player.get().moveDown();
    }
    private static void d()
    {
        Player.get().moveRight();
    }
     private static void e()
    {
    Interactor.getInstance().interact();
     // Sounder.changeSong("src\\ru\\muwa\\shq\\sounds\\songs\\muzike1.wav");
    }
    private static void i()
    {
        Inventory.getInstance().setIsOpened(!Inventory.getInstance().isOpened());
        keyboard.getKeys()[6] = false;
    }
    private static void space()
    {
        //Определяем есть ли оружие в руках (огнестрельное)
        boolean isFirearmEquipped = false;
        Weapon gun = null;

        for(int i = 0; i < Inventory.getInstance().getItems().size(); i++)
        {
            if(Inventory.getInstance().getItems().get(i).isEquipped() &&
               Inventory.getInstance().getItems().get(i) instanceof Firearm)
            {
                isFirearmEquipped = true;
                gun = (Weapon) Inventory.getInstance().getItems().get(i);
            }
        }


        //Блок стрельбы
        if(isFirearmEquipped && gun.getCurrAmmo()>0) {
            ((Firearm)gun).shot();
            double angle = -(Aim.getInstance().calculateAngle() - 90);

            Engine.getCurrentLevel().getObjects().add(
                    new Bullet((int)
                            Player.get().getAttackZone().getCenterX(),
                            (int) Player.get().getAttackZone().getCenterY(),
                            angle));

            Sounder.playSFX("src\\ru\\muwa\\shq\\sounds\\sfx\\vistrel05.wav");
        }


        //Блок рукопашки
        if(!isFirearmEquipped) {

            for (int i = 0; i < Engine.getCurrentLevel().getNPC().size(); i++) {
                NPC npc = Engine.getCurrentLevel().getNPC().get(i);
                if (npc.getSolidBox().intersects(Player.get().getAttackZone())) {
                    //TODO: здесь должа передаваться атака текущего оружия героя как аргумент. Пока так.

                    CombatUtility.attack(npc, 5);

                }
            }

        }

        keyboard.getKeys()[keyboard.SPACE] = false;

    }
    private static void enter() {

        for (GameZone z : Engine.getCurrentLevel().getZones()){
            if (z.contains(new Point(Player.get().getX(), Player.get().getY()))
                    &&
                    KeyListener.getInstance().getKeys()[KeyListener.getInstance().ENTER]
                    &&
                    z instanceof EnterZone)
                Engine.switchLevel((EnterZone) z);
            if(z.contains(new Point(Player.get().getX(), Player.get().getY()))
                    &&
                    z instanceof InteractiveEnterZone)
            {
                ((InteractiveEnterZone ) z).getGame().game();
                while (!((InteractiveEnterZone ) z).getGame().victory())
                {
                    System.out.println("мы находимся в миниигре падик лок");
                    // System.out.println("мы находимся в миниигре падик лок");
                    if(KeyListener.getInstance().getKeys()[KeyListener.getInstance().Q])
                        break;
                    if(((InteractiveEnterZone ) z).getGame().victory()
                    || ((PadikLock) ((InteractiveEnterZone ) z).getGame()).isForceQuit())
                    {
                       break;
                    }
                }
                if( ! ((PadikLock)((InteractiveEnterZone)z).getGame() ).isForceQuit() ) {
                    Engine.pause = false;
                    Engine.switchLevel(((InteractiveEnterZone) z).enterZone);
                    HUD.getInstance().getActionWindow().setVisible(false);
                }
            }

    }

        KeyListener.getInstance().getKeys()[KeyListener.getInstance().ENTER] = false;


    }

    private static void q()
    {
        if(Player.get().isBusy() && Player.get().getCurrentObject() != null)
        {
            Player.get().getCurrentObject().setInUse(false);
            Player.get().setCurrentObject(null);
            Player.get().setIsBusy(false);
        }
        Inventory.getInstance().setIsOpened(false);
        for(Container c: Engine.getCurrentLevel().getContainers())c.setIsInUse(false);
        HUD.getInstance().getDialogueWindow().setVisible(false);
    }
    private static void lmb()
    {

       /* if(Inventory.getInstance().getBox().contains
                (new Point(MouseListener.getInstance().getX() + Camera.getInstance().getX(), MouseListener.getInstance().getY() + Camera.getInstance().getY())))

        else */
        InventoryManager.grab(); // Проверка на щелчок по вещи из открытого контейнера
       // InventoryManager.getInstance().eat(); // Проверка на щелчок по вещи из открытого окна вещей игрока

        MouseButtonListener.getInstance().keys[0]=false;
    }
    private static void rmb()
    {

    }
}
