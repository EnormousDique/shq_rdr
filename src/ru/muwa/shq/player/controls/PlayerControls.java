package ru.muwa.shq.player.controls;

import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.dialogues.DialogueManager;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.animations.*;
import ru.muwa.shq.engine.combat.CombatUtility;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.g.hud.MiniGameHUD;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.listeners.MouseButtonListener;
import ru.muwa.shq.engine.p.collisions.CollisionsChecker;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.engine.utilities.InventoryManager;
import ru.muwa.shq.items.guns.*;
import ru.muwa.shq.items.knifes.Kortique;
import ru.muwa.shq.minigames.padiklock.PadikLock;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.QuestHUD;
import ru.muwa.shq.zones.*;

import java.awt.*;
import java.util.Objects;

/**
 * Класс, отвечающий за управление игроком.
 */
public class PlayerControls
{
    private static KeyListener keyboard = KeyListener.getInstance();
    private static Player player = Player.get();

    public static void controlPlayer()
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
        if(keyboard.getKeys()[keyboard.J]) j();
        if(keyboard.getKeys()[keyboard.ENTER]) enter();
        if(keyboard.getKeys()[keyboard.T]) t(); // нважата T
        if(keyboard.getKeys()[keyboard.SHIFT]) shift();
        if(keyboard.getKeys()[keyboard.U]) u();
      //  if(keyboard.getKeys()[keyboard.V]) v();
        if(keyboard.getKeys()[keyboard.P]) p();



