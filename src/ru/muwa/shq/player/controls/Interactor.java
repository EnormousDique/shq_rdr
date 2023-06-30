package ru.muwa.shq.player.controls;


import ru.muwa.shq.dialogues.Dialogue;
import ru.muwa.shq.dialogues.DialogueManager;
import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.g.camera.Camera;
import ru.muwa.shq.engine.g.hud.HUD;
import ru.muwa.shq.engine.g.hud.MiniGameHUD;
import ru.muwa.shq.engine.listeners.KeyListener;
import ru.muwa.shq.engine.listeners.MouseListener;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.engine.utilities.InventoryManager;
import ru.muwa.shq.minigames.PostBoxShq;
import ru.muwa.shq.minigames.padiklock.PadikLock;
import ru.muwa.shq.objects.containers.Container;
import ru.muwa.shq.creatures.npc.NPC;
import ru.muwa.shq.player.Inventory;
import ru.muwa.shq.player.Player;
import ru.muwa.shq.zones.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * Класс, отвечающий за взаимодейстиве игрока с usable и container объектами
 */
public class Interactor
{

    private static Interactor instance;
    private Interactor(){instance = this;}
    public static Interactor getInstance(){if(instance!=null) return instance; else return new Interactor();}

    private static int x,y;

    public void interact() throws Exception
    {
        x = MouseListener.getInstance().getX() +Camera.getInstance().getX();
        y = MouseListener.getInstance().getY()+Camera.getInstance().getY();
        //обработка нажатий на контейнер
        interactWithContainers();
        //======================================================
        //обработка нажатий по НПЦ
        interactWithNPC();
        //======================================================
        //обработка нажатий по зонам
        interactWithZones();

    }
    private static void interactWithContainers()
    {
        ArrayList<Container> activeContainers = new ArrayList<>();

        for(Container c : Engine.getCurrentLevel().getContainers()) {

            if ( c.getSolidBox().inside(x, y)
                    && Player.get().getUseZone().contains(x, y ))
            {
                activeContainers.add(c);
            }
        }
        if(activeContainers.size() >0) {
            int i = 0;
            for(i = 0; i < activeContainers.size(); i++)
            {
                if(activeContainers.get(i).getItems().size()>0)
                    break;
            }
            Container c = activeContainers.get( activeContainers.size() > 1? i : 0);  //todo выбрасывает ошибку идекса out of bounds при лутании трупов.
            if(!c.shqurable) {
                c.setIsInUse(true);
                Player.get().setIsBusy(true);
            }else {
                MiniGameHUD.currentMiniGame = c.miniGame;  //new PostBoxShq(c);
            }
        }
        if(false){}//TODO: добавить возможность открыть несколько окон шмона или переключаться между ними, если activeContainers.size() > 1
    }
    private static void interactWithNPC()
    {
        for(NPC n : Engine.getCurrentLevel().getNPC()) {

            if (n.getSolidBox().contains(x , y)){
                if(n.dialogue != null){
                    DialogueManager.playDialogueOnDemand(n.dialogue);

                }else if (!n.isEnemy){
                    Renderer.addMessage("неочем говорить");
                }
            }
        }
    }
    private static void interactWithZones()
    {
        for (GameZone z : Engine.getCurrentLevel().getZones()){

            if(z instanceof MiniGameZone && Player.get().getUseZone().intersects(z) && z.contains(x,y))
            {
                MiniGameZone mz = (MiniGameZone) z;
                MiniGameHUD.currentMiniGame = mz.miniGame;
                try {
                    Robot robot = new Robot();
                    int xoff = GameScreen.getInstance().getX();
                    int yoff = GameScreen.getInstance().getY();
                    robot.mouseMove(xoff+MiniGameHUD.x, yoff+MiniGameHUD.y);
                }catch (Exception e){Renderer.addMessage("ты не должен это видеть");}
            }


            if (Player.get().getUseZone().intersects(z) && z.contains(x,y)
                    &&
                    z instanceof EnterZone){
                Engine.switchLevel((EnterZone) z);
            }

            if(z instanceof SleepZone && Player.get().getUseZone().intersects(z) && z.contains(x,y))
            {
                if(Player.get().awake<0.5){
                    TimeMachine.rewind(800_000);
                    Renderer.playSleepyFilter();
                    Renderer.addMessage("Ну ты и соня..");
                    Player.get().awake =100;
                    continue;
                }
                if(Player.get().awake > 75){Renderer.addMessage("Совсем не хочу спать.");continue;}
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
            if(z instanceof InteractionZone && Player.get().getUseZone().intersects(z) && z.contains(x,y))
            {

                ((InteractionZone)z).use();

            }
            if(z instanceof DialogueZone && Player.get().getUseZone().intersects(z) && z.contains(x,y))
            {
                HUD.getInstance().getDialogueWindow().setVisible(true);
                ((DialogueZone)z).setActive(true);
            }
            if(z instanceof TradeZone && Player.get().getUseZone().intersects(z) && z.contains(x,y))
            {
                InventoryManager.isItemWindowVisible=true;
                ((TradeZone)z).isActive = true;
            }
            if(z instanceof BuyoutZone && Player.get().getUseZone().intersects(z) && z.contains(x,y))
            {
                InventoryManager.isItemWindowVisible=true;
                ((BuyoutZone)z).isActive = true;
            }


        }
    }

}