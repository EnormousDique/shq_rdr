package ru.muwa.shq.engine.g.hud;

import ru.muwa.shq.engine.Engine;
import ru.muwa.shq.engine.g.GameScreen;
import ru.muwa.shq.engine.g.Renderer;
import ru.muwa.shq.minigames.Domofon;
import ru.muwa.shq.minigames.MiniGame;

import java.awt.*;

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
}
