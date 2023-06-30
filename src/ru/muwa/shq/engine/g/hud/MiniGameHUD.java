package ru.muwa.shq.engine.g.hud;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.engine.time.TimeMachine;
import ru.muwa.shq.minigames.*;
import ru.muwa.shq.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


import static ru.muwa.shq.objects.GameObject.IMG_PATH;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Timer;

public class MiniGameHUD {

    static public int x = (int) (GameScreen.SCREEN_WIDTH - GameScreen.SCREEN_WIDTH / 1.2);
    static public int y = (int) (GameScreen.SCREEN_HEIGHT - GameScreen.SCREEN_HEIGHT / 1.4);
    static int width = GameScreen.SCREEN_WIDTH - 300;
    static int height = GameScreen.SCREEN_HEIGHT - 200;


    public static MiniGame currentMiniGame = null;
    public static void work()
    {
        Color oldColor = Renderer.g.getColor();
        if(currentMiniGame != null && currentMiniGame instanceof Domofon) drawDomofon();
        if(currentMiniGame != null && currentMiniGame instanceof PostBoxShq) drawPostBoxShq();
        if(currentMiniGame != null && currentMiniGame instanceof Elevator) drawElevator();
        if(currentMiniGame != null && currentMiniGame instanceof SleepMiniGame) drawSleepNSave();

        Renderer.g.setColor(oldColor);
    }
    private static void drawSleepNSave()
    {
        Engine.pause=true;
        SleepMiniGame mg = (SleepMiniGame) currentMiniGame;
        Renderer.g.setColor(Color.WHITE);
        Renderer.g.fillRect(x,y,width,height);

        Renderer.g.setColor(Color.BLACK);
        Renderer.g.drawString("Режим сна (желательно не нарушать)",x+10,y+10);

        long timeWhenToWakeUp = (long) ((100- Player.get().awake)*(4750)) + TimeMachine.getCurrentTime();
        String timeWhenToWakeUpStr = TimeMachine.convertLongToString(timeWhenToWakeUp);
        Renderer.g.drawString("Игрок будет спать до "+ timeWhenToWakeUpStr,x+10,y+40);
        //TODO: Сделать возможноть выбирать на сколько %% выспаться, и показывать соответствующее время подъёма.

        Renderer.g.fillRect(mg.save.x,mg.save.y,mg.save.width,mg.save.height);
        Renderer.g.fillRect(mg.sleep.x,mg.sleep.y,mg.sleep.width,mg.sleep.height);

        Renderer.g.setColor(Color.RED);
        Renderer.g.drawString(mg.save.text,mg.save.x,mg.save.y);
        Renderer.g.drawString(mg.sleep.text,mg.sleep.x,mg.sleep.y);

    }

    private static void drawDomofon() {
        Engine.pause=true;

        Renderer.g.setColor(Color.WHITE);
        Renderer.g.fillRect(x,y,width,height);
        Domofon d = (Domofon) currentMiniGame;
        Renderer.g.setColor(Color.BLACK);
        Renderer.g.drawString("BBOD : "+d.input,x+200,y+30);

        for (int i = 0; i < d.buttons.size(); i++) {
            Renderer.g.setColor(Color.BLACK);
            Renderer.g.fillRect(d.buttons.get(i).x+x,d.buttons.get(i).y+y,d.buttons.get(i).width,d.buttons.get(i).height);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString(d.buttons.get(i).text,d.buttons.get(i).x+10+x,d.buttons.get(i).y+10+y);

        }
    }
    private static void drawElevator()
    {
        Engine.pause=true;
        Renderer.g.setColor(Color.WHITE);
        Renderer.g.fillRect(x,y,width,height);
        Elevator e = (Elevator) currentMiniGame;
        Renderer.g.setColor(Color.BLACK);
        e.buttons=new ArrayList<>();
        for(int i =0; i< e.levels.size();i++)
        {
            e.buttons.add(new Elevator.ElevatorButton((i%2 * 50)+ MiniGameHUD.x,(i/2 * 40) + MiniGameHUD.y,(i+1)+""));
        }

        for (int i = 0; i < e.buttons.size(); i++) {
            Renderer.g.setColor(Color.BLACK);
            Renderer.g.fillRect(e.buttons.get(i).x,e.buttons.get(i).y,e.buttons.get(i).width,e.buttons.get(i).height);
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.drawString(e.buttons.get(i).text,e.buttons.get(i).x+10,e.buttons.get(i).y+10);
        }
    }
    public static boolean initPostbox =false;

    private static void drawPostBoxShq()
    {
        Engine.pause=true;
        Image minigameBlack = null;
        try {
            minigameBlack = ImageIO.read(new File(IMG_PATH+"miniGames\\WindowKlad\\WindowKladYellow\\Жёлтая 1.png"));
        } catch (IOException e) {
            System.out.println("немогу загрузить картинку миниигры с щакладкой чераня 1");
        }
         Renderer.g.drawImage(minigameBlack,(int) (GameScreen.SCREEN_WIDTH/3.06), (int) (GameScreen.SCREEN_HEIGHT/3.8),GameScreen.SCREEN_WIDTH/3,GameScreen.SCREEN_HEIGHT/2,null);



        PostBoxShq shq = (PostBoxShq) currentMiniGame;
        if(!shq.init) {
            shq.stuff = new ArrayList<>();
            for (int i = 0; i < shq.container.getItems().size(); i++) {
                shq.stuff.add(new Rectangle(i * 25+x+300, i * 25+y+200, 5, 10));
            }
            shq.init = true;
        }

        Renderer.g.setColor(Color.RED);

        for (int i = 0; i < shq.stuff.size(); i++) {
            Renderer.g.fillRect(shq.stuff.get(i).x,shq.stuff.get(i).y,shq.stuff.get(i).width,shq.stuff.get(i).height);
        }

    }
}
