package ru.muwa.shq.engine.listeners;

import ru.muwa.shq.dialogues.DialogueManager;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.Picktogram;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.hud.MiniGameHUD;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.engine.utilities.DeathUtility;
import ru.muwa.shq.engine.utilities.InventoryManager;
import ru.muwa.shq.minigames.Domofon;
import ru.muwa.shq.minigames.Elevator;
import ru.muwa.shq.minigames.Lift;
import ru.muwa.shq.minigames.SleepMiniGame;
import ru.muwa.shq.minigames.shquring.ShquringMinigame;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.quests.QuestHUD;
import ru.muwa.shq.zones.BuyoutZone;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Класс, отвечающий за прослушку кнопок мыши.
 */
public class MouseButtonListener implements MouseInputListener {

    public MouseEvent highlight;
    private static MouseButtonListener instance;

    private MouseButtonListener(){instance=this; keys = new boolean[2];} // Массив кнопок.
    // Каждой кнопке соответствует свой индекс массива.
    // Если кнопка нажата, в массиве под этим индексом хранится true
    public static MouseButtonListener getInstance()
    {
        if(instance!=null)return instance;
        else return new MouseButtonListener();
    }
    public boolean[] keys;
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {


        System.out.println("mouse clicked. x: " + e.getX()/*+ Camera.getInstance().getX())*/ + " y : " + e.getY()/*+Camera.getInstance().getY()*/);
        switch (e.getButton()) {
            case 1:
                keys[0] = true;
                break;
            case 3:
                keys[1] = true;
                break;
        }
        Picktogram pic = null;
        //ЭКРАН СМЕРТИ
        Rectangle resurrect=new Rectangle(200,310,200,200);
        if(DeathUtility.isDead && resurrect.contains(new Point(e.getX(),e.getY()))){DeathUtility.resurrect();}
        //ЭКРАН СМЕРТИ
        //=======================================================================================================

        //=======================================================================================================
        //Проверяем по инвентарю ли клик.
                 // И то что нажата ЛКМ
        //И что окно инвентаря открыто.
        for(int i = 0 ; i < InventoryManager.itemWindowPicks.size(); i++){
            if(InventoryManager.itemWindowPicks.get(i).contains(new Point(e.getX(),e.getY())))
                pic = InventoryManager.itemWindowPicks.get(i);
        }

        if(pic!=null && keys[0] && InventoryManager.isItemWindowVisible)
        {
            //Если клик был по инвентарю.
            BuyoutZone z = null;
            for(int i =0; i< Engine.getCurrentLevel().getZones().size();i++)
            {
                if(Engine.getCurrentLevel().getZones().get(i) instanceof BuyoutZone)
                {
                    if(((BuyoutZone)Engine.getCurrentLevel().getZones().get(i)).isActive)
                    {
                        z = (BuyoutZone)Engine.getCurrentLevel().getZones().get(i);
                    }
                }
            }
            if(pic!=null && z==null) {
                System.out.println("Нажали по " + pic.item);
                pic.item.pick();
            }
            if(pic!=null&&  z!=null)
            {

                if(pic.item.amount<=1 || !pic.item.isStackable()) {
                    Inventory.getInstance().getItems().remove(pic.item);
                    z.buyout.goods.add(pic.item);
                }
                else {
                    pic.item.amount-=1;
                    z.buyout.goods.add(pic.item.copy());
                }
            }
        }
        //=======================================================================================================

        //=======================================================================================================
        //Проверяем по окну ли контейнера клик.
        // И то что нажата ЛКМ
        //И что окно контейнер открыт.
        pic=null;
        for(int  i = 0 ; i < InventoryManager.containerWindowPicks.size();i++)
        {
            if(InventoryManager.containerWindowPicks.get(i).contains(new Point(e.getX(),e.getY())))
            {
                pic = InventoryManager.containerWindowPicks.get(i);
                System.out.println("icon x :" + pic.x + " y : "+ pic.y);
            }
        }

        if(pic!=null && keys[0])
        {
            System.out.println("click po ikonke containera");
            pic.item.get();
        }
        //=======================================================================================================

        //=======================================================================================================
        //Проверяем по окну ли еквипа клик.
        // И то что нажата ЛКМ
        //И что есть еквипнутая вещь.
        if(InventoryManager.equipPic!=null && InventoryManager.equipPic.contains(new Point(e.getX(),e.getY()))
                && keys[0])
        {
            InventoryManager.equipPic.item.pick();
        }
        //=======================================================================================================

        //=======================================================================================================
        //Проверяем по диалогу ли клик.
        // И то что нажата ЛКМ
        //И что окно диалога открыто.
        DialogueManager.ResponseButton r =  null;
        for(int i = 0; i < DialogueManager.buttons.size(); i++){
            if(DialogueManager.buttons.get(i).contains(new Point(e.getX(),e.getY()))
            && DialogueManager.isBusy())
                r = DialogueManager.buttons.get(i);
        }
        if(r!=null)
        {
            if(r.respond.getMsg()!=null) {
                DialogueManager.getCurrentDialogue().setCurrentMessage(r.respond.getMsg());
                if(r.respond.getAction()!=null) r.respond.getAction().performAction();
            }

            if(r.respond.getMsg()==null && r.respond.getAction()!=null) {
                r.respond.getAction().performAction();
                DialogueManager.dropDialogue();
            }
            if(r.respond.getMsg()==null && r.respond.getAction() == null){
                DialogueManager.dropDialogue();
            }

        }
        //=======================================================================================================

        //=======================================================================================================
        //Проверяем по журнали ли клик.
        // И то что нажата ЛКМ
        //И что окно журнала открыто.
        QuestHUD.QuestPic qp = null;
        QuestHUD.BossPic bp = null;
        for(int i = 0; i < QuestHUD.pics.size(); i++){
            if( QuestHUD.opened && QuestHUD.renderingQuestsList!=null && QuestHUD.pics.get(i).contains(new Point(e.getX(),e.getY()))
                    )
                qp = QuestHUD.pics.get(i);
        }
        for(int i = 0; i < QuestHUD.bosses.size(); i++){
            if( QuestHUD.opened && QuestHUD.bosses.get(i).contains(new Point(e.getX(),e.getY()))
            )
                bp = QuestHUD.bosses.get(i);
        }
        if(qp!=null)
        {
            //Нажали по конкретной квестовой панели и она нашлась
            QuestHUD.renderingQuest = qp.quest;
            QuestHUD.renderingQuestsList=null;
            //Просим рендерить квест из панельки, по которой нажали
        }
        if(bp!=null)
        {
            QuestHUD.renderingQuestsList=bp.questList;
        }
        //=======================================================================================================

        //=======================================================================================================
        //Логика домофона
        // проверяем что сейчас играем в домофон
        //И какая кнопка была нажата

        if(MiniGameHUD.currentMiniGame instanceof Domofon) {

            for (int i = 0; i < ((Domofon) MiniGameHUD.currentMiniGame).buttons.size(); i++) {
                Domofon.DomofonButton b = ((Domofon) MiniGameHUD.currentMiniGame).buttons.get(i);

                if (b.contains(new Point(e.getX()-MiniGameHUD.x, e.getY()-MiniGameHUD.y))) {

                    Domofon game = (Domofon) MiniGameHUD.currentMiniGame;

                    if(b.text.equals("Х")){ MiniGameHUD.currentMiniGame = null;Engine.pause=false;game.input="";}
                    else if(b.text.equals("В")){ Renderer.addMessage("Никто не отвечает....");game.input="";}
                    else {
                        game.input+=b.text;
                        if(game.input.equals(game.key))
                        {
                            MiniGameHUD.currentMiniGame=null;
                            Engine.switchLevel(game.level, game.whereX, game.whereY);
                            Engine.pause = false;
                            game.input="";
                        }
                    }
                }
            }
        }
        //========================================================================================================

        //===========================================================================================================
        //Если мы играем в лифт, то логика нажатия мышки следующая
        if(MiniGameHUD.currentMiniGame instanceof Elevator)
        {
            for (int i = 0; i < ((Elevator) MiniGameHUD.currentMiniGame).buttons.size(); i++) {
                Elevator.ElevatorButton b = ((Elevator) MiniGameHUD.currentMiniGame).buttons.get(i);
                if(b.contains(new Point(e.getX(),e.getY())))
                {
                    Elevator l = (Elevator) MiniGameHUD.currentMiniGame;
                    Engine.pause=false;
                    Engine.switchLevel(l.levels.get(Integer.parseInt(b.text)-1),l.distX,l.distY);
                    MiniGameHUD.currentMiniGame = null;
                }
            }
        }
        //========================================================================================================

        //===========================================================================================================
        //Логика сна и сохранения
        // проверяем что сейчас играем в домофон
        //И какая кнопка была нажата

        if(MiniGameHUD.currentMiniGame instanceof SleepMiniGame) {

            SleepMiniGame.SleepMGButton saveB = ((SleepMiniGame) MiniGameHUD.currentMiniGame).save;
            SleepMiniGame.SleepMGButton sleepB = ((SleepMiniGame) MiniGameHUD.currentMiniGame).sleep;


                if (saveB.contains(new Point(e.getX()-MiniGameHUD.x, e.getY()-MiniGameHUD.y))) {
                    System.out.println("Пытаюсь сохранить игру");
                    //Engine.saveGame();
                    System.out.println("если все пошло так, должна быть сохранена.");
                    Renderer.addMessage("Игра сохранена!");
                }
            if (sleepB.contains(new Point(e.getX(), e.getY()))) {
                TimeMachine.rewind((int)(100- Player.get().awake)*(4750));
                Renderer.addMessage("Отлично выспался");
                Player.get().awake =100;
                MiniGameHUD.currentMiniGame=null;
                Engine.pause=false;
            }
            }
        //=================================================================
        //ИГРАЕМ В МИНИИГРУ ШКУРИНГ
        //===============================================================
        if(MiniGameHUD.currentMiniGame instanceof ShquringMinigame)
        {
            playPostBoxShq(e.getX(),e.getY());
        }
        //=================================================================
        //ИГРАЕМ В МИНИИГРУ LIFT ЛИФТ
        //===============================================================
        if(MiniGameHUD.currentMiniGame instanceof Lift)
        {
            playLift(e.getX(),e.getY());
        }
        //=================================================================
        //Скролл СООБЩЕНИЙ РЕДНЕРЕРЕРА
        //===============================================================
        if(Renderer.getInstance().scrollDown.contains(new Point(e.getX(),e.getY()))){
            if(Renderer.getInstance().msgScroll>0) Renderer.getInstance().msgScroll-=1;
        }
        if(Renderer.getInstance().scrollUp.contains(new Point(e.getX(),e.getY()))){
            if(Renderer.getInstance().msgScroll<Renderer.getMessages().size()-10) Renderer.getInstance().msgScroll+=1;
        }

        //===============================================================
        //============================================================

        //После вызова необходимого кода отключаем нажатие во избежание "прокликивания".
        switch (e.getButton())
        {
            case 1:
                keys[0] = false;

                break;
            case 3:
                keys[1] = false;
                break;
        }
    }

