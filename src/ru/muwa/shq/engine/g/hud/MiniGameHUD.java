package ru.muwa.shq.engine.g.hud;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.minigames.Domofon;
import ru.muwa.shq.minigames.Elevator;
import ru.muwa.shq.minigames.MiniGame;
import ru.muwa.shq.minigames.PostBoxShq;

import java.awt.*;
import java.util.ArrayList;

public class MiniGameHUD {

static public        int x = (int) (GameScreen.SCREEN_WIDTH - GameScreen.SCREEN_WIDTH / 1.2);
static public         int y = (int) (GameScreen.SCREEN_HEIGHT - GameScreen.SCREEN_HEIGHT / 1.4);
static         int width = GameScreen.SCREEN_WIDTH-300;
static         int height = GameScreen.SCREEN_HEIGHT -200;

    public static MiniGame currentMiniGame = null;
    public static void work()
    {
        Color oldColor = Renderer.g.getColor();
        if(currentMiniGame != null && currentMiniGame instanceof Domofon) drawDomofon();
        if(currentMiniGame != null && currentMiniGame instanceof PostBoxShq) drawPostBoxShq();
        if(currentMiniGame != null && currentMiniGame instanceof Elevator) drawElevator();

        Renderer.g.setColor(oldColor);
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
    private static void drawPostBoxShq()
    {
        Engine.pause=true;

        Renderer.g.setColor(Color.WHITE);
        Renderer.g.fillRect(x,y,width,height);

        PostBoxShq shq = (PostBoxShq) currentMiniGame;

        Renderer.g.setColor(Color.RED);
        for (int i = 0; i < shq.obstacles.size(); i++) {
            Renderer.g.fillRect(shq.obstacles.get(i).x,shq.obstacles.get(i).y,shq.obstacles.get(i).width,shq.obstacles.get(i).height);

        }
        Renderer.g.setColor(Color.YELLOW);
        Renderer.g.fillRect(shq.destination.x,shq.destination.y,shq.destination.width,shq.destination.height);

    }
}