        //Мышь
        if(MouseButtonListener.getInstance().keys[0]) lmb();
        if(MouseButtonListener.getInstance().keys[0]) rmb();

    }

    private static void j() {
        QuestHUD.opened = !QuestHUD.opened;
        if(!QuestHUD.opened) QuestHUD.renderingQuest = null;
        keyboard.getKeys()[keyboard.J]=false;
    }

    //камера на кнопку V
    private static void v(){

    }
    private static void t() {
        for(GameZone z : Engine.getCurrentLevel().getZones())
        {
            if(z instanceof DialogueZone && z.contains(Player.get().getX(),Player.get().getY()))
            {
                HUD.getInstance().getDialogueWindow().setVisible(true);
                ((DialogueZone)z).setActive(true);
            }
            if(z instanceof TradeZone && z.contains(Player.get().getX(), Player.get().getY()))
            {
                ((TradeZone)z).isActive = true;
            }
            if(z instanceof BuyoutZone && z.contains(Player.get().getX(), Player.get().getY()))
            {
                ((BuyoutZone)z).isActive = true;
            }
        }
        keyboard.getKeys()[keyboard.T]=false;
    }
    // движение вверх с бегом и снятием стамины
    private static void w() {
        if(keyboard.getKeys()[keyboard.SHIFT] && Player.get().hunger>20){

            if(Player.get().getStamina() >10)
                Player.get().setSpeed(Player.get().getShiftSpeed());
            else
                Player.get().setSpeed(Player.get().getRegSpeed());

            Player.get().setStamina(Player.get().getStamina() - 1);
            Player.get().moveUp();
            if(keyboard.getKeys()[keyboard.A] || keyboard.getKeys()[keyboard.D]|| keyboard.getKeys()[keyboard.S] )
                Player.get().setStamina(Player.get().getStamina()+0.5);

        }else Player.get().moveUp();


    }// движение влево с бегом и снятием стамины


    private static void p(){
        Renderer.addMessage("Нужно добавить анимацию ссанья");
        Renderer.addMessage("Ссым.");
        Player.get().pee = 0;
        keyboard.getKeys()[keyboard.P]=false;
    }
    private static void u()
    {
        for(GameZone z : Engine.getCurrentLevel().getZones())
        {
            if(z instanceof SleepZone && Player.get().getSolidBox().intersects(z))
            {
                if(TimeMachine.getTimeOfTheDay().equals(TimeMachine.TimesOfTheDay.NIGHT)) {
                    TimeMachine.rewind((int)(100-Player.get().awake)*(4750));
                    Renderer.playSleepyFilter();
                    Renderer.addMessage("Отлично выспался");
                    Player.get().awake =100;
                }
                else{
                    String dayTimeString = "";
                    switch (TimeMachine.getTimeOfTheDay())
                    {
                        case SUNRISE:
                            dayTimeString = "рассвет";
                            break;
                        case MORNING:
                            dayTimeString = "утро";
                            break;
                        case AFTERNOON:
                            dayTimeString = "день";
                            break;
                        case EVENING:
                            dayTimeString = "вечер";
                            break;
                    }
                    Renderer.addMessage("Спать можно только ночью!");
                    Renderer.addMessage("Сейчас : " + dayTimeString);
                }
            }
            if(z instanceof InteractionZone && Player.get().getSolidBox().intersects(z))
            {

                    ((InteractionZone)z).use();

            }
        }
        keyboard.getKeys()[keyboard.U] = false;
    }


    private static void a() {
        if(keyboard.getKeys()[keyboard.SHIFT] && Player.get().hunger>20){

            if(Player.get().getStamina() >10)
                Player.get().setSpeed(Player.get().getShiftSpeed());
            else
                Player.get().setSpeed(Player.get().getRegSpeed());

            Player.get().setStamina(Player.get().getStamina() - 1);
            Player.get().moveLeft();
            if(keyboard.getKeys()[keyboard.W] || keyboard.getKeys()[keyboard.D]|| keyboard.getKeys()[keyboard.S] )
                Player.get().setStamina(Player.get().getStamina()+0.5);

        }else Player.get().moveLeft();

    }
    // движение вниз с бегом и снятием стамины
    private static void s()
    {
        if(keyboard.getKeys()[keyboard.SHIFT] && Player.get().hunger>20){

            if(Player.get().getStamina() >10)
                Player.get().setSpeed(Player.get().getShiftSpeed());
            else
                Player.get().setSpeed(Player.get().getRegSpeed());

            Player.get().setStamina(Player.get().getStamina() - 1);
            Player.get().moveDown();
            if(keyboard.getKeys()[keyboard.A] || keyboard.getKeys()[keyboard.D]|| keyboard.getKeys()[keyboard.W] )
                Player.get().setStamina(Player.get().getStamina()+0.5);

        }else Player.get().moveDown();


    }// движение вправо с бегом и снятием стамины
    private static void d()
    {
        if(keyboard.getKeys()[keyboard.SHIFT] && Player.get().hunger>20){

            if(Player.get().getStamina() >10)
                Player.get().setSpeed(Player.get().getShiftSpeed());
            else
                Player.get().setSpeed(Player.get().getRegSpeed());

            Player.get().setStamina(Player.get().getStamina() - 1);
            Player.get().moveRight();
            if(keyboard.getKeys()[keyboard.A] || keyboard.getKeys()[keyboard.W]|| keyboard.getKeys()[keyboard.S] )
                Player.get().setStamina(Player.get().getStamina()+0.5);

        }else Player.get().moveRight();

    }
    // действие на кнопку е
     private static void e() {

         try {
             Interactor.getInstance().interact();
         } catch (Exception e) {
             System.out.println("инетрактор наебнулся все ровно)");
         }

     }
     // Sounder.changeSong("src\\ru\\muwa\\shq\\sounds\\songs\\muzike1.wav");
    // открытие инвентаря на i
    private static void i()
    {
        //Inventory.getInstance().setIsOpened(!Inventory.getInstance().isOpened());
        InventoryManager.isItemWindowVisible = !InventoryManager.isItemWindowVisible;
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

            if(!Animator.isBusy()) {
                if(Player.get().currentWeapon instanceof Makarov) {
                    Animator.playPlayerAnimation(new A_MakarovShot());
                    ((Firearm) gun).shot();
                }
                if(Player.get().currentWeapon instanceof Obrez) {
                    Animator.playPlayerAnimation(new A_ObrezShot());
                    ((Firearm) gun).shot();
                }
            }
        }
        //Блок рукопашки
        if(!isFirearmEquipped) {

            if (!Animator.isBusy()) {// Проверяем не занят ли аниматор (не воспроизводится ли уже анимация удара.)

                //Наносим урон врагам, находящимся в зоне атаки

                Weapon w = Player.get().currentWeapon;

                for (int i = 0; i < Engine.getCurrentLevel().getNPC().size(); i++) {
                    NPC npc = Engine.getCurrentLevel().getNPC().get(i);
                    if (npc.getSolidBox().intersects(Player.get().getAttackZone())) {
                        CollisionsChecker.getInstance().checkAttackCollisions();
                        try {
                            int damage = Player.get().currentWeapon == null ? 5 : Player.get().currentWeapon.getDamage();
                            CombatUtility.attack(npc, damage);

                            //Поломка холодного оружия
                            if(w != null && ! (w instanceof Firearm))
                            {
                                w.setDurability(w.getDurability()-1);
                                if(w.getDurability() < 1) {
                                    Inventory.getInstance().getItems().remove(w);
                                    Player.get().currentWeapon  = null;
                                    Renderer.addMessage("Оружие сломалось!  " + w.toString().split("\\.")[5].split("@")[0]);
                                }
                            }

                        } catch (Exception e) {
                        }


                    }
                }



                //Блок анимации
                if(w instanceof Kortique) Animator.playPlayerAnimation(new A_KnifeStab());
                if(w == null) Animator.playPlayerAnimation(new A_PlayerFistPunch());
            }
        }
        keyboard.getKeys()[keyboard.SPACE] = false;
    }
    private static void enter() {

        for (GameZone z : Engine.getCurrentLevel().getZones()){

            if(z instanceof MiniGameZone && Player.get().getSolidBox().intersects(z))
            {
                MiniGameZone mz = (MiniGameZone) z;
                MiniGameHUD.currentMiniGame = mz.miniGame;
            }


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
    // выход из окон контейнеров диалогов инвентаря на q
    private static void q()
    {
        //Гасим все HUD-ы.
        InventoryManager.isItemWindowVisible = false;

        if(Player.get().isBusy() && Player.get().getCurrentObject() != null)
        {
            Objects.requireNonNull(Player.get()).getCurrentObject().setInUse(false);
            Player.get().setCurrentObject(null);
            Player.get().setIsBusy(false);
        }
        QuestHUD.opened=false;
        QuestHUD.renderingQuest = null;
        Inventory.getInstance().setIsOpened(false);
        HUD.getInstance().getContainerWindow().setVisible(false);
        for(Container c: Engine.getCurrentLevel().getContainers())c.setIsInUse(false);
        HUD.getInstance().getDialogueWindow().setVisible(false);



        if(Engine.pause)
        {
            System.out.println("MEEEEEEE");
            DialogueManager.dropDialogue();
        }

    }
    private static void lmb()
    {

       /* if(Inventory.getInstance().getBox().contains
                (new Point(MouseListener.getInstance().getX() + Camera.getInstance().getX(), MouseListener.getInstance().getY() + Camera.getInstance().getY())))

        else */
       // InventoryManager.grab(); // Проверка на щелчок по вещи из открытого контейнера
       // InventoryManager.getInstance().eat(); // Проверка на щелчок по вещи из открытого окна вещей игрока

        MouseButtonListener.getInstance().keys[0]=false;
    }
    private static void rmb()
    {

    }
    private static void shift() {
    }

    public static Player getPlayer() {
        return player;
    }
    public static void setPlayer(Player player) {
        PlayerControls.player = player;
    }
    public static void shiftRelease(){Player.get().setSpeed(Player.get().getRegSpeed());} // отпускание шифта возвращает обычную скорость игрока
}
