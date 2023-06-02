package ru.muwa.shq.quests;

import ru.muwa.shq.engine.g.Renderer;

import java.awt.*;

import static ru.muwa.shq.engine.g.GameScreen.SCREEN_HEIGHT;
import static ru.muwa.shq.engine.g.GameScreen.SCREEN_WIDTH;

public class QuestHUD {
    public static boolean opened;
    static int x = (int)(SCREEN_WIDTH - SCREEN_WIDTH * 0.75);
    static int y = (int)(SCREEN_HEIGHT - SCREEN_HEIGHT * 0.75);
    static int width = SCREEN_WIDTH / 2;
    static int height = SCREEN_HEIGHT / 2;
    public static void drawJournal()
    {

            Color oldColor = Renderer.g.getColor();
            Renderer.g.setColor(Color.WHITE);
            Renderer.g.fillRect(x,y,width,height);

            Renderer.g.setColor(oldColor);

    }
}