    private void playLift(int x, int y) {
        Lift.LiftButton lb = null;
        Lift l = (Lift) MiniGameHUD.currentMiniGame;
        for(Lift.LiftButton b : ((Lift)MiniGameHUD.currentMiniGame).buttons)
        {
            if(b.contains(x,y)) lb = b;
        }
        if(lb!=null)
        {
            int setX = l.floorCoords.get(Integer.parseInt( lb.text)-1).x;
            int setY = l.floorCoords.get(Integer.parseInt( lb.text)-1).y;

            Player.get().setY(setY);
            Player.get().setX(setX);

            MiniGameHUD.currentMiniGame=null;
            Engine.pause=false;

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        switch (e.getButton())
        {
            case 1:
                keys[0] = false;

                break;
            case 3:
                keys[1] = false;
                break;
        }
        // System.out.println("mouse released " + e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    public boolean[] getKeys(){return keys;}
    private static void playPostBoxShq(int x, int y)
    {
        ShquringMinigame shq = ((ShquringMinigame)MiniGameHUD.currentMiniGame);
        for (int i = 0; i < shq.stuff.size(); i++) {
            Rectangle r = shq.stuff.get(i);
            if(r.contains(new Point(x, y)))
            {
                Inventory.getInstance().addItem(shq.container.getItems().get(shq.stuff.indexOf(r)));
                shq.container.getItems().remove(shq.container.getItems().get(shq.stuff.indexOf(r)));
                shq.stuff.remove(r);
            }

        }
        if(shq.stuff.size()<1||shq.container.getItems().size()<1)
        {
            MiniGameHUD.initPostbox=false;
        }
    }

}
